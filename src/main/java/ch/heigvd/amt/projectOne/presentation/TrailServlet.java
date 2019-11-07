package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.Trail;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.integration.TrailDaoLocal;
import ch.heigvd.amt.projectOne.integration.UsersDaoLocal;
import ch.heigvd.amt.projectOne.utils.Consts;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = Consts.SERVLET_TRAIL)
public class TrailServlet extends HttpServlet {

    @EJB
    TrailDaoLocal trailManager;

    @EJB
    UsersDaoLocal userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        resp.setContentType("text/html;charset=UTF-8");

        if (session.getAttribute("user") != null) {
            User user = (User) req.getSession().getAttribute("user");
            List<Trail> trails = trailManager.allTrailToComeWithNoReg(user.getId());
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

            List<Trail> trails = trailManager.findTrail(currentPage, Consts.TrailPerPage);

            int rows = trailManager.getNumberOfTrails();

            int numberOfPages = rows / Consts.TrailPerPage;

            if (numberOfPages % Consts.TrailPerPage > 0) {
                numberOfPages++;
            }

            req.setAttribute("noOfPages", numberOfPages);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("trailPerPage", Consts.TrailPerPage);
            //////////////////////////////////////////////////////////////////////////////////////////////

            //List<Trail> trails = trailManager.allTrail();
            req.setAttribute("trails", trails);
            req.getRequestDispatcher(Consts.JSP_TRAIL).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action.equals("addTrail")) {

            String name = req.getParameter("name");
            double distance = Double.parseDouble(req.getParameter("distance"));
            double upAndDown = Double.parseDouble(req.getParameter("upAndDown"));
            String description = req.getParameter("description");
            int capacity = Integer.parseInt(req.getParameter("capacity"));
            String date = req.getParameter("date");

            if (trailManager.addTrail(new Trail(name, distance, upAndDown, description, capacity, date))) {

                resp.setContentType("text/html;charset=UTF-8");
                resp.sendRedirect(req.getContextPath()+Consts.SERVLET_TRAIL);
            }
        }
        else if (action.equals("data")) {

            req.setAttribute("trailer", userDao.user(Integer.parseInt(req.getParameter("trailer_id"))));
            req.getRequestDispatcher(Consts.JSP_DATA).forward(req, resp);
        }
    }
}
