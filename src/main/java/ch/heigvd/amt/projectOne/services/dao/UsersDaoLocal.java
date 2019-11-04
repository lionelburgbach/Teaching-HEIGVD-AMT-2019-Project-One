package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.User;

import javax.ejb.Local;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.sql.Blob;

@Local
public interface UsersDaoLocal {

    User connect(String email, String password);
    User user(long id);
    User dataUser(long id);
    boolean addUser(String firstname, String lastname, String date, String email, String password);
    boolean updateUser(long id, String firstname, String lastname, String date, String email, String password);
    boolean updateUser(long id, String firstname, String lastname, String date, String email, String password, InputStream profile_picture);
    boolean deleteUser(long id);
}
