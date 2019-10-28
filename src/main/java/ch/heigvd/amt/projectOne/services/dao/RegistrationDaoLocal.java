package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.Registration;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RegistrationDaoLocal {

    public List<Registration> allReg(long iduser);
    public boolean addReg(long id_user, long id_trail, int category, String date);
}
