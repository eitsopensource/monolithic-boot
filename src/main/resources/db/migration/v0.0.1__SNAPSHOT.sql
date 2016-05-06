SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

CREATE SCHEMA IF NOT EXISTS "public";
CREATE SCHEMA IF NOT EXISTS "auditing";
CREATE EXTENSION IF NOT EXISTS UNACCENT SCHEMA PG_CATALOG;

----------------------- 
-- AUDITING STRUCTURE
-----------------------
--
-- TOC entry 177 (class 1259 OID 278689)
-- Name: revision; Type: TABLE; Schema: auditing; Owner: -
--
CREATE TABLE auditing.revision
(
  id bigserial NOT NULL,
  "timestamp" bigint NOT NULL,
  user_id bigint,
  CONSTRAINT revision_pkey PRIMARY KEY (id)
);

--
-- TOC entry 178 (class 1259 OID 278695)
-- Name: user_audited; Type: TABLE; Schema: auditing; Owner: -
--
CREATE TABLE auditing.user_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  email character varying(144),
  enabled boolean,
  last_login timestamp without time zone,
  name character varying(50),
  password character varying(100),
  role integer,
  CONSTRAINT user_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk_user_audited_revision FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

----------------------- 
-- PUBLIC STRUCTURE
-----------------------

--
-- TOC entry 175 (class 1259 OID 278679)
-- Name: user; Type: TABLE; Schema: public; Owner: -
--
CREATE TABLE "public"."user"
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  email character varying(144) NOT NULL,
  enabled boolean NOT NULL,
  name character varying(50) NOT NULL,
  password character varying(100) NOT NULL,
  role integer NOT NULL,
  last_login timestamp without time zone,
  CONSTRAINT user_pkey PRIMARY KEY (id),
  CONSTRAINT uk_user_email UNIQUE (email)
);

----------------------- 
-- DEFAULT DATA
-----------------------
INSERT INTO "public"."user"(
            id, created, updated, email, enabled, name, password, role)
    VALUES (1, NOW(), null, 'admin@admin.com', TRUE, 'Administrador de Sistemas', 'd1bd2f08fead38a982aed9d4ca060152400b1b8f', 0);