package com.example.sabrinama.chineseflashcards;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class StartActivity extends AppCompatActivity {
    public static final String RADIO_CHOSEN = "com.example.sabrinama.chineseflashcards.RADIO";
    public static final String QUESTION_NUM = "com.example.sabrinama.chineseflashcards.QUESTION";
    public static ArrayList<DictionaryWord> dictionary = new ArrayList<DictionaryWord>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if (!ResultActivity.restart)
        {
            readDictionary task = new readDictionary();
            task.execute();
        }
        NumberPicker numPick = (NumberPicker) findViewById(R.id.quizSize_Picker);
        numPick.setMinValue(1);
        numPick.setMaxValue(24);
        numPick.setValue(10);
        numPick.setWrapSelectorWheel(true);
    }


    private class readDictionary extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected Void doInBackground(Void... param) {
//            ArrayList<DictionaryWord> tempDict = new ArrayList<DictionaryWord>();
            try {
                URL url = new URL("http://people.cs.georgetown.edu/~bk620/chidi.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String line = null;

                String chinese;
                String pinyin;
                String english;

                while ((line = br.readLine()) != null)
                {
                    String[] values = line.split(",");

                    chinese = values[0];
                    pinyin = values[1];
                    english = values[2];


                    DictionaryWord word = new DictionaryWord();
                    word.chineseWord.setWord(chinese);
                    word.pinyinWord.setWord(pinyin);
                    word.englishWord.setWord(english);
                    dictionary.add(word);
                }
                br.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }


    public void onButtonClicked(View view) {

        //Find index of checked radio button
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.question_type_radioGroup);
        int radioButtonID = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonID);
        int radioIdx = radioGroup.indexOfChild(radioButton);
        String radioIdxString = String.valueOf(radioIdx);

        //Get number of questions
        //Todo: Bounds checking? Notify user if selected too few/many cards
        //EditText editText = (EditText) findViewById(R.id.question_count_editText);
        NumberPicker numPick = (NumberPicker) findViewById(R.id.quizSize_Picker);
        //int questionNum = Integer.parseInt(editText.getText().toString());
        int qn = Integer.parseInt(String.valueOf(numPick.getValue()));
        //String questionNumString = String.valueOf(questionNum);

        //Send index to GameActivity
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(RADIO_CHOSEN, radioIdx);
        intent.putExtra(QUESTION_NUM, qn);
        startActivity(intent);
    }
}