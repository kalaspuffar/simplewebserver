package org.ea.socketserver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while(true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), false);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                SocketHeaders headers = new SocketHeaders(in);
                SocketRequestHandler requestHandler = new SocketRequestHandler();
                requestHandler.handle(headers, in, out);

                out.flush();
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
