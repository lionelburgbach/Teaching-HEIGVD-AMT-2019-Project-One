package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.integration.UserDao;
import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.utils.Consts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.ejb.DuplicateKeyException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServletTest {

    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse res;

    @Mock
    UserDao userDao;

    @Mock
    HttpSession httpSession;

    @Mock
    PrintWriter respWriter;

    @Mock
    RequestDispatcher requestDispatcher;

    LoginServlet servlet;

    @BeforeEach
    public void setup() throws IOException {
        servlet = new LoginServlet();
        servlet.userDao = userDao;
    }

    @Test
    public void doGetLogin()throws ServletException, IOException, DuplicateKeyException, SQLException {

        when(req.getParameter("action")).thenReturn("login");
        when(req.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        servlet.doGet(req,res);
        verify(req).getRequestDispatcher(Consts.JSP_LOGIN);
    }

    @Test
    public void doGetRegister()throws ServletException, IOException, DuplicateKeyException, SQLException {

        when(req.getParameter("action")).thenReturn("register");
        when(req.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        servlet.doGet(req,res);
        verify(req).getRequestDispatcher(Consts.JSP_REGISTER);
    }

    @Test
    public void doGetLogout()throws ServletException, IOException, DuplicateKeyException, SQLException {

        when(req.getParameter("action")).thenReturn("logout");
        when(req.getSession()).thenReturn(httpSession);
        servlet.doGet(req,res);
        verify(res).sendRedirect(req.getContextPath()+Consts.SERVLET_TRAIL);
    }

    @Test
    public void doPostLogin() throws ServletException, IOException, DuplicateKeyException, SQLException{
        when(req.getParameter("action")).thenReturn("login");
        when(req.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(req.getSession()).thenReturn(httpSession);
        servlet.doPost(req,res);
        verify(req).getRequestDispatcher(Consts.JSP_LOGIN);
    }

    @Test
    public void doPostLoginWithUser() throws ServletException, IOException, DuplicateKeyException, SQLException{
        when(req.getParameter("action")).thenReturn("login");
        when(req.getSession()).thenReturn(httpSession);
        User newUser = new User("Gui","Bl","ok","guillaume.blanco@heig-vd.ch","guillaume");
        when(req.getParameter("email")).thenReturn("gui@lol.com");
        when(req.getParameter("password")).thenReturn("guillaume");
        when(userDao.connect(anyString(),anyString())).thenReturn(newUser);
        when(userDao.exist(anyString())).thenReturn(true);
        servlet.doPost(req,res);
        verify(res).sendRedirect(req.getContextPath()+Consts.SERVLET_TRAIL);
    }

    @Test
    public void doPostRegistration() throws ServletException, IOException, DuplicateKeyException, SQLException{
        when(req.getParameter("action")).thenReturn("registration");
        when(req.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        servlet.doPost(req,res);
        verify(req).getRequestDispatcher(Consts.JSP_REGISTER);
    }
    

    @Test
    public void doPostNothing() throws ServletException, IOException, DuplicateKeyException, SQLException{
        when(req.getParameter("action")).thenReturn("nothing");
        when(req.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        when(req.getSession()).thenReturn(httpSession);
        servlet.doPost(req,res);
        verify(req).getRequestDispatcher(Consts.JSP_LOGIN);
    }
}