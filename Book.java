package ProgAssignment4;

import java.util.ArrayList;

/**
 * Contains all the information and methods nesseccary for a single book.
 *
 * @author Matthew Gordon
 * @version v1.0
 * @since 12/10/2014
 */
public class Book
{
    private ID myId;
    private String title;
    private String publisher;
    private String publicationYear;
    private String subject;
    private ArrayList<String> authorNames;
    private boolean isCheckedOut;
    private User whoCheckedItOut;
    private int [] dateCheckedOut;


    public Book() {
        title = "";
        myId = new ID();
        publisher = "";
        publicationYear = "";
        subject = "";
        authorNames = new ArrayList<String>();
        isCheckedOut = false;
        whoCheckedItOut = null;
        dateCheckedOut = null;
    }

    public Book(ID myId, String publisher, String publicationYear, String subject, ArrayList<String> newAuthorNames, boolean isCheckedOut, User whoCheckedItOut, int [] arr, String newTitle) {
        title = newTitle;
        this.myId = myId;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.subject = subject;
        authorNames = newAuthorNames;
        this.isCheckedOut = isCheckedOut;
        this.whoCheckedItOut = whoCheckedItOut;
        dateCheckedOut = arr;
    }

    /*
     * All the setters and getters for the instance variables
     */
    public String getTitle() {
        return title;
    }

    public ID getMyId() {
        return myId;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean isCheckedOut) {
        this.isCheckedOut = isCheckedOut;
    }

    public String getSubject() {
        return subject;
    }

    public String getPublicationYear() {
        return publicationYear;
    }


    /**
     * Returns all the author's names into a string separated by a coma and a space.
     * @return authorString
     */
    public String authorNamesToString()
    {
        String authorString = "";
        for(int i = 0; i < authorNames.size(); i++)
        {
            authorString = authorString + authorNames.get(i);
            if (i != authorNames.size() - 1)
                authorString = authorString + ", ";
        }
        return authorString;
    }

    /**
     * returns a String containing all the information of the book.
     */
    public String toString()
    {
        return myId + "\n" + title + "\n" + authorNamesToString() + "\n" + publisher + "\n" + publicationYear + "\n" + subject + "\n" + "Is checked out: " + isCheckedOut;
    }
}
