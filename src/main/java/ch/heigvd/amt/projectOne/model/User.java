package ch.heigvd.amt.projectOne.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Builder
@Getter
public class User {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private List<Result> resultList;
    private String imagePath;
}
