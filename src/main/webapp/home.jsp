<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="css/home.css"/>
</head>

<body>
    <header>
        <div>
            <div><a href="${pageContext.request.contextPath}/newMatch">New Match</a></div>
            <div class="page-title">Tennis Scoreboard Game</div>
            <div><a href="${pageContext.request.contextPath}/matches">Finished Matches</a></div>
        </div> 
    </header>

    <main>
        <div class="buttons">
            <div>
                <a href="${pageContext.request.contextPath}/newMatch">
                    <button>New Match</button>
                </a>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/matches">
                    <button>Finished Matches</button>
                </a>
            </div>
        </div>
    </main>

    <footer>
        Authored by Will Salas
    </footer>
</body>
</html>