package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.Registration;
import ch.heigvd.amt.projectOne.integration.RegistrationDaoLocal;
import ch.heigvd.amt.projectOne.integration.TrailDaoLocal;
import ch.heigvd.amt.projectOne.integration.UsersDaoLocal;
import ch.heigvd.amt.projectOne.utils.Consts;

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
            if(reg.size() != 0) {

                //PAGINTAION
                ///////////////////////////////////////////////////////////////////////////////////////////////

                int currentPage = 1;
                if(req.getParameter("currentPage") != null){
                    currentPage = Integer.valueOf(req.getParameter("currentPage"));
                }

                //WITH PAGINATION
                List<Registration> regs = registrationDao.allRegTrailPagination(id, currentPage, Consts.ELEMENT_PER_PAGE);

                int rows = registrationDao.getNumberOfRegsTrail(id);

                int numberOfPages = rows / Consts.ELEMENT_PER_PAGE;

                if (numberOfPages % Consts.ELEMENT_PER_PAGE > 0) {
                    numberOfPages++;
                }

                req.setAttribute("noOfPages", numberOfPages);
                req.setAttribute("currentPage", currentPage);
                req.setAttribute("trailPerPage", Consts.ELEMENT_PER_PAGE);
                //////////////////////////////////////////////////////////////////////////////////////////////

                req.setAttribute("regs", regs);
                req.getRequestDispatcher(Consts.JSP_DATA).forward(req, resp);
            }
            else{
                req.setAttribute("error", "No register yet for this Trail!");
                req.getRequestDispatcher(Consts.JSP_DATA).forward(req, resp);
            }
        }
        else if (action.equals("user")) {

            long id = Integer.parseInt(req.getParameter("id_user"));
            req.setAttribute("user", usersDao.user(id));

            //PAGINTAION
            ///////////////////////////////////////////////////////////////////////////////////////////////

            int currentPage = 1;
            if(req.getParameter("currentPage") != null){
                currentPage = Integer.valueOf(req.getParameter("currentPage"));
            }

            //WITH PAHINATION
            List<Registration> regs = registrationDao.allRegUserPagination(id, currentPage, Consts.ELEMENT_PER_PAGE);


            int rows = registrationDao.getNumberOfRegsUser(id);

            int numberOfPages = rows / Consts.ELEMENT_PER_PAGE;

            if (numberOfPages % Consts.ELEMENT_PER_PAGE > 0) {
                numberOfPages++;
            }

            req.setAttribute("noOfPages", numberOfPages);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("trailPerPage", Consts.ELEMENT_PER_PAGE);
            //////////////////////////////////////////////////////////////////////////////////////////////


            req.setAttribute("regs", regs);
            req.getRequestDispatcher(Consts.JSP_DATA_USER).forward(req, resp);
        }

    }
}
