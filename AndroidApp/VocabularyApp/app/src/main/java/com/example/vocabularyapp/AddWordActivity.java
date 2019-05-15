package com.example.vocabularyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AddWordActivity extends AppCompatActivity {

    TextView textAddWord, textDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        textAddWord = findViewById(R.id.textAddWord);
        textDescription = findViewById(R.id.textDescription);
    }

    public void okButton(View v) {
        textAddWord.setText("");
        textDescription.setText("");

        Toast toast = Toast.makeText(
                getApplicationContext(), "Слово отправлено на модерацию!",
                Toast.LENGTH_SHORT
        );
        toast.show();
    }
}
