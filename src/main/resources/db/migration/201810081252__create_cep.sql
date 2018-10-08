CREATE TABLE cep (
  id      bigserial PRIMARY KEY,
  created timestamp WITHOUT TIME ZONE NOT NULL,
  updated timestamp WITHOUT TIME ZONE,
  cep     varchar(8)
);