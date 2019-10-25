package ch.heigvd.amt.projectOne.model;

import lombok.Getter;

import java.util.*;

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
    private List<Result> results;

    public Trail(){}

    public Trail(long id, String name, double length, double upAndDown, String description, int capacity, String date){
        this.id = id;
        this.name = name;
        this.distance = length;
        this.upAndDown = upAndDown;
        this.description = description;
        this.capacity = capacity;
        this.date = date;
        this.nbIn = 0;
        this.results = new ArrayList<>();
    }

    private class SortbyTime implements Comparator<Result> {

        public int compare(Result a, Result b) {
            return a.getTime() - b.getTime();
        }
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

    public List<Result> getResults(){

        return new ArrayList<>(results);
    }

    public List<Result> results(int category){

        ArrayList<Result> resultsByCategory = new ArrayList<>();
        for (Result res : results) {
            if(res.getRegistration().getCategory() == category){
                resultsByCategory.add(res);
            }
        }
        Collections.sort(resultsByCategory, new SortbyTime());
        return  resultsByCategory;
    }

    public int resultUser(User user, int category){

        List<Result> results = results(category);
        for(int i = 0; i< results.size(); i++){
            if(results.get(i).getRegistration().getUser() == user){
                return  i+1;
            }
        }
        return -1;
    }
}
