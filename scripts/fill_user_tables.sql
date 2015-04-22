INSERT INTO tss_user VALUES
  (1, 'Adam', 'adam.sandler@gmail.com', 'adampassword');
INSERT INTO tss_user VALUES
  (2, 'Mike', 'mike.cole@gmail.com', 'mikepassword');
INSERT INTO tss_user VALUES
  (3, 'Sara', 'sara.smith@gmail.com', 'sarapassword');
INSERT INTO tss_user VALUES
  (4, 'Vade', 'vade.gordon@gmail.com', 'vadepassword');
INSERT INTO tss_user VALUES
  (5, 'Adam', 'adam.sandler@gmail.com', 'adampassword');
INSERT INTO tss_user VALUES
  (6, 'Joey', 'joey.vdome@net.com', 'joeypassword');
INSERT INTO tss_user VALUES
  (7, 'Samantha', 's.smith@gmail.com', 'samanthapassword');
INSERT INTO tss_user VALUES
  (8, 'Trevor', 'trevor.rovert@gmail.com', 'trevorpassword');


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