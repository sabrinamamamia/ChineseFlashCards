package com.example.sabrinama.chineseflashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        String message= intent.getStringExtra("radioChosen");
        TextView textview = (TextView) findViewById(R.id.textView);
        textview.setText(message);
    }
}
