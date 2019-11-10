package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.Registration;
import ch.heigvd.amt.projectOne.model.Trail;
import ch.heigvd.amt.projectOne.model.User;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class RegistrationDao implements RegistrationDaoLocal {

    @Resource(lookup = "java:/jdbc/sakila")
    private DataSource dataSource;

    @EJB
    TrailDaoLocal trailDao;
    @EJB
    UsersDaoLocal userDao;

    @Override
    public Registration registration(long idUser, long idTrail) {

        Connection connection = null;
        Registration reg = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM registration WHERE id_user_fk=? AND id_trail_fk=?");
            pstmt.setObject(1, idUser);
            pstmt.setObject(2, idTrail);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                long id = rs.getLong("id");
                User user = userDao.user(idUser);
                Trail trail = trailDao.trail(idTrail);
                reg = new Registration(id, user, trail);
            }
            return reg;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Registration> allRegUser(long idUser) {

        Connection connection = null;
        List<Registration> regs = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT DISTINCT * FROM registration AS reg INNER JOIN trail ON trail.id=reg.id_trail_fk WHERE id_user_fk=? ORDER BY trail.date;");
            pstmt.setObject(1, idUser);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                long id = rs.getLong("id");
                User user = userDao.user(idUser);
                long idTrail = rs.getInt("id_trail_fk");
                Trail trail = trailDao.trail(idTrail);
                regs.add(new Registration(id, user, trail));
            }
            return regs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public int getNumberOfRegsUser(long idUser) {

        Connection connection = null;
        int numOfRows = 0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) AS total FROM registration AS reg INNER JOIN trail ON trail.id=reg.id_trail_fk WHERE id_user_fk=?;");
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
    public List<Registration> allRegUserPagination(long idUser, int currentPage, int elementPerPage) {

        Connection connection = null;
        List<Registration> regs = new ArrayList<>();
        int start = currentPage * elementPerPage - elementPerPage;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT DISTINCT * FROM registration AS reg INNER JOIN trail ON trail.id=reg.id_trail_fk WHERE id_user_fk=? ORDER BY trail.date ASC LIMIT ?,?;");
            pstmt.setObject(1, idUser);
            pstmt.setObject(2, start);
            pstmt.setObject(3, elementPerPage);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                long id = rs.getLong("id");
                User user = userDao.user(idUser);
                long idTrail = rs.getInt("id_trail_fk");
                Trail trail = trailDao.trail(idTrail);
                regs.add(new Registration(id, user, trail));
            }
            return regs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Registration> allRegTrail(long idTrail) {

        Connection connection = null;
        List<Registration> regs = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM registration WHERE id_trail_fk=?");
            pstmt.setObject(1, idTrail);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                long id = rs.getLong("id");
                long idU = rs.getInt("id_user_fk");
                User user = userDao.user(idU);
                Trail trail = trailDao.trail(idTrail);
                regs.add(new Registration(id, user, trail));
            }
            return regs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public int getNumberOfRegsTrail(long idTrail) {

        Connection connection = null;
        int numOfRows = 0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) AS total FROM registration AS reg INNER JOIN trail ON trail.id=reg.id_trail_fk WHERE id_trail_fk=?;");
            pstmt.setObject(1, idTrail);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())
                numOfRows=rs.getInt("total");
            return numOfRows;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public List<Registration> allRegTrailPagination(long idTrail, int currentPage, int elementPerPage) {

        Connection connection = null;
        List<Registration> regs = new ArrayList<>();
        int start = currentPage * elementPerPage - elementPerPage;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT DISTINCT * FROM registration AS reg INNER JOIN trail ON trail.id=reg.id_trail_fk WHERE id_trail_fk=? ORDER BY trail.date ASC LIMIT ?,?;");
            pstmt.setObject(1, idTrail);
            pstmt.setObject(2, start);
            pstmt.setObject(3, elementPerPage);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                long id = rs.getLong("id");
                long idUser = rs.getInt("id_user_fk");
                User user = userDao.user(idUser);
                Trail trail = trailDao.trail(idTrail);
                regs.add(new Registration(id, user, trail));
            }
            return regs;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public long addReg(Registration reg) {

        Connection connection = null;
        long id = -1;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO registration (id_user_fk, id_trail_fk) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS);
            pstmt.setObject(1, reg.getUser().getId());
            pstmt.setObject(2, reg.getTrail().getId());
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
    public boolean deleteReg(long id) {

        Connection connection = null;
        int rs = 0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM registration WHERE id=?;");
            pstmt.setObject(1, id);
            rs = pstmt.executeUpdate();
            return (rs==1);
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
