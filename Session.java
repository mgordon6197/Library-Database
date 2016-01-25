package ProgAssignment4;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * contains the generic values and methods every session will have.
 *
 * @author Matthew Gordon
 * @version v1.0
 * @since 12/10/2014
 */
public abstract class Session
{
    protected String libraryID;
    protected LibrarySystem librarySystem;
    protected int year, month, day;
    protected String todaysDate;

    protected Session(String id, LibrarySystem ls) {
        libraryID = id;
        librarySystem = ls;
        Scanner keyboard = new Scanner(System.in);

        System.out.print("What is today's year? ");
        year = keyboard.nextInt();
        System.out.print("What is today's month? ");
        month = keyboard.nextInt();
        System.out.print("What is today's day? ");
        day = keyboard.nextInt();
        todaysDate = year + "/" + month + "/" + day;
        System.out.println();

        checkForUsersToBeBlocked();
    }

    /*
    getters and setters for the instance variables
     */
    public LibrarySystem getLibrarySystem()
    {
        return librarySystem;
    }

    public String getTodaysDate() {
        return todaysDate;
    }

    public void setTodaysDate(String todaysDate) {
        this.todaysDate = todaysDate;
    }

    public String getLibraryID() {
        return libraryID;
    }

    public void setID(String id) {
       libraryID = id;
    }

    public abstract void runQuery();

    /**
     * checks for users who have overdue books and blocks them.
     */
    private void checkForUsersToBeBlocked()
    {
        for(int i = 0; i < librarySystem.getUsers().getUsers().size(); i++)
        {
            if(librarySystem.getUsers().getUsers().get(i) instanceof CanCheckOutBooks)
            {
                CanCheckOutBooks newUser = (CanCheckOutBooks)librarySystem.getUsers().getUsers().get(i);
                if(newUser.overDue(year, month, day) > 0)
                    newUser.setBlockedStatus(true);
            }
        }
    }

}