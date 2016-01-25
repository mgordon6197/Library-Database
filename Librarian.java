package ProgAssignment4;

/**
 * Holds the methods and information unique to a librarian.
 *
 * @author Matthew Gordon
 * @version v1.0
 * @since 12/10/2014
 */
public class Librarian extends User
{
    public Librarian()
    {
        super();
    }

    public Librarian(String libraryID, String PIN, String name, String address, String phone)
    {
        super(name, address, libraryID, PIN, phone);
    }

    /**
     * returns the information of a librarian as a string.
     */
    public String toString()
    {
        return "Library ID: " + getLibraryID() + "\n" + "PIN #: " + getPIN() + "\n" + "Name: " + getName() + "\n" + "Address" +
                getAddress() + "\n"  + "Phone #: " + getPhone();
    }
}
