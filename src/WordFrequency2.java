import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class WordFrequency2 {
    private ArrayList<Word> wordList;

    public WordFrequency2(){
        wordList = new ArrayList<Word>();
    }

    public int getCount(String word)
    {
        boolean matched = false;
        int i = 0;
        int count = 0;

        int wordListSize = wordList.size();

        while (!matched && i <= wordListSize){
            Word oldWord = wordList.get(i);
            //If any old word matches, increment, and break out
            if (oldWord.getText().equalsIgnoreCase(word)){
                matched = true;
                count = oldWord.getCount();
            }
            i++;
        }
        return count;
    }

    public void readText(String str) //Adds all of the words to the arraylist, with their count
    {
        wordList.clear();
        if(!str.isEmpty())
        {
            str.trim();
            for (String word : str.split("[^\\w']"))// Split the string at non-alphanumerics
            {

                if(!isNumeric(word) && word.trim().length() > 0)
                word = word.toLowerCase();//Remember, lowercase word here is a String!
                if(wordList.isEmpty()){
                    wordList.add(new Word(word));
                }else{
                    boolean matched = false;
                    int i = 0;

                    int wordListSize = wordList.size();

                    while (!matched && i < wordListSize){
                        Word oldWord = wordList.get(i);
                        //If any old word matches, increment, and break out
                        if (oldWord.getText().equalsIgnoreCase(word)){
                            oldWord.incrementCount();
                            matched = true;
                        }
                        i++;
                    }//without a match, add a new word
                    if (!matched){
                        wordList.add(new Word(word));
                    }

                }

            }

        }
    }
    //TODO include a or the?
    @Override
    public String toString(){
        String output = "";
        //Sort wordlist from largest to smallest
        wordList.sort(new WordComp());
        Collections.reverse(wordList);

        output += "Words used:\n";
        for (Word word : wordList){
            output += String.format("\n%s\t%d", word.getText(),word.getCount());
        }
        return output;

    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
class WordComp implements Comparator<Word>{

    @Override
    public int compare(Word arg0, Word arg1) {
        // TODO Auto-generated method stub
        if (arg0.getCount() > arg1.getCount()){
            return 1;
        }else if (arg0.getCount() < arg1.getCount()){
            return -1;
        }else{
            return 0;
        }
    }
}
