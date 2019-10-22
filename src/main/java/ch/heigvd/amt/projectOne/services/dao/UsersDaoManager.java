package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.User;

import javax.ejb.Local;

@Local
public interface UsersDaoManager {

    public boolean connect(String email, String password);
    public User user(int id);
    public boolean addUser(String firstname, String lastname, String date, String email, String password, int category);
    public boolean updateUser(String firstname, String lastname, String date, String email, String password, int category);
    public boolean deleteUser(int id);

}
