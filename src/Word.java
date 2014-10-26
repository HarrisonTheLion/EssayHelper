
public class Word 
{
    private String text;
    private int count;

    public Word(String text)
    {
        this.text = text;
        this.count = 1;
    }

    public void incrementCount()
    {
        this.count++;
    }
    
    public int getCount()
    {
        return this.count;
    }
    
    public String getText()
    {
        return this.text;
    }
}
