package com.example.sabrinama.chineseflashcards;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
//        new Thread() {
//            @Override
//            public void run() {
//                dictionaryReader("http://people.cs.georgetown.edu/~bk620/chidi.txt");
//            }
//        }.start();

        readDictionary task = new readDictionary();
        task.execute();
        int x = 8;


        System.out.println(dictionary);

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

//        @Override
//        protected void onPostExecute(ArrayList<DictionaryWord> tempDict) {
//            dictionary = (ArrayList<DictionaryWord>)tempDict.clone();
//        }
    }


    public void dictionaryReader(String fileName)
    {
        try {
            URL url = new URL(fileName);
//            URLConnection yc = url.openConnection();
//            InputStream inputStream = url.openStream();

//
//            Scanner in = new Scanner(new BufferedReader(new InputStreamReader(yc.getInputStream())));

//            BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));
//
            Scanner in = new Scanner(new BufferedReader(new InputStreamReader(url.openStream())));

            String chinese;
            String pinyin;
            String english;
            String deliminator;

            while (in.hasNextLine()) {
                chinese = in.next();
                deliminator = in.next();
                pinyin = in.next();
                deliminator = in.next();
                english = in.next();

                DictionaryWord word = new DictionaryWord();
                word.chineseWord.setWord(chinese);
                word.pinyinWord.setWord(pinyin);
                word.englishWord.setWord(english);
                dictionary.add(word);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
        EditText editText = (EditText) findViewById(R.id.question_count_editText);
        int questionNum = Integer.parseInt(editText.getText().toString());
        String questionNumString = String.valueOf(questionNum);

        //Send index to GameActivity
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(RADIO_CHOSEN, radioIdxString);
        intent.putExtra(QUESTION_NUM, questionNumString);
        startActivity(intent);
    }
}

class DictionaryWord
{
    Word chineseWord;
    Word pinyinWord;
    Word englishWord;

    public DictionaryWord()
    {
        chineseWord = new Word();
        pinyinWord = new Word();
        englishWord = new Word();
    }

    public Word getChineseWord() {
        return chineseWord;
    }

    public void setChineseWord(Word chineseWord) {
        this.chineseWord = chineseWord;
    }

    public Word getPinyinWord() {
        return pinyinWord;
    }

    public void setPinyinWord(Word pinyinWord) {
        this.pinyinWord = pinyinWord;
    }

    public Word getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(Word englishWord) {
        this.englishWord = englishWord;
    }


}

class Word {
    String word;
    int numCorrect;
    int numTried;

    public Word()
    {
        word = "";
        numCorrect = 0;
        numTried = 0;
    }

    double lastTestTime = System.nanoTime();
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public void setNumCorrect(int numCorrect) {
        this.numCorrect = numCorrect;
    }

    public int getNumTried() {
        return numTried;
    }

    public void setNumTried(int numTried) {
        this.numTried = numTried;
    }
}
