package ch.heigvd.amt.projectOne.model;

import lombok.Getter;

import java.util.Calendar;

@Getter
public class Registration {

    private long id;
    private User user;
    private Trail trail;
    private int category;
    private String date;

    public Registration(long id, User user, Trail trail, String date){
        this.id = id;
        this.user = user;
        this.trail = trail;
        this.category = category(user);
        this.date = date;
        if(!trail.addTrailer()){
            throw new IllegalArgumentException("No more place");
        }
    }

    public Registration(User user, Trail trail, String date){
        this.user = user;
        this.trail = trail;
        this.category = category(user);
        this.date = date;
        if(!trail.addTrailer()){
            throw new IllegalArgumentException("No more place");
        }
    }

    public int category(User user){

        String[] s = user.getDateOfBirth().split("-");
        String year = s[2];
        int yearInt = Integer.parseInt(year);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int diff = currentYear-yearInt;

        if(diff <= 10){
            return 1;
        }
        else if(diff > 10 && diff <= 16){
            return 2;
        }
        else if(diff > 16 && diff <= 50){
            return 3;
        }
        else if(diff > 50){
            return  4;
        }
        return -1;
    }

}
