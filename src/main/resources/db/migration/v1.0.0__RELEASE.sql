--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.1

-- Started on 2017-03-07 12:37:22 BRT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 8 (class 2615 OID 37480)
-- Name: auditing; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA auditing;


--
-- TOC entry 2 (class 3079 OID 12623)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2391 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 1 (class 3079 OID 17001)
-- Name: unaccent; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS unaccent WITH SCHEMA pg_catalog;


--
-- TOC entry 2392 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION unaccent; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION unaccent IS 'text search dictionary that removes accents';


SET search_path = auditing, pg_catalog;

SET default_with_oids = false;

--
-- TOC entry 184 (class 1259 OID 37483)
-- Name: revision; Type: TABLE; Schema: auditing; Owner: -
--

CREATE TABLE revision (
    id bigint NOT NULL,
    "timestamp" bigint NOT NULL,
    user_id bigint
);


--
-- TOC entry 183 (class 1259 OID 37481)
-- Name: revision_id_seq; Type: SEQUENCE; Schema: auditing; Owner: -
--

CREATE SEQUENCE revision_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2393 (class 0 OID 0)
-- Dependencies: 183
-- Name: revision_id_seq; Type: SEQUENCE OWNED BY; Schema: auditing; Owner: -
--

ALTER SEQUENCE revision_id_seq OWNED BY revision.id;


--
-- TOC entry 185 (class 1259 OID 37489)
-- Name: user_audited; Type: TABLE; Schema: auditing; Owner: -
--

CREATE TABLE user_audited (
    id bigint NOT NULL,
    revision bigint NOT NULL,
    revision_type smallint,
    disabled boolean,
    email character varying(144),
    last_login timestamp without time zone,
    name character varying(50),
    password character varying(100),
    role integer
);


SET search_path = public, pg_catalog;

--
-- TOC entry 187 (class 1259 OID 37497)
-- Name: user; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE "user" (
    id bigint NOT NULL,
    created timestamp without time zone NOT NULL,
    updated timestamp without time zone,
    disabled boolean NOT NULL,
    email character varying(144) NOT NULL,
    last_login timestamp without time zone,
    name character varying(50) NOT NULL,
    password character varying(100) NOT NULL,
    role integer NOT NULL
);


--
-- TOC entry 186 (class 1259 OID 37495)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2394 (class 0 OID 0)
-- Dependencies: 186
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE user_id_seq OWNED BY "user".id;


SET search_path = auditing, pg_catalog;

--
-- TOC entry 2261 (class 2604 OID 37486)
-- Name: id; Type: DEFAULT; Schema: auditing; Owner: -
--

ALTER TABLE ONLY revision ALTER COLUMN id SET DEFAULT nextval('revision_id_seq'::regclass);


SET search_path = public, pg_catalog;

--
-- TOC entry 2262 (class 2604 OID 37500)
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


SET search_path = auditing, pg_catalog;

--
-- TOC entry 2264 (class 2606 OID 37488)
-- Name: revision_pkey; Type: CONSTRAINT; Schema: auditing; Owner: -
--

ALTER TABLE ONLY revision
    ADD CONSTRAINT revision_pkey PRIMARY KEY (id);


--
-- TOC entry 2266 (class 2606 OID 37493)
-- Name: user_audited_pkey; Type: CONSTRAINT; Schema: auditing; Owner: -
--

ALTER TABLE ONLY user_audited
    ADD CONSTRAINT user_audited_pkey PRIMARY KEY (id, revision);


SET search_path = public, pg_catalog;

--
-- TOC entry 2268 (class 2606 OID 37504)
-- Name: uk_ob8kqyqqgmefl0aco34akdtpe; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT uk_ob8kqyqqgmefl0aco34akdtpe UNIQUE (email);


--
-- TOC entry 2270 (class 2606 OID 37502)
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


SET search_path = auditing, pg_catalog;

--
-- TOC entry 2271 (class 2606 OID 37505)
-- Name: fk8c9clon52hw5mksclkjcce6k0; Type: FK CONSTRAINT; Schema: auditing; Owner: -
--

ALTER TABLE ONLY user_audited
    ADD CONSTRAINT fk8c9clon52hw5mksclkjcce6k0 FOREIGN KEY (revision) REFERENCES revision(id);


----------------------- 
-- DEFAULT DATA
-----------------------
INSERT INTO "public"."user"(
            id, created, updated, email, disabled, name, password, role)
    VALUES (1, NOW(), null, 'admin@admin.com', FALSE, 'Administrador de Sistemas', '$2a$10$bAdAVLvM.k3DqPaPYi0gnO1OffPSHLref8MElAk.u.fFQ17v9YKC2', 0);