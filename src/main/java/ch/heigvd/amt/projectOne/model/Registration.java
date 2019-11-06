package ch.heigvd.amt.projectOne.model;

import lombok.Getter;

@Getter
public class Registration {

    private long id;
    private User user;
    private Trail trail;

    public Registration(long id, User user, Trail trail){
        this.id = id;
        this.user = user;
        this.trail = trail;
        if(!trail.addTrailer()){
            throw new IllegalArgumentException("No more place");
        }
    }

    public Registration(User user, Trail trail){
        this.user = user;
        this.trail = trail;
        if(!trail.addTrailer()){
            throw new IllegalArgumentException("No more place");
        }
    }

}
