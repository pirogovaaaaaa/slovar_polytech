package com.example.vocabularyapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO Добавь ввод
        // TODO Добавь поиск
        // TODO Сделай надписи активными
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
}
