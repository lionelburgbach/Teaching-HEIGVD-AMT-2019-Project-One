package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.utils.Consts;
import ch.heigvd.amt.projectOne.utils.Crypto;
import ch.heigvd.amt.projectOne.integration.UsersDaoLocal;
import ch.heigvd.amt.projectOne.utils.DateFormat;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = Consts.SERVLET_PROFILE)
public class ProfileServlet extends HttpServlet {

    @EJB
    UsersDaoLocal userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(Consts.JSP_PROFILE).forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        User user = (User) req.getSession().getAttribute("user");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String date = req.getParameter("date");
        //TODO USE THIS METHOD
        DateFormat.correctFormatDate(date);
        String email = user.getEmail();
        String password = req.getParameter("password");

        if (password.equals("")) {
            password = user.getPassword();
        } else {
            password = Crypto.getCryptoHash(password);
        }

        if (userDao.updateUser(new User(user.getId(), firstName, lastName, date, email, password))) {
            session.setAttribute("user", userDao.user(user.getId()));
            req.getRequestDispatcher(Consts.JSP_PROFILE).forward(req, resp);
        } else {
            //TODO fail to update
            req.getRequestDispatcher(Consts.JSP_PROFILE).forward(req, resp);
        }
    }
}
