package com.example.vocabularyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NapravActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState, String naprav, String[][] napravList) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naprav);

        setTitle(naprav);
    }
}
