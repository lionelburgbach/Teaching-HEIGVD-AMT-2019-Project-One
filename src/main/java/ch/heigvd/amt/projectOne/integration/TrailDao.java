package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.Trail;
import ch.heigvd.amt.projectOne.utils.DateFormat;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
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
                String date = rs.getString("date");
                trail = new Trail(id, name, distance, upAndDown, description, DateFormat.mysqlToJava(date));
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
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM trail ORDER BY date;");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double distance = rs.getDouble("length");
                double upAndDown = rs.getDouble("up_and_down");
                String description = rs.getString("description");
                String date = rs.getString("date");
                trails.add(new Trail(id, name, distance, upAndDown, description, DateFormat.mysqlToJava(date)));
            }
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return trails;
    }

    @Override
    public int getNumberOfTrails() {

        int numOfRows = 0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) AS total FROM trail;");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
                numOfRows=rs.getInt("total");
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }

        return numOfRows;
    }

    @Override
    public List<Trail> allTrailPagination(int currentPage, int elementPerPage)  {

        List<Trail> trails = new ArrayList<>();

        int start = currentPage * elementPerPage - elementPerPage;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM trail ORDER BY date ASC LIMIT ?,?;");
            pstmt.setObject(1, start);
            pstmt.setObject(2, elementPerPage);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double distance = rs.getDouble("length");
                double upAndDown = rs.getDouble("up_and_down");
                String description = rs.getString("description");
                String date = rs.getString("date");
                trails.add(new Trail(id, name, distance, upAndDown, description, DateFormat.mysqlToJava(date)));
            }
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }

        return trails;
    }

    //READ
    @Override
    public List<Trail> allTrailToComeWithNoReg(long idUser) {

        List<Trail> trails = new ArrayList<>();
        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM trail WHERE id NOT IN (SELECT id_trail_fk FROM registration WHERE id_user_fk=?);");
            pstmt.setObject(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double distance = rs.getDouble("length");
                double upAndDown = rs.getDouble("up_and_down");
                String description = rs.getString("description");
                String date = rs.getString("date");
                trails.add(new Trail(id, name, distance, upAndDown, description, DateFormat.mysqlToJava(date)));
            }
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return trails;
    }

    @Override
    public int getNumberOfTrailsToComeWithNoReg(long idUser) {

        int numOfRows = 0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) AS total FROM trail WHERE id NOT IN (SELECT id_trail_fk FROM registration WHERE id_user_fk=?);");
            pstmt.setObject(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
                numOfRows = rs.getInt("total");
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }

        return numOfRows;
    }

    @Override
    public List<Trail> allTrailToComeWithNoRegPagination(long idUser, int currentPage, int elementPerPage) {

        List<Trail> trails = new ArrayList<>();

        int start = currentPage * elementPerPage - elementPerPage;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM trail WHERE id NOT IN (SELECT id_trail_fk FROM registration WHERE id_user_fk=?) ORDER BY date ASC LIMIT ?,?;");
            pstmt.setObject(1, idUser);
            pstmt.setObject(2, start);
            pstmt.setObject(3, elementPerPage);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double distance = rs.getDouble("length");
                double upAndDown = rs.getDouble("up_and_down");
                String description = rs.getString("description");
                String date = rs.getString("date");
                trails.add(new Trail(id, name, distance, upAndDown, description, DateFormat.mysqlToJava(date)));
            }
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return trails;
    }

    //CREATE
    @Override
    public long addTrail(Trail trail) {

        long id = -1;

        String date = DateFormat.javaToMysql(trail.getDate());

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO trail (name, length, up_and_down, description, date)\n" +
                    "VALUES (?, ?, ?, ?,?);", Statement.RETURN_GENERATED_KEYS);
            pstmt.setObject(1, trail.getName());
            pstmt.setObject(2, trail.getDistance());
            pstmt.setObject(3, trail.getUpAndDown());
            pstmt.setObject(4, trail.getDescription());
            pstmt.setObject(5, date);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()) {
                id = rs.getLong(1);
            }
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return id;
    }


    @Override
    public boolean updateTrail(Trail trail) {

        int rs = 0;

        String date = DateFormat.javaToMysql(trail.getDate());

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE trail SET name=?, length=?, up_and_down=?, description=?, date=? WHERE id=?;");
            pstmt.setObject(1, trail.getName());
            pstmt.setObject(2, trail.getDistance());
            pstmt.setObject(3, trail.getUpAndDown());
            pstmt.setObject(4, trail.getDescription());
            pstmt.setObject(5, date);
            pstmt.setObject(6, trail.getId());
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
