package com.ronnigabriel.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class AutomatedClientMainProcesses {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: Java Client <port number> <client process> <max time>");
            System.exit(1);
        }

        InetAddress address = null;
        try {
            address = InetAddress.getByName(args[0]);
        } catch (UnknownHostException ex) {
            System.err.printf("Usage: <ip address> %s invalid%n", args[0]);
            System.exit(1);
        }

        if (!address.isReachable(10)) {
            System.err.printf("Usage: can't reach <ip address> %s%n", args[0]);
            System.exit(1);
        }

        int portNumber = 0;
        try {
            portNumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("<port number> must be an integer value");
            System.exit(1);
        }

        if (portNumber < com.ronnigabriel.psp.Server.MIN_PORT_NUMBER || portNumber > com.ronnigabriel.psp.Server.MAX_PORT_NUMBER) {
            System.err.printf("<port number> must be an integer value between %d and %d%n", com.ronnigabriel.psp.Server.MIN_PORT_NUMBER, com.ronnigabriel.psp.Server.MAX_PORT_NUMBER);
            System.exit(1);
        }

        Socket socket = new Socket(address, portNumber);

        Thread keyboardThread = new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader stdIn = null;
                PrintWriter socketOut = null;
                try {
                    stdIn = new BufferedReader(new InputStreamReader(System.in));
                    socketOut = new PrintWriter(socket.getOutputStream(), true);
                    String line;
                    while ((line = stdIn.readLine()) != null) {
                        socketOut.println(line);
                    }
                } catch (IOException e) {

                } finally {
                    try {
                        stdIn.close();
                        socketOut.close();
                    } catch (IOException e) {
                    }
                }
            }
        });

        Thread networkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader socketIn = null;
                try {
                    socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line;
                    while ((line = socketIn.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socketIn.close();
                    } catch (IOException e) {
                    }
                }
            }
        });

        keyboardThread.start();
        networkThread.start();
        try {
            keyboardThread.join();
            networkThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }



    }

}

