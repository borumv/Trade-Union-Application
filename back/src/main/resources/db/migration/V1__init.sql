-- ----------------------------
-- Sequence structure for class_education_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS class_education_seq;
CREATE SEQUENCE class_education_seq
    INCREMENT 1
    MINVALUE  1
    MAXVALUE 9223372036854775807
    START 100
    CACHE 1;

-- ----------------------------
-- Create class_education table
-- ----------------------------

DROP TABLE IF EXISTS class_education;
CREATE TABLE class_education (
                                          "id" int4 NOT NULL DEFAULT nextval('class_education_seq'::regclass),
                                          "name" varchar(255) NOT NULL,
                                          "name1" varchar(255),
                                          "name2" varchar(50),
                                          "updated" timestamp(6)
);

-- ----------------------------
--  filling in the table class_education
-- ----------------------------

INSERT INTO class_education("id", "name", "name1", "name2", "updated") VALUES (1, 'Базовое', 'Базовое', NULL, '2020-05-20 20:31:01.388842');
INSERT INTO class_education("id", "name", "name1", "name2", "updated") VALUES (2, 'Высшее', 'Высшее', NULL, '2020-05-20 20:31:01.388842');
INSERT INTO class_education("id", "name", "name1", "name2", "updated") VALUES (3, 'Среднее специальное', 'Среднее специальное', NULL, '2020-05-20 20:31:01.388842');
INSERT INTO class_education("id", "name", "name1", "name2", "updated") VALUES (4, 'Неоконченное высшее', 'Неоконченное высшее', NULL, '2020-05-20 20:31:01.388842');
INSERT INTO class_education("id", "name", "name1", "name2", "updated") VALUES (5, 'Профессионально техническое', 'Профессионально техническое', NULL, '2020-05-20 20:31:01.388842');
INSERT INTO class_education("id", "name", "name1", "name2", "updated") VALUES (6, 'Среднее', 'Среднее', NULL, '2020-05-20 20:31:01.388842');
INSERT INTO class_education("id", "name", "name1", "name2", "updated") VALUES (7, 'Не указано', 'Не указано', NULL, '2020-05-20 20:31:01.388842');
INSERT INTO class_education("id", "name", "name1", "name2", "updated") VALUES (8, 'Неполное базовое', 'Неполное базовое', NULL, '2020-05-20 20:31:01.388842');
INSERT INTO class_education("id", "name", "name1", "name2", "updated") VALUES (9, 'Неполное среднее', 'Неполное среднее', NULL, '2020-05-20 20:31:01.388842');
INSERT INTO class_education("id", "name", "name1", "name2", "updated") VALUES (10, 'Отсутствует', 'Отсутствует', NULL, '2020-05-20 20:31:01.388842');


-- ----------------------------
-- Sequence structure for org_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS org_seq;
CREATE SEQUENCE org_seq
    INCREMENT 1
    MINVALUE  2
    MAXVALUE 9223372036854775807
    START 2
    CACHE 1;
-- ----------------------------
-- Table structure for class_org
-- ----------------------------
DROP TABLE IF EXISTS class_org;
CREATE TABLE class_org (
                                    "id" int4 NOT NULL DEFAULT nextval('org_seq'::regclass),
                                    "name" varchar(255),
                                    "city" varchar(255),
                                    "address" varchar(255),
                                    "updated" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON COLUMN class_org.name IS 'Название';
COMMENT ON COLUMN class_org.city IS 'Город';
COMMENT ON COLUMN class_org.address IS 'Адрес';

INSERT INTO class_org("id", "name", "city", "address", "updated") VALUES (1, 'Центральный профсоюз', 'Минск', 'ул. Ленина', '2022-01-15 15:53:24.026042');
INSERT INTO class_org("id", "name", "city", "address", "updated") VALUES (2, 'Городской профсоюз', 'Минск', 'ул. К.Маркса', '2022-05-07 19:22:35.120439');


-- ----------------------------
-- Sequence structure for job_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS job_id_seq;
CREATE SEQUENCE job_id_seq
    INCREMENT 1
    MINVALUE  1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

-- ----------------------------
-- Table structure for doc_job
-- ----------------------------
DROP TABLE IF EXISTS doc_job;
CREATE TABLE doc_job (
                                  "person_id" int4 NOT NULL,
                                  "place" varchar(255) NOT NULL,
                                  "post" varchar(255) NOT NULL,
                                  "created" date NOT NULL,
                                  "finished" date,
                                  "updated" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  "id" int4 NOT NULL DEFAULT nextval('job_id_seq'::regclass)
);
COMMENT ON COLUMN doc_job.place IS 'Назва прадпрыемства, установы, арганізацыі';
COMMENT ON COLUMN doc_job.post IS 'Займаемая пасада';

-- ----------------------------
--  filling in the table doc_job
-- ----------------------------
INSERT INTO doc_job("person_id", "place", "post", "created", "finished", "updated", "id") VALUES (1, 'завод Лихачева', 'инженер', '2021-06-01', NULL, '2021-06-29 19:26:08.378013', 1);
INSERT INTO doc_job("person_id", "place", "post", "created", "finished", "updated", "id") VALUES (1, 'МЗКТ', 'бухгалтер', '2021-06-01', NULL, '2021-06-29 19:26:08.378013', 2);

-- ----------------------------
-- Sequence structure for users_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS users_id_seq;
CREATE SEQUENCE users_id_seq
    INCREMENT 1
    MINVALUE  1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS users;
CREATE TABLE users (
                                "id" int8 NOT NULL DEFAULT nextval('users_id_seq'::regclass),
                                "email" varchar(255) NOT NULL,
                                "first_name" varchar(250),
                                "last_name" varchar(100),
                                "password" varchar(255) NOT NULL,
                                "role" varchar(20) DEFAULT 'USER'::character varying,
                                "status" varchar(20) NOT NULL DEFAULT 'ACTIVE'::character varying
);
INSERT INTO users("id", "email", "first_name", "last_name", "password", "role", "status") VALUES (5, 'borya123@gmail.com', 'string', 'string', '$2a$10$2qEQj1NvMuc9NQ4yJfoZleq2kAlx/TsHQsnEy3Bgxi5NwZ4Qdgyay', 'USER', 'ACTIVE');
INSERT INTO users("id", "email", "first_name", "last_name", "password", "role", "status") VALUES (1, 'admin@gmail.com', 'Boris', 'Borisov', '$2a$10$2qEQj1NvMuc9NQ4yJfoZleq2kAlx/TsHQsnEy3Bgxi5NwZ4Qdgyay', 'ADMIN', 'ACTIVE');
INSERT INTO users("id", "email", "first_name", "last_name", "password", "role", "status") VALUES (3, 'user2@gmail.com', 'Kostya', 'Kostkov', '$2a$10$2qEQj1NvMuc9NQ4yJfoZleq2kAlx/TsHQsnEy3Bgxi5NwZ4Qdgyay', 'USER', 'BANNED');
INSERT INTO users("id", "email", "first_name", "last_name", "password", "role", "status") VALUES (2, 'user@gmail.com', 'Vasya', 'Vaskov', '$2a$10$2qEQj1NvMuc9NQ4yJfoZleq2kAlx/TsHQsnEy3Bgxi5NwZ4Qdgyay', 'USER', 'ACTIVE');