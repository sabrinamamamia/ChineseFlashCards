package com.example.sabrinama.chineseflashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


import static com.example.sabrinama.chineseflashcards.StartActivity.dictionary;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        Integer radioIdx = intent.getIntExtra(StartActivity.RADIO_CHOSEN, -1);  //-1 is default val
        Integer questionNum = intent.getIntExtra(StartActivity.QUESTION_NUM,-1);

        Random rand = new Random();
        int  correctButton = rand.nextInt(3) + 0;
        int  randWord = rand.nextInt(dictionary.size()-1);

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
            setOptionVal(buttonList, "english", randWord, correctButton);
        }

        //Pinyin
        else if (radioIdx == 1) {
            textview.setText(dictionary.get(randWord).getPinyinWord().word);
            setOptionVal(buttonList, "pinyin", randWord, correctButton);
        }

        //Chinese
        else if (radioIdx == 2) {
            textview.setText(dictionary.get(randWord).getChineseWord().word);
            setOptionVal(buttonList, "chinese", randWord, correctButton);
        }
    }

    public void onAnswerClicked(View view) {


    }

    public void setOptionVal(ArrayList<TextView>list, String type, int dictIdx, int correctIdx) {

        String option = " ";
        if (type == "english") {
            for (int i = 0; i < list.size(); i++) {
                if (i == correctIdx) {
                    option = dictionary.get(dictIdx).getChineseWord().word + "\n\n" +
                            dictionary.get(dictIdx).getPinyinWord().word;
                }
                else {
                    Random rand = new Random();
                    int randWord = rand.nextInt(dictionary.size() - 1) + 0;

                    option = dictionary.get(randWord).getChineseWord().word + "\n\n" +
                            dictionary.get(randWord).getPinyinWord().word;
                }
                list.get(i).setText(option);
            }
        }

        else if (type == "chinese") {
            for (int i = 0; i < list.size(); i++) {
                if (i == correctIdx) {
                    option = dictionary.get(dictIdx).getEnglishWord().word + "\n\n" +
                            dictionary.get(dictIdx).getPinyinWord().word;
                }
                else {
                    Random rand = new Random();
                    int randWord = rand.nextInt(dictionary.size() - 1) + 0;

                    option = dictionary.get(randWord).getEnglishWord().word + "\n\n" +
                            dictionary.get(randWord).getPinyinWord().word;
                }
                list.get(i).setText(option);
            }
        }

        else if (type == "pinyin") {
            for (int i = 0; i < list.size(); i++) {
                if (i == correctIdx) {
                    option = dictionary.get(dictIdx).getEnglishWord().word + "\n\n" +
                            dictionary.get(dictIdx).getChineseWord().word;
                }
                else {
                    Random rand = new Random();
                    int randWord = rand.nextInt(dictionary.size() - 1) + 0;

                    option = dictionary.get(randWord).getEnglishWord().word + "\n\n" +
                            dictionary.get(randWord).getChineseWord().word;
                }
                list.get(i).setText(option);
            }
        }
    }
}
