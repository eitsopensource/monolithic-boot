SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

SET search_path TO public;

TRUNCATE "user" CASCADE;

INSERT INTO "user" (id, created, disabled, email, password, name, role) 
     VALUES ( 9999, NOW(), false, 'admin@email.com', 'd1bd2f08fead38a982aed9d4ca060152400b1b8f', 'Administrator', 0);
     
INSERT INTO "user" (id, created, disabled, email, password, name, role) 
     VALUES ( 1000, NOW(), false, 'user001@testing.com', 'd1bd2f08fead38a982aed9d4ca060152400b1b8f', 'User 001', 0);
     
INSERT INTO "user" (id, created, disabled, email, password, name, role) 
     VALUES ( 1001, NOW(), false, 'user002@testing.com', 'd1bd2f08fead38a982aed9d4ca060152400b1b8f', 'User 002', 1);
     
INSERT INTO "user" (id, created, disabled, email, password, name, role) 
     VALUES ( 1002, NOW(), false, 'xova@testing.com', 'd1bd2f08fead38a982aed9d4ca060152400b1b8f', 'Xóva :x', 2);