-- Primary Key structure for table class_education
-- ----------------------------
ALTER TABLE class_education ADD CONSTRAINT class_education_pkey PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table person_main
-- ----------------------------
ALTER TABLE person_main ADD CONSTRAINT "person_pkey" PRIMARY KEY ("id");

-- Primary Key structure for table class_org
-- ----------------------------
ALTER TABLE class_org ADD CONSTRAINT class_org_pkey PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table doc_job
-- ----------------------------
ALTER TABLE doc_job ADD CONSTRAINT ux_doc_job UNIQUE ("person_id", "place", "post", "created", "finished");

-- ----------------------------
-- Primary Key structure for table doc_job
-- ----------------------------
ALTER TABLE doc_job ADD CONSTRAINT work_change_doc_pkey PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE users ADD CONSTRAINT "users_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table doc_member
-- ----------------------------
ALTER TABLE doc_member ADD CONSTRAINT ux_doc_member UNIQUE ("person_id", "created", "org_id");

-- ----------------------------
-- Primary Key structure for table doc_member
-- ----------------------------
ALTER TABLE doc_member ADD CONSTRAINT membership_card_doc_pkey PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table doc_payment
-- ----------------------------
ALTER TABLE doc_payment ADD CONSTRAINT "ux_doc_payment" UNIQUE ("person_id", "created");

-- ----------------------------
-- Primary Key structure for table doc_payment
-- ----------------------------
ALTER TABLE doc_payment ADD CONSTRAINT "payment_doc_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table permissions
-- ----------------------------
ALTER TABLE permissions ADD CONSTRAINT "permissions_pk_2" UNIQUE ("id");

-- ----------------------------
-- Primary Key structure for table permissions
-- ----------------------------
ALTER TABLE permissions ADD CONSTRAINT "permissions_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table doc_job
-- ----------------------------
ALTER TABLE doc_job ADD CONSTRAINT "work_change_doc_person_id_fkey" FOREIGN KEY ("person_id") REFERENCES person_main ("id") ON DELETE NO ACTION ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table doc_member
-- ----------------------------
ALTER TABLE doc_member ADD CONSTRAINT "membership_card_doc_person_id_fkey" FOREIGN KEY ("person_id") REFERENCES person_main ("id") ON DELETE NO ACTION ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Keys structure for table doc_payment
-- ----------------------------
ALTER TABLE doc_payment ADD CONSTRAINT "payment_doc_person_id_fkey" FOREIGN KEY ("person_id") REFERENCES person_main ("id") ON DELETE NO ACTION ON UPDATE CASCADE;

