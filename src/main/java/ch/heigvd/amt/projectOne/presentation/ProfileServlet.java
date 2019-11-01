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

@WebServlet(urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

    @EJB
    UsersDaoLocal userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("user") != null) {
            req.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(req, resp);
        }
        else {
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("user") != null) {

            HttpSession session = req.getSession();

            User user = (User) req.getSession().getAttribute("user");
            String f = req.getParameter("firstname");
            String l = req.getParameter("lastname");
            String d = req.getParameter("date");
            String e = req.getParameter("email");
            String p = req.getParameter("password");
            if(p.equals("")) {
                p = user.getPassword();
            }
            else{
                p = Utils.getCryptoHash(p);
            }

            String[] s = d.split("-");
            String year = s[0];
            String month = s[1];
            String day = s[2];
            String date = year + "/" + month + "/" + day;

            if (userDao.updateUser(user.getId(), f, l, date, e, p)) {
                session.setAttribute("user", userDao.user(user.getId()));
                req.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(req, resp);
            } else {
                PrintWriter pw= resp.getWriter();
                pw.println("<script type=\"text/javascript\">");
                pw.println("alert('Fail to Update');");
                pw.println("</script>");
                req.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(req, resp);
            }
        }
        else{
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
    }
}
