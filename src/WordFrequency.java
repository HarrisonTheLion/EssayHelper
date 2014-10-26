import java.util.TreeMap;

public class WordFrequency
{
    public static TreeMap<String, Integer> frequency = new TreeMap<String, Integer>();

    // Returns the count of a given key/word in a given TreeMap
    public static int getCount(String word, TreeMap<String, Integer> frequency)
    {
        if (frequency.containsKey(word))
        {
            return frequency.get(word);
        } else // There are no occurrences of this word in the TreeMap
        {
            return 0;
        }
    }

    public static void readText(TreeMap<String, Integer> frequency, String str)
    {
        int count;
        if (str.length() > 0)
        {
            for (String word : str.split("[\\W]"))
            {
                word = word.toLowerCase();
                count = getCount(word, frequency);
                frequency.put(word, count + 1);
            }
        }

    }

    // Returns a String of an alphabetical list of words and their frequencies in a given TreeMap
    public static String allWords(TreeMap<String, Integer> frequency)
    {
        String words = "";
        words += ("--------------------------------------------------\n");
        words += ("Word (# of Times)\n");

        // Adds word (frequency) to a new line
        for (String word : frequency.keySet())
        {
            words += word + "  (" + (frequency.get(word)).toString() + ")" + "\n";
        }

        words += ("--------------------------------------------------");

        return words;
    }
}