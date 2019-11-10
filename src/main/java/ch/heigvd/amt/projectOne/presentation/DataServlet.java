package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.Registration;
import ch.heigvd.amt.projectOne.integration.RegistrationDaoLocal;
import ch.heigvd.amt.projectOne.integration.TrailDaoLocal;
import ch.heigvd.amt.projectOne.integration.UsersDaoLocal;
import ch.heigvd.amt.projectOne.utils.Consts;
import ch.heigvd.amt.projectOne.utils.Pagination;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = Consts.SERVLET_DATA)
public class DataServlet extends HttpServlet {

    @EJB
    TrailDaoLocal trailDao;

    @EJB
    RegistrationDaoLocal registrationDao;

    @EJB
    UsersDaoLocal usersDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action.equals("registers")) {

            long id = Integer.parseInt(req.getParameter("id_trail"));
            req.setAttribute("trail", trailDao.trail(id));

            List<Registration> reg = registrationDao.allRegTrail(id);

            if (reg.size() != 0) {

                int currentPage = 1;
                if (req.getParameter(Consts.CURRENT_PAGE) != null){
                    currentPage = Integer.valueOf(req.getParameter(Consts.CURRENT_PAGE));
                }

                List<Registration> regs = registrationDao.allRegTrailPagination(id, currentPage, Consts.ELEMENT_PER_PAGE);

                int rows = registrationDao.getNumberOfRegsTrail(id);

                //Param for the pagination
                req.setAttribute(Consts.NO_OF_PAGES, Pagination.getNumberPages(rows, Consts.ELEMENT_PER_PAGE));
                req.setAttribute(Consts.CURRENT_PAGE, currentPage);
                req.setAttribute(Consts.ELEM_PER_PAGE_JSP, Consts.ELEMENT_PER_PAGE);

                req.setAttribute("regs", regs);
                req.getRequestDispatcher(Consts.JSP_DATA).forward(req, resp);

            } else{

                req.setAttribute("error", "No register yet for this Trail!");
                req.getRequestDispatcher(Consts.JSP_DATA).forward(req, resp);
            }

        } else if (action.equals("user")) {

            long id = Integer.parseInt(req.getParameter("id_user"));
            req.setAttribute("user", usersDao.user(id));

            int currentPage = 1;
            if (req.getParameter(Consts.CURRENT_PAGE) != null){
                currentPage = Integer.valueOf(req.getParameter(Consts.CURRENT_PAGE));
            }
            List<Registration> regs = registrationDao.allRegUserPagination(id, currentPage, Consts.ELEMENT_PER_PAGE);

            int rows = registrationDao.getNumberOfRegsUser(id);

            //Param for the pagination
            req.setAttribute(Consts.NO_OF_PAGES, Pagination.getNumberPages(rows, Consts.ELEMENT_PER_PAGE));
            req.setAttribute(Consts.CURRENT_PAGE, currentPage);
            req.setAttribute(Consts.ELEM_PER_PAGE_JSP, Consts.ELEMENT_PER_PAGE);

            req.setAttribute("regs", regs);
            req.getRequestDispatcher(Consts.JSP_DATA_USER).forward(req, resp);
        }
    }
}
