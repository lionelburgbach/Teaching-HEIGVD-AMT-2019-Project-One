package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.services.dao.RegistrationDaoLocal;
import ch.heigvd.amt.projectOne.utils.Consts;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = Consts.SERVLET_HOME)
public class HomeServlet extends HttpServlet {

    @EJB
    RegistrationDaoLocal regDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User)req.getSession().getAttribute("user");
        req.setAttribute("regs", regDao.allReg(user.getId()));
        req.getRequestDispatcher(Consts.JSP_HOME).forward(req, resp);
    }
}
