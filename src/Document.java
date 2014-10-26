/* import org.apache.poi.hwpf.Word2Forrest;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
*/
/*
 * Make sure to add the jar files to the classPath!!
 * 
 * C:\Users\ramag_000\Downloads\poi-bin-3.10.1-20140818\poi-3.10.1\poi-3.10.1-20140818.jar
C:\Users\ramag_000\Downloads\poi-bin-3.10.1-20140818\poi-3.10.1\poi-examples-3.10.1-20140818.jar
C:\Users\ramag_000\Downloads\poi-bin-3.10.1-20140818\poi-3.10.1\poi-excelant-3.10.1-20140818.jar
C:\Users\ramag_000\Downloads\poi-bin-3.10.1-20140818\poi-3.10.1\poi-ooxml-3.10.1-20140818.jar
C:\Users\ramag_000\Downloads\poi-bin-3.10.1-20140818\poi-3.10.1\poi-ooxml-schemas-3.10.1-20140818.jar
C:\Users\ramag_000\Downloads\poi-bin-3.10.1-20140818\poi-3.10.1\poi-scratchpad-3.10.1-20140818.jar
C:\Users\ramag_000\Downloads\poi-bin-3.10.1-20140818\poi-3.10.1\ooxml-lib\dom4j-1.6.1.jar
C:\Users\ramag_000\Downloads\poi-bin-3.10.1-20140818\poi-3.10.1\ooxml-lib\stax-api-1.0.1.jar
C:\Users\ramag_000\Downloads\poi-bin-3.10.1-20140818\poi-3.10.1\ooxml-lib\xmlbeans-2.6.0.jar

 */

/* import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.util.Scanner;

public class Document
{
    private String fileText = "";

    //document takes filepath, alternative could be a File
    public Document(String filePath) throws FileNotFoundException
    {// throws on Constructor should be fixed. Kludge
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        if (filePath.substring(filePath.length() - 3).equals("txt"))
        {
            try
            {
                fileText = new String(Files.readAllBytes(Paths.get(filePath)));
            } catch (IOException e)
            {
                e.printStackTrace();
            }


        } else if (filePath.substring(filePath.length() - 1).equals("x")) //check if doc or docx
        {
            try
            {
                XWPFDocument doc = new XWPFDocument(fis);
                XWPFWordExtractor extract = new XWPFWordExtractor(doc);
                fileText = extract.getText();
                extract.close();
            } catch (IOException e)
            {

                e.printStackTrace();
            }

        } else //if not a docx
        {
            try
            {
                WordExtractor wordExtractor = new WordExtractor(fis);
                fileText = wordExtractor.getText();
                wordExtractor.close();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public String getText()
    {
        return fileText;
    }
}

*/
