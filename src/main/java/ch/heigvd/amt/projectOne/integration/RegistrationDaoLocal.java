package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.Registration;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RegistrationDaoLocal {

    Registration registration(long idUser, long idTrail);
    List<Registration> allRegUser(long idUser);
    List<Registration> allRegTrail(long idTrail);
    boolean addReg(Registration reg);
    boolean deleteReg(long id);
}
