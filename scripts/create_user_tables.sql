DROP TABLE IF EXISTS tss_user_group;
DROP TABLE IF EXISTS tss_user_role;
DROP TABLE IF EXISTS tss_group_role;
DROP TABLE IF EXISTS tss_user;
DROP TABLE IF EXISTS tss_group;
DROP TABLE IF EXISTS tss_role;


CREATE TABLE tss_user
(
  id integer NOT NULL ,
  username character varying(40),
  email character varying(40),
  pass_hash character varying(60),
  CONSTRAINT tss_usr_pk PRIMARY KEY (id)
);


CREATE TABLE tss_group
(
  id integer NOT NULL,
  name character(100),
  CONSTRAINT tss_grp_pk PRIMARY KEY (id)
);


CREATE TABLE tss_role
(
  id integer NOT NULL,
  rolename character varying(40) NOT NULL,
  CONSTRAINT tss_rl_pk PRIMARY KEY (id)
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
