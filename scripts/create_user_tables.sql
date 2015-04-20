DROP TABLE user_group;
DROP TABLE user_role;
DROP TABLE group_role;
DROP TABLE tss_user;
DROP TABLE tss_group;
DROP TABLE role;


CREATE TABLE tss_user
(
  username character varying(40) NOT NULL,
  email character varying(40),
  password character varying(40),
  CONSTRAINT username PRIMARY KEY (username)
);


CREATE TABLE tss_group
(
  group_id integer NOT NULL,
  name character(100),
  CONSTRAINT group_id PRIMARY KEY (group_id)
);


CREATE TABLE role
(
  rolename character varying(40) NOT NULL,
  CONSTRAINT rolename PRIMARY KEY (rolename)
);


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
);


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
);


CREATE TABLE group_role
(
  rolename character varying(40) NOT NULL,
  group_id integer NOT NULL,
  CONSTRAINT rolename_groupid PRIMARY KEY (rolename, group_id),
  CONSTRAINT group_id FOREIGN KEY (group_id)
      REFERENCES tss_group (group_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT rolename FOREIGN KEY (rolename)
      REFERENCES role (rolename) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
