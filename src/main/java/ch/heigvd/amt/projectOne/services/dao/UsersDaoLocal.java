package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.User;

import javax.ejb.Local;

@Local
public interface UsersDaoLocal {

    User connect(String email, String password);
    User user(long id);
    User participant(long id);
    boolean addUser(String firstname, String lastname, String date, String email, String password);
    boolean updateUser(long id, String firstname, String lastname, String date, String email, String password);
    boolean deleteUser(long id);

}
