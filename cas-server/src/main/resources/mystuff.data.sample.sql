INSERT INTO `mystuff`.`USER`
(`id`,
`email`,
`enabled`,
`password`,
`cellPhone`,
`username`)
VALUES
(1,
"test@test.com",
true,
"test",
"0123456789",
"test");

INSERT INTO `mystuff`.`USER`
(`id`,
`email`,
`enabled`,
`password`,
`cellPhone`,
`username`)
VALUES
(2,
"admin@test.com",
true,
"admin",
"0123456789",
"admin");

INSERT INTO `mystuff`.`AUTHORITY`
(`id`,
`authority`,
`user_id`)
VALUES
(1,
"ROLE_USER",
1);

INSERT INTO `mystuff`.`AUTHORITY`
(`id`,
`authority`,
`user_id`)
VALUES
(2,
"ROLE_USER",
2);

INSERT INTO `mystuff`.`AUTHORITY`
(`id`,
`authority`,
`user_id`)
VALUES
(3,
"ROLE_SUPERVISOR",
2);