package ch.heigvd.amt.projectOne.model;

import lombok.Getter;

@Getter
public class Actor {

    private long id;
    private String firstname;
    private String lastname;

    public Actor(long id, String firstname, String lastname){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
