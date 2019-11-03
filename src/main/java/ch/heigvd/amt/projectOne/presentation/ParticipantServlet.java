package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.model.Utils;
import ch.heigvd.amt.projectOne.services.dao.UsersDaoLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/participant")
public class ParticipantServlet extends HttpServlet {

    @EJB
    UsersDaoLocal userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("user") != null) {
            req.getRequestDispatcher("/WEB-INF/pages/participant.jsp").forward(req, resp);
        }
        else {
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("user") != null) {
          /*  User participant = userDao.participant(Integer.parseInt(req.getParameter("participant_id")));
            req.setAttribute("firstname",participant.getFirstName());
            req.setAttribute("lastname",participant.getLastName());
           */
            req.setAttribute("participant",userDao.participant(Integer.parseInt(req.getParameter("participant_id"))));
            req.getRequestDispatcher("/WEB-INF/pages/participant.jsp").forward(req, resp);
        }
        else{
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
    }
}
