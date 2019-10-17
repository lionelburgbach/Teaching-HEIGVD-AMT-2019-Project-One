package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.User;

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
public class UserManager implements UsersManagerLocal {

    @Resource(lookup = "java:/jdbc/sakila")
    private DataSource dataSource;

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try{
            Connection connection = dataSource.getConnection();
            System.out.println("Schema" + connection.getSchema());
            System.out.println("Catalog" + connection.getCatalog());
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String firstname = rs.getString("firstname");
                System.out.println(firstname);
                String lastname = rs.getString("lastname");
                System.out.println(lastname);
                String email = rs.getString("email");
                String password = rs.getString("password");
                long id = rs.getLong("id");
                users.add(new User(id, email, password, firstname, lastname));
            }
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
}