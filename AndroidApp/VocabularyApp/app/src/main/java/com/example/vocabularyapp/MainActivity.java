package com.example.vocabularyapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    TextView textOpis, textWordSearch;


    private class GetNaprav extends AsyncTask<String, Void, String> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String...params) {
            Request.Builder builder = new Request.Builder();
            builder.url(params[0]);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();
                return params[1] + " " +
                        (response.body() != null ? response.body().string() : null);
            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String naprav = s.substring(0, s.indexOf(" "));
            s = s.substring(s.indexOf("{"));

            Intent intentNaprav = new Intent(
                    MainActivity.this, NapravActivity.class
            );
            intentNaprav.putExtra("naprav", naprav);
            intentNaprav.putExtra("jArr", s);
            startActivity(intentNaprav);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textOpis = findViewById(R.id.textOpis);
        textWordSearch = findViewById(R.id.textWordSearch);
    }

    @Override
    public void processFinish(String output) {
        try {
            JSONObject jsonObject = new JSONObject(output);
            JSONArray jsonArray = jsonObject.getJSONArray(jsonObject.keys().next());

            String temporaryStr = jsonArray.getString(0);
            temporaryStr = temporaryStr.substring(1, temporaryStr.length() - 1);
            String[] temporaryArray = temporaryStr.split("[,\"]");
            // TODO НАпиши поиск по индексу, везде ехо херачить будешь
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_us:
                Intent intentAboutUs = new Intent(this, AboutUsActivity.class);
                startActivity(intentAboutUs);
                return true;
            case R.id.about_app:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("О приложении");
                builder.setMessage(R.string.aboutAppText);
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

                return true;
            case R.id.add_word:
                Intent intentAddWord = new Intent(this, AddWordActivity.class);
                startActivity(intentAddWord);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View v) {
        if (!IsOnline.isOnline()) {
            Toast toast = Toast.makeText(
                    getApplicationContext(), "Нет подключения к интернету!",
                    Toast.LENGTH_SHORT
            );
            toast.show();

            return;
        }
        GetNaprav getNaprav = new GetNaprav();
        switch (v.getId()) {
            case R.id.textLink1:
                getNaprav.execute(
                        "http://116.203.41.4:5000/api/v1.0/terms/10.05.03", "10.05.03"
                );
                break;
            case R.id.textLink2:
                getNaprav.execute(
                        "http://116.203.41.4:5000/api/v1.0/terms/10.03.01", "10.03.01"
                );
                break;
            case R.id.textLink3:
                getNaprav.execute(
                        "http://116.203.41.4:5000/api/v1.0/terms/09.03.01.01", "09.03.01.01"
                );
                break;
            case R.id.textLink4:
                getNaprav.execute(
                        "http://116.203.41.4:5000/api/v1.0/terms/09.03.01.02", "09.03.01.02"
                );
                break;
            case R.id.textLink5:
                getNaprav.execute(
                        "http://116.203.41.4:5000/api/v1.0/terms/09.03.03", "09.03.03"
                );
                break;
            case R.id.butSearch:
                FindWordApi findWordApi = new FindWordApi();
                findWordApi.delegate = this;
                findWordApi.execute(
                        "http://116.203.41.4:5000/api/v1.0/term/" + textWordSearch.getText()
                );
                break;
        }
    }
}
