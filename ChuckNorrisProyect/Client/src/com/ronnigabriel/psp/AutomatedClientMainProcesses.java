package com.ronnigabriel.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.file.Paths;

public class AutomatedClientMainProcesses {

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

        String  host = (args[1]);
        int numClients = Integer.parseInt(args[2]);
       int  maxRandom = Integer.parseInt(args[3]) ;
        String classPath =Paths.get( System.getProperty("user.dir") , "out").toString();
        for (int i = 0; i < numClients; i++) {

            ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", classPath, AutomatedClient.class.getName(), String.valueOf(portNumber),host.toString(), String.valueOf(maxRandom), String.valueOf(i)).inheritIO();
            processBuilder.start();
        }
    }


}


