import java.awt.*;
import java.awt.Color;
import javax.swing.*;
//import javax.swing.text.BadLocationException;
//import javax.swing.text.DefaultHighlighter;
//import javax.swing.text.Highlighter;
//import javax.swing.text.Highlighter.HighlightPainter;
import java.awt.event.*;
//import javax.swing.*;
//import java.lang.reflect.Array;
import java.util.*;
//import java.io.*;

/**
 * EssayHelper
 *
 * @author Rama Gosula, Zac Guo, Harrison Leon (aka "The Flaming Monks")
 *
 * 2014-10-25
 */

public class EssayHelper extends JApplet
        implements ActionListener
{
    //Area for building GUI
    private JTextArea inputField;

    //Button labels
    private final String RUN = "Redundancy";
    private final String FREQ = "Frequency";
    private final String CLEAR = "Clear";

    //List of redundant phrases
    private String[] badWords={"and also", "and/or", "as to whether", "basically", "essentially", "totally", "each and every", "etc",
            "he/she", "firstly", "secondly", "thirdly", "got", "ain't", "interesting", "kind of", "literally", "lots",
            "lots of", "just", "the reason why is because", "till", "try", "try and", "very", "really", "quite"};

    public void init()
    {
        // GUI elements are added to the applet's content pane, so get it for us.
        Container contentPane = getContentPane();
        contentPane.setBackground(new Color(67, 166, 232));

        // set a layout with some spacing
        contentPane.setLayout(new BorderLayout(12,12));
        //window size
        setSize(600, 600);

        // add the title label
        JLabel title = new JLabel("Essay Helper 2000");
        title.setFont(new Font("Helvetica", Font.BOLD, 20));
        contentPane.add(title, BorderLayout.NORTH);

        // create the center part with prompt and text field and add it
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(67, 166, 232));
        JLabel prompt = new JLabel("Enter your text:  ");
        prompt.setFont(new Font("Helvetica", Font.PLAIN, 16));
        centerPanel.add(prompt);
        inputField = new JTextArea("Paste Here");

        //add scrolling capability

        inputField.setFont(new Font("Serif", Font.ITALIC, 16));
        //make sure text doesn't go off the screen
        inputField.setLineWrap(true);
        inputField.setWrapStyleWord(true);
        inputField.setPreferredSize( new Dimension( 400, 400 ) );
        centerPanel.add(inputField);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        // make a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(52, 129, 180));
        // add the buttons to the button panel
        JButton run = new JButton(RUN);
        run.addActionListener(this);
        buttonPanel.add(run);

        JButton freq = new JButton(FREQ);
        freq.addActionListener(this);
        buttonPanel.add(freq);

        JButton clear = new JButton(CLEAR);
        clear.addActionListener(this);
        buttonPanel.add(clear);

        // add the buttons panel to the content pane
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getAppletInfo()
    {
        return "Title: Essay Helper  \n" +
                "Author: Zac Guo, Harrison Leon, Rama Gosula  \n" +
                "An applet to improve your writing. ";
    }

    public void actionPerformed(ActionEvent evt)
    {
        String command = evt.getActionCommand();
        // if clear button pressed
        if(CLEAR.equals(command))
            inputField.setText("");
            // uppercase button pressed
        else if(FREQ.equals(command))
        {
            checkRepetitiveness();
        }
        else if(RUN.equals(command))
        {
            checkBadPhrases();
            //calculateScore();

        }
    }

    public void checkBadPhrases()
    {
        //initialize
        String str = inputField.getText();

        String[] words = str.split("\\s+");
        //int length=words.length;                                  [Unused code removed temporarily]
        //String[] matches = new String[length];                    [Unused code removed temporarily]
        //JOptionPane.showMessageDialog(null,words);
        //JOptionPane.showMessageDialog(null,badWords);
        /* Replace non-word characters with empty (delete)
         * for(int i = 0; i<words.length; i++){
        words[i] = words[i].replaceAll("[^\w]", "");
        }*/

        for (String badWord : badWords)
        {
            for (int j = 0; j < words.length; j++)
            {
                String lower = words[j].toLowerCase();
                if (lower.equals(badWord))
                {
                    //matches[j] = words[j];                        [Unused code removed temporarily]
                    words[j] = "*" + words[j].toUpperCase() + "*";

                }
            }
        }
        //JOptionPane.showMessageDialog(null,matches);
        String paragraph="";

        for (String word : words)
        {
            paragraph = paragraph + " " + word;
        }

        inputField.setText(paragraph);
    }

    public void checkRepetitiveness()
    {
        String str = inputField.getText(); // Receives text from inputField

        WordFrequency.readText(WordFrequency.frequency, str); // Takes String from inputField and creates TreeMap with frequencies
        inputField.setText(allWords(WordFrequency.frequency)); // Sets text to list of word frequencies
    }

    // Prints out a formatted alphabetical list of words and their frequencies in a given TreeMap
    public static String allWords (TreeMap<String, Integer> frequency)
    {
        String words = "";
        words += ("--------------------------------------------------\n");
        words += ("           Word    # of Times\n");

        for (String word : frequency.keySet())
        {
            words +=(word + (frequency.get(word)).toString()) + "\n";
        }

        words += ("--------------------------------------------------");

        return words;
    }

    public void calculateScore()
    {
    }
}



