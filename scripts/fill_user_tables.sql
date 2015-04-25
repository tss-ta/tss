
-- password is 'pass'
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


INSERT INTO tss_group VALUES
  (1, 'readonly');
INSERT INTO tss_group VALUES
  (2, 'managers');
INSERT INTO tss_group VALUES
  (3, 'black_list');


INSERT INTO tss_role VALUES
  (1, 'ADMIN');
INSERT INTO tss_role VALUES
  (2, 'DRIVER');
INSERT INTO tss_role VALUES
  (3, 'CUSTOMER');


INSERT INTO car VALUES
  (DEFAULT, 'AAA333BBB', 4, FALSE, FALSE, TRUE, TRUE);
INSERT INTO car VALUES
  (DEFAULT, 'DDD555CCC', 3, FALSE, TRUE, TRUE, FALSE);
INSERT INTO car VALUES
  (DEFAULT, 'EEE777FFF', 2, TRUE, FALSE, FALSE, FALSE);
  INSERT INTO car VALUES
  (DEFAULT, 'ADA343BAB', 1, FALSE, FALSE, TRUE, TRUE);
INSERT INTO car VALUES
  (DEFAULT, 'DQD565CCE', 2, FALSE, TRUE, TRUE, FALSE);
INSERT INTO car VALUES
  (DEFAULT, 'ELE777FLF', 1, TRUE, FALSE, FALSE, FALSE);
  INSERT INTO car VALUES
  (DEFAULT, 'QWE123RTY', 4, FALSE, FALSE, TRUE, TRUE);
INSERT INTO car VALUES
  (DEFAULT, 'MR0000BIN', 3, FALSE, TRUE, TRUE, FALSE);
INSERT INTO car VALUES
  (DEFAULT, 'AEE387FFF', 2, TRUE, FALSE, FALSE, TRUE);
INSERT INTO car VALUES
  (DEFAULT, 'ELU778FLF', 1, TRUE, FALSE, FALSE, FALSE);
  INSERT INTO car VALUES
  (DEFAULT, 'QWE129RTY', 4, FALSE, FALSE, TRUE, TRUE);
INSERT INTO car VALUES
  (DEFAULT, 'IO0770STD', 3, FALSE, TRUE, TRUE, FALSE);
INSERT INTO car VALUES
  (DEFAULT, 'EGE737FII', 2, TRUE, FALSE, FALSE, FALSE);

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


INSERT INTO tss_group_role VALUES
  (1, 2);
INSERT INTO tss_group_role VALUES
  (2, 1);
INSERT INTO tss_group_role VALUES
  (3, 3);