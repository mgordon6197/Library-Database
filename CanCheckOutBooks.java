package ProgAssignment4;

public interface CanCheckOutBooks {
    int overDue(int year, int month, int day);
    boolean returnBook(String book);
    boolean checkOutBook(String book, String date);
    int getMaxDaysCheckOut();
    void setBlockedStatus(boolean bool);
    boolean isBlocked();
}
