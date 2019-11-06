package ch.heigvd.amt.projectOne.presentation;

import ch.heigvd.amt.projectOne.services.dao.RegistrationDao;
import ch.heigvd.amt.projectOne.utils.Consts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.ejb.DuplicateKeyException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DataServletTest {

    @Mock
    HttpServletRequest req;

    @Mock
    HttpServletResponse res;

    @Mock
    RegistrationDao registrationDao;

    @Mock
    PrintWriter respWriter;

    DataServlet servlet;

    @BeforeEach
    public void setup() throws IOException {

        req = mock(HttpServletRequest.class);
        res = mock(HttpServletResponse.class);
        servlet = new DataServlet();
        servlet.registrationDao = registrationDao;
        when(req.getParameter("action")).thenReturn("registers");
        when(req.getParameter("trail_id")).thenReturn("1");
        when(res.getWriter()).thenReturn(respWriter);
    }

    @Test
    void doPost()throws ServletException, IOException, DuplicateKeyException, SQLException {

        servlet.doPost(req, res);
        verify(registrationDao, atLeastOnce()).allRegUser(any());
    }
}