package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.Trail;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TrailDaoManager {

    public Trail trail(int id);
    public List<Trail> allTrail();
    public boolean addTrail(String name, double length, double upAndDown, String description, int capacity, String date);
    public boolean updateTrail(String name, double length, double upAndDown, String description, int capacity, String date);
    public boolean deleteTrail(int id);
}
