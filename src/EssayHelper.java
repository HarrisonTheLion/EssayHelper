import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * EssayHelper
 *
 * @author Rama Gosula, Zac Guo, Harrison Leon (aka "The Flaming Monks")
 *         <p/>
 *         2014-10-25
 */

public class EssayHelper extends JApplet
        implements ActionListener
{
    //Button labels
    private final String RUN = "Redundancy";
    private final String FREQ = "Frequency";
    private final String CLEAR = "Clear";
    private final String FILE = "File";
    //Area for building GUI
    private JTextArea inputField;
    //List of redundant phrases
    private String[] badWords = {"and also", "and/or", "as to whether", "basically", "essentially", "totally", "each and every", "etc",
            "he/she", "firstly", "secondly", "thirdly", "got", "ain't", "interesting", "kind of", "literally", "lots",
            "lots of", "just", "the reason why is because", "till", "try", "try and", "very", "really", "quite"};



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
        inputField.setFont(new Font("Serif", Font.ITALIC, 16));

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

        JButton file = new JButton(FILE);
        file.addActionListener(this);
        buttonPanel.add(file);

        // add the buttons panel to the content pane
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    public String getAppletInfo()
    {
        return "Title: Essay Helper  \n" +
                "Author: Rama Gosula, Zac Guo, Harrison Leon  \n" +
                "An applet to improve your writing. ";
    }

    public void actionPerformed(ActionEvent evt)
    {
        String command = evt.getActionCommand();
        // if clear button pressed
        if (CLEAR.equals(command))
        {
            inputField.setText("");

        } else if (FREQ.equals(command))
        {
            checkRepetitiveness();
        } else if (RUN.equals(command))
        {
            try
            {
                checkBadPhrases();
            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        else if (FILE.equals(command))
        {
            inputField.setText(getFilePath());
        }
    }

    private String getFilePath()
    {
        String filePath = "";

        final JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "doc", "docx");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            filePath = file.getPath();
        }

        return filePath;
    }

    public void checkBadPhrases() throws FileNotFoundException
    {
        //initialize
        String input = inputField.getText();
        input.replace("\\", "\\\\"); // fix accidental escapes in file-path

        Document doc = new Document(input);
        String str = doc.getText();

        String[] words = str.split("\\s+"); // splits at whitespace

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
        String paragraph = "";

        for (String word : words)
        {
            paragraph = paragraph + " " + word;
        }

        inputField.setText(paragraph);
    }

    public void checkRepetitiveness()
    {
        String str = inputField.getText(); // Receives text from inputField

        WordFrequency2 freq = new WordFrequency2();
        freq.readText(str);
        inputField.setText(freq.toString());
        // Takes String from inputField and creates TreeMap with frequencies
        // Sets text to list of word frequencies
    }

}

