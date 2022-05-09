package com.ronnigabriel.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AutomatedClient extends Client {

    static int maxClients = 5;
    static int time = 1;

    public AutomatedClient() {
        super();
    }

    public static String loop(String[] args) throws IOException {

        String localhost = args[1];
        int portNumber = Integer.parseInt(args[0]);
        for (int i = 0; i < maxClients; i++) {
            Socket socket = new Socket(localhost, portNumber);
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            {
                String line;
                while ((line = stdIn.readLine()) != null) {
                    socketOut.println(line);
                    System.out.println(socketIn.readLine());
                }
            }

        }    return ChuckNorrisAPI.random();

    }
}
