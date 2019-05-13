package com.example.vocabularyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NapravActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naprav);

        ListView listOpr = findViewById(R.id.listOpr);

        Intent intent = getIntent();
        setTitle(intent.getStringExtra("naprav"));
        String s = intent.getStringExtra("jArr");

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray array = jsonObject.getJSONArray(jsonObject.keys().next());

            String[]  textArray = new String[array.length()];
            for (int i = 0; i < array.length(); ++i) {
                String temporaryStr = array.getString(i);
                temporaryStr = temporaryStr.substring(1, temporaryStr.length() - 1);
                String[] temporaryArray = temporaryStr.split("[,\"]");
                textArray[i] = temporaryArray[1];
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_list_item_1, textArray
            );
            listOpr.setAdapter(adapter);

            // TODO Сделай кликабельно
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
