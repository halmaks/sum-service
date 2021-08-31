SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE item;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO item(id, value)
VALUES ('one', 1),
       ('two', 2);
