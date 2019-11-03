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

    public Map<Trail, List<Result>>  results(){

        Map<Trail, List<Result>> resByRanking = new HashMap<>(results);
        Iterator<Map.Entry<Trail, List<Result>>> it = resByRanking.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Trail, List<Result>> pair = it.next();
            Collections.sort(pair.getValue(), new SortbyTime());
        }
        return resByRanking;
    }

    public Map<Trail, List<Result>> getRanking(){
        return results;
    }

    /*
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
     */
}
