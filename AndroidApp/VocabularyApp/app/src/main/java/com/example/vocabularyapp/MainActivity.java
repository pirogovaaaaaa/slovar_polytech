package com.example.vocabularyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
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
        menu.add(0, 1, 0, "О нас");
        menu.add(0, 2, 0, "Что нового");
        menu.add(0, 3, 0, "О приложении");
        menu.add(0, 4, 0, "Добавить слово");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent intentAboutUs = new Intent(this, AboutUsActivity.class);
                startActivity(intentAboutUs);
                return true;
            case 2:
                // TODO Что нового
                return true;
            case 3:
                // TODO О приложении
                return true;
            case 4:
                Intent intentAddWord = new Intent(this, AddWordActivity.class);
                startActivity(intentAddWord);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
