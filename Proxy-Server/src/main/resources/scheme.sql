CREATE TABLE SYSUSER(
  id NUMBER (20),
  username varchar2(128),
  password varchar2(128)
)

CREATE TABLE SYSROLE(
  id NUMBER (20),
  name varchar2(128)
)

CREATE TABLE USER_ROLE(
  user_id NUMBER (20),
  role_id NUMBER (20),
  password varchar2(128)
)



INSERT INTO SYSUSER (id, username, password) VALUES (1, 'forezp', '123456');
INSERT INTO SYSUSER (id, username, password)  VALUES (2, 'admin', '123456');

INSERT INTO SYSROLE (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO SYSROLE (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
