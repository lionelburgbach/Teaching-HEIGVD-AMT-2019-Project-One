package ch.heigvd.amt.projectOne.model;

import lombok.Getter;

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
