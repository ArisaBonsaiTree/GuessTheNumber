DROP
DATABASE IF EXISTS gameDBtest;

CREATE
DATABASE gameDBtest;

USE
gameDBtest;

CREATE TABLE game
(
    gameId          INT PRIMARY KEY AUTO_INCREMENT,
    numberGenerated VARCHAR(4),
    inProgress      BOOLEAN
);

CREATE TABLE round
(
    roundId        INT PRIMARY KEY AUTO_INCREMENT,
    gameId         INT,
    userGuess      VARCHAR(4),
    partialCorrect INT,
    exactCorrect   INT,
    createdAt      TIMESTAMP,
    FOREIGN KEY (gameId) REFERENCES game (gameId)
);