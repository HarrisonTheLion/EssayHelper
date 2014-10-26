/**
 * Created by Harrison on 10/25/2014.
 */
import java.util.*;
import java.io.*;

public class WordFrequency
{
    public static void main(String[] args)
    {
        TreeMap<String, Integer> frequency = new TreeMap<String, Integer>();

        readTextFile(frequency);
        printAllWords(frequency);
    }

    public static int getCount (String word, TreeMap<String, Integer> frequency)
    {
        if (frequency.containsKey(word))
        {
            return frequency.get(word);
        }

        else // There are no occurrences of this word
        {
            return 0;
        }
    }

    public static void printAllWords(TreeMap<String, Integer> frequency)
    {
        System.out.println("--------------------------------------------------");
        System.out.println("           Word    # of Times\n");

        for (String word : frequency.keySet())
        {
            System.out.printf("%15s     %d\n", word, frequency.get(word));
        }

        System.out.println("--------------------------------------------------");
    }

    public static void readTextFile(TreeMap<String, Integer> frequency)
    {

        Integer count;

        try
        {
            BufferedReader br = new BufferedReader(new FileReader("D:\\Harrison\\Desktop\\sample.txt"));
            String s;
            while ((s=(br.readLine()))!=null)
            {
                for (String word : s.split("[\\W]"))
                {
                    word = word.toLowerCase();
                    count = getCount(word, frequency);
                    frequency.put(word, count+1);
                }

            }
            //textFile = new Scanner(new FileReader("D:\\Harrison\\Desktop\\sample.txt"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
