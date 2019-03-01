package ru.msaitov;

import org.junit.Before;
import org.junit.Test;
import ru.msaitov.sender.Sender;
import ru.msaitov.sender.SenderImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ServletJSPTest {

    private ServletJSP servletJSP;
    private Sender sender = mock(SenderImpl.class);
    private HttpServletRequest req = mock(HttpServletRequest.class);
    private HttpServletResponse res = mock(HttpServletResponse.class);

    @Before
    public void setValue() {
        servletJSP = new ServletJSP(sender);
        when(req.getParameter("cityWeather")).thenReturn("Moscow");
    }


    @Test
    public void runMethod() throws ServletException, IOException {
        servletJSP.service(req, res);
        verify(sender, times(1)).sendCity("Moscow");

    }

}
