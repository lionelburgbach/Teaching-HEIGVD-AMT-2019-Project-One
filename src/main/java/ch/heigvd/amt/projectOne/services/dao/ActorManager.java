package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.Actor;

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
public class ActorManager implements ActorsManagerLocal {

    @Resource(lookup = "java:/jdbc/sakila")
    private DataSource dataSource;

    @Override
    public List<Actor> findAllActors() {
        List<Actor> actors = new ArrayList<>();
        try{
            Connection connection = dataSource.getConnection();
            System.out.println("Schema" + connection.getSchema());
            System.out.println("Catalog" + connection.getCatalog());
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM actor");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                long id = rs.getLong("actor_id");
                actors.add(new Actor(id, firstname, lastname));
            }
            connection.close();
        }catch (SQLException ex){

            Logger.getLogger(ActorManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actors;
    }
}