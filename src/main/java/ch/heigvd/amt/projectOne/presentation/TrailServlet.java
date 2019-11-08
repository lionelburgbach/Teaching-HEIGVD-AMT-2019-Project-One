package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.Trail;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.integration.TrailDaoLocal;
import ch.heigvd.amt.projectOne.utils.Consts;
import ch.heigvd.amt.projectOne.utils.DateFormat;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebServlet(urlPatterns = Consts.SERVLET_TRAIL)
public class TrailServlet extends HttpServlet {

    @EJB
    TrailDaoLocal trailManager;

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
            List<Trail> trails = trailManager.allTrailToComeWithNoRegPagination(user.getId(), currentPage, Consts.ELEMENT_PER_PAGE);

            int rows = trailManager.getNumberOfTrailsToComeWithNoReg(user.getId());

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
            List<Trail> trails = trailManager.allTrailPagination(currentPage, Consts.ELEMENT_PER_PAGE);


            int rows = trailManager.getNumberOfTrails();

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

        String name = req.getParameter("name");
        double distance = Double.parseDouble(req.getParameter("distance"));
        double upAndDown = Double.parseDouble(req.getParameter("upAndDown"));
        String description = req.getParameter("description");
        String date = req.getParameter("date");

        boolean d = DateFormat.correctFormatDate(date);
        //TODO controle
        try {
            d = DateFormat.possibleDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(d){
            req.setAttribute("error", "Wrong Date Format");
        }

        if (trailManager.addTrail(new Trail(name, distance, upAndDown, description, date)) != -1) {

            resp.setContentType("text/html;charset=UTF-8");
            resp.sendRedirect(req.getContextPath()+Consts.SERVLET_TRAIL);
        }
    }
}
