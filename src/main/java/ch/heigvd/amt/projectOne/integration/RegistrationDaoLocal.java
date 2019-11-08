package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.Registration;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RegistrationDaoLocal {

    Registration registration(long idUser, long idTrail);

    List<Registration> allRegUser(long idUser);
    int getNumberOfRegsUser(long idUser);
    List<Registration> allRegUserPagination(long idUser, int currentPage, int elementPerPage);

    List<Registration> allRegTrail(long idTrail);
    int getNumberOfRegsTrail(long idTrail);
    List<Registration> allRegTrailPagination(long idTrail, int currentPage, int elementPerPage);

    boolean addReg(Registration reg);
    boolean deleteReg(long id);
}
