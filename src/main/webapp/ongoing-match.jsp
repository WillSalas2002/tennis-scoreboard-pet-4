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
        <td>${requestScope.matchScore.player1.name}</td>
        <td>${requestScope.matchScore.player1.point.value}</td>
        <td>${requestScope.matchScore.player1.setScore}</td>
        <td>${requestScope.matchScore.player1.matchScore}</td>
    </tr>
    <tr>
        <td>${requestScope.matchScore.player2.name}</td>
        <td>${requestScope.matchScore.player2.point.value}</td>
        <td>${requestScope.matchScore.player2.setScore}</td>
        <td>${requestScope.matchScore.player2.matchScore}</td>
    </tr>
</table>

<!-- Форма и кнопки для фиксирования очков -->
<div class="button-container">
    <form id="player1-form" method="POST" action="${pageContext.request.contextPath}/matchScore?uuid=${requestScope.uuid}">
        <input type="hidden" name="scorerId" value="${requestScope.matchScore.player1.id}">
        <button type="submit">Игрок 1 выиграл текущее очко</button>
    </form>

    <form id="player2-form" method="POST" action="${pageContext.request.contextPath}/matchScore?uuid=${requestScope.uuid}">
        <input type="hidden" name="scorerId" value="${requestScope.matchScore.player2.id}">
        <button type="submit">Игрок 2 выиграл текущее очко</button>
    </form>
</div>
</body>
</html>
