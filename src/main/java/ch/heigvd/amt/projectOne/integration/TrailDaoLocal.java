package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.Trail;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TrailDaoLocal {

    Trail trail(long id);
    List<Trail> allTrail();
    List<Trail> allTrailToComeWithNoReg(long idUser);
    boolean addTrail(Trail trail);
    boolean updateTrail(Trail trail);
    boolean deleteTrail(long id);
    int getNumberOfTrails();
    List<Trail> findTrail(int currentPage, int trailPerPage);
}
