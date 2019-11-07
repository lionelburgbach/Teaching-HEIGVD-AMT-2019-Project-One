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
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class UsersDaoLocalTest {


    @EJB
    UsersDaoLocal usersDao;

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void itShouldBePossibleToCreateAUser() throws DuplicateKeyException, SQLException {

        User lio = new User(0,"lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        usersDao.addUser(lio);
    }

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void itShouldBePossibleToCreateAndRetrieveAUserViaTheUsersDAO() throws DuplicateKeyException {

        User lio = new User("lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        usersDao.addUser(lio);
        User lioLoaded = usersDao.connect("amt@amt.ch", "lionel");
        assertEquals(lio.getEmail(), lioLoaded.getEmail());
    }
}