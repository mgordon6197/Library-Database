package ProgAssignment4;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Holds BookDatabase and UserDatabase objects and according methods to the database.
 *
 * 761831-7
 * mgordon@umail.ucsb.edu
 *
 * @author Matthew Gordon
 * @version 1.0
 * @since 12/10/2014
 */
public class LibrarySystem
{
    BookDatabase books;
    UserDatabase users;
    Scanner inputStream;

    public LibrarySystem()
    {
        getData();
    }

    public BookDatabase getBooks() {
        return books;
    }

    public UserDatabase getUsers() {
        return users;
    }

    /**
     * calls all the parsing methods from a file to store in the databse.
     */
    private void getData()
    {
        inputStream = null;
        try{
            inputStream = new Scanner(new File("library.data"));
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
            System.exit(0);
        }

        books = new BookDatabase();
        users = new UserDatabase();

        parseBooks();
        parseStudents();
        parseProfessors();
        parseLibrarians();

        inputStream.close();
    }

    /**
     * parses the books from a file and stores them.
     */
    private void parseBooks()
    {
        //skipping the first three lines.
        inputStream.nextLine();
        inputStream.nextLine();
        inputStream.nextLine();

        //declaring variables for the data that will be recieved
        String title;
        String publisher;
        String publicationYear;
        String subject;

        //will stop parsing when we hit "::::::::::"
        while(!inputStream.hasNext("::::::::::"))
        {
            //initializes the magic number
            ID myId = new ID();
            ArrayList<String> authorNames = new ArrayList<String>();
            myId.setMagicNumber(inputStream.nextLine());

            title = inputStream.nextLine();

            //parses and formats the names of the authors
            String authorString = inputStream.nextLine();
            String [] authorArray = authorString.split(",");
            for(int i = 0; i < authorArray.length; i++)
            {
                authorArray[i] = authorArray[i].trim();
                authorNames.add(authorArray[i]);
            }

            publisher = inputStream.nextLine();
            publicationYear = inputStream.nextLine();
            subject = inputStream.nextLine();

            //adds the book to the database
            books.addBook(new Book(myId, publisher, publicationYear, subject, authorNames, false, null, null, title));
        }
    }

    /**
     * parses the students from the file and stores them
     */
    private void parseStudents()
    {
        //skips the first three lines
        inputStream.nextLine();
        inputStream.nextLine();
        inputStream.nextLine();

        //will stop parsing when the file hits "::::::::::"
        while(!inputStream.hasNext("::::::::::")) {
            Student student = new Student();
            String libraryId = inputStream.nextLine();
            student.setLibraryID(libraryId);
            student.setPIN(inputStream.nextLine());
            student.setName(inputStream.nextLine());
            student.setAddress(inputStream.nextLine());
            student.setPhone(inputStream.nextLine());

            String bookString = inputStream.nextLine();
            String[] strArr = bookString.split(",");

            //checks out the book that was parsed according to the date that was given
            for (int i = 0; i < strArr.length; i++) {
                String[] arr = strArr[i].split(":");
                student.checkOutBook(arr[1], arr[0]);

                books.checkOutBook(arr[1]);
            }

            //sets the users status to blocked if there is one
            if (inputStream.hasNext("\\[Blocked\\]")) {
                student.setBlockedStatus(true);
                inputStream.nextLine();
            } else
                student.setBlockedStatus(false);

            users.addUser(student);
        }
    }


    /**
     * parses all the Professors into the database.
     */
    private void parseProfessors()
    {
        inputStream.nextLine();
        inputStream.nextLine();
        inputStream.nextLine();

        while(!inputStream.hasNext("::::::::::")) {
            Professor professor = new Professor();
            professor.setLibraryID(inputStream.nextLine());
            professor.setPIN(inputStream.nextLine());
            professor.setName(inputStream.nextLine());
            professor.setAddress(inputStream.nextLine());
            professor.setPhone(inputStream.nextLine());

            String bookString = inputStream.nextLine();
            String[] strArr = bookString.split(",");

            for (int i = 0; i < strArr.length; i++) {
                String[] arr = strArr[i].split(":");
                professor.checkOutBook(arr[1], arr[0]);

                books.checkOutBook(arr[1]);
            }

            //sets the users status to blocked if there is one
            if (inputStream.hasNext("\\[Blocked\\]")) {
                professor.setBlockedStatus(true);
                inputStream.nextLine();
            } else
                professor.setBlockedStatus(false);

            users.addUser(professor);
        }
    }


    /**
     * parses all the librarians into the database.
     */
    private void parseLibrarians()
    {
        inputStream.nextLine();
        inputStream.nextLine();
        inputStream.nextLine();

        while(!inputStream.hasNext("::::::::::") && inputStream.hasNext()) {
            Librarian librarian = new Librarian();
            librarian.setLibraryID(inputStream.nextLine());
            librarian.setPIN(inputStream.nextLine());
            librarian.setName(inputStream.nextLine());
            librarian.setAddress(inputStream.nextLine());
            librarian.setPhone(inputStream.nextLine());

            users.addUser(librarian);
        }
    }


    /**
     * creates a session according to the authenticated user.
     */
    public void runSession()
    {
        boolean done = false;
        Scanner keyboard = new Scanner(System.in);

        while(!done)
        {
            Session session = null;
            SessionFactory factory = new SessionFactory();
            User user;
            user = authenticate();//returns the correct user
            if(user != null) {
                System.out.println("Authorization Successful");
                session = factory.createSession(user, this);
                session.runQuery();
            }
            else
                System.out.println("Incorrect username and/or password");

            System.out.println("Would you like to log in another user?(y/n)");
            String str = keyboard.next();

            if(!str.equalsIgnoreCase("y"))
                done = true;
        }
    }

    /**
     * prompts the user for their library ID and PIN # and returns the corresponding user.
     * @return
     */
    private User authenticate()
    {
        Scanner keyboard = new Scanner(System.in);
        String ID, PIN;
        boolean found = false;

        System.out.print("Enter ID(User Name): ");
        ID = keyboard.next();
        System.out.print("Enter PIN #: ");
        PIN = keyboard.next();

        //calls the authenticate method within UserDatabase
        return users.authenticate(ID, PIN);
    }


    /**
     * searches for a book that contains the keyword in the BookDatabse.
     * @param keyword
     */
    public void search(String keyword)
    {
        books.search(keyword);
    }

    /**
     * returns a book to the databse with the passed magicNum from the user with the LibraryId.
     * @param libraryId
     * @param magicNum
     */
    public void returnBook(String libraryId, String magicNum)
    {
        boolean val2 = users.returnBook(libraryId, magicNum);
        boolean val1;

        //checks if the user had the book checked out already
        if (val2) {
            val1 = books.returnBook(magicNum);

            //checks if the book exists in the database
            if (!val1)
                System.out.println("Could not find book in database");
            else
                System.out.println("Successfully returned book");
        }
        else
            System.out.println("User hasn't checked this book out");

        System.out.println();
    }

    /**
     * checks a book out with the passed magic number, on the date passed, to the user with the passed library id.
     * @param libraryId
     * @param magicNum
     * @param todaysDate
     */
    public void checkOutBook(String libraryId, String magicNum, String todaysDate)
    {
        //makes sure the user isn't blocked
        if(!users.isBlocked(libraryId)) {
            if(books.checkOutBook(magicNum)) {
                if(!users.checkOutBook(libraryId, magicNum, todaysDate))
                    System.out.println("Successfully checked out book");
            }

        }
        else
            System.out.println("You are currently blocked");

        System.out.println();
    }

    /**
     * shows the info of the user with the passed libraryId.
     * @param id
     */
    public void showInfo(String id)
    {
        users.showInfo(id);
    }

    /**
     * returns the user with the passed library ID
     * @param searchID
     */
    public User findUser(String searchID)
    {
        return users.findUser(searchID);
    }
}