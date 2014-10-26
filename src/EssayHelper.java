
import java.util.ArrayList;
import java.awt.*;
import java.awt.Color;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
/**
 * EssayHelper
 *
 * @authors Zac Guo, Harrison Leon, Rama Gosula
 *
 * 2014-10-25
 */
public class EssayHelper extends JApplet
        implements ActionListener
{
    //Area for building GUI
    private JTextArea inputField;

    //Button labels
    private final String RUN = "Run";
    private final String CLEAR = "Clear";

    //List of redundant phrases
    private String[] badWords={"and also", "and/or", "as to whether", "basically"};

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
        buttonPanel.setBackground(new Color(67, 166, 232));
        // add the buttons to the button panel
        JButton run = new JButton(RUN);
        run.addActionListener(this);
        buttonPanel.add(run);

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
        else if(RUN.equals(command))
        {
            checkBadPhrases();
            checkRepetitiveness();
            calculateScore();

        }
    }

    public void checkBadPhrases()
    {
        //initialize 
        Scanner scan = new Scanner(System.in);
        String str = inputField.getText();

        String[] words = str.split("\\s+");
        int length=words.length;
        String[] matches = new String[length];
        //JOptionPane.showMessageDialog(null,words);
        //JOptionPane.showMessageDialog(null,badWords);
        /* Replace non-word characters with empty (delete)
         * for(int i = 0; i<words.length; i++){
        words[i] = words[i].replaceAll("[^\w]", "");
        }*/

        for(int i=0; i<badWords.length; i++){
            for(int j=0; j<words.length; j++){
                if(words[j].equals(badWords[i])){
                    matches[j]=words[j];
                    words[j]="*"+words[j].toUpperCase()+"*";

                }
            }
        }
        //JOptionPane.showMessageDialog(null,matches);
        String paragraph="";

        for( int k=0;k< words.length;k++)
        {
            paragraph=paragraph + " " + words[k];
        }

        inputField.setText(paragraph);
    }

    public void checkRepetitiveness()
    {
    }

    public void calculateScore()
    {
    }
}
