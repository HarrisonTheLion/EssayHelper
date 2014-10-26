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
                if (!isNumeric(word))
                {
                    word = word.toLowerCase();//Remember, lowercase word here is a String!
                    if(wordList.isEmpty())
                        wordList.add(new Word(word));

                    }else{
                        boolean matched = false;
                        int i = 0;

                        int wordListSize = wordList.size();

                        while (!matched && i < wordListSize)
                        {
                            Word oldWord = wordList.get(i);
                            //If any old word matches, increment, and break out
                            if (oldWord.getText().equalsIgnoreCase(word))
                            {
                                oldWord.incrementCount();
                                matched = true;
                            }
                            i++;
                        }//without a match, add a new word
                        if (!matched)
                        {
                            wordList.add(new Word(word));
                        }

                }

            }

        }
    }

    @Override
    public String toString()
    {
        String words = "";
        //Sort wordlist from largest to smallest
        wordList.sort(new WordComp());
        Collections.reverse(wordList);

            words += ("--------------------------------------------------\n");
            words += ("(Frequency) Word\n");

        for (Word word : wordList)
        {
            words += String.format("\n%s\t\t%d", word.getText(),word.getCount());
        }

            words += ("\n--------------------------------------------------");

        return words;

    }

    public static boolean isNumeric(String str)
    {
        try
        {
            Integer.parseInt(str);
        } catch (Exception e)
        {
            return false;
        }
        return true;
    }
}
class WordComp implements Comparator<Word>{

    @Override
    public int compare(Word arg0, Word arg1) {
        if (arg0.getCount() > arg1.getCount()){
            return 1;
        }else if (arg0.getCount() < arg1.getCount()){
            return -1;
        }else{
            return 0;
        }
    }
}
