select * from profile;
TRUNCATE profile;

UPDATE databasechangelog
SET md5sum = NULL;
