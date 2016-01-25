package ProgAssignment4;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Holds all the methods needed for a session used by a librarian.
 *
 * @author Matthew Gordon
 * @version v1.0
 * @since 12/10/2014
 */
public class LibrarianSession extends Session
{
    Scanner keyboard;

    public LibrarianSession(String libraryID, LibrarySystem ls)
    {
        super(libraryID, ls);
        keyboard = new Scanner(System.in);
    }

    /**
     * Prompts the user to choose an action and runs the appropriate method.
     */
    public void runQuery()
    {
        boolean done = false;
        while(!done) {
            String choice;
            System.out.println("Enter your choice(as a number):");
            System.out.println("1) List a user's information");
            System.out.println("2) Change a user's blocked status");
            System.out.println("3) List all user's with at least one overdue book");
            System.out.println("4) Log Out");
            choice = keyboard.next();

            if (choice.equals("1"))
                findUser();
            else if(choice.equals("2"))
                changeStatus();
            else if(choice.equals("3"))
                listOverdue();
            else {
                done = true;
                System.out.println("Logged out");
            }

            System.out.println();
        }
    }

    /**
     * prompts the user to enter a library ID and finds the corresponding user.
     */
    public void findUser()
    {
        System.out.println("You chose to list a user's information\n");
        String searchID;
        System.out.print("Enter user's library ID: ");
        searchID = keyboard.next();
        System.out.print(librarySystem.findUser(searchID));
    }


    /**
     * prompts the user to enter a library ID and what they want to change the blocked status to.
     */
   public void changeStatus()
   {
       System.out.println("You chose to change a user's blocked status");
        String searchID;
        System.out.print("Enter user's library ID: ");
        searchID = keyboard.next();
        User changeUser = librarySystem.findUser(searchID);
        if(changeUser instanceof CanCheckOutBooks)
        {
            CanCheckOutBooks ccob = (CanCheckOutBooks)changeUser;
            System.out.println("Enter new blocked status(blocked/unblocked)");
            String status = keyboard.next();
            if(status.equalsIgnoreCase("unblocked")) {
                System.out.println("You chose to unblock this user");
                ccob.setBlockedStatus(false);
            }
            else if(status.equalsIgnoreCase("blocked")) {
                System.out.println("you chose to block this user");
                ccob.setBlockedStatus(true);
            }
            else
                System.out.println("Invalid status");
        }
        else
            System.out.println("Please Enter a non-librarian ID");
   }

    /**
     * lists all users that have at least one overdue book.
     */
    public void listOverdue()
    {
        System.out.println("You chose to list all user's with at least one overdue book\n");
        ArrayList<User> users = librarySystem.getUsers().getUsers();
        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i) instanceof CanCheckOutBooks)
            {
                CanCheckOutBooks ccob = (CanCheckOutBooks)users.get(i);
                if(ccob.overDue(year, month, day) > 0)
                {
                    System.out.println(ccob);
                }
            }
        }
    }

}
