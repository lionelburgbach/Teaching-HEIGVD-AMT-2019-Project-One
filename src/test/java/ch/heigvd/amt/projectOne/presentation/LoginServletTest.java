package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.integration.UserDao;
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
import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServletTest {

    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse res;

    @Mock
    RequestDispatcher requestDispatcher;

    LoginServlet servlet;

    @BeforeEach
    public void setup() throws IOException {
        servlet = new LoginServlet();
    }

    @Test
    public void doGet()throws ServletException, IOException, DuplicateKeyException, SQLException {

        when(req.getParameter("action")).thenReturn("login");
        when(req.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        servlet.doGet(req,res);
        verify(req).getRequestDispatcher(Consts.JSP_LOGIN);
    }
}