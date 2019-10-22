package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.Trail;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class TrailDao implements TrailDaoManager {

    @Resource(lookup = "java:/jdbc/sakila")
    private DataSource dataSource;

    //READ
    @Override
    public Trail trail(int id) {

        Trail trail = null;
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM trail WHERE id=?");
            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                double length = rs.getDouble("length");
                double upAndDown = rs.getDouble("up_and_down");
                String description = rs.getString("description");
                int capacity = rs.getInt("capacity");
                String date = rs.getString("date");
                trail = new Trail(id, name, length, upAndDown, description, capacity, date);
            }
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trail;
    }

    //READ
    @Override
    public List<Trail> allTrail() {

        List<Trail> trails = new ArrayList<>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM trail");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double length = rs.getDouble("length");
                double upAndDown = rs.getDouble("up_and_down");
                String description = rs.getString("description");
                int capacity = rs.getInt("capacity");
                String date = rs.getString("date");
                trails.add(new Trail(id, name, length, upAndDown, description, capacity, date));
            }
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trails;
    }

    //CREATE
    @Override
    public boolean addTrail(String name, double length, double upAndDown, String description, int capacity, String date) {

        int rs = 0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO user (name, length, up_and_down, description, capacity, date)\n" +
                    "VALUES (?, ?, ?, ?, ?,?);");
            pstmt.setObject(1, name);
            pstmt.setObject(2, length);
            pstmt.setObject(3, upAndDown);
            pstmt.setObject(4, description);
            pstmt.setObject(5, capacity);
            pstmt.setObject(6, date);
            rs = pstmt.executeUpdate();
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rs == 1);
    }

    //UPDATE
    @Override
    public boolean updateTrail(String name, double length, double upAndDown, String description, int capacity, String date) {

        int rs = 0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE trail SET name=?, length=?, up_and_down=?, description=?, capacity=?, date=?");
            pstmt.setObject(1, name);
            pstmt.setObject(2, length);
            pstmt.setObject(3, upAndDown);
            pstmt.setObject(4, description);
            pstmt.setObject(5, capacity);
            pstmt.setObject(6, date);
            rs = pstmt.executeUpdate();
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rs == 1);
    }

    //DELETE
    @Override
    public boolean deleteTrail(int id) {

        int rs = 0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM trail WHERE id=?;");
            pstmt.setObject(1, id);
            rs = pstmt.executeUpdate();
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rs == 1);
    }
}
