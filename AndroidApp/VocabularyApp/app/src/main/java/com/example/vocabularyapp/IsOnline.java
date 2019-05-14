package com.example.vocabularyapp;

class IsOnline {
    static Boolean isOnline() {
        try {
            Process process = java.lang.Runtime.getRuntime().exec(
                    "ping -c 1 www.google.com");
            int returnVal = process.waitFor();
            return returnVal == 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
