package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class UserDao implements UsersDaoLocal {

    @Resource(lookup = "java:/jdbc/sakila")
    private DataSource dataSource;

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
                String emailUser = rs.getString("email");
                String passwordUser = rs.getString("password");
                String date = rs.getString("date");
                user = new User(id, emailUser, passwordUser, firstname, lastname, date);
            }
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
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
                String mail = rs.getString("email");
                String password = rs.getString("password");
                String date = rs.getString("date");
                user = new User(id, mail, password, firstname, lastname, date);
            }
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    //CREATE
    @Override
    public boolean addUser(String firstname, String lastname, String date, String email, String password) {

        int rs = 0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO user (firstname, lastname, email, password, date)\n" +
                    "VALUES (?, ?, ?, ?, ?);");
            pstmt.setObject(1, firstname);
            pstmt.setObject(2, lastname);
            pstmt.setObject(3, email);
            pstmt.setObject(4, password);
            pstmt.setObject(5, date);
            rs = pstmt.executeUpdate();
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rs == 1);
    }

    //UPDATE
    @Override
    public boolean updateUser(String firstname, String lastname, String date, String email, String password) {

        int rs = 0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("UPDATE user SET firstname=?, lastname=?, email=?, password=?, date=?");
            pstmt.setObject(1, firstname);
            pstmt.setObject(2, lastname);
            pstmt.setObject(3, email);
            pstmt.setObject(4, password);
            pstmt.setObject(5, date);
            rs = pstmt.executeUpdate();
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rs == 1);
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

            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rs == 1);
    }
}