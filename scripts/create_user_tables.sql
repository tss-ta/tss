DROP TABLE "User_Group";
DROP TABLE "User_Role";
DROP TABLE "User";
DROP TABLE "Group";
DROP TABLE "Role";


CREATE TABLE "User"
(
  username character varying(40) NOT NULL,
  email character varying(40),
  password character varying(40),
  CONSTRAINT username PRIMARY KEY (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "User"
  OWNER TO postgres;

INSERT INTO "User" VALUES
  ('Adam', 'adam.sandler@gmail.com', 'adampassword');
INSERT INTO "User" VALUES
  ('Mike', 'mike.cole@gmail.com', 'mikepassword');
INSERT INTO "User" VALUES
  ('Sara', 'sara.smith@gmail.com', 'sarapassword');
INSERT INTO "User" VALUES
  ('Vade', 'vade.gordon@gmail.com', 'vadepassword');



CREATE TABLE "Group"
(
  group_id integer NOT NULL,
  name character(100),
  type character(40),
  CONSTRAINT group_id PRIMARY KEY (group_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Group"
  OWNER TO postgres;

INSERT INTO "Group" VALUES
  (1, 'readonly', 'readonly_group');
INSERT INTO "Group" VALUES
  (2, 'managers', 'managers_group');
INSERT INTO "Group" VALUES
  (3, 'black_list', 'black_list_group');


CREATE TABLE "Role"
(
  rolename character varying(40) NOT NULL,
  CONSTRAINT rolename PRIMARY KEY (rolename)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "Role"
  OWNER TO postgres;

INSERT INTO "Role" VALUES
  ('admin');
INSERT INTO "Role" VALUES
  ('driver');
INSERT INTO "Role" VALUES
  ('customer');


CREATE TABLE "User_Group"
(
  username character(40) NOT NULL,
  group_id integer NOT NULL,
  CONSTRAINT username_groupid PRIMARY KEY (username, group_id),
  CONSTRAINT group_id FOREIGN KEY (group_id)
      REFERENCES "Group" (group_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT username FOREIGN KEY (username)
      REFERENCES "User" (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "User_Group"
  OWNER TO postgres;

INSERT INTO "User_Group" VALUES
  ('Adam', 2);
INSERT INTO "User_Group" VALUES
  ('Sara', 2);
INSERT INTO "User_Group" VALUES
  ('Mike', 1);
INSERT INTO "User_Group" VALUES
  ('Vade', 3);
INSERT INTO "User_Group" VALUES
  ('Mike', 3);


CREATE TABLE "User_Role"
(
  username character varying(40) NOT NULL,
  rolename character varying(40) NOT NULL,
  CONSTRAINT username_rolename PRIMARY KEY (username, rolename),
  CONSTRAINT rolename FOREIGN KEY (rolename)
      REFERENCES "Role" (rolename) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT username FOREIGN KEY (username)
      REFERENCES "User" (username) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "User_Role"
  OWNER TO postgres;

INSERT INTO "User_Role" VALUES
  ('Mike', 'admin');
INSERT INTO "User_Role" VALUES
  ('Sara', 'admin');
INSERT INTO "User_Role" VALUES
  ('Vade', 'customer');
INSERT INTO "User_Role" VALUES
  ('Mike', 'customer');