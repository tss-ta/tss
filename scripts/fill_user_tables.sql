INSERT INTO tss_user VALUES
  ('Adam', 'adam.sandler@gmail.com', 'adampassword');
INSERT INTO tss_user VALUES
  ('Mike', 'mike.cole@gmail.com', 'mikepassword');
INSERT INTO tss_user VALUES
  ('Sara', 'sara.smith@gmail.com', 'sarapassword');
INSERT INTO tss_user VALUES
  ('Vade', 'vade.gordon@gmail.com', 'vadepassword');


INSERT INTO tss_group VALUES
  (1, 'readonly', 'readonly_group');
INSERT INTO tss_group VALUES
  (2, 'managers', 'managers_group');
INSERT INTO tss_group VALUES
  (3, 'black_list', 'black_list_group');


INSERT INTO role VALUES
  ('admin');
INSERT INTO role VALUES
  ('driver');
INSERT INTO role VALUES
  ('customer');


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


INSERT INTO user_role VALUES
  ('Mike', 'admin');
INSERT INTO user_role VALUES
  ('Sara', 'admin');
INSERT INTO user_role VALUES
  ('Vade', 'customer');
INSERT INTO user_role VALUES
  ('Mike', 'customer');