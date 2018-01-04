package org.ea.socketserver;

import org.ea.Endpoint;
import org.ea.HttpResponseMessages;
import org.ea.controllers.notes.NotesCollection;
import org.ea.controllers.notes.NotesItem;
import org.ea.repositories.NotesRepository;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocketRequestHandler {
    private static final int BUFFER_SIZE = 65535;
    private Map<String, Endpoint> endpointMap = new HashMap<String, Endpoint>();

    public SocketRequestHandler() {
        endpointMap.put("/notes", new NotesCollection(NotesRepository.getInstance()));
        endpointMap.put("/notes/{id}", new NotesItem(NotesRepository.getInstance()));
    }

    private String getRequestBody(BufferedReader in, int len) throws IOException {
        if(len == 0) return "";
        StringBuilder sb = new StringBuilder();
        char[] buff = new char[BUFFER_SIZE];
        int read = BUFFER_SIZE;
        while(read > BUFFER_SIZE - 1) {
            read = in.read(buff, 0, BUFFER_SIZE);
            sb.append(Arrays.copyOfRange(buff, 0, read));
        }
        return sb.toString();
    }

    private void setResponseBody(PrintWriter out, String s, int responseCode) {
        out.println("HTTP/1.1 " + responseCode + " " + HttpResponseMessages.getResponse(responseCode));
        out.println("Connection: keep-alive");
        out.println("Access-Control-Allow-Credentials: true");
        out.println("Access-Control-Allow-Origin: http://localhost:8081");
        out.println("Content-Type: application/json");
        out.println("Date: " + (new Date()).toString());
        out.println("Vary: Origin");
        out.println("Content-Length: " + s.length());
        out.println();
        out.print(s);
    }

    private void handleOPTION(PrintWriter out) {
        out.println("HTTP/1.1 204 " + HttpResponseMessages.getResponse(204));
        out.println("Connection: keep-alive");
        out.println("Access-Control-Allow-Credentials: true");
        out.println("Access-Control-Allow-Origin: http://localhost:8081");
        out.println("Access-Control-Allow-Headers: content-type");
        out.println("Access-Control-Allow-Methods: GET,PUT,POST,PATCH,DELETE");
        out.println("Date: " + (new Date()).toString());
        out.println("Vary: Origin, Access-Control-Request-Headers");
    }

    private Endpoint getEndpoint(String s) {
        Pattern keyPattern = Pattern.compile("\\{([a-zA-Z]+)\\}");
        Endpoint ep = null;
        for(String path : endpointMap.keySet()) {
            List<String> keys = new ArrayList<>();
            String testPath = path;
            Matcher m = keyPattern.matcher(path);
            while(m.find()) {
                String key = m.group();
                keys.add(m.group().substring(1, key.length() - 1));
                testPath = testPath.replace(key, "([^\\/]+)");
            }

            Pattern testPattern = Pattern.compile("^" + testPath + "$");
            if(s == null) return null;
            Matcher stringMatcher = testPattern.matcher(s);
            if(!stringMatcher.matches() || stringMatcher.groupCount() != keys.size()) continue;
            ep = endpointMap.get(path);

            for(int i=0; i<keys.size(); i++) {
                String value = stringMatcher.group(i+1);
                ep.addPathParam(keys.get(i), value);
            }
            return ep;
        }
        return ep;
    }

    public void handle(SocketHeaders headers, BufferedReader in, PrintWriter out) throws IOException {
        if("OPTIONS".equals(headers.getMethod())) {
            this.handleOPTION(out);
            return;
        }

        Endpoint ep = this.getEndpoint(headers.getPath());
        if(ep == null) {
            System.out.println(headers.getMethod());
            System.out.println(headers.getPath());
            return;
        }
        String resp = ep.handleRequest(headers.getMethod(), this.getRequestBody(in, headers.getContentLength()));
        this.setResponseBody(out, resp, ep.getResponseCode());
    }
}