package com.ronnigabriel.psp;

import java.util.Observable;

public class Channel extends Observable {
    @Override
    public void notifyObservers(Object arg) {
        setChanged();
        super.notifyObservers(arg);
    }
}
