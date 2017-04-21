package com.example.sabrinama.chineseflashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();

        String message = intent.getStringExtra(StartActivity.RADIO_CHOSEN);
        TextView textview = (TextView) findViewById(R.id.textView);
        textview.setText(message);

        String message2 = intent.getStringExtra(StartActivity.QUESTION_NUM);
        TextView textview2 = (TextView) findViewById(R.id.textView2);
        textview2.setText(message2);

    }

}
