package com.example.sabrinama.chineseflashcards;

/**
 * Created by Henry on 4/22/17.
 */

public class Word {
        String word;
        int numCorrect;
        int numTried;
        double lastTestTime = System.nanoTime();

        public Word()
        {
            word = "";
            numCorrect = 0;
            numTried = 0;

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

        public int getNumTried() {
            return numTried;
        }

        public void setNumTried(int numTried) {
            this.numTried = numTried;
        }

}
