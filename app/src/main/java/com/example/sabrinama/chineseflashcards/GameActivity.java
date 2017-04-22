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
        Integer radioIdx = intent.getIntExtra(StartActivity.RADIO_CHOSEN, -1);  //-1 is default val
        Integer questionNum = intent.getIntExtra(StartActivity.QUESTION_NUM,-1);

        if (radioIdx == 0) {

        }
        else if (radioIdx == 1) {

        }
        else if (radioIdx == 2) {

        }

    }

}
