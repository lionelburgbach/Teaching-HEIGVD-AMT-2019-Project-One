package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.Actor;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ActorsManagerLocal {

    public List<Actor> findAllActors();
}
