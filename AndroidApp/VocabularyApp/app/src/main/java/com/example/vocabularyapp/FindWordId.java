package com.example.vocabularyapp;

import android.os.AsyncTask;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FindWordId extends AsyncTask<String, Void, String> {

    FindWordInterface delegate = null;

    @Override
    protected String doInBackground(String... strings) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();

        Request.Builder builder = new Request.Builder();
        builder.url(strings[0]);
        Request request = builder.build();

        try {
            Response response = client.newCall(request).execute();
            return response.body() != null ? response.body().string() : null;
        } catch (Exception e) {
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
