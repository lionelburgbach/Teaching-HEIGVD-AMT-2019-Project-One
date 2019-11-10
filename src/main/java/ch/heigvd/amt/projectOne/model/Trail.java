package ch.heigvd.amt.projectOne.model;

import lombok.Getter;

/**
 * Class Trail represent the differents trails that a user can registrate at. It has a unique ID who is determnine by the DB (auto-increment).
 * It as a name, a distance, an upAndDown (who is the difference of height between the sart and the end), a description and a date
 */
@Getter
public class Trail {

    private long id;
    private String name;
    private double distance;
    private double upAndDown;
    private String description;
    private String date;

    public Trail(long id, String name, double length, double upAndDown, String description, String date){

        this.id = id;
        this.name = name;
        this.distance = length;
        this.upAndDown = upAndDown;
        this.description = description;
        this.date = date;
    }


    public Trail(String name, double length, double upAndDown, String description, String date){

        this.name = name;
        this.distance = length;
        this.upAndDown = upAndDown;
        this.description = description;
        this.date = date;
    }
}
