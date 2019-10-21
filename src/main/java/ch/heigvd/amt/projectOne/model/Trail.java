package ch.heigvd.amt.projectOne.model;

import lombok.Getter;

import java.util.*;

@Getter
public class Trail {

    private long id;
    private String name;
    private double length;
    private double upAndDown;
    private String description;
    private int capacity;
    private int nbIn;
    private String date;
    private List<Result>results;

    public Trail(long id, String name, double length, double upAndDown, String description, int capacity, int nbIn, String date){
        this.id = id;
        this.name = name;
        this.length = length;
        this.upAndDown = upAndDown;
        this.description = description;
        this.capacity = capacity;
        this.nbIn = nbIn;
        this.date = date;
        results = new ArrayList<Result>();
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

    public void addResult(Result res){
       results.add(res);
    }

    public void resluts(){


    }


}
