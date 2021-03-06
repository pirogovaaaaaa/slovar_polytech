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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NapravListActivity extends AppCompatActivity implements FindOprInterface {

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
            JSONArray jsonArray = jsonObject.getJSONArray(jsonObject.keys().next());

            String[]  textArray = new String[jsonArray.length()];
            final String[] wordsId = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); ++i) {
                String temporaryStr = jsonArray.getString(i);
                temporaryStr = temporaryStr.substring(1, temporaryStr.length() - 1);
                String[] temporaryArray = temporaryStr.split(",");
                textArray[i] = temporaryArray[0].substring(1, temporaryArray[0].length() - 1);
                wordsId[i] = temporaryArray[1];
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_list_item_1, textArray
            );

            listNaprav.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (!IsOnline.isOnline()) {
                        Toast toast = Toast.makeText(
                                getApplicationContext(), "Нет подключения к интернету!",
                                Toast.LENGTH_SHORT
                        );
                        toast.show();

                        return;
                    }
                    FindOprOfWord findOprOfWord = new FindOprOfWord();
                    findOprOfWord.delegate = NapravListActivity.this;
                    findOprOfWord.execute(
                            "http://116.203.41.4:5000/api/v1.0/opreds/" + wordsId[position]);

                }
            });
            listNaprav.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void oprFind(String output) {
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
}
