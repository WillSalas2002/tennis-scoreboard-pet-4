CREATE TABLE Player
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Match
(
    id      SERIAL PRIMARY KEY,
    player1_id INTEGER NOT NULL,
    player2_id INTEGER NOT NULL,
    winner_id  INTEGER NOT NULL,
    FOREIGN KEY (player1_id) REFERENCES Player (id),
    FOREIGN KEY (player2_id) REFERENCES Player (id),
    FOREIGN KEY (winner_id) REFERENCES Player (id)
);