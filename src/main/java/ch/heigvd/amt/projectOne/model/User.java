package ch.heigvd.amt.projectOne.model;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

/**
 * Class User represent a client in our application. He have a unique ID who is determined by the DB (auto-increment). He is characterized
 * by a firstname, a lastname, a date of birth, a picture of profl, an email (who is unique and serves to authentication) and a password
 * who do the pair with the mail. The password is stocked as an hash.
 */

@Builder
@Getter
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private InputStream profilePicture;
    private String email;
    private String password;

    public User(long id, String firstName, String lastName, String dateOfBirth, InputStream profilePicture, String email, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.profilePicture = profilePicture;
        this.email = email;
        this.password = password;
    }

    public User(long id, String firstName, String lastName, String dateOfBirth, String email, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String dateOfBirth, InputStream profilePicture, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.profilePicture = profilePicture;
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String dateOfBirth, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
    }
}
