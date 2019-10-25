package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.Trail;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TrailDaoLocal {

    public Trail trail(int id);
    public List<Trail> allTrail();
    //public List<Trail> allTrailGen();
    public boolean addTrail(String name, double distance, double upAndDown, String description, int capacity, String date);
    public boolean updateTrail(String name, double distance, double upAndDown, String description, int capacity, String date);
    public boolean deleteTrail(int id);
}
