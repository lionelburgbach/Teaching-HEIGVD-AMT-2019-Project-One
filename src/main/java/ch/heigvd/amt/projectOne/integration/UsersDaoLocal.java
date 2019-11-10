package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.User;

import javax.ejb.Local;
import java.io.InputStream;

@Local
public interface UsersDaoLocal {

    /**
     *
     * @param email of the user who want to connect
     * @param password of the user who want to connect
     * @return the user with is ID (if the pair email/password is correct )
     */
    User connect(String email, String password);

    /**
     *
     * @param id of the user that we want to retrieve
     * @return the user who match with the ID
     */
    User user(long id);

    /**
     *
     * @param user that we want to add in the DB
     * @return the ID of the user (that we ust add)
     */
    long addUser(User user);

    /**
     *
     * @param user that we want to change (we need that the user have already an ID)
     * @return a boolean who indicate if the change passed
     */
    boolean updateUser(User user);

    /**
     *
     * @param id of the user whose photo we want to change
     * @param profilePicture the new profil picture in an Inputstream format
     * @return  a boolean who indicate if the change passed
     */
    boolean updatePictureUser(long id, InputStream profilePicture);

    /**
     *
     * @param id of the user that we want to delete
     * @return  a boolean who indicate if the suppression passed
     */
    boolean deleteUser(long id);

    /**
     *
     * @param email that we want to if it's already use
     * @return a boolean who indicate if the email already exist
     */
    boolean exist(String email);
}
