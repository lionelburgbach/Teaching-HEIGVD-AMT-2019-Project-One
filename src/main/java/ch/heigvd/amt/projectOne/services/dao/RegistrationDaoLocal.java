package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.Registration;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RegistrationDaoLocal {

    Registration registration(long idUser, long idTrail);
    List<Registration> allRegUser(long idUser);
    List<Registration> allRegTrail(long idTrail);
    boolean addReg(long idUser, long idTrail, String date);
    boolean deleteReg(long id);
}
