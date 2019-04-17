package com.example.vocabulary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddWordActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_word);

        buttonExit = findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(this);

        // TODO Применить настрой
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonExit:
                super.onBackPressed();
                break;
            default:
                break;
        }
    }
}
