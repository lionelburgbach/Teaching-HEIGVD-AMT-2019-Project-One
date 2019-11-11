package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.Registration;
import ch.heigvd.amt.projectOne.model.Trail;
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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class TrailDaoLocalTest {

    @EJB
    TrailDaoLocal trailDao;

    @EJB
    UsersDaoLocal userDao;

    @EJB
    RegistrationDaoLocal regDao;

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAndRetrieveASpecificTrail() throws DuplicateKeyException, SQLException {
        Trail newTrail = new Trail("name",200,300, "description", "20-11-2020");
        long idTrail = trailDao.addTrail(newTrail);
        Trail trailRetrieve = trailDao.trail(idTrail);
        assertEquals(newTrail.getName(), trailRetrieve.getName());
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToRetreiveSomeTrails() throws DuplicateKeyException, SQLException {
        Trail newTrail = new Trail("name",200,300, "description", "20-11-2020");
        trailDao.addTrail(newTrail);
        Trail newTrail2 = new Trail("name2",200,300, "description", "20-11-2020");
        trailDao.addTrail(newTrail2);
        List<Trail> lTrail = trailDao.allTrail();
        assertEquals(newTrail.getName(), lTrail.get(0).getName());
        assertEquals(newTrail2.getName(), lTrail.get(1).getName());
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToRetriveTrailWithPagination() throws DuplicateKeyException, SQLException {
        String name = "name";
        for(int i = 1; i < 6; i++) {
            name += i;
            Trail newTrail = new Trail(name, 200, 300, "description", "20-11-2020");
            trailDao.addTrail(newTrail);
            name = name.substring(0,4);
        }
        List<Trail> lTrail = trailDao.allTrailPagination(1,2);
        assertEquals(lTrail.get(0).getName(),"name1");
        assertEquals(lTrail.get(1).getName(),"name2");
        List<Trail> lTrail2 = trailDao.allTrailPagination(2,2);
        assertEquals(lTrail2.get(0).getName(),"name3");
        assertEquals(lTrail2.get(1).getName(),"name4");
    }


    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToRetreiveAndCountTrailWithoutRegistration() throws DuplicateKeyException, SQLException {
        Trail newTrail = new Trail("name",200,300, "description", "20-11-2020");
        trailDao.addTrail(newTrail);
        Trail newTrail2 = new Trail("name2",200,300, "description", "20-11-2020");
        long idTrail2 = trailDao.addTrail(newTrail2);
        User lio = new User("lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        long idUser = userDao.addUser(lio);
        Registration reg = new Registration(userDao.user(idUser), trailDao.trail(idTrail2));
        regDao.addReg(reg);
        List<Trail> lTrail = trailDao.allTrailToComeWithNoReg(idUser);
        assertEquals(newTrail.getName(), lTrail.get(0).getName());
        assertEquals(lTrail.size(),1);
        assertEquals(trailDao.getNumberOfTrailsToComeWithNoReg(idUser),1);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToRetriveTrailWithPaginationAndWithoutRegistration() throws DuplicateKeyException, SQLException {
        Trail TrailWithReg = new Trail("name",200,300, "description", "20-11-2020");
        long idTrailWithReg = trailDao.addTrail(TrailWithReg);
        String name = "name";
        for(int i = 1; i < 6; i++) {
            name += i;
            Trail newTrail = new Trail(name, 200, 300, "description", "20-11-2020");
            trailDao.addTrail(newTrail);
            name = name.substring(0,4);
        }
        User lio = new User("lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        long idUser = userDao.addUser(lio);
        Registration reg = new Registration(userDao.user(idUser), trailDao.trail(idTrailWithReg));
        regDao.addReg(reg);

        // We can see that we don't take the first create trail because he has a registration
        List<Trail> lTrail = trailDao.allTrailToComeWithNoRegPagination(idUser,1,5);
        assertEquals(lTrail.get(0).getName(),"name1");
        assertEquals(lTrail.get(1).getName(),"name2");
        assertEquals(lTrail.get(2).getName(),"name3");
        assertEquals(lTrail.get(3).getName(),"name4");
        assertEquals(lTrail.get(4).getName(),"name5");
        assertEquals(lTrail.size(),5);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCountTrails() throws DuplicateKeyException, SQLException {
        Trail newTrail = new Trail("name",200,300, "description", "20-11-2020");
        trailDao.addTrail(newTrail);
        Trail newTrail2 = new Trail("name2",200,300, "description", "20-11-2020");
        trailDao.addTrail(newTrail2);
        assertEquals(2,trailDao.getNumberOfTrails());
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToUpdateATrail() throws DuplicateKeyException, SQLException {
        Trail newTrail = new Trail("name",200,300, "description", "20-11-2020");
        long idTrail = trailDao.addTrail(newTrail);
        Trail updateTrail = new Trail(idTrail,"name2",newTrail.getDistance(),newTrail.getUpAndDown(),newTrail.getDescription(), newTrail.getDate());
        trailDao.updateTrail(updateTrail);
        assertEquals("name2",trailDao.trail(idTrail).getName());
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToDeleteATrail() throws DuplicateKeyException, SQLException {
        Trail newTrail = new Trail("name",200,300, "description", "20-11-2020");
        long idTrail = trailDao.addTrail(newTrail);
        assertNotNull(trailDao.trail(idTrail));
        trailDao.deleteTrail(idTrail);
        assertNull(trailDao.trail(idTrail));
    }
}