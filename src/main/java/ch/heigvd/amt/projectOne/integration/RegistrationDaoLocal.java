package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.Registration;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RegistrationDaoLocal {

    /**
     *
     * @param idUser is the id of the user who want to regitrate in the trail
     * @param idTrail is the id of the trail where the user want to registrate
     * @return the regitration of the user in the trail
     */
    Registration registration(long idUser, long idTrail);

    /**
     *
     * @param idUser ID that we use to check if the registration contain the user
     * @return a list of all registration of a user
     */
    List<Registration> allRegUser(long idUser);

    /**
     *
     * @param idUser ID that we use to check if the registration contain the user
     * @return the number of registration who contain the user
     */
    int getNumberOfRegsUser(long idUser);

    /**
     *
     * @param idUser ID that we use to check if the registration contain the user
     * @param currentPage who represent the page where is the client actually
     * @param elementPerPage who represent the number of registration that we want to retrieve
     * @return a list who contain the number of elementPerPage registrations who contain the user
     */
    List<Registration> allRegUserPagination(long idUser, int currentPage, int elementPerPage);

    /**
     *
     * @param idTrail ID that we use to check if the registration contain the trail
     * @return a list of all registration at a trail
     */
    List<Registration> allRegTrail(long idTrail);

    /**
     *
     * @param idTrail ID that we use to check if the registration contain the trail
     * @return the number of registration who contain the trail
     */
    int getNumberOfRegsTrail(long idTrail);

    /**
     *
     * @param idTrail ID that we use to check if the registration contain the trail
     * @param currentPage who represent the page where is the client actually
     * @param elementPerPage who represent the number of registration that we want to retrieve
     * @return a list who contain the number of elementPerPage registrations who contain the trail
     */
    List<Registration> allRegTrailPagination(long idTrail, int currentPage, int elementPerPage);

    /**
     *
     * @param reg the registration that we want to add in the DB
     * @return the ID of the registration
     */
    long addReg(Registration reg);

    /**
     *
     * @param id the ID of the registration that we want to delete
     * @return a boolean who indicate if the suppression passed
     */
    boolean deleteReg(long id);
}
