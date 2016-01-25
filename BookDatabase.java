package ProgAssignment4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains an arrayList of Book objects and methods to manipulate and read them.
 *
 * @author Matthew Gordon
 * @version v1.0
 * @since 12/10/2014
 */
public class BookDatabase {
    private ArrayList<Book> books;

    public BookDatabase()
    {
        books = new ArrayList<Book>();
    }

    /**
     * getter for the arraylist of books..
     * @return
     */
    public ArrayList<Book> getBooks()
    {
        return books;
    }

    /**
     * Adds the passed book variable into the arraylist.
     * @param book
     */
    public void addBook(Book book)
    {
        books.add(book);
    }

    /**
     * sets the Book variable in the list, that matches the passed Book variable, checked out status to true.
     * For all purposes, we are checking out the book from the database.
     * @param magicNum
     */
    public boolean checkOutBook(String magicNum) {
        int k = 0;
        for (; k < books.size(); k++) {
            if (magicNum.equalsIgnoreCase(books.get(k).getMyId().toString())) {
                books.get(k).setCheckedOut(true);
                break;
            }
        }

        if(k >= books.size()) {
            System.out.println("Book not found");
            return false;
        }
        else
            return true;
    }


    /**
     * sets the Book variable in the list, that matches the passed Book variable, checked out status to false.
     * For all purposes, we are returning the book to the database.
     * @param magicNum
     * @return value
     */
    public boolean returnBook(String magicNum)
    {
        boolean value = false;
        for (int k = 0; k < books.size(); k++) {
            if (magicNum.equalsIgnoreCase(books.get(k).getMyId().toString())) {
                books.get(k).setCheckedOut(false);
                value = true;
                break;
            }
        }
        return value;
    }

    /**
     * searches for a book that contains the keyword in any of it's fields and prints it.
     * @param keyword
     */
    public void search(String keyword)
    {
        ArrayList<Book> available = new ArrayList<Book>();
        ArrayList<Book> checkedOut = new ArrayList<Book>();
        int i = 0;
        //cycle through all of the books in the database
        for (; i < books.size(); i++)
        {
            //checks against all fields of the book.
            if (books.get(i).getMyId().toString().toLowerCase().contains(keyword)
                    || books.get(i).getTitle().toLowerCase().contains(keyword)
                    || books.get(i).getSubject().toLowerCase().contains(keyword)
                    || books.get(i).getPublicationYear().toLowerCase().contains(keyword)
                    || books.get(i).authorNamesToString().toLowerCase().contains(keyword))
            {
                if (!books.get(i).isCheckedOut())
                    available.add(books.get(i));
                else
                    checkedOut.add(books.get(i));
            }
        }

        //organizes the books according to availability and prints them. prints no books statement if there are none.
        if(available.size() == 0 && checkedOut.size() == 0)
            System.out.println("No books match the keyword");
        else
        {
            if(available.size() != 0) {
                System.out.println("Books available for checking out\n------------------------------------------------------------------------------");
                for (int k = 0; k < available.size(); k++)
                    System.out.println(available.get(k) + "\n");
            }

            if(checkedOut.size() != 0) {
                System.out.println("Books already checked out\n------------------------------------------------------------------------------");
                for (int k = 0; k < checkedOut.size(); k++)
                    System.out.println(checkedOut.get(k) + "\n");
            }
        }
        System.out.println();
    }
}
