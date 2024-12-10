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

-- INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(1, 1, '06/01/2024', '16/01/2024');
-- INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(2, 5, '10/03/2024', '22/03/2024');
-- INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(3, 6, '15/05/2024', '21/05/2024');
-- INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(4, 2, '29/07/2024', '03/08/2024');
-- INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(5, 3, '04/09/2024', '16/09/2024');
-- INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(6, 4, '10/11/2024', '23/11/2024');

INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(1, 1, PARSEDATETIME('06/01/2024', 'dd/MM/yyyy'), PARSEDATETIME('16/01/2024', 'dd/MM/yyyy'));
INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(2, 5, PARSEDATETIME('10/03/2024', 'dd/MM/yyyy'), PARSEDATETIME('22/03/2024', 'dd/MM/yyyy'));
INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(3, 5, PARSEDATETIME('15/05/2024', 'dd/MM/yyyy'), PARSEDATETIME('21/05/2024', 'dd/MM/yyyy'));
INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(4, 2, PARSEDATETIME('29/07/2024', 'dd/MM/yyyy'), PARSEDATETIME('03/08/2024', 'dd/MM/yyyy'));
INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(5, 3, PARSEDATETIME('04/09/2024', 'dd/MM/yyyy'), PARSEDATETIME('16/09/2024', 'dd/MM/yyyy'));
INSERT INTO loan(game_id, client_id, loan_date, return_date) VALUES(6, 4, PARSEDATETIME('10/11/2024', 'dd/MM/yyyy'), PARSEDATETIME('23/11/2024', 'dd/MM/yyyy'));



