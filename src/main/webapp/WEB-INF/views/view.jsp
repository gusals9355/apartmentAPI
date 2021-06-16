<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>
        <tr>
            <th>법정동</th>
            <th>지번</th>
            <th>아파트명</th>
            <th>거래금액</th>
            <th>건축년도</th>
            <th>계약년도</th>
            <th>계약월</th>
            <th>계약일</th>
            <th>전용면적</th>
            <th>층</th>
        </tr>
        <c:forEach var="item" items="${list}">
            <tr>
                <td>${item.local_nm}</td>
                <td>${item.dong}</td>
                <td>${item.jibun}</td>
                <td>${item.apartment_name}</td>
                <td>${item.deal_amount}</td>
                <td>${item.build_year}</td>
                <td>${item.deal_yeare}</td>
                <td>${item.deal_month}</td>
                <td>${item.deal_day}</td>
                <td>${item.area_for_exclusive_use}</td>
                <td>${item.flr}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
