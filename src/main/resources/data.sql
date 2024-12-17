INSERT INTO category(name) VALUES('Eurogames');
INSERT INTO category(name) VALUES('Ameritrash');
INSERT INTO category(name) VALUES('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

INSERT INTO client(name) VALUES('Frodo Bolson');
INSERT INTO client(name) VALUES('Guybrush Threepwood');
INSERT INTO client(name) VALUES('Link');
INSERT INTO client(name) VALUES('Monkey D. Luffy');
INSERT INTO client(name) VALUES('Gale Dekarios');

INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(1, 1, '2024-01-06', '2024-01-16');
INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(2, 5, '2024-03-10', '2024-03-22');
INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(3, 5, '2024-05-15', '2024-05-21');
INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(4, 2, '2024-07-16', '2024-07-23');
INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(5, 3, '2024-07-17', '2024-07-25');
INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(6, 4, '2024-11-10', '2024-11-23');

