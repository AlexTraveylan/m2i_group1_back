package com.jeniti.back.uuid;

import java.util.Random;

public class Uuidperso {

    static private char randomChar() {
        Random rand = new Random();
        return (char)(rand.nextInt(26) + 97);
    }

    static private char randomNumber() {
        return ((((int) (Math.random()*10))) +"").toCharArray()[0];
    }

    static public String randomUUIDperso() {

        String uuid ="";
        for (int i = 0; i<73; i++) {
            if (Math.random() < 0.5) {
                uuid += randomNumber();
            } else {
                uuid += randomChar();
            }
        }

        return uuid;

    }
}
