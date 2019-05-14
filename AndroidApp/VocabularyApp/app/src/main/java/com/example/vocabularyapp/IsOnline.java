package com.example.vocabularyapp;

public class IsOnline {
    public static Boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            return returnVal == 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
