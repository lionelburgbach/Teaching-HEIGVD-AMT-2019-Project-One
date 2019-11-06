package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.model.User;
import ch.heigvd.amt.projectOne.services.dao.RegistrationDao;
import ch.heigvd.amt.projectOne.utils.Consts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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
class HomeServletTest {

    User lio = new User(1, "amt@amt.ch", "amt", "lionel", "Burgbacher", "05-03-1989", null);

    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse res;

    @Mock
    RegistrationDao regDao;

    @Mock
    PrintWriter respWriter;

    @Mock
    RequestDispatcher dispatcher;

    @Mock
    HttpSession session;

    HomeServlet servlet;

    @BeforeEach
    public void setup() throws IOException {

        req = mock(HttpServletRequest.class);
        res = mock(HttpServletResponse.class);
        servlet = new HomeServlet();
        servlet.regDao = regDao;
        session = mock(HttpSession.class);
        Mockito.when(req.getSession()).thenReturn(session);
        when(req.getSession().getAttribute("user")).thenReturn(lio);
        when(req.getRequestDispatcher(Consts.JSP_HOME)).thenReturn(dispatcher);
    }

    @Test
    void doGet() throws ServletException, IOException, DuplicateKeyException, SQLException {
        servlet.doGet(req, res);
        req.setAttribute("regs",regDao.allRegUser(lio.getId()));
        verify(dispatcher).forward(req, res);
//        verify(regDao, atLeastOnce()).allRegUser(any());
    }
}