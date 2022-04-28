package com.ronnigabriel.psp;

public class ChuckNorrisMonitor {

    // Numero de clientes maximos

    private static final  int _customers = 5;  // Los clientes maximos , tienen que ser concurrentes
    private int _resources = 0; // Recursos

    public synchronized void download  (ChuckNorrisAPI chuckNorrisAPI) throws InterruptedException {
        while (_resources <= _customers ){
            System.out.printf("There is not enough resources, Thread %s  waiting ");
            wait();
        }
        _resources --;
        System.out.printf("Thread %s downloading");
        // Instancia de ChuckNorrisApi

        notifyAll();
    }

    public synchronized  void endUp (ChuckNorrisAPI chuckNorrisAPI){
        _resources ++;
        System.out.printf("Thread %s end up, There are enough resources");
        notifyAll();
    }




}
