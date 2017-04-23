package com.example.sabrinama.chineseflashcards;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import static com.example.sabrinama.chineseflashcards.StartActivity.dictionary;

public class GameActivity extends AppCompatActivity {

    int correctOption;

    public int getCorrectOption() {return correctOption;};
    public void setCorrectOption(int n) {correctOption = n;};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        Integer radioIdx = intent.getIntExtra(StartActivity.RADIO_CHOSEN, -1);  //-1 is default val
        Integer questionNum = intent.getIntExtra(StartActivity.QUESTION_NUM,-1);

        createScreen(radioIdx,questionNum);
    }

    public int createScreen(int radioIdx, int questionNum) {
        Random rand = new Random();
        int randButton = rand.nextInt(3) + 0;
        int  randWord = rand.nextInt(dictionary.size()-1);
        setCorrectOption(randButton);

        TextView textview = (TextView) findViewById(R.id.word);
        TextView button0 = (TextView) findViewById(R.id.button0);
        TextView button1 = (TextView) findViewById(R.id.button1);
        TextView button2 = (TextView) findViewById(R.id.button2);
        TextView button3 = (TextView) findViewById(R.id.button3);

        ArrayList<TextView> buttonList = new ArrayList<>();
        buttonList.add(button0);
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);

        //English
        if (radioIdx == 0) {
            textview.setText(dictionary.get(randWord).getEnglishWord().word);
            createOptionVal(buttonList, "english", randWord, randButton);
        }

        //Pinyin
        else if (radioIdx == 1) {
            textview.setText(dictionary.get(randWord).getPinyinWord().word);
            createOptionVal(buttonList, "pinyin", randWord, randButton);
        }

        //Chinese
        else if (radioIdx == 2) {
            textview.setText(dictionary.get(randWord).getChineseWord().word);
            createOptionVal(buttonList, "chinese", randWord, randButton);
        }

        return randWord;
    }

    public void createOptionVal(ArrayList<TextView>list, String type,
                                int correctWord, int correctButton) {

        String option = " ";
        for (int i = 0; i < list.size(); i++) {
            int a;
            if (i == correctButton) {
                a = correctWord;
            }
            else {
                Random rand = new Random();
                int randWord = rand.nextInt(dictionary.size() - 1) + 0;
                a = randWord;
            }

            if (type.equals("english")) {
                option = dictionary.get(a).getChineseWord().word + "\n\n" +
                        dictionary.get(a).getPinyinWord().word;
            }
            else if (type.equals("chinese")) {
                option = dictionary.get(a).getEnglishWord().word + "\n\n" +
                        dictionary.get(a).getPinyinWord().word;
            }
            else if (type.equals("pinyin")) {
                option = dictionary.get(a).getEnglishWord().word + "\n\n" +
                        dictionary.get(a).getChineseWord().word;
            }
            list.get(i).setText(option);
        }
    }

    public void onOptionClicked(View view) {

        int buttonId = -1;

        if (view.getId() == findViewById(R.id.button0).getId()) { buttonId = 0; }
        if (view.getId() == findViewById(R.id.button1).getId()) { buttonId = 1; }
        if (view.getId() == findViewById(R.id.button2).getId()) { buttonId = 2; }
        if (view.getId() == findViewById(R.id.button3).getId()) { buttonId = 3; }

        if (buttonId == getCorrectOption()) {
            //TODO: Button size gets slightly bigger when color changes
            view.setBackgroundColor(Color.parseColor("#00ff00"));
        }
        else {
            view.setBackgroundColor(Color.parseColor("#ff0000"));
            if (getCorrectOption() == 0) {
                Button button0 = (Button) findViewById(R.id.button0);
                button0.setBackgroundColor(Color.parseColor("#00ff00"));
            }
            if (getCorrectOption() == 1) {
                Button button1 = (Button) findViewById(R.id.button1);
                button1.setBackgroundColor(Color.parseColor("#00ff00"));
            }
            if (getCorrectOption() == 2) {
                Button button2 = (Button) findViewById(R.id.button2);
                button2.setBackgroundColor(Color.parseColor("#00ff00"));
            }
            if (getCorrectOption() == 3) {
                Button button3 = (Button) findViewById(R.id.button3);
                button3.setBackgroundColor(Color.parseColor("#00ff00"));
            }
        }
    }
}
