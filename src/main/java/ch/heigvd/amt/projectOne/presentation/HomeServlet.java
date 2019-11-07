package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.Registration;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.integration.RegistrationDaoLocal;
import ch.heigvd.amt.projectOne.utils.Consts;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = Consts.SERVLET_HOME)
public class HomeServlet extends HttpServlet {

    @EJB
    RegistrationDaoLocal regDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User)req.getSession().getAttribute("user");

        //PAGINTAION
        ///////////////////////////////////////////////////////////////////////////////////////////////

        int currentPage = 1;
        if(req.getParameter("currentPage") != null){
            currentPage = Integer.valueOf(req.getParameter("currentPage"));
        }

        //WITH PAGINATION
        List<Registration> regs = regDao.allRegUserPagination(user.getId(), currentPage, Consts.ELEMENT_PER_PAGE);


        int rows = regDao.getNumberOfRegsUser(user.getId());

        int numberOfPages = rows / Consts.ELEMENT_PER_PAGE;

        if (numberOfPages % Consts.ELEMENT_PER_PAGE > 0) {
            numberOfPages++;
        }

        req.setAttribute("noOfPages", numberOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("trailPerPage", Consts.ELEMENT_PER_PAGE);
        //////////////////////////////////////////////////////////////////////////////////////////////

        //List<Registration> regs = regDao.allRegUser(user.getId());
        req.setAttribute("regs", regs);
        req.getRequestDispatcher(Consts.JSP_HOME).forward(req, resp);
    }
}
