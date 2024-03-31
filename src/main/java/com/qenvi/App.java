/*
 * Educative Java Code Snippets by Artem MELNYK is marked with CC0 1.0
 */


package com.qenvi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) {
        // Start TCP server
        startTCPServer();
        
        // Main application logic (if any)
        System.out.println("Application started.");
        // Add any other application logic here
        
        // Keep the main thread running
        while (true) {
            try {
                Thread.sleep(1000); // Sleep to keep the main thread alive
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void startTCPServer() {
        int portNumber = 12345; // Change this to your desired port number
        
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("TCP server listening on port " + portNumber + "...");
            
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     OutputStream out = clientSocket.getOutputStream();
                     InputStream in = clientSocket.getInputStream()) {
                    
                    System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                    
                    // Read incoming message
                    byte[] buffer = new byte[1024];
                    int bytesRead = in.read(buffer);
                    if (bytesRead != -1) {
                        String receivedMessage = new String(buffer, 0, bytesRead);
                        System.out.println("Received message: " + receivedMessage);
                        
                        // Prepare response
                        byte[] response = "Response from server".getBytes();
                        
                        // Send response
                        out.write(response);
                        out.flush();
                        System.out.println("Response sent");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
