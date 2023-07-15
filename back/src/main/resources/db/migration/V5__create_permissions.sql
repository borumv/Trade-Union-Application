-- ----------------------------
-- Sequence structure for permissions_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS permissions_id_seq;
CREATE SEQUENCE permissions_id_seq
    INCREMENT 1
    MINVALUE  1
    MAXVALUE 9223372036854775807
    START WITH 1
    CACHE 1;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS permissions;
CREATE TABLE permissions (
                                      id int4 NOT NULL DEFAULT nextval('permissions_id_seq'::regclass),
                                      role varchar(20),
                                      resource varchar,
                                      action varchar,
                                      permit bool
);
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (2, 'USER', 'persons', 'insert', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (3, 'USER', 'persons', 'delete', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (4, 'USER', 'persons', 'read', 't');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (5, 'USER', 'persons', 'update', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (6, 'ADMIN', 'persons', 'insert', 't');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (7, 'ADMIN', 'persons', 'delete', 't');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (8, 'ADMIN', 'persons', 'read', 't');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (9, 'ADMIN', 'persons', 'update', 't');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (10, 'ADMIN', 'tradeunion', 'insert', 't');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (11, 'ADMIN', 'tradeunion', 'delete', 't');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (12, 'ADMIN', 'tradeunion', 'update', 't');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (13, 'ADMIN', 'tradeunion', 'read', 't');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (14, 'USER', 'tradeunion', 'insert', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (15, 'USER', 'tradeunion', 'delete', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (16, 'USER', 'tradeunion', 'update', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (17, 'USER', 'tradeunion', 'read', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (18, 'ADMIN', 'docpayments', 'delete', 't');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (19, 'ADMIN', 'docpayments', 'update', 't');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (20, 'ADMIN', 'docpayments', 'read', 't');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (21, 'USER', 'docpayments', 'insert', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (22, 'USER', 'docpayments', 'delete', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (23, 'USER', 'docpayments', 'update', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (24, 'USER', 'docpayments', 'read', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (25, 'ADMIN', 'education', 'delete', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (26, 'ADMIN', 'education', 'insert', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (27, 'ADMIN', 'education', 'update', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (28, 'ADMIN', 'education', 'read', 't');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (29, 'USER', 'education', 'delete', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (30, 'USER', 'education', 'insert', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (31, 'USER', 'education', 'update', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (32, 'USER', 'education', 'read', 't');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (33, 'ADMIN', 'docpayments', 'delete', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (34, 'ADMIN', 'docpayments', 'insert', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (35, 'ADMIN', 'docpayments', 'update', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (36, 'ADMIN', 'docpayments', 'read', 't');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (37, 'USER', 'docpayments', 'delete', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (38, 'USER', 'docpayments', 'insert', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (39, 'USER', 'docpayments', 'update', 'f');
INSERT INTO permissions("id", "role", "resource", "action", "permit") VALUES (40, 'USER', 'docpayments', 'read', 'f');