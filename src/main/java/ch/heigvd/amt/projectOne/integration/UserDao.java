package ch.heigvd.amt.projectOne.integration;

import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.utils.DateFormat;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class UserDao implements UsersDaoLocal {

    @Resource(lookup = "java:/jdbc/sakila")
    private DataSource dataSource;

    private static final Logger LOG = Logger.getLogger(UserDao.class.getName());

    @Override
    public User connect(String email, String password) {

        User user = null;

        try{
            Connection connection = dataSource.getConnection();
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
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return user;
    }

    //READ
    @Override
    public User user(long id) {
        User user = null;
        try{
            Connection connection = dataSource.getConnection();
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
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return user;
    }

    //CREATE
    @Override
    public boolean addUser(User user) {
        int rs = 0;

        String date = DateFormat.javaToMysql(user.getDateOfBirth());

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO user (firstname, lastname, date, email, password)\n" +
                    "VALUES (?, ?, ?, ?, ?);");
            pstmt.setObject(1, user.getFirstName());
            pstmt.setObject(2, user.getLastName());
            pstmt.setObject(3, date);
            pstmt.setObject(4, user.getEmail());
            pstmt.setObject(5, user.getPassword());
            rs = pstmt.executeUpdate();
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return (rs == 1);
    }


    //Update
    @Override
    public boolean updateUser(User user) {

        String date = DateFormat.javaToMysql(user.getDateOfBirth());

        int rs = 0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE user SET firstname=?, lastname=?, date=?, email=?, password=? WHERE id=?;");
            pstmt.setObject(1, user.getFirstName());
            pstmt.setObject(2, user.getLastName());
            pstmt.setObject(3, date);
            pstmt.setObject(4, user.getEmail());
            pstmt.setObject(5, user.getPassword());
            pstmt.setObject(6, user.getId());
            rs = pstmt.executeUpdate();
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return (rs == 1);
    }

    @Override
    public boolean updatePictureUser(long id, InputStream profile_picture) {
        return false;
    }

    //DELETE
    @Override
    public boolean deleteUser(long id){

        int rs = 0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM user WHERE id=?;");
            pstmt.setObject(1, id);
            rs = pstmt.executeUpdate();
            connection.close();
        }catch (SQLException ex){

            LOG.log(Level.SEVERE, null, ex);
        }
        return (rs == 1);
    }
}