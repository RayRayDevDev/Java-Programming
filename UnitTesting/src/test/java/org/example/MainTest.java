package org.example;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Main mainExample = new Main();
    @org.junit.jupiter.api.Test
    void add() {
        assertEquals(50, mainExample.add(25,25));
        //assertEquals(25, mainExample.add(25, 25));
    }

    @org.junit.jupiter.api.Test
    void divide() {
        assertEquals(5, mainExample.divide(20,4));
        //assertEquals(25, mainExample.divide(250, 50));
    }
}