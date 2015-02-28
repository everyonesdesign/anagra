package anagramFinder;

import formMediator.FormMediator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickAnagramFinder implements AnagramFinder {

    @Override
    public void generateAnagrams(String[] sourceWords, FormMediator mediator) {

        int minLetters;
        List<String> correctedInputList = new ArrayList<String>();
        List<String> outputList = new ArrayList<String>();
        int correctedInputListLength;
        int globalWordsLoopCounter = 0;
        String word1;
        String word2;

        try {
            minLetters = mediator.getMinLetters();
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(null, "Введите корректное число!");
            return;
        }

        mediator.setOutputListData(new String[0]);

        int lettersLoop = minLetters;
        while (true) {

            correctedInputList.clear();
            int maxLetters = 0;

            for (int i=0; i<sourceWords.length; i++) {

                //calculate max length to break when needed
                if (getStringSize(sourceWords[i]) > maxLetters) {
                    maxLetters = sourceWords[i].length();
                }

                //add appropriate words
                if (getStringSize(sourceWords[i]) == lettersLoop) {
                    correctedInputList.add(sourceWords[i]);
                }

            }

            if (lettersLoop ==  maxLetters+1) break;
            lettersLoop++;

            correctedInputListLength = correctedInputList.size();

            for (int i=0; i<correctedInputListLength; i++) {

                word1 = correctedInputList.get(i);

                //check: if current thread is interrupted then go out of the function
                if (Thread.currentThread().isInterrupted()) {
                    // generate string array variable from the list
                    setData(mediator, outputList);
                    return;
                }

                for (int j=0; j<correctedInputListLength; j++) {

                    word2 = correctedInputList.get(j);

                    //if the word is not empty and not the same and their letters match
                    if (!word1.equals(word2) && compare(word1, word2)) {
                        outputList.add(0, word1 + " - " + word2);

                        //set word with second index to empty so anagrams won't repeat
                        correctedInputList.remove(j);
                        j--;
                        correctedInputListLength = correctedInputList.size();
                    }

                }

                // generate string array variable from the list
                if (i%100==0) {
                    setData(mediator, outputList);
                    mediator.setProgress(globalWordsLoopCounter*100 /sourceWords.length);
                }

                globalWordsLoopCounter++;
                correctedInputList.remove(i);
                i--;
                correctedInputListLength = correctedInputList.size();

            }

        }

        setData(mediator, outputList);

        mediator.setProgress(100);
        mediator.afterGetAnagrams();

    }

    private void setData(FormMediator mediator, List<String> data) {
        Object[] dataArray = data.toArray();
        String[] dataStringsArray =  Arrays.copyOf(dataArray, dataArray.length, String[].class);
        mediator.setOutputListData(dataStringsArray);
    }

    public boolean compare(String word1, String word2) {

        word1 = stripSymbols(word1);
        word2 = stripSymbols(word2);

        //word 1 to lower-cased sorted array
        char[] word1Array = word1.toLowerCase().toCharArray();
        Arrays.sort(word1Array);

        //word 2 to lower-cased sorted array
        char[] word2Array = word2.toLowerCase().toCharArray();
        Arrays.sort(word2Array);

        return Arrays.equals(word1Array, word2Array);

    }

    private String stripSymbols(String string) {
        return string.replaceAll("\\.|,|\\-_", "");
    }

    private int getStringSize(String string) {
        string = stripSymbols(string);
        return string.length();
    }

}
