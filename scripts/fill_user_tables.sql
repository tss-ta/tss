

-- password is 'pass'
INSERT INTO tss_user VALUES
  (DEFAULT, 'Pass', 'pass', '$2a$10$xmN7pxqh6RzT3Lv25Epj9e1tZp0r997ZBImK7UWGWzrCPXC9PJFpq');
INSERT INTO tss_user VALUES
  (DEFAULT, 'Adam', 'adam.sandler@gmail.com', '$2a$10$xmN7pxqh6RzT3Lv25Epj9e1tZp0r997ZBImK7UWGWzrCPXC9PJFpq');
INSERT INTO tss_user VALUES
  (DEFAULT, 'Mike', 'mike.cole@gmail.com', '$2a$10$xmN7pxqh6RzT3Lv25Epj9e1tZp0r997ZBImK7UWGWzrCPXC9PJFpq');
INSERT INTO tss_user VALUES
  (DEFAULT, 'Sara', 'sara.smith@gmail.com', '$2a$10$xmN7pxqh6RzT3Lv25Epj9e1tZp0r997ZBImK7UWGWzrCPXC9PJFpq');
INSERT INTO tss_user VALUES
  (DEFAULT, 'Vade', 'vade.gordon@gmail.com', '$2a$10$xmN7pxqh6RzT3Lv25Epj9e1tZp0r997ZBImK7UWGWzrCPXC9PJFpq');
INSERT INTO tss_user VALUES
  (DEFAULT, 'Adam', 'adam1.sandler@gmail.com', '$2a$10$xmN7pxqh6RzT3Lv25Epj9e1tZp0r997ZBImK7UWGWzrCPXC9PJFpq');
INSERT INTO tss_user VALUES
  (DEFAULT, 'Joey', 'joey.vdome@net.com', '$2a$10$xmN7pxqh6RzT3Lv25Epj9e1tZp0r997ZBImK7UWGWzrCPXC9PJFpq');
INSERT INTO tss_user VALUES
  (DEFAULT, 'Samantha', 's.smith@gmail.com', '$2a$10$xmN7pxqh6RzT3Lv25Epj9e1tZp0r997ZBImK7UWGWzrCPXC9PJFpq');
INSERT INTO tss_user VALUES
  (DEFAULT, 'Trevor', 'trevor.rovert@gmail.com', '$2a$10$xmN7pxqh6RzT3Lv25Epj9e1tZp0r997ZBImK7UWGWzrCPXC9PJFpq');
INSERT INTO tss_user VALUES
  (DEFAULT, 'Monica', 'monica.harmonica@gmail.com', '$2a$10$xmN7pxqh6RzT3Lv25Epj9e1tZp0r997ZBImK7UWGWzrCPXC9PJFpq');
INSERT INTO tss_user VALUES
  (DEFAULT, 'Izya', 'iz@ya.com', '$2a$10$xmN7pxqh6RzT3Lv25Epj9e1tZp0r997ZBImK7UWGWzrCPXC9PJFpq');


INSERT INTO tss_group VALUES
  (DEFAULT , 'readonly');
INSERT INTO tss_group VALUES
  (DEFAULT , 'managers');
INSERT INTO tss_group VALUES
  (DEFAULT , 'black_list');


INSERT INTO tss_role VALUES
  (1, 'ADMIN');
INSERT INTO tss_role VALUES
  (2, 'DRIVER');
INSERT INTO tss_role VALUES
  (3, 'CUSTOMER');
INSERT INTO tss_role VALUES
  (4, 'BANNED');


INSERT INTO car VALUES
  (DEFAULT, 'AAA-333-BBB', 4, FALSE, FALSE, TRUE, TRUE);
INSERT INTO car VALUES
  (DEFAULT, 'DDD-555-CCC', 3, FALSE, TRUE, TRUE, FALSE);
INSERT INTO car VALUES
  (DEFAULT, 'EEE-777-FFF', 2, TRUE, FALSE, FALSE, FALSE);
  INSERT INTO car VALUES
  (DEFAULT, 'ADA-343-BAB', 1, FALSE, FALSE, TRUE, TRUE);
INSERT INTO car VALUES
  (DEFAULT, 'DQD-565-CCE', 2, FALSE, TRUE, TRUE, FALSE);
INSERT INTO car VALUES
  (DEFAULT, 'ELE-777-FLF', 1, TRUE, FALSE, FALSE, FALSE);
  INSERT INTO car VALUES
  (DEFAULT, 'QWE-123-RTY', 4, FALSE, FALSE, TRUE, TRUE);
INSERT INTO car VALUES
  (DEFAULT, 'MMR-000-BIN', 3, FALSE, TRUE, TRUE, FALSE);
INSERT INTO car VALUES
  (DEFAULT, 'AEE-387-FFF', 2, TRUE, FALSE, FALSE, TRUE);
INSERT INTO car VALUES
  (DEFAULT, 'ELU-778-FLF', 1, TRUE, FALSE, FALSE, FALSE);
  INSERT INTO car VALUES
  (DEFAULT, 'QWE-129-RTY', 4, FALSE, FALSE, TRUE, TRUE);
INSERT INTO car VALUES
  (DEFAULT, 'IOD-770-STD', 3, FALSE, TRUE, TRUE, FALSE);
INSERT INTO car VALUES
  (DEFAULT, 'EGE-737-FII', 2, TRUE, FALSE, FALSE, FALSE);

INSERT INTO driver VALUES
  (8, 1, TRUE, TRUE, FALSE);


-- INSERT INTO driver_car VALUES
--   (8, 1, '2015-04-28 18:40:29.008+03', NULL , 1);


INSERT INTO tss_user_group VALUES
  (1, 2);
INSERT INTO tss_user_group VALUES
  (3, 2);
INSERT INTO tss_user_group VALUES
  (2, 1);
INSERT INTO tss_user_group VALUES
  (4, 3);
INSERT INTO tss_user_group VALUES
  (2, 3);
INSERT INTO tss_user_group VALUES
  (10, 3);

INSERT INTO tss_user_role VALUES
  (2, 1);
INSERT INTO tss_user_role VALUES
  (3, 1);
INSERT INTO tss_user_role VALUES
  (4, 3);
INSERT INTO tss_user_role VALUES
  (2, 3);
INSERT INTO tss_user_role VALUES
  (1, 1);
INSERT INTO tss_user_role VALUES
  (5, 1);
INSERT INTO tss_user_role VALUES
  (6, 3);
INSERT INTO tss_user_role VALUES
  (7, 3);
INSERT INTO tss_user_role VALUES
  (8, 2);
INSERT INTO tss_user_role VALUES
  (10, 4);
INSERT INTO tss_user_role VALUES
  (10, 1);
INSERT INTO tss_user_role VALUES
  (11, 4);

INSERT INTO tss_group_role VALUES
  (1, 2);
INSERT INTO tss_group_role VALUES
  (2, 1);
INSERT INTO tss_group_role VALUES
  (3, 3);
INSERT INTO tss_group_role VALUES
  (4, 3);


INSERT INTO contacts 
	(username, email, user_id)
	VALUES
	('Pass', 'pass', 1);
INSERT INTO contacts 
	(username, email, user_id)
	VALUES
	('Adam', 'adam.sandler@gmail.com', 2);
INSERT INTO contacts 
	(username, email, user_id)
	VALUES
	('Mike', 'mike.cole@gmail.com', 3);
INSERT INTO contacts 
	(username, email, user_id)
	VALUES
	('Sara', 'sara.smith@gmail.com', 4);
INSERT INTO contacts 
	(username, email, user_id)
	VALUES
	('Vade', 'vade.gordon@gmail.com', 5);
INSERT INTO contacts 
	(username, email, user_id)
	VALUES
	('Adam', 'adam1.sandler@gmail.com', 6);
INSERT INTO contacts 
	(username, email, user_id)
	VALUES
	('Joey', 'joey.vdome@net.com', 7);
INSERT INTO contacts 
	(username, email, user_id)
	VALUES
	('Samantha', 's.smith@gmail.com', 8);
INSERT INTO contacts 
	(username, email, user_id)
	VALUES
	('Trevor', 'trevor.rovert@gmail.com', 9);
INSERT INTO contacts
	(username, email, user_id)
	VALUES
	('Monica', 'monica.harmonica@gmail.com', 10);

INSERT INTO tariff 
(tariff_name, plus_coef, multiple_coef) 
VALUES
('van', 20, 1.1);
INSERT INTO tariff 
(tariff_name, plus_coef, multiple_coef) 
VALUES
('cargo', 20, 1.5);
INSERT INTO tariff 
(tariff_name, plus_coef, multiple_coef) 
VALUES
('business', 30, 1.4);
INSERT INTO tariff 
(tariff_name, plus_coef, multiple_coef) 
VALUES
('economy', 0, 0.9);
INSERT INTO tariff 
(tariff_name, plus_coef, multiple_coef) 
VALUES
('van', 20, 1.1);
INSERT INTO tariff 
(tariff_name, plus_coef, multiple_coef) 
VALUES
('wi-fi', 10, 1.01);
INSERT INTO tariff 
(tariff_name, plus_coef, multiple_coef) 
VALUES
('conditioner', 10, 1.07);
INSERT INTO tariff 
(tariff_name, plus_coef, multiple_coef) 
VALUES
('no_smoked', 10, 1.01);
INSERT INTO tariff 
(tariff_name, plus_coef, multiple_coef) 
VALUES
('missed', 50, 1);
INSERT INTO tariff 
(tariff_name, plus_coef, multiple_coef) 
VALUES
('weekend', 10, 1.03);
INSERT INTO tariff 
(tariff_name, plus_coef, multiple_coef) 
VALUES
('night', 40, 1.07);
INSERT INTO tariff 
(tariff_name, plus_coef, multiple_coef) 
VALUES
('rush_hour', 10, 1.3);
INSERT INTO tariff 
(tariff_name, plus_coef, multiple_coef) 
VALUES
('celebration', 100, 1.3);
INSERT INTO tariff 
(tariff_name, plus_coef, multiple_coef) 
VALUES
('meet_guest', 100, 1.03);
INSERT INTO tariff 
(tariff_name, plus_coef, multiple_coef) 
VALUES
('convey', 0, 0.9);
INSERT INTO tariff
(tariff_name, plus_coef, multiple_coef)
VALUES
  ('animal', 20, 1.1);
INSERT INTO tariff
(tariff_name, plus_coef, multiple_coef)
VALUES
  ('per_km', 5, 1);


---------------------------------------
-- REPORT SYSTEM ----------------------
---------------------------------------

INSERT INTO driver_category (id, name) VALUES (1, 'B');
INSERT INTO driver_category (id, name) VALUES (2, 'C');
INSERT INTO driver_category (id, name) VALUES (3, 'D');

INSERT INTO car_category (id, name) VALUES (1, 'Econom');
INSERT INTO car_category (id, name) VALUES (2, 'Van');
INSERT INTO car_category (id, name) VALUES (3, 'Business');
INSERT INTO car_category (id, name) VALUES (4, 'Cargo');


INSERT INTO report_info (id, name, description, select_query, count_query, countable, page_size, export_size) values
(DEFAULT,
'The most popular Car category',
'This report shows all popular Car category in Taxi Service System',
'SELECT cc.name AS "Category", count(txo.car_category) AS "Orders Amount" FROM taxi_order AS txo JOIN car_category AS cc ON txo.car_category = cc.id GROUP BY txo.car_category, cc.name ORDER BY count(txo.car_category) DESC;',
null, false, 0, 1000);
INSERT INTO report_info (id, name, description, select_query, count_query, countable, page_size, export_size) values
(DEFAULT,
'The most popular Driver category',
'This report shows all popular Driver category in Taxi Service System',
'SELECT dct.name AS "Category", count(d.category) AS "Orders Amount" FROM driver AS d JOIN driver_car AS dc	ON d.driver_id = dc.driver_id JOIN taxi_order AS o ON dc.id = o.driver_car_id JOIN driver_category AS dct ON dct.id = d.category GROUP BY d.category, dct.name ORDER BY count(d.category) DESC',
null, false, 0, 1000);
INSERT INTO report_info (id, name, description, select_query, count_query, countable, page_size, export_size) values
(DEFAULT,
'The most popular Car options',
'This report shows all popular Car options in Taxi Service System',
'SELECT * FROM (SELECT ''Animal Transport'' AS "Option Name", sum(animal_transport::int) AS "Orders with this option"	FROM taxi_order	UNION	SELECT ''WI-FI'' AS "Option Name", sum(wifi::int) AS "Orders with this option" FROM taxi_order	UNION	SELECT ''Conditioner'' AS "Option Name", sum(conditioner::int) AS "Orders with this option" FROM taxi_order) AS T ORDER BY "Orders with this option" DESC',
null, false, 0, 1000);
INSERT INTO report_info (id, name, description, select_query, count_query, countable, page_size, export_size) values
(DEFAULT,'The most popular Car options by User',
'This report shows all popular Car options by each User in Taxi Service System',
'SELECT t.email AS "User Email", t."Option Name", t."Orders with option"	FROM (SELECT c.email, ''Animal Transport'' AS "Option Name", sum(animal_transport::int) AS "Orders with option"	FROM taxi_order	JOIN contacts AS c ON c.id = contacts_id	GROUP BY c.email UNION	SELECT c.email, ''WI-FI'' AS "Option Name", sum(wifi::int) AS "Orders with option" FROM taxi_order	JOIN contacts AS c ON c.id = contacts_id	GROUP BY c.email UNION	SELECT c.email, ''Conditioner'' AS "Option Name", sum(conditioner::int) AS "Orders with option"	FROM taxi_order	JOIN contacts AS c ON c.id = contacts_id	GROUP BY c.email) AS t ORDER BY t.email, t."Orders with option" DESC LIMIT ? OFFSET ?',
'SELECT count(t."Option Name") FROM (SELECT c.email, ''Animal Transport'' AS "Option Name", sum(animal_transport::int) AS "Orders with option"	FROM taxi_order	JOIN contacts AS c ON c.id = contacts_id	GROUP BY c.email UNION	SELECT c.email, ''WI-FI'' AS "Option Name", sum(wifi::int) AS "Orders with option" FROM taxi_order	JOIN contacts AS c ON c.id = contacts_id	GROUP BY c.email UNION	SELECT c.email, ''Conditioner'' AS "Option Name", sum(conditioner::int) AS "Orders with option" FROM taxi_order	JOIN contacts AS c ON c.id = contacts_id	GROUP BY c.email) AS t',
true, 15, 1000);