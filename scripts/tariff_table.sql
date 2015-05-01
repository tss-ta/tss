DROP TABLE IF EXISTS tariff;

CREATE TABLE tariff
(
  id serial NOT NULL ,
  tariff_name character varying(40),
  plus_coef float(8),
  multiple_coef float(8),
  active_from TIMESTAMP;
  active_to TIMESTAMP;
  CONSTRAINT tariff_pk PRIMARY KEY (id)
);

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






