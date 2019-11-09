package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.User;

import javax.ejb.Local;
import java.io.InputStream;

@Local
public interface UsersDaoLocal {

    User connect(String email, String password);
    User user(long id);
    long addUser(User user);
    boolean updateUser(User user);
    boolean updatePictureUser(long id, InputStream profilePicture);
    boolean deleteUser(long id);
    boolean exist(String email);
}
