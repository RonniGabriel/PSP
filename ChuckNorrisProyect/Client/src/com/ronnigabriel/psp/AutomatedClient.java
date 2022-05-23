package com.ronnigabriel.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class AutomatedClient  extends Thread{



    public AutomatedClient (){
        super();
    }

    @Override
    public void run() {
        super.run();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int portNumber = Integer.parseInt(args[0]);
        loop(portNumber,InetAddress.getByName(args[1]));
        int maxClients = Integer.parseInt(args[2]);
        String autoLine = args[4];


    }

    public static String loop(int portNumber, InetAddress host) throws IOException, InterruptedException {

        try (
                Socket socket = new Socket(host, portNumber);
                BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true))
        {
            while (true) {
                // TODO PASAR RANDOM POR PARAMETROS

                Thread.sleep((long) (Math.random()* 1000 ));
                socketOut.println();
                System.out.println(socketIn.readLine());
            }

        }

    }
}
