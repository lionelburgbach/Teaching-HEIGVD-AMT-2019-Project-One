package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.Registration;
import ch.heigvd.amt.projectOne.model.Trail;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.services.dao.RegistrationDaoLocal;
import ch.heigvd.amt.projectOne.services.dao.TrailDaoLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    @EJB
    RegistrationDaoLocal registrationDao;

    @EJB
    TrailDaoLocal trailDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (req.getSession().getAttribute("user") != null) {

            if (action.equals("enroll")) {

                User user = (User) req.getSession().getAttribute("user");
                Trail trail = trailDao.trail(Integer.parseInt(req.getParameter("trail_id")));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                String s = formatter.format(date);
                Registration reg = new Registration(user, trail, s);
                registrationDao.addReg(user.getId(), trail.getId(), reg.getCategory(), s);
                req.setAttribute("regs", registrationDao.allReg(user.getId()));
                req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
            }
            else if (action.equals("delReg")) {

                long id = Long.parseLong(req.getParameter("reg_id"));
                registrationDao.deleteReg(id);
                User user = (User) req.getSession().getAttribute("user");
                req.setAttribute("regs", registrationDao.allReg(user.getId()));
                req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
            }
        }
        else {
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
    }
}
