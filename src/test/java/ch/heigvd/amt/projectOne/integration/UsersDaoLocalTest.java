package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.User;
import org.arquillian.container.chameleon.deployment.api.DeploymentParameters;
import org.arquillian.container.chameleon.deployment.maven.MavenBuild;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.DuplicateKeyException;
import javax.ejb.EJB;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class UsersDaoLocalTest {


    @EJB
    UsersDaoLocal usersDao;


    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAndRetrieveAUserViaTheUsersDAO() throws DuplicateKeyException {

        User lio = new User("lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        long idLio = usersDao.addUser(lio);
        User lioRetrieve = usersDao.user(idLio);
        assertEquals(lio.getEmail(), lioRetrieve.getEmail());
    }


  @Test
  @Transactional(TransactionMode.COMMIT)
    public void itShouldBePossibleToDeleteAUser() throws DuplicateKeyException{
        User gui = new User("Guillaume", "Blanco", "19-06-1994", "gui@amt.ch", "guillaume");
        long idGui = usersDao.addUser(gui);
        assertNotNull(usersDao.user(idGui));
        usersDao.deleteUser(idGui);
        assertNull(usersDao.user(idGui));
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToConnectAUser() throws DuplicateKeyException{
        User gui = new User("Guillaume", "Blanco", "19-06-1994", "gui@co.ch", "guillaume");
        usersDao.addUser(gui);
        User connectUser = usersDao.connect("gui@co.ch","guillaume");
        assertNotNull(usersDao.user(connectUser.getId()));
        assertEquals("Guillaume",connectUser.getFirstName());
    }


    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToUpdateAUser() throws DuplicateKeyException {

        User lio = new User("lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        long idLio = usersDao.addUser(lio);
        User newLio = new User(idLio,"lio",lio.getLastName(), lio.getDateOfBirth(), lio.getEmail(), lio.getPassword());
        usersDao.updateUser(newLio);
        User lioModified = usersDao.user(idLio);
        assertEquals(lio.getEmail(), lioModified.getEmail());
        assertNotEquals(lio.getFirstName(), lioModified.getFirstName());
    }


}