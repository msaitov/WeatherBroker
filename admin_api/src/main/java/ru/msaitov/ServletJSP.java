package ru.msaitov;

import ru.msaitov.sender.Sender;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сервлет
 */
public class ServletJSP extends HttpServlet {


    private Sender sender;

    @Inject
    public ServletJSP(Sender sender) {
        this.sender = sender;
    }

    /**
     * Принимаем по JSP название города и отправляем sender
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws IOException {
        if (req==null || res==null) {
            throw new RuntimeException("Parameter req or res is null");
        }

        String value = req.getParameter("cityWeather");

        value = value.trim();
        if (value.equals("")) {
            throw new RuntimeException("Get paramemert cityWeather is empty");
        }

        sender.sendCity(value);
        res.sendRedirect("index.jsp");
    }
}
