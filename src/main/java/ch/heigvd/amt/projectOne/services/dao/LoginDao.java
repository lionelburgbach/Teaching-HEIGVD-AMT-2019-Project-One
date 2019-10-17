package ch.heigvd.amt.projectOne.services.dao;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class LoginDao implements LoginDaoManager {

    @Resource(lookup = "java:/jdbc/sakila")
    private DataSource dataSource;

    @Override
    public boolean connect(String email, String password) {

        boolean connec = false;
        int count = 0;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT COUNT(*) FROM user WHERE email=? AND password=?");
            pstmt.setObject(1, email);
            pstmt.setObject(2, password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                count = rs.getInt(1);
            }
            if(count == 1){
                connec = true;
            }
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connec;
    }
}
