SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';


CREATE SCHEMA auditing;
CREATE EXTENSION IF NOT EXISTS unaccent SCHEMA public;

CREATE TABLE auditing.revision (
  id          bigserial NOT NULL PRIMARY KEY,
  "timestamp" bigint    NOT NULL,
  user_id     bigint
);

CREATE TABLE auditing.user_audited (
  id                              bigint NOT NULL,
  revision                        bigint NOT NULL,
  revision_type                   smallint,
  disabled                        boolean,
  email                           character varying(144),
  last_login                      timestamp without time zone,
  name                            character varying(50),
  password                        character varying(100),
  role                            integer,
  password_reset_token            varchar,
  password_reset_token_expiration timestamp with time zone,
  CONSTRAINT fk_user_audited_revision FOREIGN KEY (revision) REFERENCES auditing.revision (id),
  CONSTRAINT pk_user_audited PRIMARY KEY (id, revision)
);

CREATE TABLE "user" (
  id                              bigint                   NOT NULL PRIMARY KEY,
  created                         timestamp with time zone NOT NULL,
  updated                         timestamp with time zone,
  disabled                        boolean                  NOT NULL,
  email                           character varying(144)   NOT NULL,
  last_login                      timestamp with time zone,
  name                            character varying(50)    NOT NULL,
  password                        character varying(100)   NOT NULL,
  role                            integer                  NOT NULL,
  password_reset_token            varchar,
  password_reset_token_expiration timestamp with time zone
);

CREATE UNIQUE INDEX idx_user_email
  ON "user" (lower(email));

-----------------------
-- DEFAULT DATA
-----------------------
INSERT INTO "user" (
  id, created, updated, email, disabled, name, password, role)
VALUES (1, NOW(), NULL, 'admin@admin.com', FALSE, 'Administrador de Sistemas',
        '$2a$10$bAdAVLvM.k3DqPaPYi0gnO1OffPSHLref8MElAk.u.fFQ17v9YKC2', 0);