package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.services.dao.UsersDaoManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

@WebServlet(urlPatterns = "/registration")
public class RegisterServlet extends HttpServlet {

    @EJB
    private UsersDaoManager userManager;

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

        String[] s = d.split("-");
        String day = s[0];
        String month = s[1];
        String year = s[2];
        String date = year+"/"+month+"/"+day;

        int yearInt = Integer.parseInt(year);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int diff = currentYear-yearInt;
        int category = category(diff);

        if(userManager.addUser(f, l, date, e, p,category)){
            req.setAttribute("email", e);
            req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
        }
        else{
            req.getSession().removeAttribute("fail");
            req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        }
        out.close();
    }

    public int category(int diff){

        if(diff <= 10){
            return 1;
        }
        else if(diff > 10 && diff <= 16){
            return 2;
        }
        else if(diff > 16 && diff <= 50){
            return 3;
        }
        else if(diff > 50){
            return  4;
        }
        return -1;
    }
}
