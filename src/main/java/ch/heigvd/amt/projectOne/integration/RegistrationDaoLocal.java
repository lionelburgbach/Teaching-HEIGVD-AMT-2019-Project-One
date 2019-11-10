package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.Registration;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RegistrationDaoLocal {

    /**
     * Return the registraion for a User for a Trail
     * @param idUser    id of the user
     * @param idTrail   id of the trail
     * @return the registration of a user for a trail
     */
    Registration registration(long idUser, long idTrail);

    /**
     * Return all registration of the user
     * @param idUser    id of the user
     * @return a list of all registration for a user
     */
    List<Registration> allRegUser(long idUser);

    /**
     * Return the number of registrion for a user
     * @param idUser    id of the user
     * @return the number of registration for a user
     */
    int getNumberOfRegsUser(long idUser);

    /**
     * Return some registration for a user for pagination
     * @param idUser            id of the user
     * @param currentPage       current page
     * @param elementPerPage    number of element per page
     * @return a list of registration for a user with pagination
     */
    List<Registration> allRegUserPagination(long idUser, int currentPage, int elementPerPage);

    /**
     * Return all registration for a trail
     * @param idTrail  id of the trail
     * @return a list of all registration for a trail
     */
    List<Registration> allRegTrail(long idTrail);

    /**
     * Return the number of registrion for a trail
     * @param idTrail   id of the trail
     * @return the number of registration for a trail
     */
    int getNumberOfRegsTrail(long idTrail);

    /**
     * Return some registration for a trail for pagination
     * @param idTrail           id of the trail
     * @param currentPage       current page
     * @param elementPerPage    number of element per page
     * @return a list of registration for a trail with pagination
     */
    List<Registration> allRegTrailPagination(long idTrail, int currentPage, int elementPerPage);

    /**
     * Add a registration
     * @param reg   the registration
     * @return the id of the registration
     */
    long addReg(Registration reg);

    /**
     * Delete a registration
     * @param id    id of the registration
     * @return boolean if the registration has been delete or not
     */
    boolean deleteReg(long id);
}
