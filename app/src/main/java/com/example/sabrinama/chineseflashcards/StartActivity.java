package com.example.sabrinama.chineseflashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class StartActivity extends AppCompatActivity {
    public static final String RADIO_CHOSEN = "com.example.sabrinama.chineseflashcards.RADIO";
    public static final String QUESTION_NUM = "com.example.sabrinama.chineseflashcards.QUESTION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
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
