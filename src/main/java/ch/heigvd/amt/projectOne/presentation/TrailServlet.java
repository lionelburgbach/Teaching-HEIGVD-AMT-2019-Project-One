package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.Trail;
import ch.heigvd.amt.projectOne.services.dao.TrailDaoLocal;
import ch.heigvd.amt.projectOne.services.dao.UsersDaoLocal;
import ch.heigvd.amt.projectOne.utils.Consts;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        resp.setContentType("text/html;charset=UTF-8");
        List<Trail> trails =  trailManager.allTrailToCome();
        req.setAttribute("trails", trails);
        req.getRequestDispatcher(Consts.JSP_TRAIL).forward(req, resp);
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

            String[] s = date.split("-");
            String day = s[0];
            String month = s[1];
            String year = s[2];
            String dates = year + "/" + month + "/" + day;

            if (trailManager.addTrail(name, distance, upAndDown, description, capacity, dates)) {

                resp.setContentType("text/html;charset=UTF-8");
                resp.sendRedirect(req.getContextPath()+Consts.SERVLET_TRAIL);
            }
        }
        else if (action.equals("data")) {

            req.setAttribute("trailer", userDao.dataUser(Integer.parseInt(req.getParameter("trailer_id"))));
            req.getRequestDispatcher(Consts.JSP_DATA).forward(req, resp);
        }
    }
}
