package anagramFinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultAnagramFinder implements AnagramFinder {

    @Override
    public String[] generateAnagrams(String[] sourceWords, int minLetters) {

        List<String> outputList = new ArrayList<String>();

        for (int i=0; i<sourceWords.length; i++) {
            for (int j=0; j<sourceWords.length; j++) {
                //if the word is not empty and not the same and their letters match
                if (sourceWords[i].length()>= minLetters && !sourceWords[i].equals(sourceWords[j]) && compare(sourceWords[i], sourceWords[j])) {
                    outputList.add(sourceWords[i] + " - " + sourceWords[j]);
                    //set word with second index to empty so anagrams won't repeat
                    sourceWords[j] = "";
                }
            }
        }

        // generate string array variable from the list
        Object[] outputArray = outputList.toArray();
        String[] outputStringsArray =  Arrays.copyOf(outputArray, outputArray.length, String[].class);

        return outputStringsArray;
    }

    public boolean compare(String word1, String word2) {

        //word 1 to lower-cased sorted array
        char[] word1Array = word1.toLowerCase().toCharArray();
        Arrays.sort(word1Array);

        //word 2 to lower-cased sorted array
        char[] word2Array = word2.toLowerCase().toCharArray();
        Arrays.sort(word2Array);

        return Arrays.equals(word1Array, word2Array);

    }

}
