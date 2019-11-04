package ch.heigvd.amt.projectOne.model;

import java.util.*;

public class Ranking {

    private Map<Trail, List<Result>> results;

    public Ranking(){

        this.results = new HashMap<>();
    }

    public void add(Trail trail, List<Result> result){

        results.put(trail, result);
    }

    public Map<Trail, List<Result>> getRanking(){
        return results;
    }
}
