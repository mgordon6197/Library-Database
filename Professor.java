package ProgAssignment4;

import java.util.ArrayList;

/**
 * contains all the information and methods needed for a professor in the library.
 *
 * @author Matthew Gordon
 * @version v1.0
 * @since 12/10/2014
 */
public class Professor extends User implements CanCheckOutBooks
{
    public static final int MAX_CHECKOUT_DAYS = 20;
    private ArrayList<String> checkedOutBooks;
    private ArrayList<String> checkedOutDates;
    private boolean isBlocked;

    public Professor() {
        super();
        isBlocked = false;
        checkedOutBooks = new ArrayList<String>();
        checkedOutDates = new ArrayList<String>();
    }

    public Professor(String libraryID, String PIN, String name, String address, ArrayList<String> checkedOutBooks, String phone) {
        super(name, address, libraryID, PIN, phone);
        this.checkedOutBooks = checkedOutBooks;
    }

    /**
     * returns the maximum days a professor can check out a book.
     * @return MAX_CHECKOUT_DAYS
     */
    public int getMaxDaysCheckOut()
    {
        return MAX_CHECKOUT_DAYS;
    }

    /**
     * checks whether the professor is blocked or not.
     * @return isBlocked
     */
    public boolean isBlocked()
    {
        return isBlocked;
    }

    /**
     * sets isBlocked to the passed boolean
     * @param bool
     */
    public void setBlockedStatus(boolean bool)
    {
        isBlocked = bool;
    }

    /**
     * returns the passed magic number as a book.
     * @param book
     */
    public boolean returnBook(String book)
    {
        int i = 0;
        for(; i < checkedOutBooks.size(); i++)
        {
            if(book.equalsIgnoreCase(checkedOutBooks.get(i)))
            {
                checkedOutBooks.remove(i);
                checkedOutDates.remove(i);
                return true;
            }
        }

        if(i >= checkedOutBooks.size())
            System.out.println("Book hasn't been checked out by this user");

        return false;
    }

    /**
     * checks out the passed magic number as a book.
     * @param book
     * @param date
     */
    public boolean checkOutBook(String book, String date)
    {
        boolean found = false;
        for(int i = 0; i < checkedOutBooks.size(); i++)
        {
            if(checkedOutBooks.get(i).equalsIgnoreCase(book))
                found = true;
        }
        if(!found) {
            checkedOutBooks.add(book);
            checkedOutDates.add(date);
        }
        else
            System.out.println("Already checked out that book");
        return found;
    }

    public String toString()
    {
        String str = "";
        for(int i = 0; i < checkedOutBooks.size(); i++)
        {
            int [] arr = convertToDueDate(checkedOutDates.get(i));
            String otherStr = arr[0] + "/" + arr[1] + "/" + arr[2];
            str = str + checkedOutBooks.get(i) +  " Due Date: " + otherStr + "\n";
        }

        return "Library ID: " + getLibraryID() + "\n" + "PIN #: " + getPIN() + "\n" + "Name: " + getName() + "\n" + "Address" +
                getAddress() + "\n"  + "Phone #: " + getPhone() + "\n" + str + "Blocked Status: " + isBlocked + "\n";
    }

    /**
     * returns a list of due dates for the checked out books.
     * @return
     */
    private ArrayList<int[]> getDueDates()
    {
        ArrayList<int[]> list = new ArrayList<int[]>();
        for(int i = 0; i < checkedOutDates.size(); i++)
        {
            list.add(convertToDueDate(checkedOutDates.get(i)));
        }

        return list;
    }

    /**
     * returns the number of overdue books this user has.
     * @param year
     * @param month
     * @param day
     * @return count
     */
    public int overDue(int year, int month, int day)
    {
        int count = 0;
        int currentDays = year * 366 + month * 31 + day;
        ArrayList<int[]> checkedOut = getDueDates();
        for(int i = 0; i < checkedOut.size(); i++)
        {
            int [] checkOutDate = checkedOut.get(i);
            int checkedOutDays = checkOutDate[0] * 366 + checkOutDate[1] * 31 + checkOutDate[2];
            if(checkedOutDays + MAX_CHECKOUT_DAYS < currentDays)
                count++;
        }

        return count;
    }

    /**
     * converts the passed checked out date in the form "YYYY/MM/DD" to the due date.
     * @param checkOutDate
     * @return dueDate
     */
    private int [] convertToDueDate(String checkOutDate)
    {
        //parses the string
        int year, month, day, daysInMonth;
        String [] strArr = checkOutDate.split("/");
        year = Integer.parseInt(strArr[0]);
        month = Integer.parseInt(strArr[1]);
        day = Integer.parseInt(strArr[2]);

        //checks for leap year and assigns the number od days the current month has.
        if(month == 2 && isLeapYear(year))
            daysInMonth = 29;
        else if (month == 2)
            daysInMonth = 28;
        else if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
            daysInMonth = 31;
        else
            daysInMonth = 30;

        //adds the maximum check out days according to the current month if it goes over the total days in the month.
        if(day + MAX_CHECKOUT_DAYS > daysInMonth) {
            day = MAX_CHECKOUT_DAYS - (daysInMonth - day);
            month++;
            month = month % 12;
            if(month == 0)
                month = 12;
            else if(month == 1)
                year++;
        }
        else
            day += MAX_CHECKOUT_DAYS;

        int [] dueDate = {year, month, day};
        return dueDate;
    }

    /**
     * checks if the passed year is a leap year
     * @param year
     * @return bool
     */
    private boolean isLeapYear(int year)
    {
        boolean bool = false;

        if(year % 4 == 0) {
            if (year % 100 != 0)
                bool = true;
            else if(year % 400 == 0)
                bool = true;
        }

        return bool;
    }
}
