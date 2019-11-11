package ch.heigvd.amt.projectOne.model;

import lombok.Getter;

/**
 * The Registration class is the link between an user and a trail (a user can registrate to a trail).
 * It has a unique ID who is determined by the DB (auto-increment), a user and a trail.
 */

@Getter
public class Registration {

    private long id;
    private User user;
    private Trail trail;

    public Registration(long id, User user, Trail trail){
        this.id = id;
        this.user = user;
        this.trail = trail;
    }

    public Registration(User user, Trail trail){
        this.user = user;
        this.trail = trail;
    }
}
