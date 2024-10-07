<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Finished Matches</title>
    <link rel="stylesheet" href="./css/finished-matches.css"/>
</head>
<body>
    <header>
        <div>
            <div><a href="${pageContext.request.contextPath}/home">Home</a></div>
            <div class="page-title">Finished Matches</div>
            <div><a href="${pageContext.request.contextPath}/newMatch">New Match</a></div>
        </div>
    </header>

    <main>
        <div class="search-bar">
            <form>
                <input type="text" name="filterByName" placeholder="Enter name..."/>
                <input type="submit" value="Search"/>
            </form>
        </div>
        <div class="matches-container">
            <div class="match">
                <div class="data id">Match Id</div>
                <div class="data player-name">First Player</div>
                <div class="data player-name">Second Player</div>
            </div>
            <c:forEach items="${requestScope.matches}" var="matches">
                <div class="match">
                    <div class="data id">${matches.id}</div>
                    <div class="data player-name">${matches.player1Name}</div>
                    <div class="data player-name">${matches.player2Name}</div>
                </div>
            </c:forEach>
        </div>
        <div class="pagination">
            <c:set var="currentPage" value="${param.page != null ? param.page : 1}" />

            <form class="button" method="GET" action="${pageContext.request.contextPath}/matches">
                <c:choose>
                    <c:when test="${currentPage == 1}">
                        <input type="submit" value="&lt;&lt;" disabled/>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="page" value="1"/>
                        <input type="submit" value="&lt;&lt;"/>
                    </c:otherwise>
                </c:choose>
            </form>

            <form class="button" method="GET" action="${pageContext.request.contextPath}/matches">
                <c:choose>
                    <c:when test="${currentPage == 1}">
                        <input type="hidden" name="page" value="1"/>
                        <input type="submit" value="&lt;" disabled/>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="page" value="${currentPage - 1}"/>
                        <input type="submit" value="&lt;"/>
                    </c:otherwise>
                </c:choose>
            </form>

            <form class="button">
                <input type="submit" value="${currentPage}"/>
            </form>

            <form class="button" method="GET" action="${pageContext.request.contextPath}/matches">
                <c:choose>
                    <c:when test="${currentPage == pageCount}">
                        <input type="submit" value="&gt;" disabled/>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="page" value="${currentPage + 1}"/>
                        <input type="submit" value="&gt;"/>
                    </c:otherwise>
                </c:choose>
            </form>

            <form class="button" method="GET" action="${pageContext.request.contextPath}/matches">
                <c:choose>
                    <c:when test="${currentPage == pageCount}">
                        <input type="submit" value="&gt;&gt;" disabled/>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="page" value="${pageCount}"/>
                        <input type="submit" value="&gt;&gt;"/>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
    </main>

    <footer>
        Authored by Will Salas
    </footer>
</body>
</html>
