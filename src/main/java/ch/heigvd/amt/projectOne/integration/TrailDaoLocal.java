package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.Trail;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TrailDaoLocal {

    /**
     * Return a trail
     * @param id    id of the trail
     * @return a trail with this id
     */
    Trail trail(long id);

    /**
     * Return all trail
     * @return a list who contain all trails
     */
    List<Trail> allTrail();

    /**
     * Return number of trail
     * @return the number of all trail
     */
    int getNumberOfTrails();

    /**
     * Return some trail with pagination
     * @param currentPage       current page
     * @param elementPerPage    number of element per page
     * @return some trail with pagination
     */
    List<Trail> allTrailPagination(int currentPage, int elementPerPage);

    /**
     * Return all trail with no registration for a user
     * @param idUser    id of the user
     * @return all the trail with no registration for a user
     */
    List<Trail> allTrailToComeWithNoReg(long idUser);

    /**
     * Return number of trail with no registration for a user
     * @param idUser    id of the user
     * @return number of trail with no registration for a user
     */
    int getNumberOfTrailsToComeWithNoReg(long idUser);

    /**
     * Return all trail with no registration for a user with pagination
     * @param idUser            id of the user
     * @param currentPage       current page
     * @param elementPerPage    number of element per page
     * @return all trail with no registration for a user with pagination
     */
    List<Trail> allTrailToComeWithNoRegPagination(long idUser, int currentPage, int elementPerPage);

    /**
     * Add a trail
     * @param trail trail to add
     * @return the id of the trail
     */
    long addTrail(Trail trail);

    /**
     * Update a trail
     * @param trail trail to modify
     * @return a boolean if the trail has been update or not
     */
    boolean updateTrail(Trail trail);

    /**
     * Delete a trail
     * @param id    if for the trail to delete
     * @return boolean if the trail has been delete or not
     */
    boolean deleteTrail(long id);
}
