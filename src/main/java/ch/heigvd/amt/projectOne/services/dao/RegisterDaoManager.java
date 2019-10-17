package ch.heigvd.amt.projectOne.services.dao;

import javax.ejb.Local;
import java.util.Date;

@Local
public interface RegisterDaoManager {

    public boolean addUser(String firstname, String lastname, Date date, String email, String password);
}
