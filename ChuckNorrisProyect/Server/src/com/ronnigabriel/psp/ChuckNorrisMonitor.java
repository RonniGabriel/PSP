package com.ronnigabriel.psp;

public class ChuckNorrisMonitor {

    // Numero de clientes maximos

    private  final  int _maxCustomers;  // Los clientes maximos , tienen que ser concurrentes
    private int _customers = 0; // Recursos

    public ChuckNorrisMonitor( int maxCustomers) {
        _maxCustomers = maxCustomers;
    }

    public synchronized ChuckNorrisAPI getDownloader  () throws InterruptedException {
        while (_customers == _maxCustomers ){
            System.out.printf("There is not enough resources, Thread %s  waiting ");
            wait();
        }
        _customers ++;
        System.out.print("Thread %s downloading");
        return new ChuckNorrisAPI();
    }

    public synchronized  void endUp (){
        _customers --;
        System.out.print("Thread %s end up, There are enough resources");
        notifyAll(); // Se notifica que sigan los procesos.
    }

}
