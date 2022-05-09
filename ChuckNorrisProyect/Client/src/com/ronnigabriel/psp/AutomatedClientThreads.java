package com.ronnigabriel.psp;

import com.sun.org.apache.xpath.internal.objects.XNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class AutomatedClientThreads extends Thread {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: java Client <port number>");
            System.exit(1);
        }
        int portNumber = 0;
        try {
            portNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("<port number> must be an integer value");
            System.exit(1);
        }

        if (portNumber < com.ronnigabriel.psp.Server.MIN_PORT_NUMBER || portNumber > com.ronnigabriel.psp.Server.MAX_PORT_NUMBER) {
            System.err.printf("<port number> must be an integer value between %d and %d%n", com.ronnigabriel.psp.Server.MIN_PORT_NUMBER, com.ronnigabriel.psp.Server.MAX_PORT_NUMBER);
            System.exit(1);
        }
        InetAddress localhost = InetAddress.getLocalHost();
        Socket socket = new Socket(localhost, portNumber);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try (Socket socket = new Socket(localhost, portNumber);
                     BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
                ) {
                    String line;
                    AutomatedClient.loop();
                    while ((line = stdIn.readLine()) != null) {
                        socketOut.println(line);
                        System.out.println(socketIn.readLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try (Socket socket = new Socket(localhost, portNumber);
                     BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
                ) {
                    String line;
                    AutomatedClient.loop();
                    while ((line = stdIn.readLine()) != null) {
                        socketOut.println(line);
                        System.out.println(socketIn.readLine());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });


    }


}
