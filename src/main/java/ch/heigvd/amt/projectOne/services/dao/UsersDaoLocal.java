package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.User;

import javax.ejb.Local;

@Local
public interface UsersDaoLocal {

    public boolean connect(String email, String password);
    public User user(int id);
    public boolean addUser(String firstname, String lastname, String date, String email, String password);
    public boolean updateUser(String firstname, String lastname, String date, String email, String password);
    public boolean deleteUser(int id);

}
