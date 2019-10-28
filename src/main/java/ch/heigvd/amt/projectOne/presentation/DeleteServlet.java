package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.Registration;
import ch.heigvd.amt.projectOne.model.Trail;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.services.dao.RegistrationDaoLocal;
import ch.heigvd.amt.projectOne.services.dao.TrailDaoLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delTrail")
public class DeleteServlet extends HttpServlet {

    @EJB
    RegistrationDaoLocal registrationDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            long id = Long.parseLong(req.getParameter("reg_id"));
            registrationDao.deleteReg(id);
            User user = (User)req.getSession().getAttribute("user");
            req.setAttribute("regs", registrationDao.allReg(user.getId()));
            req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("login");
        }
    }
}
