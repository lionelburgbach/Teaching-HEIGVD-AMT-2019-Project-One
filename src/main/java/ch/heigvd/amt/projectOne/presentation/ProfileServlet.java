package ch.heigvd.amt.projectOne.presentation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("email") != null) {
            req.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("login");
        }
    }
}
