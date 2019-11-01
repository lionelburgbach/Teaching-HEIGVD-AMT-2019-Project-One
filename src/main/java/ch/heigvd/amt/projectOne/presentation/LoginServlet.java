package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.model.Utils;
import ch.heigvd.amt.projectOne.services.dao.RegistrationDaoLocal;
import ch.heigvd.amt.projectOne.services.dao.UsersDaoLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @EJB
    UsersDaoLocal userDao;

    @EJB
    RegistrationDaoLocal regDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if(action.equals("login")) {

            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
        else if(action.equals("logout")){

            resp.setContentType("text/html;charset=UTF-8");
            req.getSession().invalidate();
            req.getRequestDispatcher("/trail").forward(req, resp);
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

            password = Utils.getCryptoHash(password);
            user = userDao.connect(email, password);

            if (user != null) {
                session.setAttribute("user", user);
                req.setAttribute("regs", regDao.allReg(user.getId()));
                req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
            } else {
                req.getSession().removeAttribute("user");
                req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
            }
        }
        else if(action.equals("registration")){

            String f=req.getParameter("firstname");
            String l=req.getParameter("lastname");
            String d=req.getParameter("date");
            String e=req.getParameter("email");
            String p=req.getParameter("password");

            p = Utils.getCryptoHash(p);

            String[] s = d.split("-");
            String day = s[0];
            String month = s[1];
            String year = s[2];
            String date = year+"/"+month+"/"+day;

            if(userDao.addUser(f, l, date, e, p)){
                user = userDao.connect(e,p);
                if (user != null) {
                    session.setAttribute("user", user);
                    req.setAttribute("regs", regDao.allReg(user.getId()));
                    req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
                }
            }
            else{
                req.getSession().removeAttribute("user");
                req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
            }
        }
    }
}
