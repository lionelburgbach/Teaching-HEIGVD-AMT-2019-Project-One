package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.Registration;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RegistrationDaoLocal {


    Registration registration(long idUser, long idTrail);
    List<Registration> allReg(long idUser);
    List<Registration> allRegWithResUser(long idUser);
    List<Registration> allRegWithResTrail(long idTrail);
    boolean addReg(long id_user, long id_trail, int category, String date);
    boolean deleteReg(long id);
}
