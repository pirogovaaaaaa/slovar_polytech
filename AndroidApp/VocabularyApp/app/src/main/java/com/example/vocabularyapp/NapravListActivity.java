package com.example.vocabularyapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NapravListActivity extends AppCompatActivity implements FindWordInterface, FindOprInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naprav_list);

        ListView listNaprav = findViewById(R.id.listNaprav);

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
            listNaprav.setAdapter(adapter);

            // TODO Сделай кликабельно
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listNaprav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FindWordApi findWordApi = new FindWordApi();
                findWordApi.delegate = NapravListActivity.this;
                findWordApi.execute(
                        "http://116.203.41.4:5000/api/v1.0/term/" + ((TextView) view).getText()
                );

            }
        });
    }

    @Override
    public void wordFind(String output) {
        try {
            JSONObject jsonObject = new JSONObject(output);
            JSONArray jsonArray = jsonObject.getJSONArray(jsonObject.keys().next());

            String temporaryStr = jsonArray.getString(0);
            temporaryStr = temporaryStr.substring(temporaryStr.indexOf(",") + 2,
                    temporaryStr.length() - 2);

            AlertDialog.Builder builder = new AlertDialog.Builder(NapravListActivity.this);
            builder.setTitle("Определение");
            builder.setMessage(temporaryStr);
            builder.setCancelable(false);
            builder.setNegativeButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }
            );

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processFinish(String output) {
        try {
            JSONObject jsonObject = new JSONObject(output);
            JSONArray jsonArray = jsonObject.getJSONArray(jsonObject.keys().next());

            String temporaryStr = jsonArray.getString(0);
            temporaryStr = temporaryStr.substring(1, temporaryStr.length() - 1);
            String[] temporaryArray = temporaryStr.split("[,\"]");

            FindOprOfWord findOprOfWord = new FindOprOfWord();
            findOprOfWord.delegate = this;
            findOprOfWord.execute(
                    "http://116.203.41.4:5000/api/v1.0/opreds/" + temporaryArray[3]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
