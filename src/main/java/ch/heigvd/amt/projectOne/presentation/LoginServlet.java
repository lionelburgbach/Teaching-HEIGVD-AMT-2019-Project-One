package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.business.Identification;
import ch.heigvd.amt.projectOne.business.RegistrationUser;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.utils.Consts;
import ch.heigvd.amt.projectOne.integration.UsersDaoLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = Consts.SERVLET_LOGIN)
public class LoginServlet extends HttpServlet {

    @EJB
    UsersDaoLocal userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        switch (action) {
            case "login":
                req.getRequestDispatcher(Consts.JSP_LOGIN).forward(req, resp);
                break;
            case "register":
                req.getRequestDispatcher(Consts.JSP_REGISTER).forward(req, resp);
                break;
            case "logout":
                resp.setContentType("text/html;charset=UTF-8");
                req.getSession().invalidate();
                resp.sendRedirect(req.getContextPath() + Consts.SERVLET_TRAIL);
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        User user = null;
        resp.setContentType("text/html;charset=UTF-8");

        if (action.equals("login")) {

            Identification ident = new Identification(userDao);
            user = ident.login(req);

            if (user != null) {
                session.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath()+Consts.SERVLET_TRAIL);
            }
            else {
                req.getSession().removeAttribute("user");
                req.setAttribute("error", ident);
                req.getRequestDispatcher(Consts.JSP_LOGIN).forward(req, resp);
            }

        } else if (action.equals("registration")){

            RegistrationUser newUser = new RegistrationUser(userDao);
            user = newUser.registrationUser(req);

            if (user != null){

                req.setAttribute("regUser", newUser);
                session.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + Consts.SERVLET_TRAIL);

            } else {

                req.setAttribute( "error", newUser );
                req.getRequestDispatcher(Consts.JSP_REGISTER).forward(req, resp);
            }

        } else {
            req.getSession().removeAttribute("user");
            req.getRequestDispatcher(Consts.JSP_LOGIN).forward(req, resp);
        }
    }
}
