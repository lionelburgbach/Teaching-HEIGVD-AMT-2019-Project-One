package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.Registration;
import ch.heigvd.amt.projectOne.model.Trail;
import ch.heigvd.amt.projectOne.model.User;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class RegistrationDao implements RegistrationDaoLocal {

    @Resource(lookup = "java:/jdbc/sakila")
    private DataSource dataSource;

    @EJB
    TrailDaoLocal trailDao;
    @EJB
    UsersDaoLocal userDao;

    private static final Logger LOG = Logger.getLogger(TrailDao.class.getName());

    @Override
    public Registration registration(long idUser, long idTrail) {

        Registration reg = null;
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM registration WHERE id_user_fk=? AND id_trail_fk=?");
            pstmt.setObject(1, idUser);
            pstmt.setObject(2, idTrail);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                long id = rs.getLong("id");
                User user = userDao.user(idUser);
                Trail trail = trailDao.trail(idTrail);
                String date = rs.getString("date");
                reg = new Registration(id, user, trail, date);
            }
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reg;
    }

    @Override
    public List<Registration> allReg(long idUser) {

        List<Registration> regs = new ArrayList<>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM registration WHERE id_user_fk=?");
            pstmt.setObject(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                long id = rs.getLong("id");
                User user = userDao.user(idUser);
                long idTrail = rs.getInt("id_trail_fk");
                Trail trail = trailDao.trail(idTrail);
                String date = rs.getString("date");
                regs.add(new Registration(id, user, trail, date));
            }
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regs;
    }

    @Override
    public List<Registration> allRegWithResUser(long idUser) {

        List<Registration> regs = new ArrayList<>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT DISTINCT * FROM registration AS reg INNER JOIN result AS res ON reg.id = res.id_reg_fk WHERE reg.id_user_fk=?;");
            pstmt.setObject(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                long id = rs.getLong("id");
                User user = userDao.user(idUser);
                long idTrail = rs.getInt("id_trail_fk");
                Trail trail = trailDao.trail(idTrail);
                String date = rs.getString("date");
                regs.add(new Registration(id, user, trail, date));
            }
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regs;
    }

    @Override
    public List<Registration> allRegWithResTrail(long idTrail) {

        List<Registration> regs = new ArrayList<>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT DISTINCT * FROM registration AS reg INNER JOIN result AS res ON reg.id = res.id_reg_fk WHERE reg.id_traiL_fk=? ORDER BY res.time;");
            pstmt.setObject(1, idTrail);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                long id = rs.getLong("id");
                long idUser = rs.getInt("id_user_fk");
                User user = userDao.user(idUser);
                Trail trail = trailDao.trail(idTrail);
                String date = rs.getString("date");
                regs.add(new Registration(id, user, trail, date));
            }
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return regs;
    }

    //CREATE
    @Override
    public boolean addReg(long id_user, long id_trail, String date) {

        int rs = 0;

        if(this.registration(id_user, id_trail) == null){

            try {
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO registration (category, date, id_user_fk, id_trail_fk)\n" +
                        "VALUES (?, ?, ?);");
                pstmt.setObject(1, date);
                pstmt.setObject(2, id_user);
                pstmt.setObject(3, id_trail);
                rs = pstmt.executeUpdate();
                connection.close();
            } catch (SQLException ex) {

                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{

            //TODO if the registration already exist
            return false;
        }
        return (rs == 1);
    }

    //DELETE
    @Override
    public boolean deleteReg(long id) {

        int rs = 0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM registration WHERE id=?;");
            pstmt.setObject(1, id);
            rs = pstmt.executeUpdate();
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return (rs == 1);
    }
}
