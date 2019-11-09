package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.Trail;
import ch.heigvd.amt.projectOne.utils.DateFormat;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TrailDao implements TrailDaoLocal {

    @Resource(lookup = "java:/jdbc/sakila")
    private DataSource dataSource;

    //READ
    @Override
    public Trail trail(long id) {

        Connection connection = null;
        Trail trail = null;
        try {
            connection = dataSource.getConnection();
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
            return trail;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        }
        finally {
            closeConnection(connection);
        }
    }

    //READ
    @Override
    public List<Trail> allTrail() {

        Connection connection = null;
        List<Trail> trails = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
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
            return trails;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public int getNumberOfTrails() {

        Connection connection = null;
        int numOfRows = 0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) AS total FROM trail;");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                numOfRows = rs.getInt("total");
            }
            return numOfRows;
        }catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        }
        finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Trail> allTrailPagination(int currentPage, int elementPerPage)  {

        Connection connection = null;
        List<Trail> trails = new ArrayList<>();
        int start = currentPage * elementPerPage - elementPerPage;
        try {
            connection = dataSource.getConnection();
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
            return trails;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    //READ
    @Override
    public List<Trail> allTrailToComeWithNoReg(long idUser) {

        Connection connection = null;
        List<Trail> trails = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
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
            return trails;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public int getNumberOfTrailsToComeWithNoReg(long idUser) {

        Connection connection = null;
        int numOfRows = 0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) AS total FROM trail WHERE id NOT IN (SELECT id_trail_fk FROM registration WHERE id_user_fk=?);");
            pstmt.setObject(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                numOfRows = rs.getInt("total");
            }
            return numOfRows;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Trail> allTrailToComeWithNoRegPagination(long idUser, int currentPage, int elementPerPage) {

        Connection connection = null;
        List<Trail> trails = new ArrayList<>();
        int start = currentPage * elementPerPage - elementPerPage;
        try {
            connection = dataSource.getConnection();
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
            return trails;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    //CREATE
    @Override
    public long addTrail(Trail trail) {

        Connection connection = null;
        long id = -1;
        String date = DateFormat.javaToMysql(trail.getDate());
        try {
            connection = dataSource.getConnection();
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
            return id;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }


    @Override
    public boolean updateTrail(Trail trail) {

        Connection connection = null;
        int rs = 0;
        String date = DateFormat.javaToMysql(trail.getDate());
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE trail SET name=?, length=?, up_and_down=?, description=?, date=? WHERE id=?;");
            pstmt.setObject(1, trail.getName());
            pstmt.setObject(2, trail.getDistance());
            pstmt.setObject(3, trail.getUpAndDown());
            pstmt.setObject(4, trail.getDescription());
            pstmt.setObject(5, date);
            pstmt.setObject(6, trail.getId());
            rs = pstmt.executeUpdate();
            return (rs == 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    //DELETE
    @Override
    public boolean deleteTrail(long id) {

        Connection connection = null;
        int rs = 0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM trail WHERE id=?;");
            pstmt.setObject(1, id);
            rs = pstmt.executeUpdate();
            return (rs == 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
