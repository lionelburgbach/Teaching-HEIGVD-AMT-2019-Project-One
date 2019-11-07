package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.Trail;
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


    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateATrail() throws DuplicateKeyException, SQLException {
        Trail newTrail = new Trail("name",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail);
        List<Trail> lTrail = trailDao.allTrail();
        assertEquals(newTrail.getName(), lTrail.get(0).getName());
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToRetreiveSomeTrails() throws DuplicateKeyException, SQLException {
        Trail newTrail = new Trail("name",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail);
        Trail newTrail2 = new Trail("name2",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail2);
        List<Trail> lTrail = trailDao.allTrail();
        assertEquals(newTrail.getName(), lTrail.get(0).getName());
        assertEquals(newTrail2.getName(), lTrail.get(1).getName());
    }

    // TODO Ajouter un avec registration pour comparer
  /*  @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToRetreiveTrailWithoutRegistration() throws DuplicateKeyException, SQLException {
        Trail newTrail = new Trail("name",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail);
        List<Trail> lTrail = trailDao.allTrailToComeWithNoReg(0);
        assertEquals(newTrail.getName(), lTrail.get(0).getName());
    }*/

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToRetrieveATrail() throws DuplicateKeyException, SQLException {
        Trail newTrail = new Trail("name",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail);
        List<Trail> lTrail = trailDao.allTrail();
        Trail retrieveTrail = trailDao.trail(lTrail.get(0).getId());
        assertEquals(newTrail.getName(), retrieveTrail.getName());
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCountTrail() throws DuplicateKeyException, SQLException {
        Trail newTrail = new Trail("name",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail);
        Trail newTrail2 = new Trail("name2",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail2);
        assertEquals(2,trailDao.getNumberOfTrails());
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToUpdateATrail() throws DuplicateKeyException, SQLException {
        Trail newTrail = new Trail("name",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail);
        List<Trail> lTrail = trailDao.allTrail();
        long id = lTrail.get(0).getId();
        Trail updateTrail = new Trail(id,"name2",newTrail.getDistance(),newTrail.getUpAndDown(),newTrail.getDescription(),newTrail.getCapacity(), newTrail.getDate());
        trailDao.updateTrail(updateTrail);
        String nameUpdate = trailDao.trail(id).getName();
        assertEquals("name2",nameUpdate);
    }

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToDeleteATrail() throws DuplicateKeyException, SQLException {
        Trail newTrail = new Trail("name",200,300, "description",20, "20-11-2020");
        trailDao.addTrail(newTrail);
        List<Trail> lTrail = trailDao.allTrail();
        long id = lTrail.get(0).getId();
        assertNotNull(trailDao.trail(id));
        trailDao.deleteTrail(id);
        assertNull(trailDao.trail(id));
    }



    // TODO TESTER findTrail Demander Lio

}