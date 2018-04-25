package org.ea;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;


public class SimpleServer {
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(SERVER_PORT), 0);
            server.createContext("/", new RequestHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
            System.out.println("Server started at " + SERVER_PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}