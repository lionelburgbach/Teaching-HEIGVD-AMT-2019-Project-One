package ch.heigvd.amt.projectOne.model;

import lombok.Getter;

@Getter
public class Result {

    private User user;
    private double time;

    public Result(int idUser, User user){
        this.user = user;
        this.time = time;
    }
}
