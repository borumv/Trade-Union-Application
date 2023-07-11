-- ----------------------------
-- Sequence structure for payment_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS payment_id_seq;
CREATE SEQUENCE payment_id_seq
    INCREMENT 1
    MINVALUE  1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;


-- ----------------------------
-- Table structure for doc_payment
-- ----------------------------
DROP TABLE IF EXISTS doc_payment;
CREATE TABLE doc_payment (
  "person_id" int4 NOT NULL,
  "created" date NOT NULL,
  "finished" date,
  "org_id" int4 NOT NULL,
  "updated" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "id" int4 NOT NULL DEFAULT nextval('payment_id_seq'::regclass)
)
;
COMMENT ON COLUMN doc_payment.person_id IS 'Член';
COMMENT ON COLUMN doc_payment.created IS 'Начало периода';
COMMENT ON COLUMN doc_payment.finished IS 'Конец периода';
COMMENT ON COLUMN doc_payment.org_id IS 'Прафсаюз і арганізацыя';
INSERT INTO doc_payment("person_id", "created", "finished", "org_id", "updated", "id") VALUES (1, '2021-06-13', NULL, 1, '2022-04-21 20:01:58.828', 6);
INSERT INTO "doc_payment"("person_id", "created", "finished", "org_id", "updated", "id") VALUES (3, '2021-06-18', NULL, 1, '2021-06-29 19:27:41.859862', 1);
INSERT INTO "doc_payment"("person_id", "created", "finished", "org_id", "updated", "id") VALUES (2, '2019-06-07', NULL, 1, '2021-06-29 19:27:41.859862', 2);
INSERT INTO "doc_payment"("person_id", "created", "finished", "org_id", "updated", "id") VALUES (3, '2021-06-01', '2022-04-21', 1, '2022-04-21 20:22:03.309', 14);
INSERT INTO "doc_payment"("person_id", "created", "finished", "org_id", "updated", "id") VALUES (4, '2017-06-14', '2022-04-21', 1, '2022-04-21 20:23:37.439', 12);
INSERT INTO "doc_payment"("person_id", "created", "finished", "org_id", "updated", "id") VALUES (5, '2016-06-25', '2022-04-21', 1, '2022-04-21 20:26:36.347', 11);
INSERT INTO "doc_payment"("person_id", "created", "finished", "org_id", "updated", "id") VALUES (6, '2021-06-19', '2022-04-23', 1, '2022-04-23 10:17:34.564', 10);
INSERT INTO "doc_payment"("person_id", "created", "finished", "org_id", "updated", "id") VALUES (7, '2021-06-03', '2022-04-23', 1, '2022-04-23 10:35:57.484', 9);
INSERT INTO "doc_payment"("person_id", "created", "finished", "org_id", "updated", "id") VALUES (8, '2021-06-23', '2022-04-23', 1, '2022-04-23 10:40:42.925', 7);
INSERT INTO "doc_payment"("person_id", "created", "finished", "org_id", "updated", "id") VALUES (3, '2021-06-26', '2022-04-23', 1, '2022-04-23 10:40:49.419', 5);
INSERT INTO "doc_payment"("person_id", "created", "finished", "org_id", "updated", "id") VALUES (5, '2020-08-05', '2022-04-23', 1, '2022-04-23 18:36:24.386', 4);
INSERT INTO "doc_payment"("person_id", "created", "finished", "org_id", "updated", "id") VALUES (6, '2020-06-20', '2022-04-23', 1, '2022-04-23 19:23:40.794', 3);
INSERT INTO "doc_payment"("person_id", "created", "finished", "org_id", "updated", "id") VALUES (7, '2022-05-05', NULL, 0, '2022-05-05 18:39:37.462019', 15);
INSERT INTO "doc_payment"("person_id", "created", "finished", "org_id", "updated", "id") VALUES (5, '2022-05-05', NULL, 0, '2022-05-05 18:43:46.039922', 23);
INSERT INTO "doc_payment"("person_id", "created", "finished", "org_id", "updated", "id") VALUES (1, '2022-04-01', '2022-05-06', 1, '2022-05-06 09:26:37.641', 13);
INSERT INTO "doc_payment"("person_id", "created", "finished", "org_id", "updated", "id") VALUES (1, '2022-05-05', '2022-05-07', 35, '2022-05-07 19:15:13.847', 27);