package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.services.dao.RegisterDaoManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/registration")
public class RegisterServlet extends HttpServlet {


    @EJB
    private RegisterDaoManager registerDao;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();

        String f=req.getParameter("firstname");
        String l=req.getParameter("lastname");
        String d=req.getParameter("date");
        String e=req.getParameter("email");
        String p=req.getParameter("password");

        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        registerDao.addUser(f, l, date, e, p);

        if(registerDao.addUser(f, l, date, e, p)){
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
        else{
            req.getSession().removeAttribute("fail");
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
        out.close();
    }
}
