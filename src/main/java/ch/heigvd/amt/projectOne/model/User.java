package ch.heigvd.amt.projectOne.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class User {

    private long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    //private Map<Integer, Result> resultList;
    //private String imagePath;

    public User(long id, String email, String password, String firstName, String lastName, String dateOfBirth){
        this.id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
}
