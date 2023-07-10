-- ----------------------------
-- Sequence structure for person_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS person_id_seq CASCADE ;
CREATE SEQUENCE person_id_seq
    INCREMENT 1
    MINVALUE  1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

-- ----------------------------
-- Table structure for person_main
-- ----------------------------
DROP TABLE IF EXISTS person_main;
CREATE TABLE person_main (
                                      fn varchar(255) NOT NULL,
                                      ln varchar(255) NOT NULL,
                                      mn varchar(255),
                                      birth date NOT NULL,
                                      education varchar(255) ,
                                      address varchar(255),
                                      phone varchar(255),
                                      id int4 NOT NULL DEFAULT nextval('person_id_seq'::regclass),
                                      birth_place varchar(255) COLLATE "pg_catalog"."default",
                                      live_place varchar(255) COLLATE "pg_catalog"."default",
                                      reg_place varchar(255) COLLATE "pg_catalog"."default",
                                      marital_id int2 NOT NULL DEFAULT 0,
                                      citizenship varchar(100) COLLATE "pg_catalog"."default",
                                      nationality varchar(100) COLLATE "pg_catalog"."default",
                                      comment varchar(255) COLLATE "pg_catalog"."default",
                                      updated timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON COLUMN person_main.fn IS 'Імя';
COMMENT ON COLUMN person_main.ln IS 'Прозвішча';
COMMENT ON COLUMN person_main.mn IS 'Імя па бацьку';
COMMENT ON COLUMN person_main.birth IS 'Дата нараджэня';
COMMENT ON COLUMN person_main.education IS 'Адукацыя';
COMMENT ON COLUMN person_main.address IS 'Дамашні адрас';
COMMENT ON COLUMN person_main.phone IS '№ тэлефона';
COMMENT ON COLUMN person_main.birth_place IS 'Место рождения';
COMMENT ON COLUMN person_main.live_place IS 'Место фактического жительства';
COMMENT ON COLUMN person_main.reg_place IS 'Место регистрации';
COMMENT ON COLUMN person_main.marital_id IS 'Семейное положение';
COMMENT ON COLUMN person_main.citizenship IS 'Гражданство';
COMMENT ON COLUMN person_main.nationality IS 'Национальность';
COMMENT ON COLUMN person_main.comment IS 'Комментарий';

INSERT INTO "person_main"("fn", "ln", "mn", "birth", "education", "address", "phone", "id", "birth_place", "live_place", "reg_place", "marital_id", "citizenship", "nationality", "comment", "updated") VALUES ('Александр', 'Иванов', 'Александрович', '1990-03-15', 'Высшее', 'Минск', '293356998', 1, 'Минск', 'Минск', 'Минск', 1, 'Беларусь', 'Белорус', 'Комментариев нет', '2022-05-20 15:30:00');
INSERT INTO "person_main"("fn", "ln", "mn", "birth", "education", "address", "phone", "id", "birth_place", "live_place", "reg_place", "marital_id", "citizenship", "nationality", "comment", "updated") VALUES ('Екатерина', 'Смирнова', 'Дмитриевна', '1985-09-22', 'Высшее', 'Гродно', '293356998', 2, 'Гродно', 'Гродно', 'Минск', 0, 'Беларусь', 'Белорус', NULL, '2022-06-10 12:45:00');
INSERT INTO "person_main"("fn", "ln", "mn", "birth", "education", "address", "phone", "id", "birth_place", "live_place", "reg_place", "marital_id", "citizenship", "nationality", "comment", "updated") VALUES ('Иван', 'Петров', 'Иванович', '1998-07-10', 'Среднее', 'Витебск', '293356998', 3, 'Витебск', 'Витебск', 'Витебск', 0, 'Беларусь', 'Белорус', NULL, '2022-07-01 09:15:00');
INSERT INTO "person_main"("fn", "ln", "mn", "birth", "education", "address", "phone", "id", "birth_place", "live_place", "reg_place", "marital_id", "citizenship", "nationality", "comment", "updated") VALUES ('Мария', 'Сидорова', 'Алексеевна', '1992-05-12', 'Высшее', 'Брест', '293356998', 4, 'Брест', 'Брест', 'Минск', 1, 'Беларусь', 'Белорус', 'Комментарий', '2022-08-15 16:20:00');
INSERT INTO "person_main"("fn", "ln", "mn", "birth", "education", "address", "phone", "id", "birth_place", "live_place", "reg_place", "marital_id", "citizenship", "nationality", "comment", "updated") VALUES ('Андрей', 'Козлов', 'Андреевич', '1995-12-03', 'Среднее', 'Могилев', '293356998', 5, 'Могилев', 'Могилев', 'Минск', 0, 'Беларусь', 'Белорус', NULL, '2022-09-30 11:10:00');
INSERT INTO "person_main"("fn", "ln", "mn", "birth", "education", "address", "phone", "id", "birth_place", "live_place", "reg_place", "marital_id", "citizenship", "nationality", "comment", "updated") VALUES ('Елена', 'Ковалева', 'Игоревна', '1993-08-18', 'Высшее', 'Гомель', '293356998', 6, 'Гомель', 'Гомель', 'Минск', 1, 'Беларусь', 'Белорус', NULL, '2022-10-15 14:55:00');
INSERT INTO "person_main"("fn", "ln", "mn", "birth", "education", "address", "phone", "id", "birth_place", "live_place", "reg_place", "marital_id", "citizenship", "nationality", "comment", "updated") VALUES ('Алексей', 'Новиков', 'Сергеевич', '1997-04-27', 'Среднее', 'Минск', '293356998', 7, 'Минск', 'Минск', 'Минск', 0, 'Беларусь', 'Белорус', NULL, '2022-11-20 10:30:00');
INSERT INTO "person_main"("fn", "ln", "mn", "birth", "education", "address", "phone", "id", "birth_place", "live_place", "reg_place", "marital_id", "citizenship", "nationality", "comment", "updated") VALUES ('Ольга', 'Соколова', 'Александровна', '1991-11-10', 'Высшее', 'Минск', '293356998', 8, 'Минск', 'Минск', 'Минск', 1, 'Беларусь', 'Белорус', 'Комментарий', '2022-12-05 16:45:00');
INSERT INTO "person_main"("fn", "ln", "mn", "birth", "education", "address", "phone", "id", "birth_place", "live_place", "reg_place", "marital_id", "citizenship", "nationality", "comment", "updated") VALUES ('Михаил', 'Кузнецов', 'Иванович', '1994-02-09', 'Среднее', 'Брест', '293356998', 9, 'Брест', 'Брест', 'Минск', 0, 'Беларусь', 'Белорус', NULL, '2023-01-10 12:15:00');
INSERT INTO "person_main"("fn", "ln", "mn", "birth", "education", "address", "phone", "id", "birth_place", "live_place", "reg_place", "marital_id", "citizenship", "nationality", "comment", "updated") VALUES ('Анна', 'Павлова', 'Васильевна', '1996-07-24', 'Высшее', 'Минск', '293356998', 10, 'Минск', 'Минск', 'Минск', 1, 'Беларусь', 'Белорус', NULL, '2023-02-25 09:50:00');
