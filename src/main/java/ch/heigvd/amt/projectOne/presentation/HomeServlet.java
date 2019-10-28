package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.Trail;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.services.dao.RegistrationDaoLocal;
import ch.heigvd.amt.projectOne.services.dao.TrailDao;
import ch.heigvd.amt.projectOne.services.dao.TrailDaoLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    @EJB
    RegistrationDaoLocal regDao;

    @EJB
    TrailDaoLocal trailDao;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            User user = (User)req.getSession().getAttribute("user");
            req.setAttribute("regs", regDao.allReg(user.getId()));
            req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("login");
        }
    }
}
