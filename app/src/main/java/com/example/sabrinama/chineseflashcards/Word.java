package com.example.sabrinama.chineseflashcards;

/**
 * Created by Henry on 4/22/17.
 */

public class Word {
        String word;
        int numCorrect;

        double lastTestTime;

        public Word()
        {
            word = "";
            numCorrect = 0;
            lastTestTime = 0;
        }


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

        public double getLastTestTime() {return lastTestTime;}

        public void setLastTestTime(double lastTestTime) {this.lastTestTime = lastTestTime;}
}
