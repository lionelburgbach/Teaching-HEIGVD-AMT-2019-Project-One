package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.buisness.RegistrationTrail;
import ch.heigvd.amt.projectOne.model.Trail;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.integration.TrailDaoLocal;
import ch.heigvd.amt.projectOne.utils.Consts;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = Consts.SERVLET_TRAIL)
public class TrailServlet extends HttpServlet {

    @EJB
    TrailDaoLocal trailDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        resp.setContentType("text/html;charset=UTF-8");

        if (session.getAttribute("user") != null) {

            User user = (User) req.getSession().getAttribute("user");

            //PAGINTAION
            ///////////////////////////////////////////////////////////////////////////////////////////////

            int currentPage = 1;
            if(req.getParameter("currentPage") != null){
                currentPage = Integer.valueOf(req.getParameter("currentPage"));
            }

            //WITH PAGINATION
            List<Trail> trails = trailDao.allTrailToComeWithNoRegPagination(user.getId(), currentPage, Consts.ELEMENT_PER_PAGE);

            int rows = trailDao.getNumberOfTrailsToComeWithNoReg(user.getId());

            int numberOfPages = rows / Consts.ELEMENT_PER_PAGE;

            if (numberOfPages % Consts.ELEMENT_PER_PAGE > 0) {
                numberOfPages++;
            }

            req.setAttribute("noOfPages", numberOfPages);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("trailPerPage", Consts.ELEMENT_PER_PAGE);
            //////////////////////////////////////////////////////////////////////////////////////////////

            //WITHOUT PAGINATION
            //List<Trail> trails = trailManager.allTrailToComeWithNoReg(user.getId());

            req.setAttribute("trails", trails);
            req.getRequestDispatcher(Consts.JSP_TRAIL).forward(req, resp);
        }
        else {

            //PAGINTAION
            ///////////////////////////////////////////////////////////////////////////////////////////////

            int currentPage = 1;
            if(req.getParameter("currentPage") != null){
                currentPage = Integer.valueOf(req.getParameter("currentPage"));
            }

            //WITH PAGINATION
            List<Trail> trails = trailDao.allTrailPagination(currentPage, Consts.ELEMENT_PER_PAGE);


            int rows = trailDao.getNumberOfTrails();

            int numberOfPages = rows / Consts.ELEMENT_PER_PAGE;

            if (numberOfPages % Consts.ELEMENT_PER_PAGE > 0) {
                numberOfPages++;
            }

            req.setAttribute("noOfPages", numberOfPages);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("trailPerPage", Consts.ELEMENT_PER_PAGE);
            //////////////////////////////////////////////////////////////////////////////////////////////

            //WITHOUT PAGINATION
            //List<Trail> trails = trailManager.allTrail();

            req.setAttribute("trails", trails);
            req.getRequestDispatcher(Consts.JSP_TRAIL).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RegistrationTrail registrationTrail = new RegistrationTrail(trailDao);

        boolean res = registrationTrail.registrationTrail(req);

        if(res){

            resp.setContentType("text/html;charset=UTF-8");
            resp.sendRedirect(req.getContextPath() + Consts.SERVLET_TRAIL);
        }
        else {

            req.setAttribute("error", registrationTrail);
            req.getRequestDispatcher(Consts.JSP_TRAIL).forward(req, resp);
        }
    }
}
