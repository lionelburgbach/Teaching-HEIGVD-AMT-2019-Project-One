package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.Trail;

import javax.annotation.Resource;
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
public class TrailDao implements TrailDaoLocal {

    @Resource(lookup = "java:/jdbc/sakila")
    private DataSource dataSource;

    private static final Logger LOG = Logger.getLogger(TrailDao.class.getName());

    //READ
    @Override
    public Trail trail(long id) {

        Trail trail = null;
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM trail WHERE id=?;");
            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                double distance = rs.getDouble("length");
                double upAndDown = rs.getDouble("up_and_down");
                String description = rs.getString("description");
                int capacity = rs.getInt("capacity");
                String date = rs.getString("date");
                trail = new Trail(id, name, distance, upAndDown, description, capacity, date);
            }
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return trail;
    }

    //READ
    @Override
    public List<Trail> allTrail() {

        List<Trail> trails = new ArrayList<>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM trail;");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double distance = rs.getDouble("length");
                double upAndDown = rs.getDouble("up_and_down");
                String description = rs.getString("description");
                int capacity = rs.getInt("capacity");
                String date = rs.getString("date");
                trails.add(new Trail(id, name, distance, upAndDown, description, capacity, date));
            }
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return trails;
    }

    //READ
    @Override
    public List<Trail> allTrailToCome() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date(System.currentTimeMillis());
        String s = formatter.format(currentDate);

        List<Trail> trails = new ArrayList<>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM trail WHERE date >?;");
            pstmt.setObject(1, s);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double distance = rs.getDouble("length");
                double upAndDown = rs.getDouble("up_and_down");
                String description = rs.getString("description");
                int capacity = rs.getInt("capacity");
                String date = rs.getString("date");
                trails.add(new Trail(id, name, distance, upAndDown, description, capacity, date));
            }
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return trails;
    }

    //READ
    @Override
    public List<Trail> allTrailDone() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date(System.currentTimeMillis());
        String s = formatter.format(currentDate);

        List<Trail> trails = new ArrayList<>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM trail WHERE date < ?;");
            pstmt.setObject(1, s);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double distance = rs.getDouble("length");
                double upAndDown = rs.getDouble("up_and_down");
                String description = rs.getString("description");
                int capacity = rs.getInt("capacity");
                String date = rs.getString("date");
                trails.add(new Trail(id, name, distance, upAndDown, description, capacity, date));
            }
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return trails;
    }

    //CREATE
    @Override
    public boolean addTrail(String name, double distance, double upAndDown, String description, int capacity, String date) {

        int rs = 0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO trail (name, length, up_and_down, description, capacity, date)\n" +
                    "VALUES (?, ?, ?, ?, ?,?);");
            pstmt.setObject(1, name);
            pstmt.setObject(2, distance);
            pstmt.setObject(3, upAndDown);
            pstmt.setObject(4, description);
            pstmt.setObject(5, capacity);
            pstmt.setObject(6, date);
            rs = pstmt.executeUpdate();
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return (rs == 1);
    }

    //UPDATE
    @Override
    public boolean updateTrail(long id, String name, double distance, double upAndDown, String description, int capacity, String date) {

        int rs = 0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE trail SET name=?, length=?, up_and_down=?, description=?, capacity=?, date=? WHERE id=?;");
            pstmt.setObject(1, name);
            pstmt.setObject(2, distance);
            pstmt.setObject(3, upAndDown);
            pstmt.setObject(4, description);
            pstmt.setObject(5, capacity);
            pstmt.setObject(6, date);
            pstmt.setObject(7, id);
            rs = pstmt.executeUpdate();
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return (rs == 1);
    }

    //DELETE
    @Override
    public boolean deleteTrail(long id) {

        int rs = 0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM trail WHERE id=?;");
            pstmt.setObject(1, id);
            rs = pstmt.executeUpdate();
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return (rs == 1);
    }
}
