package com.ronnigabriel.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        // InetAddress localhost = InetAddress.getLocalHost();

        ProcessBuilder processBuilder = new ProcessBuilder("user.dir", "-cp", "out", AutomatedClient.class.toString(), AutomatedClient.loop());

        Process process = processBuilder.start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String processResult = bufferedReader.readLine();
        System.out.println(String.format("Proceso 1 : %s",processResult));

    }
}


