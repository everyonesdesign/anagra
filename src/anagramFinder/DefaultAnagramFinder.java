package anagramFinder;

import formMediator.FormMediator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultAnagramFinder implements AnagramFinder {

    @Override
    public void generateAnagrams(String[] sourceWords, FormMediator mediator) {

        int sourceWordsLength = sourceWords.length;
        int minLetters;
        List<String> outputList = new ArrayList<String>();
        Object[] outputArray;
        String[] outputStringsArray;

        try {
            minLetters = mediator.getMinLetters();
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(null,"Введите корректное число!");
            return;
        }

        mediator.setOutputListData(new String[0]);

        for (int i=0; i<sourceWords.length; i++) {

            //check: if current thread is interrupted then go out of the function
            if (Thread.currentThread().isInterrupted()) {
                return;
            }

            for (int j=0; j<sourceWords.length; j++) {
                //if the word is not empty and not the same and their letters match
                if (getStringSize(sourceWords[i])>= minLetters && !sourceWords[i].equals(sourceWords[j]) && compare(sourceWords[i], sourceWords[j])) {
                    outputList.add(0, sourceWords[i] + " - " + sourceWords[j]);
                    //set word with second index to empty so anagrams won't repeat
                    sourceWords[j] = "";

                    // generate string array variable from the list
                    outputArray = outputList.toArray();
                    outputStringsArray =  Arrays.copyOf(outputArray, outputArray.length, String[].class);
                    mediator.setOutputListData(outputStringsArray);
                }
            }

            mediator.setProgress(i * 100 / (sourceWordsLength - 1));
        }

        mediator.afterGetAnagrams();

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
