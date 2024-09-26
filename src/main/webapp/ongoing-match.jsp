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
        <th>Game</th>
        <th>Set</th>
        <th>Match</th>
    </tr>
    <tr>
        <td>${requestScope.matchScoreModel.player1.name}</td>
        <c:choose>
            <c:when test="${requestScope.matchScoreModel.matchScore.currentSet.currentGame.gameState == 'REGULAR'}">
                <td>${requestScope.matchScoreModel.matchScore.currentSet.currentGame.getPlayerScore(0).pointCode}</td>
            </c:when>
            <c:otherwise>
                <td>${requestScope.matchScoreModel.matchScore.currentSet.currentGame.getPlayerScore(0)}</td>
            </c:otherwise>
        </c:choose>
        <td>${requestScope.matchScoreModel.matchScore.currentSet.getPlayerScore(0)}</td>
        <td>${requestScope.matchScoreModel.matchScore.getPlayerScore(0)}</td>
    </tr>
    <tr>
        <td>${requestScope.matchScoreModel.player2.name}</td>
        <c:choose>
            <c:when test="${requestScope.matchScoreModel.matchScore.currentSet.currentGame.gameState == 'REGULAR'}">
                <td>${requestScope.matchScoreModel.matchScore.currentSet.currentGame.getPlayerScore(1).pointCode}</td>
            </c:when>
            <c:otherwise>
                <td>${requestScope.matchScoreModel.matchScore.currentSet.currentGame.getPlayerScore(1)}</td>
            </c:otherwise>
        </c:choose>
        <td>${requestScope.matchScoreModel.matchScore.currentSet.getPlayerScore(1)}</td>
        <td>${requestScope.matchScoreModel.matchScore.getPlayerScore(1)}</td>
    </tr>
</table>

<!-- Форма и кнопки для фиксирования очков -->
<div class="button-container">
    <form id="player1-form" method="POST"
          action="${pageContext.request.contextPath}/matchScore?uuid=${requestScope.uuid}">
        <input type="hidden" name="scorerId" value="${requestScope.matchScoreModel.player1.id}">
        <button type="submit">Игрок 1 выиграл текущее очко</button>
    </form>

    <form id="player2-form" method="POST"
          action="${pageContext.request.contextPath}/matchScore?uuid=${requestScope.uuid}">
        <input type="hidden" name="scorerId" value="${requestScope.matchScoreModel.player2.id}">
        <button type="submit">Игрок 2 выиграл текущее очко</button>
    </form>
</div>
</body>
</html>
