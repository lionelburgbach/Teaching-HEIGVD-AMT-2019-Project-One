package ch.heigvd.amt.projectOne.services.dao;

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
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class UsersDaoLocalTest {


    @EJB
    UsersDaoLocal usersDao;

    @Test
    @Transactional(TransactionMode.COMMIT)
    public void itShouldBePossibleToCreateAUser() throws DuplicateKeyException, SQLException {

        usersDao.addUser( "amt@amt.ch", "lionel", "lionel","burgbacher", "05-03-1989");
    }

    /*
    @Test
    @Transactional(TransactionMode.COMMIT)
    public void itShouldBePossibleToCreateAndRetrieveAUserViaTheUsersDAO() throws DuplicateKeyException {

        User lio = new User(0, "amt@amt.ch", "lionel", "lionel","burgbacher", "05-03-1989");
        usersDao.addUser( "lionel", "burgbacher", "05-03-1989","amt@amt.ch", "lionel");
        User lioLoaded = usersDao.connect("amt@amt.ch", "lionel");
        assertSame(lio, lioLoaded);
    }
     */
}