package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.User;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UsersManagerLocal {

    public List<User> findAllUsers();
}
