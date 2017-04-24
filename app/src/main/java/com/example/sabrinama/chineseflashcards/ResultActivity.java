package com.example.sabrinama.chineseflashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import static com.example.sabrinama.chineseflashcards.StartActivity.dictionary;

public class ResultActivity extends AppCompatActivity {

    public static boolean restart = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        double elapsedTime = intent.getDoubleExtra(GameActivity.TIME,-1);
        Integer totalQuestion = intent.getIntExtra(GameActivity.NUM_QUESTION, -1);
        Integer correctNum = intent.getIntExtra(GameActivity.CORRECT, -1);

        int second = (int) (elapsedTime/1000);
        int min = second/60;
        second = second % 60;
        String timeString = String.format("%02d:%02d",min,second);

        int totalLearnedWords = 0;
        for (int i = 0; i < dictionary.size(); i++)
        {
            totalLearnedWords += dictionary.get(i).correctWord;
        }


        TextView score = (TextView) findViewById(R.id.score_textView);
        score.setText(Integer.toString(correctNum) + "/" + Integer.toString(totalQuestion));

        TextView time = (TextView) findViewById(R.id.time_textView);
        time.setText(timeString);

        TextView totalLearned = (TextView) findViewById(R.id.totalWord_textView);
        totalLearned.setText(Integer.toString(totalLearnedWords));

    }


    public void onButtonClicked(View view)
    {
        restart = true;
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

}
