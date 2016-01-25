package ProgAssignment4;

/**
 * Holds all the values and methods generic to a user.
 *
 * @author Matthew Gordon
 * @version v1.0
 * @since 12/10/2014
 */
public abstract class User
{
    private String name;
    private String address;
    private String libraryID;
    private String PIN;
    private String phone;

    public User()
    {
        name = "";
        address = "";
        libraryID = "";
        PIN = "";
        phone = "";
    }

    public User(String name, String address, String libraryID, String PIN, String newPhone) {
        this.name = name;
        this.address = address;
        this.libraryID = libraryID;
        this.PIN = PIN;
        phone = newPhone;
    }

    /*
     * getters and setters for instance variables
     */
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLibraryID() {
        return libraryID;
    }

    public void setLibraryID(String libraryID) {
        this.libraryID = libraryID;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public abstract String toString();
}
