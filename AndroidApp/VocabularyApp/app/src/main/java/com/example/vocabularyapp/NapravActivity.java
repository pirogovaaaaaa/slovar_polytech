package com.example.vocabularyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONArray;

public class NapravActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState, String naprav, JSONArray napravList) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naprav);

        setTitle(naprav);
    }
}
