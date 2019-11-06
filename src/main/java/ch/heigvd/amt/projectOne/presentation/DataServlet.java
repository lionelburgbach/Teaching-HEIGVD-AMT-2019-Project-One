package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.Registration;
import ch.heigvd.amt.projectOne.services.dao.RegistrationDaoLocal;
import ch.heigvd.amt.projectOne.services.dao.TrailDaoLocal;
import ch.heigvd.amt.projectOne.services.dao.UsersDaoLocal;
import ch.heigvd.amt.projectOne.utils.Consts;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = Consts.SERVLET_DATA)
public class DataServlet extends HttpServlet {

    @EJB
    TrailDaoLocal trailDao;

    @EJB
    RegistrationDaoLocal registrationDao;

    @EJB
    UsersDaoLocal usersDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action.equals("registers")) {

            long id = Integer.parseInt(req.getParameter("trail_id"));

            req.setAttribute("trail", trailDao.trail(id));
            List<Registration> reg = registrationDao.allRegTrail(id);
            if(reg.size() != 0) {
                req.setAttribute("regs", registrationDao.allRegTrail(id));
                req.getRequestDispatcher(Consts.JSP_DATA).forward(req, resp);
            }
            else{
                req.setAttribute("error", "No register yet for this Trail!");
                req.getRequestDispatcher(Consts.JSP_DATA).forward(req, resp);
            }
        }
        else if (action.equals("user")) {

            long id = Integer.parseInt(req.getParameter("user_id"));
            req.setAttribute("user", usersDao.user(id));
            req.setAttribute("regs", registrationDao.allRegUser(id));
            req.getRequestDispatcher(Consts.JSP_DATA_USER).forward(req, resp);
        }
    }
}
