package ProgAssignment4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains methods for a session of a user that can check out books. (Professor/Student)
 *
 * @author Matthew Gordon
 * @version v1.0
 * @since 12/10/2014
 */
public class CanCheckOutBooksSession extends Session
{
    Scanner keyboard = new Scanner(System.in);

    public CanCheckOutBooksSession(String id, LibrarySystem librarySystem) {
        super(id, librarySystem);
    }

    /**
     * Prompts the user to choose which action he or she wants to take and runs that method.
     */
    public void runQuery()
    {
        String choice;
        boolean done = false;
        while (done == false) {
            System.out.println("Enter your choice(as the number):");
            System.out.println("1) Search for a book");
            System.out.println("2) Check out a book using the magic number");
            System.out.println("3) Return a book using a magic number");
            System.out.println("4) Show your user information");
            System.out.println("5) Log out");
            choice = keyboard.next();

            if (choice.equals("1"))
                search();
            else if (choice.equals("2"))
                checkOutBook();
            else if (choice.equals("3"))
                returnBook();
            else if (choice.equals("4"))
                showInfo();
            else
            {
                System.out.println("Logged out");
                done = true;
            }
        }
    }

    /**
     * searches for a book that a user defined keyword matches any of the fields in that books description.
     */
    public void search()
    {
        System.out.println("You chose to search for a book");
        System.out.print("Enter keyword: ");
        String keyword = keyboard.next().toLowerCase();
        System.out.println();
        librarySystem.search(keyword);
    }

    /**
     * checks out a book from the database and into the current users information.
     */
    public void checkOutBook()
    {
        System.out.println("You chose to check out a book");
        System.out.println("Enter magic number");
        String magicNum = keyboard.next();
        librarySystem.checkOutBook(libraryID, magicNum, todaysDate);
    }

    /**
     * returns a book from the current user to the library.
     */
    private void returnBook()
    {
        System.out.println("You chose to return a book");
        System.out.println("Enter magic number");
        String magicNum = keyboard.next();
        librarySystem.returnBook(libraryID, magicNum);
    }

    /**
     * uses the users toString() method to print out the current users information
     */
    public void showInfo()
    {
        librarySystem.showInfo(libraryID);
    }
}
