package com.example.sabrinama.chineseflashcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void onRadioButtonClicked(View view) {
        String buttonId = "";
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.english_button:
                if (checked)
                    buttonId = "english_button";
                break;
            case R.id.pinyin_button:
                if (checked)
                    buttonId = "pinyin_button";
                break;
            case R.id.chinese_button:
                if (checked)
                    buttonId = "chinese_button";
                break;
        }

        //Send selected radio button id to GameActivity
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("radioChosen", buttonId);
        startActivity(intent);
    }
}
