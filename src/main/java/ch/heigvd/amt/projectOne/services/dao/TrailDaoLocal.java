package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.Trail;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TrailDaoLocal {

    Trail trail(long id);
    List<Trail> allTrail();
    //public List<Trail> allTrailGen();
    boolean addTrail(String name, double distance, double upAndDown, String description, int capacity, String date);
    boolean updateTrail(long id, String name, double distance, double upAndDown, String description, int capacity, String date);
    boolean deleteTrail(long id);
}
