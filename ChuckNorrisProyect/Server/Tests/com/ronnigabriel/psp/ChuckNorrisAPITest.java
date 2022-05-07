package com.ronnigabriel.psp;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ChuckNorrisAPITest {

    @org.junit.jupiter.api.Test
    void random() throws IOException {
        assertNotNull(new ChuckNorrisAPI().random());

    }

    @org.junit.jupiter.api.Test
    void jokeFor() throws IOException {
        assertNotNull(new ChuckNorrisAPI().jokeFor("horse"));
    }
}