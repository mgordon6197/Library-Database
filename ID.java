package ProgAssignment4;

/**
 * Holds all relevant data for the "Magic Number" of a book. making it unique in a database.
 *
 * @author Matthew Gordon
 * @version v1.0
 * @since 12/10/2014
 */
public class ID
{
    private String mainClass;
    private String subClass;
    private String serialNum;
    private String copyNum;

    public ID()
    {
        mainClass = "";
        subClass = "";
        serialNum = "";
        copyNum = "";
    }

    public ID(String mainClass, String subClass, String serialNum, String copyNum) {
        this.mainClass = mainClass;
        this.subClass = subClass;
        this.serialNum = serialNum;
        this.copyNum = copyNum;
    }

    /**
     * sets the magic number according to the passed string.
     * @param id
     */
    public void setMagicNumber(String id)
    {
        String [] tokens = id.split("\\.");
        if(tokens.length == 4)
        {
            mainClass = tokens[0];
            subClass = tokens[1];
            serialNum = tokens[2];
            copyNum = tokens[3];
        }
        else
            System.out.println("Not a Magic Number");
    }

    /**
     * returns the Magic Number as a String
     */
    public String toString()
    {
        return mainClass + "." + subClass + "." + serialNum + "." + copyNum;
    }
}
