<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <style>
        table {
            width: 50%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #000;
            text-align: center;
        }

        .button-container {
            text-align: center;
            margin: 20px;
        }

        button {
            padding: 10px 20px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<h1 style="text-align: center;">Match Score</h1>

<!-- Таблица с именами игроков и их текущим счётом -->
<table>
    <tr>
        <th>Player</th>
        <c:choose>
            <c:when test="${requestScope.matchScoreView.gameState == 'REGULAR'}">
                <th>Game</th>
            </c:when>
            <c:otherwise>
                <th>Tie-Break</th>
            </c:otherwise>
        </c:choose>
        <th>Set</th>
        <th>Match</th>
    </tr>
    <tr>
        <td>${requestScope.matchScoreView.player1Name}</td>
        <td>${requestScope.matchScoreView.player1GameScore}</td>
        <td>${requestScope.matchScoreView.player1SetScore}</td>
        <td>${requestScope.matchScoreView.player1MatchScore}</td>
    </tr>
    <tr>
        <td>${requestScope.matchScoreView.player2Name}</td>
        <td>${requestScope.matchScoreView.player2GameScore}</td>
        <td>${requestScope.matchScoreView.player2SetScore}</td>
        <td>${requestScope.matchScoreView.player2MatchScore}</td>
    </tr>
</table>

<!-- Форма и кнопки для фиксирования очков -->
<div class="button-container">
    <form id="player1-form" method="POST"
          action="${pageContext.request.contextPath}/matchScore?uuid=${requestScope.uuid}">
        <input type="hidden" name="scorerId" value="${requestScope.matchScoreView.player1Id}">
        <button type="submit">Игрок 1 выиграл текущее очко</button>
    </form>

    <form id="player2-form" method="POST"
          action="${pageContext.request.contextPath}/matchScore?uuid=${requestScope.uuid}">
        <input type="hidden" name="scorerId" value="${requestScope.matchScoreView.player2Id}">
        <button type="submit">Игрок 2 выиграл текущее очко</button>
    </form>
</div>
</body>
</html>
