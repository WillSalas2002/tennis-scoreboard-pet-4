<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Match</title>
    <link rel="stylesheet" href="css/new-match.css"/>
</head>
<body>
    <header>
        <div>
            <div><a href="${pageContext.request.contextPath}/home">Home</a></div>
            <div class="page-title">New Match</div>
            <div><a href="${pageContext.request.contextPath}/matches">Finished Matches</a></div>
        </div>
    </header>
    
    <main>
        <form method="POST" action="${pageContext.request.contextPath}/newMatch">
            <div class="title">Start New Match</div>
            <label>
                <span>
                    Player 1
                </span>
                <input type="text" name="player1Name" placeholder="Enter name..."/>
            </label>
            <label>
                <span>
                    Player 2
                </span>
                <input type="text" name="player2Name" placeholder="Enter name..."/>
            </label>
            <input type="submit" value="Start Game"/>
        </form>
    </main>

    <footer>
        Authored by Will Salas
    </footer>
</body>
</html>