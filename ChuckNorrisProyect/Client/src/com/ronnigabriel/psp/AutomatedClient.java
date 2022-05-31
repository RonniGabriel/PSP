package com.ronnigabriel.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class AutomatedClient  {


    public static void main(String[] args) throws IOException, InterruptedException {

        int portNumber = Integer.parseInt(args[0]);
        int maxRandom= Integer.parseInt(args[2]);
        InetAddress address =InetAddress.getByName(args[1]);
        String id = args[3];
        loop(portNumber, address,maxRandom,id);
    }

    public static String loop(int portNumber, InetAddress host , int maxRandom, String id ) throws IOException, InterruptedException {

        try (
                Socket socket = new Socket(host, portNumber);
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true))
        {
            while (true) {
                Thread.sleep((long) (Math.random()* maxRandom ));
                socketOut.println("random");
                System.out.println(String.format("Client %s: %s",id, socketIn.readLine()));
            }

        }

    }
}
