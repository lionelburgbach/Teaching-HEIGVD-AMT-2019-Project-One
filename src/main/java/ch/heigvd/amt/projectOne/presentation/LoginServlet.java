package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.utils.Consts;
import ch.heigvd.amt.projectOne.utils.Crypto;
import ch.heigvd.amt.projectOne.services.dao.UsersDaoLocal;

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

        if(action.equals("login")) {

            req.getRequestDispatcher(Consts.JSP_LOGIN).forward(req, resp);
        }
        else if(action.equals("logout")){

            resp.setContentType("text/html;charset=UTF-8");
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath()+Consts.SERVLET_TRAIL);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        User user = null;
        resp.setContentType("text/html;charset=UTF-8");

        if(action.equals("login")) {

            String email = req.getParameter("email");
            String password = req.getParameter("password");

            password = Crypto.getCryptoHash(password);
            user = userDao.connect(email, password);

            if (user != null) {
                session.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath()+Consts.SERVLET_TRAIL);
            } else {
                req.getSession().removeAttribute("user");
                //session.setAttribute("error", "Wrong password or email");
                req.getRequestDispatcher(Consts.JSP_LOGIN).forward(req, resp);
            }
        }
        else if(action.equals("registration")){

            String f=req.getParameter("firstname");
            String l=req.getParameter("lastname");
            String d=req.getParameter("date");
            String e=req.getParameter("email");
            String p=req.getParameter("password");

            p = Crypto.getCryptoHash(p);

            String[] s = d.split("-");
            String day = s[0];
            String month = s[1];
            String year = s[2];
            String date = year+"/"+month+"/"+day;

            if(userDao.addUser(f, l, date, e, p)){
                user = userDao.connect(e,p);
                if (user != null) {
                    session.setAttribute("user", user);
                    resp.sendRedirect(req.getContextPath()+Consts.SERVLET_TRAIL);
                }
            }
            else{
                req.getSession().removeAttribute("user");
                req.getRequestDispatcher(Consts.JSP_LOGIN).forward(req, resp);
            }
        }
    }
}
