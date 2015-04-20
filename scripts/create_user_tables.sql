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
