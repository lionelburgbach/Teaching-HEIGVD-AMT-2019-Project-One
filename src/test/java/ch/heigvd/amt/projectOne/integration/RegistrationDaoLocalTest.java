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
public class RegistrationDaoLocalTest {

    @EJB
    RegistrationDaoLocal regDao;

    @EJB
    UsersDaoLocal userDao;

    @EJB
    TrailDaoLocal trailDao;

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAndRetrieveARegistration() throws DuplicateKeyException, SQLException {
        User lio = new User(0,"lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        long idUser = userDao.addUser(lio);
        Trail newTrail = new Trail("name",200,300, "description", "20-11-2020");
        long idTrail = trailDao.addTrail(newTrail);
        Registration reg = new Registration(userDao.user(idUser),trailDao.trail(idTrail));
        regDao.addReg(reg);
        Registration retrReg = regDao.registration(idUser,idTrail);
        assertEquals(retrReg.getUser().getFirstName(),lio.getFirstName());
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToRetrieveAndCountAllRegOfOneUser() throws DuplicateKeyException, SQLException {
        User lio = new User(0,"lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        long idLio = userDao.addUser(lio);
        User gui = new User(0,"lionel","burgbacher", "05-03-1989", "gui@amt.ch", "lionel");
        long idGui = userDao.addUser(gui);
        Trail newTrail = new Trail("name",200,300, "description", "20-11-2020");
        long idTrail1 = trailDao.addTrail(newTrail);
        Trail newTrail2 = new Trail("name2",200,300, "description", "20-11-2020");
        long idTrail2 = trailDao.addTrail(newTrail2);
        Registration reg = new Registration(userDao.user(idLio),trailDao.trail(idTrail1));
        Registration reg2 = new Registration(userDao.user(idLio),trailDao.trail(idTrail2));
        Registration reg3 = new Registration(userDao.user(idGui),trailDao.trail(idTrail2));
        regDao.addReg(reg);
        regDao.addReg(reg2);
        regDao.addReg(reg3);
        List<Registration> lReg = regDao.allRegUser(idLio);
        assertEquals(lReg.get(0).getUser().getFirstName(),lio.getFirstName());
        assertEquals(lReg.get(1).getUser().getFirstName(),lio.getFirstName());
        assertEquals(lReg.size(),2);
        assertEquals(regDao.getNumberOfRegsUser(idLio),2);
        assertEquals(regDao.getNumberOfRegsUser(idGui),1);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToRetrieveAndCountAllRegOfOneTrail() throws DuplicateKeyException, SQLException {
        User lio = new User(0,"lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        long idLio = userDao.addUser(lio);
        User gui = new User(0,"lionel","burgbacher", "05-03-1989", "gui@amt.ch", "lionel");
        long idGui = userDao.addUser(gui);
        Trail newTrail = new Trail("name",200,300, "description", "20-11-2020");
        long idTrail1 = trailDao.addTrail(newTrail);
        Trail newTrail2 = new Trail("name2",200,300, "description", "20-11-2020");
        long idTrail2 = trailDao.addTrail(newTrail2);
        Registration reg = new Registration(userDao.user(idLio),trailDao.trail(idTrail1));
        Registration reg2 = new Registration(userDao.user(idLio),trailDao.trail(idTrail2));
        Registration reg3 = new Registration(userDao.user(idGui),trailDao.trail(idTrail2));
        regDao.addReg(reg);
        regDao.addReg(reg2);
        regDao.addReg(reg3);
        List<Registration> lReg = regDao.allRegTrail(idTrail1);
        assertEquals(lReg.get(0).getTrail().getName(),newTrail.getName());
        assertEquals(lReg.size(),1);
        assertEquals(regDao.getNumberOfRegsTrail(idTrail1),1);
        assertEquals(regDao.getNumberOfRegsTrail(idTrail2),2);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToRetrieveAllRegOfOneUserWithPagination() throws DuplicateKeyException, SQLException {
        User lio = new User(0,"lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        long idLio = userDao.addUser(lio);
        User gui = new User(0,"lionel","burgbacher", "05-03-1989", "gui@amt.ch", "lionel");
        long idGui = userDao.addUser(gui);
        Trail newTrail = new Trail("name",200,300, "description", "20-11-2020");
        long idTrail1 = trailDao.addTrail(newTrail);
        Trail newTrail2 = new Trail("name2",200,300, "description", "20-11-2020");
        long idTrail2 = trailDao.addTrail(newTrail2);
        Registration reg = new Registration(userDao.user(idLio),trailDao.trail(idTrail1));
        Registration reg2 = new Registration(userDao.user(idLio),trailDao.trail(idTrail2));
        Registration reg3 = new Registration(userDao.user(idGui),trailDao.trail(idTrail2));
        regDao.addReg(reg);
        regDao.addReg(reg3);
        regDao.addReg(reg2);
        List<Registration> lReg = regDao.allRegUserPagination(idLio,1,2);
        assertEquals(lReg.get(0).getUser().getFirstName(),lio.getFirstName());
        assertEquals(lReg.get(1).getUser().getFirstName(),lio.getFirstName());
        assertEquals(lReg.size(),2);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToRetrieveAllRegOfOneTrailWithPagination() throws DuplicateKeyException, SQLException {
        User lio = new User(0,"lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        long idLio = userDao.addUser(lio);
        User gui = new User(0,"lionel","burgbacher", "05-03-1989", "gui@amt.ch", "lionel");
        long idGui = userDao.addUser(gui);
        Trail newTrail = new Trail("name",200,300, "description", "20-11-2020");
        long idTrail1 = trailDao.addTrail(newTrail);
        Trail newTrail2 = new Trail("name2",200,300, "description", "20-11-2020");
        long idTrail2 = trailDao.addTrail(newTrail2);
        Registration reg = new Registration(userDao.user(idLio),trailDao.trail(idTrail1));
        Registration reg2 = new Registration(userDao.user(idLio),trailDao.trail(idTrail2));
        Registration reg3 = new Registration(userDao.user(idGui),trailDao.trail(idTrail2));
        regDao.addReg(reg);
        regDao.addReg(reg2);
        regDao.addReg(reg3);
        List<Registration> lReg = regDao.allRegTrailPagination(idTrail1,1,2);
        assertEquals(lReg.get(0).getTrail().getName(),newTrail.getName());
        assertEquals(lReg.size(),1);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToDeleteARegistration() throws DuplicateKeyException, SQLException {
        User lio = new User(0,"lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        long idUser = userDao.addUser(lio);
        Trail newTrail = new Trail("name",200,300, "description", "20-11-2020");
        long idTrail = trailDao.addTrail(newTrail);
        Registration reg = new Registration(userDao.user(idUser),trailDao.trail(idTrail));
        regDao.addReg(reg);
        assertNotNull(regDao.registration(idUser,idTrail));
        regDao.deleteReg(regDao.registration(idUser,idTrail).getId());
        assertNull(regDao.registration(idUser,idTrail));
    }
}