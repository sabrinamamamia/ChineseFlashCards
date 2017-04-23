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
    int correctOption2;
    int currentWord;
    int radioIdx;
    int numQuestions;

    public int getCorrectOption() {return correctOption;};
    public void setCorrectOption(int n) {correctOption = n;};

    public int getCorrectOption2() {return correctOption2;};
    public void setCorrectOption2(int n) {correctOption2 = n;};

    public int getCurrentWord() {return currentWord;};
    public void setCurrentWord(int n) {currentWord = n;};

    public int getRadioIdx() {return radioIdx;};
    public void setRadioIdx(int n) {radioIdx = n;};

    public int getNumQuestions() {return numQuestions;};
    public void setNumQuestions(int n) {numQuestions = n;};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        Integer radioIdx = intent.getIntExtra(StartActivity.RADIO_CHOSEN, -1);  //-1 is default val
        Integer questionNum = intent.getIntExtra(StartActivity.QUESTION_NUM,-1);
        setRadioIdx(radioIdx);
        setNumQuestions(questionNum);
        createScreen(radioIdx);

    }

    public int createScreen(int radioIdx) {
        Random rand = new Random();
        int randButton1 = rand.nextInt((3 - 0) + 1) + 0;
        int randButton2 = rand.nextInt((7 - 4) + 1) + 4;
        int  randWord = rand.nextInt(dictionary.size()-1);
        setCorrectOption(randButton1);
        setCorrectOption2(randButton2);
        setCurrentWord(randWord);

        TextView textview = (TextView) findViewById(R.id.word);
        TextView button0 = (TextView) findViewById(R.id.button0);
        TextView button1 = (TextView) findViewById(R.id.button1);
        TextView button2 = (TextView) findViewById(R.id.button2);
        TextView button3 = (TextView) findViewById(R.id.button3);
        TextView button4 = (TextView) findViewById(R.id.button4);
        TextView button5 = (TextView) findViewById(R.id.button5);
        TextView button6 = (TextView) findViewById(R.id.button6);
        TextView button7 = (TextView) findViewById(R.id.button7);

        ArrayList<TextView> buttonList = new ArrayList<>();
        buttonList.add(button0);
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        buttonList.add(button5);
        buttonList.add(button6);
        buttonList.add(button7);

        for (int i = 0; i < buttonList.size(); i++) {                           //Set button style
            buttonList.get(i).setBackgroundResource(R.drawable.button_border);
        }

        //Set test time
        dictionary.get(randWord).getEnglishWord().setLastTestTime(System.nanoTime());
        dictionary.get(randWord).getChineseWord().setLastTestTime(System.nanoTime());
        dictionary.get(randWord).getPinyinWord().setLastTestTime(System.nanoTime());

        //Set # of times asked
        dictionary.get(randWord).setNumTried(dictionary.get(randWord).getNumTried()+1);

        //English
        if (radioIdx == 0) {
            textview.setText(dictionary.get(randWord).getEnglishWord().word);   //Set textview
            createOptionVal(buttonList, randWord, randButton1, randButton2); //Set options
        }
        //Pinyin
        else if (radioIdx == 1) {
            textview.setText(dictionary.get(randWord).getPinyinWord().word);
            createOptionVal(buttonList, randWord, randButton1, randButton2);
        }
        //Chinese
        else if (radioIdx == 2) {
            textview.setText(dictionary.get(randWord).getChineseWord().word);
            createOptionVal(buttonList, randWord, randButton1, randButton2);
        }
        return randWord;
    }

    public void createOptionVal(ArrayList<TextView>list,
                                int correctWord, int correctButton, int correctButton2) {

        String option = " ";
        for (int i = 0; i < list.size(); i++) {
            int a;
            if (i == correctButton || i == correctButton2) {
                a = correctWord;
            }
            else {
                Random rand = new Random();
                int randWord = rand.nextInt(dictionary.size() - 1);
                a = randWord;
            }

            //Question: English, Option Set 1: Chinese, Option Set 2: Pinyin
            if (getRadioIdx()==0) {
                if (i < 4) {
                    option = dictionary.get(a).getChineseWord().word;
                }
                else {
                    option = dictionary.get(a).getPinyinWord().word;
                }
            }

            //Question: Pinyin, Option Set 1: English, Option Set 2: Chinese
            else if (getRadioIdx()==1) {
                if (i < 4) {
                    option = dictionary.get(a).getEnglishWord().word;
                }
                else {
                    option = dictionary.get(a).getChineseWord().word;
                }
            }

            //Question: Chinese, Option Set 1: English, Option Set 2: Pinyin
            else if (getRadioIdx()==2) {
                if (i < 4) {
                    option = dictionary.get(a).getEnglishWord().word;
                }
                else {
                    option = dictionary.get(a).getPinyinWord().word;
                }
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
        if (view.getId() == findViewById(R.id.button4).getId()) { buttonId = 4; }
        if (view.getId() == findViewById(R.id.button5).getId()) { buttonId = 5; }
        if (view.getId() == findViewById(R.id.button6).getId()) { buttonId = 6; }
        if (view.getId() == findViewById(R.id.button7).getId()) { buttonId = 7; }

        if (buttonId >= 0 && buttonId <= 3) {

            //User selects correct option
            if (buttonId == getCorrectOption()) {

                //Change button color
                //TODO: Button size gets slightly bigger when color changes
                view.setBackgroundColor(Color.parseColor("#00ff00"));

                //Update numCorrect
                //Question: English, Option Set 1: Chinese
                if (getRadioIdx()==0) {
                    dictionary.get(getCurrentWord()).getChineseWord().setNumCorrect(
                            dictionary.get(getCurrentWord()).getChineseWord().getNumCorrect()+1);
                }

                //Question: Pinyin, Option Set 1: English
                if (getRadioIdx()==1) {
                    dictionary.get(getCurrentWord()).getEnglishWord().setNumCorrect(
                            dictionary.get(getCurrentWord()).getEnglishWord().getNumCorrect()+1);
                }

                //Question: Chinese, Option Set 1: English
                if (getRadioIdx()==2) {
                    dictionary.get(getCurrentWord()).getEnglishWord().setNumCorrect(
                            dictionary.get(getCurrentWord()).getEnglishWord().getNumCorrect()+1);
                }
            }

            //User selects incorrect option
            else {
                view.setBackgroundColor(Color.parseColor("#ff0000"));
                if (getCorrectOption() == 0) {
                    Button button0 = (Button) findViewById(R.id.button0);
                    button0.setBackgroundColor(Color.parseColor("#00ff00"));
                } else if (getCorrectOption() == 1) {
                    Button button1 = (Button) findViewById(R.id.button1);
                    button1.setBackgroundColor(Color.parseColor("#00ff00"));
                } else if (getCorrectOption() == 2) {
                    Button button2 = (Button) findViewById(R.id.button2);
                    button2.setBackgroundColor(Color.parseColor("#00ff00"));
                } else if (getCorrectOption() == 3) {
                    Button button3 = (Button) findViewById(R.id.button3);
                    button3.setBackgroundColor(Color.parseColor("#00ff00"));
                }
            }
        }

        else if (buttonId >= 4 && buttonId <= 7) {
            if (buttonId == getCorrectOption2()) {
                view.setBackgroundColor(Color.parseColor("#00ff00"));
                //Question: English, Option Set 1: Pinyin
                if (getRadioIdx()==0) {
                    dictionary.get(getCurrentWord()).getPinyinWord().setNumCorrect(
                            dictionary.get(getCurrentWord()).getPinyinWord().getNumCorrect()+1);
                }
                //Question: Pinyin, Option Set 2: Chinese
                if (getRadioIdx()==1) {
                    dictionary.get(getCurrentWord()).getChineseWord().setNumCorrect(
                            dictionary.get(getCurrentWord()).getChineseWord().getNumCorrect()+1);
                }
                //Question: Chinese, Option Set 2: Pinyin
                if (getRadioIdx()==2) {
                    dictionary.get(getCurrentWord()).getPinyinWord().setNumCorrect(
                            dictionary.get(getCurrentWord()).getPinyinWord().getNumCorrect()+1);
                }
            }

            else {
                view.setBackgroundColor(Color.parseColor("#ff0000"));
                if (getCorrectOption2() == 4) {
                    Button button4 = (Button) findViewById(R.id.button4);
                    button4.setBackgroundColor(Color.parseColor("#00ff00"));
                }
                else if (getCorrectOption2() == 5) {
                    Button button5 = (Button) findViewById(R.id.button5);
                    button5.setBackgroundColor(Color.parseColor("#00ff00"));
                }
                else if (getCorrectOption2() == 6) {
                    Button button6 = (Button) findViewById(R.id.button6);
                    button6.setBackgroundColor(Color.parseColor("#00ff00"));
                }
                else if (getCorrectOption2() == 7) {
                    Button button7 = (Button) findViewById(R.id.button7);
                    button7.setBackgroundColor(Color.parseColor("#00ff00"));
                }
            }
        }
    }

    public void onNextClicked(View view) {

        if (getNumQuestions() != 1) {
            createScreen(getRadioIdx());
            setNumQuestions(getNumQuestions()-1);
        }
        else {
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("test", 0);
            startActivity(intent);
        }
    }

}
