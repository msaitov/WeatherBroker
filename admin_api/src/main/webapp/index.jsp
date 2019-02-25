<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>$Title$</title>
</head>

<body>
  <form action="serviceRun" method="get">
    <p>
      Введите город:
      <input name="cityWeather" type="text" value=
        <%=request.getParameter("cityWeather")!= null ? request.getParameter("cityWeather") : "" %>>
      <input type="submit" name="button" value="submit"/>
    </p>
  </form>
</body>
</html>