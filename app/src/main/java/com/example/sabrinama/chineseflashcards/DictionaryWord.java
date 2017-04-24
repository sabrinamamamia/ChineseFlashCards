package com.example.sabrinama.chineseflashcards;

/**
 * Created by Henry on 4/22/17.
 */

public class DictionaryWord {
        Word chineseWord;
        Word pinyinWord;
        Word englishWord;
        int numTried;

    public int getCorrectWord() {
        return correctWord;
    }

    public void setCorrectWord(int correctWord) {
        this.correctWord = correctWord;
    }

    int correctWord;

        public DictionaryWord()
        {
            chineseWord = new Word();
            pinyinWord = new Word();
            englishWord = new Word();
            correctWord = 0;
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
        public int getNumTried() {
        return numTried;
    }
        public void setNumTried(int numTried) {
        this.numTried = numTried;
    }
}
