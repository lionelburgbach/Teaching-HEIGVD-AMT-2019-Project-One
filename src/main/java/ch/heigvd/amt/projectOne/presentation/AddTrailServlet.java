package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.services.dao.TrailDaoLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/addTrail")
public class AddTrailServlet extends HttpServlet {

    @EJB
    TrailDaoLocal trailManager;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

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
        String dates = year+"/"+month+"/"+day;

        if(trailManager.addTrail(name, distance, upAndDown, description, capacity, dates)){
            resp.setContentType("text/html;charset=UTF-8");
            req.setAttribute("trails", trailManager.allTrail());
            req.getRequestDispatcher("/WEB-INF/pages/trail.jsp").forward(req, resp);
        }
        else{
            req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
        }

    }
}
