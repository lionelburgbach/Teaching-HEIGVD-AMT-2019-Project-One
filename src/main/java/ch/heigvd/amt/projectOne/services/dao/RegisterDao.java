package ch.heigvd.amt.projectOne.services.dao;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class RegisterDao implements RegisterDaoManager {

    @Resource(lookup = "java:/jdbc/sakila")
    private DataSource dataSource;

    @Override
    public boolean addUser(String firstname, String lastname, Date date, String email, String password) {

        int rs = 0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO user (firstname, lastname, email, password)\n" +
                    "VALUES (?, ?, ?, ?);");
            pstmt.setObject(1, firstname);
            pstmt.setObject(2, lastname);
            //pstmt.setObject(3, date);
            pstmt.setObject(3, email);
            pstmt.setObject(4, password);
            rs = pstmt.executeUpdate();
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rs == 1);
    }
}
