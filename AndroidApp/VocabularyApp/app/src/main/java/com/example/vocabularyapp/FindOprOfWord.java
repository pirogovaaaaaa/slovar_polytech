package com.example.vocabularyapp;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FindOprOfWord extends AsyncTask<String, Void, String> {

    private OkHttpClient client = new OkHttpClient();
    FindOprInterface delegate = null;

    @Override
    protected String doInBackground(String... strings) {
        Request.Builder builder = new Request.Builder();
        builder.url(strings[0]);
        Request request = builder.build();

        try {
            Response response = client.newCall(request).execute();
            return response.body() != null ? response.body().string() : null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        delegate.wordFind(s);
    }
}
