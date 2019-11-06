package ch.heigvd.amt.projectOne.model;

import lombok.Getter;

@Getter
public class Trail {

    private long id;
    private String name;
    private double distance;
    private double upAndDown;
    private String description;
    private int capacity;
    private int nbIn;
    private String date;

    public Trail(long id, String name, double length, double upAndDown, String description, int capacity,String date){
        this.id = id;
        this.name = name;
        this.distance = length;
        this.upAndDown = upAndDown;
        this.description = description;
        this.capacity = capacity;
        this.date = date;
        this.nbIn = 0;
    }

    public boolean addTrailer(){

        if(nbIn < capacity){
            nbIn++;
            return true;
        }
        else{
            return false;
        }
    }
}
