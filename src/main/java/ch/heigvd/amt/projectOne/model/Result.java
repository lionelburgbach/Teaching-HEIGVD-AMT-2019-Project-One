package ch.heigvd.amt.projectOne.model;

import lombok.Getter;

@Getter
public class Result {

    private long id;
    private Registration registration;
    private int time;

    public Result(long id, Registration registration, int time){

        if(time < 0){
            throw new IllegalArgumentException("Time cannot be negative");
        }

        this.id = id;
        this.registration = registration;
        this.time = time;
    }

}
