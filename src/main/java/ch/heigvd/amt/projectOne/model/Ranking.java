package ch.heigvd.amt.projectOne.model;

import java.util.*;

public class Ranking {

    private Map<Trail, List<Result>> results;

    public Ranking(){

        this.results = new HashMap<>();
    }

    private class SortbyTime implements Comparator<Result> {

        public int compare(Result a, Result b) {
            return a.getTime() - b.getTime();
        }
    }

    public void add(Trail trail, List<Result> result){

        results.put(trail, result);
    }


    public Map<Trail, List<Result>> getRanking(){
        return results;
    }
}
