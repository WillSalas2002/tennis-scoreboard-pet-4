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
                <input type="text" name="filter_by_name" placeholder="Enter name..."/>
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
            <form class="button">
                <input type="submit" value="&lt;&lt;"/>
            </form>
            <form class="button">
                <input type="submit" value="&lt;"/>
            </form>
            <form class="button">
                <input type="submit" value="1"/>
            </form>
            <form class="button">
                <input type="submit" value="&gt;"/>
            </form>
            <form class="button">
                <input type="submit" value="&gt;&gt;"/>
            </form>
        </div>
    </main>

    <footer>
        Authored by Will Salas
    </footer>
</body>
</html>
