import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.deploy.util.StringUtils;

/**
 * EssayHelper
 *
 * @author Rama Gosula, Zac Guo, Harrison Leon (aka "The Flaming Monks")
 *         <p>
 *         Originally created for "TrojanHacks" Hackathon, held by ACM at USC
 *         First created 10/25/14 to 10/26/14
 *         Last updated 10/29/14
 *         </p>
 */

public class EssayHelper extends JApplet implements ActionListener
{
    // Button labels
    private final String REDUNDANCY = "Redundancy";
    private final String FREQUENCY = "Frequency";
    private final String CLEAR = "Clear";
    private final String FILE = "Select File";

    // Area for building GUI
    private JTextArea inputField;

    // List of redundant phrases
    private List<String> badPhrases = new ArrayList<String>(Arrays.asList("and also", "and/or", "as to whether", "basically", "essentially", "totally", "each and every", "etc",
            "he/she", "firstly", "secondly", "thirdly", "got", "ain't", "interesting", "kind of", "literally", "lots",
            "lots of", "just", "the reason why is because", "till", "try", "try and", "very", "really", "quite"));

    /* private List<String> commonWords = new ArrayList<String>(Arrays.asList("the", "be", "to", "og", "and", "and", "a", "in", "that",
            "have", "I", "it", "for", "not", "on", "with", "he", "as",
            "you", "do", "at", "this", "but", "his", "by", "from"));
    */

    String patternString = "\\b(" + StringUtils.join(badPhrases, "|") + ")\\b";
    Pattern pattern = Pattern.compile(patternString);

    public void init()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        // GUI elements are added to the applet content pane, so get it for us.
        Container contentPane = getContentPane();
        contentPane.setBackground(new Color(67, 166, 232));

        // set a layout with some spacing
        contentPane.setLayout(new BorderLayout(15, 15));
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

        inputField = new JTextArea("Paste Here", 10, 10);
        inputField.setLineWrap(true); // makes sure text doesn't go off the screen
        inputField.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(inputField); // adds a scroll bar to input window
        add(scroll, BorderLayout.CENTER);
        inputField.setFont(new Font("Times New Roman", Font.ITALIC, 16));

        // Makes a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(52, 129, 180));

        // Adds four buttons
        JButton redundancy = new JButton(REDUNDANCY);
        redundancy.addActionListener(this);
        buttonPanel.add(redundancy);

        JButton frequency = new JButton(FREQUENCY);
        frequency.addActionListener(this);
        buttonPanel.add(frequency);

        JButton clear = new JButton(CLEAR);
        clear.addActionListener(this);
        buttonPanel.add(clear);

        JButton file = new JButton(FILE);
        file.addActionListener(this);
        buttonPanel.add(file);

        // Adds the buttons panel to the content pane
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getAppletInfo()
    {
        return "Title: Essay Helper  \n" +
                "Author: Rama Gosula, Zac Guo, Harrison Leon  \n" +
                "An applet to improve your writing. ";
    }

    // Calls certain methods depending on which buttons are pressed
    public void actionPerformed(ActionEvent evt)
    {
        String command = evt.getActionCommand();

        if (REDUNDANCY.equals(command)) // Redundancy button is pressed
            checkBadPhrases();
        else if (FREQUENCY.equals(command)) // Frequency button is pressed
            checkRepetitiveness();
        else if (CLEAR.equals(command)) // Clear button is pressed
            inputField.setText("");
        else if (FILE.equals(command)) // File button is pressed
        {
            try {
                inputField.setText(getFileText());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private String getFileText() throws FileNotFoundException
    {
        File file = null;

        final JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "doc", "docx");
        fc.setFileFilter(filter);

        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            file = fc.getSelectedFile();
        }

        Document doc = new Document(file != null ? file.getPath() : null);
        return doc.getText();
    }

    public void checkBadPhrases()
    {


        String str = inputField.getText();
        String[] phrases = str.split("[,.;()/!?]"); // splits at punctuation
        int start, end;
        String first, mid, last;

        for (int i=0; i < phrases.length; i++)
        {
            List<Integer> indexes = hasBadPhrase(phrases[i]);

            if (indexes.size() > 0)
            {
                for (int j = 0; j < indexes.size(); j+=2)
                {
                    start = indexes.get(j);
                    end = indexes.get(j + 1);
                    first = phrases[i].substring(0, start);
                    mid = phrases[i].substring(start, end);
                    last = phrases[i].substring(end);

                    phrases[i] = first + "***" + mid.toUpperCase() + "***" + last;
                }
            }
        }

        inputField.setText(Arrays.toString(phrases));
    }

    public List<Integer> hasBadPhrase(String str)
    {
        List<Integer> result = new ArrayList<Integer>();
        Matcher matcher = pattern.matcher(str);

        while (matcher.find())
        {
            result.add(matcher.start());
            result.add(matcher.end());
        }

        return result;
    }

    public void checkRepetitiveness()
    {
        String str = inputField.getText(); // Receives text from inputField

        WordFrequency2 freq = new WordFrequency2();
        freq.readText(str);
        inputField.setText(freq.toString());

    }

}
