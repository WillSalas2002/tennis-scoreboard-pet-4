<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./css/matches.css"/>
    <title>Home</title>
</head>

<body>
<header>
    This is header
</header>
<div class="container">
    <c:forEach items="${requestScope.matches}" var="matches">
        <div class="table-row">
            <div class="table-value">${matches.id}</div>
            <div class="table-value">${matches.player1Name}</div>
            <div class="table-value">${matches.player2Name}</div>
            <div class="table-value">${matches.winnerName}</div>
        </div>
    </c:forEach>
</div>
<div class="pagination">
    <c:forEach var = "page" begin = "1" end = "${requestScope.pageCount}">
        <form method="GET" action="matches">
            <input type="hidden" name="page" value="${page}"/>
            <c:if test="${requestScope.filterByName != null}">
                <input type="hidden" name="filterByName" value="${requestScope.filterByName}"/>
            </c:if>
            <input type="submit" value="${page}"/>
        </form>
    </c:forEach>
</div>
<footer>
    This is footer
</footer>
</body>

</html>
