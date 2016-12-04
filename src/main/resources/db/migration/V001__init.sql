CREATE TABLE hello (
    id      BIGINT PRIMARY KEY,
    message VARCHAR(255) NOT NULL
);

CREATE SEQUENCE hibernate_sequence
    START WITH 1000
    INCREMENT BY 50
    CACHE 500;

INSERT INTO hello VALUES (1, 'hello');
INSERT INTO hello VALUES (2, 'ahoj');
INSERT INTO hello VALUES (3, 'tschüs');
INSERT INTO hello VALUES (4, 'salut');
INSERT INTO hello VALUES (5, 'ciao');