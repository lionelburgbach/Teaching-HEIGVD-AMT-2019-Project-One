package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.User;

import javax.ejb.Local;
import java.io.InputStream;

@Local
public interface UsersDaoLocal {

    /**
     * Check if the couple email password exist
     * @param email     user's email
     * @param password  user's password
     * @return the user
     */
    User connect(String email, String password);

    /**
     * Return a user
     * @param id
     * @return the user who match with the ID
     */
    User user(long id);

    /**
     * Add a user
     * @param user user
     * @return the ID of the user
     */
    long addUser(User user);

    /**
     * Update a user
     * @param user user
     * @return a boolean if the user has been update or not
     */
    boolean updateUser(User user);

    /**
     * Update a picture
     * @param id                id of the user
     * @param profilePicture    the new profil picture in an Inputstream format
     * @return  a boolean if the picture has been update or not
     */
    boolean updatePictureUser(long id, InputStream profilePicture);

    /**
     * Delete a user
     * @param id  id of the user
     * @return  a boolean if the user has been delete or not
     */
    boolean deleteUser(long id);

    /**
     * Return if the email exist or not
     * @param email email
     * @return a boolean if the email already exist or not
     */
    boolean exist(String email);
}
