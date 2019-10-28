package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.services.dao.TrailDaoLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/trail")
public class TrailServlet extends HttpServlet {

    @EJB
    TrailDaoLocal trailManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        req.setAttribute("trails", trailManager.allTrail());
        req.getRequestDispatcher("/WEB-INF/pages/trail.jsp").forward(req, resp);
    }
}
