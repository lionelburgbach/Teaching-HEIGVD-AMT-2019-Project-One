package ch.heigvd.amt.projectOne.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ranking {

    private List<Result> results;

    public Ranking(List<Result> results){

        this.results = new ArrayList<>(results);
    }

    private class SortbyTime implements Comparator<Result> {

        public int compare(Result a, Result b) {
            return a.getTime() - b.getTime();
        }
    }

    public List<Result> results(){

        ArrayList<Result> resByRanking = new ArrayList<>(results);
        Collections.sort(resByRanking ,new SortbyTime() );
        return  resByRanking;
    }

    public List<Result> resultsByCategory(int category){

        ArrayList<Result> resultsByCategory = new ArrayList<>();
        for (Result res : results) {
            if(res.getRegistration().getCategory() == category){
                resultsByCategory.add(res);
            }
        }
        Collections.sort(resultsByCategory,new SortbyTime() );
        return  resultsByCategory;
    }

    public int resultUser(User user, int category){

        List<Result> results = resultsByCategory(category);
        for(int i = 0; i< results.size(); i++){
            if(results.get(i).getRegistration().getUser() == user){
                return  i+1;
            }
        }
        return -1;
    }
}
