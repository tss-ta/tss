DROP TABLE user_group;
DROP TABLE user_role;
DROP TABLE tss_user;
DROP TABLE tss_group;
DROP TABLE role;


CREATE TABLE tss_user
(
  username character varying(40) NOT NULL,
  email character varying(40),
  password character varying(40),
  CONSTRAINT username PRIMARY KEY (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tss_user
  OWNER TO postgres;

INSERT INTO tss_user VALUES
  ('Adam', 'adam.sandler@gmail.com', 'adampassword');
INSERT INTO tss_user VALUES
  ('Mike', 'mike.cole@gmail.com', 'mikepassword');
INSERT INTO tss_user VALUES
  ('Sara', 'sara.smith@gmail.com', 'sarapassword');
INSERT INTO tss_user VALUES
  ('Vade', 'vade.gordon@gmail.com', 'vadepassword');



CREATE TABLE tss_group
(
  group_id integer NOT NULL,
  name character(100),
  type character(40),
  CONSTRAINT group_id PRIMARY KEY (group_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tss_group
  OWNER TO postgres;

INSERT INTO tss_group VALUES
  (1, 'readonly', 'readonly_group');
INSERT INTO tss_group VALUES
  (2, 'managers', 'managers_group');
INSERT INTO tss_group VALUES
  (3, 'black_list', 'black_list_group');


CREATE TABLE role
(
  rolename character varying(40) NOT NULL,
  CONSTRAINT rolename PRIMARY KEY (rolename)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE role
  OWNER TO postgres;

INSERT INTO role VALUES
  ('admin');
INSERT INTO role VALUES
  ('driver');
INSERT INTO role VALUES
  ('customer');


CREATE TABLE user_group
(
  username character(40) NOT NULL,
  group_id integer NOT NULL,
  CONSTRAINT username_groupid PRIMARY KEY (username, group_id),
  CONSTRAINT group_id FOREIGN KEY (group_id)
      REFERENCES tss_group (group_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT username FOREIGN KEY (username)
      REFERENCES tss_user (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_group
  OWNER TO postgres;

INSERT INTO user_group VALUES
  ('Adam', 2);
INSERT INTO user_group VALUES
  ('Sara', 2);
INSERT INTO user_group VALUES
  ('Mike', 1);
INSERT INTO user_group VALUES
  ('Vade', 3);
INSERT INTO user_group VALUES
  ('Mike', 3);


CREATE TABLE user_role
(
  username character varying(40) NOT NULL,
  rolename character varying(40) NOT NULL,
  CONSTRAINT username_rolename PRIMARY KEY (username, rolename),
  CONSTRAINT rolename FOREIGN KEY (rolename)
      REFERENCES role (rolename) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT username FOREIGN KEY (username)
      REFERENCES tss_user (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_role
  OWNER TO postgres;

INSERT INTO user_role VALUES
  ('Mike', 'admin');
INSERT INTO user_role VALUES
  ('Sara', 'admin');
INSERT INTO user_role VALUES
  ('Vade', 'customer');
INSERT INTO user_role VALUES
  ('Mike', 'customer');