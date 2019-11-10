package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.Trail;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TrailDaoLocal {

    /**
     *
     * @param id of the trail that we want to retrieve
     * @return the trail corresponding to the id
     */
    Trail trail(long id);

    /**
     *
     * @return a list who contain all the trails
     */
    List<Trail> allTrail();

    /**
     *
     * @return the number of all trails
     */
    int getNumberOfTrails();

    /**
     *
     * @param currentPage who represent the page where is the client actually
     * @param elementPerPage who represent the number of trail that we want to retrieve
     * @return a list of trail who contain the number of elementPerPage trail for the currentPage
     */
    List<Trail> allTrailPagination(int currentPage, int elementPerPage);

    /**
     *
     * @param idUser represent the id of the user what we use to check the registration (if he is in)
     * @return all the trail who not contain a registration with this user
     */
    List<Trail> allTrailToComeWithNoReg(long idUser);

    /**
     *
     * @param idUser represent the id of the user what we use to check the registration (if he is in)
     * @return the number of trail who not contain a registration with this user
     */
    int getNumberOfTrailsToComeWithNoReg(long idUser);

    /**
     *
     * @param idUser represent the id of the user what we use to check the registration (if he is in)
     * @param currentPage who represent the page where is the client actually
     * @param elementPerPage who represent the number of trail that we want to retrieve
     * @return a list of trail who contain the number of elementPerPage trail for the currentPage and that we have no registration with the user
     */
    List<Trail> allTrailToComeWithNoRegPagination(long idUser, int currentPage, int elementPerPage);

    /**
     *
     * @param trail that we want to add to the DB
     * @return the id of the trail
     */
    long addTrail(Trail trail);

    /**
     *
     * @param trail that we want to modify (need that the ID is already set in the trail)
     * @return a boolean who indicate if the change passed
     */
    boolean updateTrail(Trail trail);

    /**
     *
     * @param id of the trail that we want to delete
     * @return a boolean who indicate if the suppression passed
     */
    boolean deleteTrail(long id);
}
