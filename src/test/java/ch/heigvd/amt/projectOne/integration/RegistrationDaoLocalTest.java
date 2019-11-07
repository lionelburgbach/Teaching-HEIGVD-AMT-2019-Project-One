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
    public void itShouldBePossibleToCreateARegistration() throws DuplicateKeyException, SQLException {
        User lio = new User(0,"lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        userDao.addUser(lio);
        Trail newTrail = new Trail("name",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail);
        List<Trail> lTrail = trailDao.allTrail();
        User lioLoaded = userDao.connect("amt@amt.ch", "lionel");
        Registration reg = new Registration(lioLoaded,lTrail.get(0));
        regDao.addReg(reg);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAndRetrieveARegistration() throws DuplicateKeyException, SQLException {
        User lio = new User(0,"lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        userDao.addUser(lio);
        Trail newTrail = new Trail("name",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail);
        List<Trail> lTrail = trailDao.allTrail();
        User lioLoaded = userDao.connect("amt@amt.ch", "lionel");
        long idTrail = lTrail.get(0).getId();
        long idUser = lioLoaded.getId();
        Registration reg = new Registration(lioLoaded,lTrail.get(0));
        regDao.addReg(reg);
        Registration retrReg = regDao.registration(idUser,idTrail);
        assertEquals(retrReg.getUser().getFirstName(),lio.getFirstName());
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToRetrieveAllRegOfOneUser() throws DuplicateKeyException, SQLException {
        User lio = new User(0,"lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        userDao.addUser(lio);
        User gui = new User(0,"lionel","burgbacher", "05-03-1989", "gui@amt.ch", "lionel");
        userDao.addUser(gui);
        Trail newTrail = new Trail("name",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail);
        Trail newTrail2 = new Trail("name2",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail2);
        List<Trail> lTrail = trailDao.allTrail();
        User lioLoaded = userDao.connect("amt@amt.ch", "lionel");
        User guiLoaded = userDao.connect("gui@amt.ch", "lionel");
        Registration reg = new Registration(lioLoaded,lTrail.get(0));
        Registration reg2 = new Registration(lioLoaded,lTrail.get(1));
        Registration reg3 = new Registration(guiLoaded,lTrail.get(1));
        regDao.addReg(reg);
        regDao.addReg(reg2);
        regDao.addReg(reg3);
        List<Registration> lReg = regDao.allRegUser(lioLoaded.getId());
        assertEquals(lReg.get(0).getUser().getFirstName(),lio.getFirstName());
        assertEquals(lReg.get(1).getUser().getFirstName(),lio.getFirstName());
        assertEquals(lReg.size(),2);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToRetrieveAllRegOfOneTrail() throws DuplicateKeyException, SQLException {
        User lio = new User(0,"lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        userDao.addUser(lio);
        User gui = new User(0,"lionel","burgbacher", "05-03-1989", "gui@amt.ch", "lionel");
        userDao.addUser(gui);
        Trail newTrail = new Trail("name",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail);
        Trail newTrail2 = new Trail("name2",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail2);
        List<Trail> lTrail = trailDao.allTrail();
        User lioLoaded = userDao.connect("amt@amt.ch", "lionel");
        User guiLoaded = userDao.connect("gui@amt.ch", "lionel");
        Registration reg = new Registration(lioLoaded,lTrail.get(0));
        Registration reg2 = new Registration(lioLoaded,lTrail.get(1));
        Registration reg3 = new Registration(guiLoaded,lTrail.get(1));
        regDao.addReg(reg);
        regDao.addReg(reg2);
        regDao.addReg(reg3);
        List<Registration> lReg = regDao.allRegTrail(lTrail.get(0).getId());
        assertEquals(lReg.get(0).getTrail().getName(),lTrail.get(0).getName());
        assertEquals(lReg.size(),1);
    }

    @Test
   // @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToDeleteARegistration() throws DuplicateKeyException, SQLException {
        User lio = new User(0,"lionel","burgbacher", "05-03-1989", "amt@amt.ch", "lionel");
        userDao.addUser(lio);
        Trail newTrail = new Trail("name",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail);
        List<Trail> lTrail = trailDao.allTrail();
        User lioLoaded = userDao.connect("amt@amt.ch", "lionel");
        long idTrail = lTrail.get(0).getId();
        long idUser = lioLoaded.getId();
        Registration reg = new Registration(lioLoaded,lTrail.get(0));
        regDao.addReg(reg);
        Registration retrReg = regDao.registration(idUser,idTrail);
        assertNotNull(retrReg);
        regDao.deleteReg(retrReg.getId());
        assertNull(retrReg);
    }

}