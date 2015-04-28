
DROP TABLE IF EXISTS taxi_order;
DROP TABLE IF EXISTS tss_user_group;
DROP TABLE IF EXISTS tss_user_role;
DROP TABLE IF EXISTS tss_group_role;
DROP TABLE IF EXISTS driver_car;
DROP TABLE IF EXISTS driver;
DROP TABLE IF EXISTS tss_user;
DROP TABLE IF EXISTS tss_group;
DROP TABLE IF EXISTS tss_role;
DROP TABLE IF EXISTS route;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS tariff;
DROP TABLE IF EXISTS address;




CREATE TABLE tss_user
(
  id serial NOT NULL,
  username character varying(40),
  email character varying(40),
  pass_hash character varying(60),
  CONSTRAINT tss_usr_pk PRIMARY KEY (id)
);


CREATE TABLE tss_group
(
  id serial NOT NULL,
  name character(100),
  CONSTRAINT tss_grp_pk PRIMARY KEY (id)
);


CREATE TABLE tss_role
(
  id integer NOT NULL,
  rolename character varying(40) NOT NULL,
  CONSTRAINT tss_rl_pk PRIMARY KEY (id)
);

CREATE TABLE car
(
  id serial NOT NULL,
  lic_plate CHARACTER VARYING(10) NOT NULL,
  category INTEGER NOT NULL,
  available BOOLEAN,
  animalable BOOLEAN,
  wifi BOOLEAN,
  conditioner BOOLEAN,
  CONSTRAINT car_pk PRIMARY KEY (id)
);


CREATE TABLE driver
(
  driver_id integer NOT NULL,
  category integer,
  available boolean,
  is_male boolean,
  smokes boolean,
  car_id integer,
   CONSTRAINT pk_driv_id PRIMARY KEY (driver_id),
   CONSTRAINT fk_user FOREIGN KEY (driver_id)
   REFERENCES tss_user (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT fk_car FOREIGN KEY (car_id)
   REFERENCES car (id) MATCH SIMPLE
     ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE tss_user_group
(
  user_id integer NOT NULL,
  group_id integer NOT NULL,
  CONSTRAINT tss_usr_grp_pk PRIMARY KEY (user_id, group_id),
  CONSTRAINT tss_usr_grp_grp_id_fk FOREIGN KEY (group_id)
      REFERENCES tss_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tss_usr_grp_usr_id_fk FOREIGN KEY (user_id)
      REFERENCES tss_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE tss_user_role
(
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT tss_usr_rl_pk PRIMARY KEY (user_id, role_id),
  CONSTRAINT tss_usr_rl_rl_id_fk FOREIGN KEY (role_id)
      REFERENCES tss_role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tss_usr_rl_usr_id_fk FOREIGN KEY (user_id)
      REFERENCES tss_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE tss_group_role
(
  role_id integer NOT NULL,
  group_id integer NOT NULL,
  CONSTRAINT tss_grp_rl_pk PRIMARY KEY (role_id, group_id),
  CONSTRAINT tss_grp_rl_grp_id_fk FOREIGN KEY (group_id)
      REFERENCES tss_group (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tss_grp_rl_rl_id_fk FOREIGN KEY (role_id)
      REFERENCES tss_role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE driver_car
(
  id serial NOT NULL,
  driver_id integer,
  car_id integer,
  assign_time timestamp with time zone,
  unassign_time timestamp with time zone,
  CONSTRAINT pk_dr_car_id PRIMARY KEY (id),
  CONSTRAINT fk_car_id FOREIGN KEY (car_id)
  REFERENCES car (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_drv_id FOREIGN KEY (driver_id)
  REFERENCES driver (driver_id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE NO ACTION
);
-- CREATE TABLE driver_car
-- (
-- 	id serial NOT NULL,
-- 	driver_id INT,
-- 	car_id INT,
-- 	assigned_time TIMESTAMP,
-- 	unassigned_time TIMESTAMP,
--   	CONSTRAINT driver_car_pk PRIMARY KEY (id),
-- 	CONSTRAINT driver_car_car_fk FOREIGN KEY (car_id)
--       	REFERENCES car (id) MATCH SIMPLE
-- 		 ON UPDATE NO ACTION ON DELETE NO ACTION,
-- 	CONSTRAINT driver_car_driver_fk FOREIGN KEY (driver_id)
--       	REFERENCES driver (driver_id)  MATCH SIMPLE
-- 		ON UPDATE NO ACTION ON DELETE NO ACTION
-- );
CREATE TABLE tariff
(
  id serial NOT NULL,
  tariff_name character varying(40),
  plus_coef float(8),
  multiple_coef float(8),
  CONSTRAINT tariff_pk PRIMARY KEY (id)
);

CREATE TABLE address
(
	addr_id serial NOT NULL,
	altitude real NOT NULL,
	longtitude real NOT NULL,
	CONSTRAINT addr_id_pk PRIMARY KEY (addr_id)
);

CREATE TABLE route
(
	route_id serial NOT NULL,
	from_addr_id integer NOT NULL,
	to_addr_id integer NOT NULL,
	path_content CHARACTER VARYING(40) NOT NULL,
	CONSTRAINT route_id_pk PRIMARY KEY (route_id),
	CONSTRAINT route_addr_from_id_fk FOREIGN KEY (from_addr_id)
    	 REFERENCES address (addr_id) MATCH SIMPLE
     	 ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT route_addr_to_id_fk FOREIGN KEY (to_addr_id)
     	 REFERENCES address (addr_id) MATCH SIMPLE
    	  ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE TABLE taxi_order
(
  id serial NOT NULL,
  price double precision,
  payment integer,
  booking_time timestamp,
  order_time timestamp,
  music_style integer,
  status integer,
  comment character varying(4000),
  male boolean,
  smoke boolean,
  driver_car_id integer,
  car_category integer,
  animal_transport boolean,
  wifi boolean,
  conditioner boolean,
  user_id integer,
  route_id integer,
  tariff_id integer,
  service_option_id integer,
  CONSTRAINT tx_rdr_pk PRIMARY KEY (id),
  CONSTRAINT tx_rdr_drvr_cr_id_fk FOREIGN KEY (driver_car_id)
      REFERENCES driver_car (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tx_rdr_usr_id_fk FOREIGN KEY (user_id)
      REFERENCES tss_user (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
	    CONSTRAINT tx_rdr_trff_id_fk FOREIGN KEY (tariff_id)
      REFERENCES tariff (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tx_rdr_rt_id_fk FOREIGN KEY (route_id)
      REFERENCES route (route_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

