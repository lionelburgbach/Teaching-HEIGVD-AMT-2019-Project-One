package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.Trail;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TrailDaoLocal {

    Trail trail(long id);

    List<Trail> allTrail();
    int getNumberOfTrails();
    List<Trail> allTrailPagination(int currentPage, int elementPerPage);

    List<Trail> allTrailToComeWithNoReg(long idUser);
    int getNumberOfTrailsToComeWithNoReg(long idUser);
    List<Trail> allTrailToComeWithNoRegPagination(long idUser, int currentPage, int elementPerPage);

    boolean addTrail(Trail trail);
    boolean updateTrail(Trail trail);
    boolean deleteTrail(long id);
}
