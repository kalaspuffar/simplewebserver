package org.ea.socketserver;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class SocketHeaders {
    private Map<String, String> headers = new HashMap<String, String>();
    private String method;
    private String path;

    public SocketHeaders(BufferedReader in) throws Exception {
        String inputLine;
        method = null;
        while ((inputLine = in.readLine()) != null) {
            if(inputLine.equals("")) break;
            if(method == null) {
                method = inputLine.split(" ")[0];
                path = inputLine.split(" ")[1];
                continue;
            }
            int firstColon = inputLine.indexOf(":");
            String key = inputLine.substring(0, firstColon);
            String val = inputLine.substring(firstColon+2);
            headers.put(key, val);
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHeader(String key) {
        return this.headers.get(key);
    }

    public int getContentLength() {
        if(!this.headers.containsKey("Content-Length")) return 0;
        String len = this.headers.get("Content-Length");
        return Integer.parseInt(len);
    }
}
