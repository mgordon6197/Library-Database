package ProgAssignment4;

import java.util.ArrayList;

/**
 * uses the factory pattern in creating an appropriate session.
 *
 * @author Matthew Gordon
 * @version v1.0
 * @since 12/10/2014
 */
public class SessionFactory
{
    /**
     * returns a session object according to which kind of user was passed.
     * @param user
     * @param ls
     */
    public Session createSession(User user, LibrarySystem ls)
    {
        if(user instanceof Student || user instanceof Professor)
            return new CanCheckOutBooksSession(user.getLibraryID(), ls);
        else if(user instanceof Librarian)
            return new LibrarianSession(user.getLibraryID(), ls);
        else
            return null;
    }
}
