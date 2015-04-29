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
