package com.ronnigabriel.psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class PeerConnection extends Thread implements Observer {

    private final Socket clientSocket;
    private final PrintWriter socketOut;
    private final BufferedReader socketIn;
    private final ChuckNorrisMonitor chuckNorrisMonitor;

    public PeerConnection(Socket clientSocket, ChuckNorrisMonitor chuckNorrisMonitor) throws IOException {
        this.clientSocket = clientSocket;
        this.chuckNorrisMonitor = chuckNorrisMonitor;

        socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
        socketIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = socketIn.readLine()) != null) {
               //  channel.notifyObservers(line);
            }
        } catch (IOException e) {
            System.out.printf("%s disconnected%n", clientSocket);
        } finally {
           // channel.deleteObserver(this);
            try {
                socketIn.close();
                socketOut.close();
                clientSocket.close();
            } catch (IOException e) {

            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        socketOut.println(arg.toString());
    }
}