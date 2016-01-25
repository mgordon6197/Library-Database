package ProgAssignment4;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * holds an arrayList of User objects and all methods needed to manipulate and read it.
 *
 * @author Matthew Gordon
 * @version v1.0
 * @since 12/10/2014
 */
public class UserDatabase {
    private ArrayList<User> users;

    public UserDatabase()
    {
        users = new ArrayList<User>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user)
    {
        users.add(user);
    }


    /**
     * sets the blocked status of the user with the passed library id to the passed boolean.
     * @param libraryId
     * @param status
     */
    public void setBlockedStatus(String libraryId, boolean status)
    {
        for (int k = 0; k < users.size(); k++) {
            if (libraryId.equalsIgnoreCase(users.get(k).getLibraryID())) {
                CanCheckOutBooks ccob = (CanCheckOutBooks)users.get(k);
                ccob.setBlockedStatus(true);
                break;
            }
        }
    }

    /**
     * searches for the user with the passed library ID and PIN # and returns it.
     * @param ID
     * @param PIN
     */
    public User authenticate(String ID, String PIN)
    {
        int i = 0;
        for(; i < users.size(); i++)
        {
            if(users.get(i).getLibraryID().equals(ID))
                break;
        }

        if(i < users.size() && users.get(i).getPIN().equals(PIN))
            return users.get(i);
        else
            return null;
    }

    /**
     * returns a book with the passed magic number from the user with the passed library ID and
     * @param libraryId
     * @param magicNum
     * @return value
     */
    public boolean returnBook(String libraryId, String magicNum)
    {
        boolean value = false;
        for (int k = 0; k < users.size(); k++) {
            if (libraryId.equalsIgnoreCase(users.get(k).getLibraryID())) {
                CanCheckOutBooks ccob = (CanCheckOutBooks)users.get(k);
                value = ccob.returnBook(magicNum);
                break;
            }
        }
        return value;
    }

    /**
     * checks out the book with the passed library ID o the passed date
     * from the user with the passed library ID.
     * @param libraryId
     * @param magicNum
     * @param checkOutDate
     */
    public boolean checkOutBook(String libraryId, String magicNum, String checkOutDate)
    {
        boolean found = false;
        for (int k = 0; k < users.size(); k++) {
            if (libraryId.equalsIgnoreCase(users.get(k).getLibraryID())) {
                CanCheckOutBooks ccob = (CanCheckOutBooks)users.get(k);
                if(ccob.checkOutBook(magicNum, checkOutDate))
                    found = true;
            }
        }
        return found;
    }


    /**
     * checks whether the user with the passed library ID is blocked or not.
     * @param libraryID
     * @return value
     */
    public boolean isBlocked(String libraryID)
    {
        boolean value = false;
        for(int i = 0; i < users.size(); i++)
        {
            if(libraryID.equals(users.get(i).getLibraryID())) {
                CanCheckOutBooks ccob = (CanCheckOutBooks)users.get(i);
                value = ccob.isBlocked();
            }
        }

        return value;
    }

    /**
     * shows the info of the user with the passed library ID
     * @param id
     */
    public void showInfo(String id)
    {
        for(int i = 0; i < users.size(); i++)
        {
            if(id.equals(users.get(i).getLibraryID())) {
                System.out.println(users.get(i));
                break;
            }
        }
    }

    /**
     * finds and returns the user with the passed library ID
     * @param searchID
     */
    public User findUser(String searchID)
    {
        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getLibraryID().equals(searchID))
                return users.get(i);
        }

        System.out.println("Could not find user");
        return null;
    }
}
