package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.utils.DateFormat;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;

@Stateless
public class UserDao implements UsersDaoLocal {

    @Resource(lookup = "java:/jdbc/sakila")
    private DataSource dataSource;

    @Override
    public User connect(String email, String password) {

        Connection connection = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user WHERE email=? AND password=?");
            pstmt.setObject(1, email);
            pstmt.setObject(2, password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String date = rs.getString("date");
                String emailUser = rs.getString("email");
                String passwordUser = rs.getString("password");
                user = new User(id, firstname, lastname, DateFormat.mysqlToJava(date), null, emailUser, passwordUser);
            }
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public User user(long id) {

        Connection connection = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user WHERE id=?");
            pstmt.setObject(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String date = rs.getString("date");
                InputStream profilePicture = rs.getBinaryStream("profile_picture");
                String mail = rs.getString("email");
                String password = rs.getString("password");
                user = new User(id, firstname, lastname, DateFormat.mysqlToJava(date), profilePicture, mail, password);
            }
            return user;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public long addUser(User user) {

        Connection connection = null;
        long id = -1;
        String date = DateFormat.javaToMysql(user.getDateOfBirth());
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO user (firstname, lastname, date, email, password)\n" +
                    "VALUES (?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            pstmt.setObject(1, user.getFirstName());
            pstmt.setObject(2, user.getLastName());
            pstmt.setObject(3, date);
            pstmt.setObject(4, user.getEmail());
            pstmt.setObject(5, user.getPassword());
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
    public boolean updateUser(User user) {

        Connection connection = null;
        String date = DateFormat.javaToMysql(user.getDateOfBirth());
        int rs = 0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE user SET firstname=?, lastname=?, date=?, email=?, password=? WHERE id=?;");
            pstmt.setObject(1, user.getFirstName());
            pstmt.setObject(2, user.getLastName());
            pstmt.setObject(3, date);
            pstmt.setObject(4, user.getEmail());
            pstmt.setObject(5, user.getPassword());
            pstmt.setObject(6, user.getId());
            rs = pstmt.executeUpdate();
            return (rs == 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public boolean updatePictureUser(long id, InputStream profilePicture) {

        Connection connection = null;
        int rs = 0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE user SET profile_picture=? WHERE id=?;");
            pstmt.setObject(1, profilePicture);
            pstmt.setObject(2, id);
            rs = pstmt.executeUpdate();
            return (rs == 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Error(ex);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public boolean deleteUser(long id){

        Connection connection = null;
        int rs = 0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM user WHERE id=?;");
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

    @Override
    public boolean exist(String email) {

        Connection connection = null;
        int numOfRows = 0;
        try {
            connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) AS exist FROM user WHERE email=?;");
            pstmt.setObject(1, email);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                numOfRows = rs.getInt("exist");
            }
            return (numOfRows==1);
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