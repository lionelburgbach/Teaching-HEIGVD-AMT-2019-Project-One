package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.Registration;
import ch.heigvd.amt.projectOne.model.Result;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ResultDaoLocal {

    List<Result> allResultUser(long idUser);
    List<Result> allResultTrail(long idTrail);
}
