package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.Registration;
import ch.heigvd.amt.projectOne.model.Trail;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.integration.RegistrationDaoLocal;
import ch.heigvd.amt.projectOne.integration.TrailDaoLocal;
import ch.heigvd.amt.projectOne.utils.Consts;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = Consts.SERVLET_REGISTRATION)
public class RegistrationServlet extends HttpServlet {

    @EJB
    RegistrationDaoLocal registrationDao;

    @EJB
    TrailDaoLocal trailDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String action = req.getParameter("action");
        User user = (User) req.getSession().getAttribute("user");

        if (action.equals("enroll")) {

            Trail trail = trailDao.trail(Integer.parseInt(req.getParameter("trail_id")));
            registrationDao.addReg(new Registration(user, trail));
        }
        else if (action.equals("delReg")) {

            long id = Long.parseLong(req.getParameter("reg_id"));
            registrationDao.deleteReg(id);
        }

        resp.sendRedirect(req.getContextPath()+Consts.SERVLET_HOME);
    }
}
