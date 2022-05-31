package com.ronnigabriel.psp;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

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

        int maxRandom= Integer.parseInt(args[3]);
        InetAddress  address = InetAddress.getByName(args[1]);
        int numClients = Integer.parseInt(args[2]);
        for (int i = 0; i < numClients; i++) {
            int thePortNumber = portNumber;
            //TODO: guarda estos threads en un array
            /*List<Thread> clients = new ArrayList<Thread>();
            Thread client = new Thread();
*/
            int finalI = i; // LANDA
            Thread client = new Thread(() -> {
                try {
                    AutomatedClient.loop(thePortNumber, address, maxRandom,String.valueOf(finalI));
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            });
            client.start();

    }


}}