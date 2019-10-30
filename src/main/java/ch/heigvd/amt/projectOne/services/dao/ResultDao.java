package ch.heigvd.amt.projectOne.services.dao;

import ch.heigvd.amt.projectOne.model.Registration;
import ch.heigvd.amt.projectOne.model.Result;

import javax.annotation.Resource;
import javax.ejb.EJB;
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
public class ResultDao implements ResultDaoLocal {

    @Resource(lookup = "java:/jdbc/sakila")
    private DataSource dataSource;

    private static final Logger LOG = Logger.getLogger(TrailDao.class.getName());

    @EJB
    RegistrationDaoLocal registrationDao;

    @Override
    public List<Result> allResultUser(long idUser) {

        List<Result> results = new ArrayList<>();

        List<Registration> registrations = registrationDao.allRegWithResUser(idUser);

        for (Registration reg: registrations) {

            try {
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement("SELECT DISTINCT * FROM result AS res INNER JOIN registration AS reg ON reg.id=res.id_reg_fk WHERE reg.id=?;");
                pstmt.setObject(1, reg.getId());
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    long id = Long.parseLong(rs.getString("id"));
                    int time = Integer.parseInt(rs.getString("time"));
                    results.add(new Result(id, reg, time));
                }
                connection.close();
            } catch (SQLException ex) {

                LOG.log(Level.SEVERE, null, ex);
            }
        }
        return results;
    }

    @Override
    public List<Result> allResultTrail(long idTrail) {

        List<Result> results = new ArrayList<>();

        List<Registration> registrations = registrationDao.allRegWithResTrail(idTrail);

        for (Registration reg: registrations) {

            try {
                Connection connection = dataSource.getConnection();
                PreparedStatement pstmt = connection.prepareStatement("SELECT DISTINCT * FROM result AS res INNER JOIN registration AS reg ON reg.id=res.id_reg_fk WHERE reg.id=? ORDER BY res.time ASC;");
                pstmt.setObject(1, reg.getId());
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    long id = Long.parseLong(rs.getString("id"));
                    int time = Integer.parseInt(rs.getString("time"));
                    results.add(new Result(id, reg, time));
                }
                connection.close();
            } catch (SQLException ex) {

                LOG.log(Level.SEVERE, null, ex);
            }
        }
        return results;
    }
}
