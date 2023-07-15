-----------------
-- Sequence structure for member_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS member_id_seq;
CREATE SEQUENCE member_id_seq
    INCREMENT 1
    MINVALUE  1
    MAXVALUE 9223372036854775807
    START WITH 1
    CACHE 1;

-- ----------------------------
-- create table
-- ----------------------------
DROP TABLE IF EXISTS doc_member;
CREATE TABLE doc_member (
  "num" int4 NOT NULL DEFAULT nextval('member_id_seq'::regclass),
  "created" date NOT NULL,
  "finished" date,
  "completed" date NOT NULL,
  "person_id" int4 NOT NULL,
  "org_id" int4 NOT NULL,
  "updated" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "id" int4 NOT NULL DEFAULT nextval('member_id_seq'::regclass)
)
;
COMMENT ON COLUMN doc_member.num IS 'Членскі білет №';
COMMENT ON COLUMN doc_member.created IS 'Дата уступлення ў прафсаюз';
COMMENT ON COLUMN doc_member.finished IS 'Дата выхада з прафсаюза';
COMMENT ON COLUMN doc_member.completed IS 'Дата заполнения';
COMMENT ON COLUMN doc_member.person_id IS 'Член';
COMMENT ON COLUMN doc_member.org_id IS 'Прафсаюза і арганізацыі';

INSERT INTO "doc_member"("num", "created", "finished", "completed", "person_id", "org_id", "updated", "id") VALUES (1, '2021-06-01', NULL, '2020-01-01', 6, 0, '2021-06-29 19:26:50.69732', 1);
INSERT INTO "doc_member"("num", "created", "finished", "completed", "person_id", "org_id", "updated", "id") VALUES (3, '2020-05-01', '2021-05-01', '2020-01-01', 1, 1, '2021-07-07 10:20:03.387141', 13);
INSERT INTO "doc_member"("num", "created", "finished", "completed", "person_id", "org_id", "updated", "id") VALUES (123, '2020-01-01', NULL, '2020-02-01', 3, 1, '2021-07-14 08:59:42.460357', 14);
INSERT INTO "doc_member"("num", "created", "finished", "completed", "person_id", "org_id", "updated", "id") VALUES (13, '2020-02-01', NULL, '2020-03-01', 4, 0, '2021-07-26 11:27:12.780477', 15);
INSERT INTO "doc_member"("num", "created", "finished", "completed", "person_id", "org_id", "updated", "id") VALUES (14, '2020-01-05', NULL, '2020-04-02', 5, 0, '2021-07-26 11:28:52.320823', 16);
INSERT INTO "doc_member"("num", "created", "finished", "completed", "person_id", "org_id", "updated", "id") VALUES (16, '2021-03-08', NULL, '2020-05-01', 6, 0, '2021-07-26 11:29:22.504784', 18);
INSERT INTO "doc_member"("num", "created", "finished", "completed", "person_id", "org_id", "updated", "id") VALUES (17, '2018-05-07', NULL, '2019-05-02', 7, 1, '2021-07-26 11:30:01.585201', 19);
INSERT INTO "doc_member"("num", "created", "finished", "completed", "person_id", "org_id", "updated", "id") VALUES (18, '2018-05-04', NULL, '2018-05-01', 8, 0, '2021-07-26 11:30:21.705022', 20);
INSERT INTO "doc_member"("num", "created", "finished", "completed", "person_id", "org_id", "updated", "id") VALUES (23, '2022-01-06', '2022-01-05', '2022-01-28', 3, 0, '2022-01-11 16:48:46.910699', 9);
INSERT INTO "doc_member"("num", "created", "finished", "completed", "person_id", "org_id", "updated", "id") VALUES (28, '2022-04-23', NULL, '2022-04-10', 1, 1, '2022-04-23 16:06:14.380474', 29);
INSERT INTO "doc_member"("num", "created", "finished", "completed", "person_id", "org_id", "updated", "id") VALUES (7, '2022-04-23', '2022-04-23', '2022-04-07', 2, 1, '2022-04-23 16:07:08.314158', 33);
