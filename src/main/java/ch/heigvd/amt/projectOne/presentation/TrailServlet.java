package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.buisness.RegistrationTrail;
import ch.heigvd.amt.projectOne.model.Trail;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.integration.TrailDaoLocal;
import ch.heigvd.amt.projectOne.utils.Consts;
import ch.heigvd.amt.projectOne.utils.Pagination;

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

        int currentPage = 1;
        if (req.getParameter(Consts.CURRENT_PAGE) != null){
            currentPage = Integer.valueOf(req.getParameter(Consts.CURRENT_PAGE));
        }

        if (session.getAttribute("user") != null) {

            User user = (User) req.getSession().getAttribute("user");

            //LIST WITH PAGINATION
            List<Trail> trails = trailDao.allTrailToComeWithNoRegPagination(user.getId(), currentPage, Consts.ELEMENT_PER_PAGE);

            int rows = trailDao.getNumberOfTrailsToComeWithNoReg(user.getId());

            req.setAttribute(Consts.NO_OF_PAGES, Pagination.getNumberPages(rows, Consts.ELEMENT_PER_PAGE));
            req.setAttribute(Consts.CURRENT_PAGE, currentPage);
            req.setAttribute(Consts.ELEM_PER_PAGE_JSP, Consts.ELEMENT_PER_PAGE);

            //LIST WITH NO PAGINATION
            //List<Trail> trails = trailDao.allTrailToComeWithNoReg(user.getId());

            req.setAttribute("trails", trails);
            req.getRequestDispatcher(Consts.JSP_TRAIL).forward(req, resp);

        } else {

            //LIST WITH PAGINATION
            List<Trail> trails = trailDao.allTrailPagination(currentPage, Consts.ELEMENT_PER_PAGE);

            int rows = trailDao.getNumberOfTrails();

            req.setAttribute(Consts.NO_OF_PAGES, Pagination.getNumberPages(rows, Consts.ELEMENT_PER_PAGE));
            req.setAttribute(Consts.CURRENT_PAGE, currentPage);
            req.setAttribute(Consts.ELEM_PER_PAGE_JSP , Consts.ELEMENT_PER_PAGE);

            //LIST WITH NO PAGINATION
            //List<Trail> trails = trailDao.allTrail();

            req.setAttribute("trails", trails);
            req.getRequestDispatcher(Consts.JSP_TRAIL).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RegistrationTrail registrationTrail = new RegistrationTrail(trailDao);

        boolean res = registrationTrail.registrationTrail(req);

        if (res){

            resp.setContentType("text/html;charset=UTF-8");
            resp.sendRedirect(req.getContextPath() + Consts.SERVLET_TRAIL);

        } else {

            req.setAttribute("error", registrationTrail);
            req.setAttribute(Consts.CURRENT_PAGE, 1);
            req.setAttribute("fail", "falg");
            req.getRequestDispatcher(Consts.JSP_TRAIL).forward(req, resp);
        }
    }
}
