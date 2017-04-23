package com.example.sabrinama.chineseflashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.sabrinama.chineseflashcards.StartActivity.dictionary;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        int test = intent.getIntExtra("test",-1);

        TextView textview = (TextView) findViewById(R.id.textView);

        textview.setText(Integer.toString(test));

    }
}
