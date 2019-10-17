package ch.heigvd.amt.projectOne.services.dao;

import javax.ejb.Local;

@Local
public interface LoginDaoManager {

    public boolean connect(String email, String password);
}
