
-- ----------------------------
-- Table structure for modes
-- ----------------------------
DROP TABLE IF EXISTS modes;
CREATE TABLE modes (
                                "num" int4,
                                "mode" text
);

INSERT INTO "modes"("num", "mode") VALUES (2, 'HIGH');
INSERT INTO "modes"("num", "mode") VALUES (1, 'HIGH');