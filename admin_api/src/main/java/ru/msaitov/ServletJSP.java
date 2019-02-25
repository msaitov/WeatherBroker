package ru.msaitov;

import ru.msaitov.sender.SenderImpl;

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

    @Inject
    private SenderImpl sender;

    /**
     * Принимаем по JSP название города и отправляем sender
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
        String value = req.getParameter("cityWeather");
        sender.sendCity(value);
        res.sendRedirect("index.jsp");
    }
}
