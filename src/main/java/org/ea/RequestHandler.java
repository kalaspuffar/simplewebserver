package org.ea;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.ea.controllers.notes.NotesCollection;
import org.ea.controllers.notes.NotesItem;
import org.ea.repositories.NotesRepository;
import sun.net.httpserver.MyHttpExchangeImpl;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler implements HttpHandler {

    private Map<String, Endpoint> endpointMap = new HashMap<String, Endpoint>();

    public RequestHandler() {
        endpointMap.put("/notes", new NotesCollection(NotesRepository.getInstance()));
        endpointMap.put("/notes/{id}", new NotesItem(NotesRepository.getInstance()));
    }

    private String getRequestBody(HttpExchange t) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(t.getRequestBody()));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        br.close();
        return sb.toString();
    }

    private void setResponseBody(HttpExchange t, String s, int responseCode) throws IOException {
        t.getResponseHeaders().add("Access-Control-Allow-Credentials", "true");
        t.getResponseHeaders().add("Access-Control-Allow-Origin", "http://localhost:8081");
        t.getResponseHeaders().add("Connection", "keep-alive");
        t.getResponseHeaders().add("Content-Type", "application/json");
        t.getResponseHeaders().add("Date", new Date().toString());
        t.getResponseHeaders().add("Vary", "Origin");
        t.sendResponseHeaders(responseCode, s.length());
        OutputStream os = t.getResponseBody();
        os.write(s.getBytes());
        os.close();
    }

    private void handleOPTION(HttpExchange t) throws IOException {
        System.out.println("OPTIONS");
        t.getResponseHeaders().add("Connection", "keep-alive");
        t.getResponseHeaders().add("Access-Control-Allow-Credentials", "true");
        t.getResponseHeaders().add("Access-Control-Allow-Origin", "http://localhost:8081");
        t.getResponseHeaders().add("Access-Control-Allow-Headers", "content-type");
        t.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,PUT,POST,PATCH,DELETE");
        t.getResponseHeaders().add("Date", new Date().toString());
        t.getResponseHeaders().add("Vary", "Origin, Access-Control-Request-Headers");
        t.sendResponseHeaders(204, 0);
    }

    public Endpoint getEndpoint(String s) {
        return endpointMap.get(s);
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        MyHttpExchangeImpl mt = new MyHttpExchangeImpl(t);

        if(mt.getRequestMethod().equals("OPTIONS")) {
            this.handleOPTION(mt);
        }
        Endpoint ep = this.getEndpoint(mt.getRequestURI().getPath());
        String resp = ep.handleRequest(mt.getRequestMethod(), this.getRequestBody(mt));
        this.setResponseBody(mt, resp, ep.getResponseCode());
    }
}
