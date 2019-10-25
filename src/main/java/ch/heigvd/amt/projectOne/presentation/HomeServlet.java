package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.services.dao.UsersDaoLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    @EJB
    UsersDaoLocal usersDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("login");
        }
    }
}
