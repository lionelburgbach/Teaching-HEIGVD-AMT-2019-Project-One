package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.services.dao.LoginDaoManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @EJB
    private LoginDaoManager loginDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();

        String n=req.getParameter("email");
        String p=req.getParameter("password");

        if(loginDao.connect(n, p)){
            req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
        }
        else{
            req.getSession().removeAttribute("fail");
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
        out.close();
    }
}
