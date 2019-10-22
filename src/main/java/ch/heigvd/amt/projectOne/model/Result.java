package ch.heigvd.amt.projectOne.model;

import lombok.Getter;

@Getter
public class Result {

    private User user;
    private int time;

    public Result(User user, int time){
        this.user = user;
        this.time = time;
    }
}
