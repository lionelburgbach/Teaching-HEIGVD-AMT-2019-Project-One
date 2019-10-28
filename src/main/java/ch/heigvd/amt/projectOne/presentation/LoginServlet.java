package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.User;
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
    UsersDaoLocal loginDao;

    @EJB
    RegistrationDaoLocal regDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        HttpSession session = req.getSession();

        String email =req.getParameter("email");
        String password =req.getParameter("password");

        User user = loginDao.connect(email, password);

        if(user != null){
            session.setAttribute("user", user);
            req.setAttribute("regs", regDao.allReg(user.getId()));
            req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
        }
        else{
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
    }
}
