<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/result" method="post">
        <div>
            년도 :<select name="deal_year">
                 <c:forEach var="i" begin="2000" end="2020">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            월 : <select name="deal_month">
                <c:forEach var="i" begin="1" end="12">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            지역: 대구시
            <select name="ex_cd">
                <c:forEach var="local" items="${list}">
                    <option value="${local.local_nm}">${local.local_nm}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <input type="submit" value="search">
        </div>
    </form>
</body>
</html>
