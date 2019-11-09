package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.buisness.UpdateUser;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.utils.Consts;
import ch.heigvd.amt.projectOne.integration.UsersDaoLocal;

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

        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        User user = (User) req.getSession().getAttribute("user");

        if (action.equals("user")) {

            boolean ok;

            UpdateUser updateUser = new UpdateUser(userDao);
            ok = updateUser.updateUser(req, user.getEmail(), user.getPassword(), user.getId());

            if(ok){
                session.setAttribute("user", userDao.user(user.getId()));
                req.getRequestDispatcher(Consts.JSP_PROFILE).forward(req, resp);
            }
            else{
                req.setAttribute("error", updateUser);
                req.getRequestDispatcher(Consts.JSP_PROFILE).forward(req, resp);
            }
        }
        //TODO PITCURE
        else if(action.equals("picture")){}
    }

}
