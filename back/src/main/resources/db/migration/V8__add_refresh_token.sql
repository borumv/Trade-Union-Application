DROP SEQUENCE IF EXISTS refreshtoken_seq;
CREATE SEQUENCE refreshtoken_seq
    INCREMENT 1
    MINVALUE  1
    MAXVALUE 9223372036854775807
    START WITH 1
    CACHE 1;

CREATE TABLE refreshtoken (
                              id int4 NOT NULL DEFAULT nextval('refreshtoken_seq'::regclass),
                              user_id BIGINT NOT NULL,
                              token VARCHAR(255) NOT NULL UNIQUE,
                              expiryDate TIMESTAMP NOT NULL,
                              FOREIGN KEY (user_id) REFERENCES "users"(id)
);
