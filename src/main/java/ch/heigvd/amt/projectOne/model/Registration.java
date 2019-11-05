package ch.heigvd.amt.projectOne.model;

import lombok.Getter;

@Getter
public class Registration {

    private long id;
    private User user;
    private Trail trail;
    private String date;

    public Registration(long id, User user, Trail trail, String date){
        this.id = id;
        this.user = user;
        this.trail = trail;
        this.date = date;
        if(!trail.addTrailer()){
            throw new IllegalArgumentException("No more place");
        }
    }

    public Registration(User user, Trail trail, String date){
        this.user = user;
        this.trail = trail;
        this.date = date;
        if(!trail.addTrailer()){
            throw new IllegalArgumentException("No more place");
        }
    }

}
