/*
 Navicat Premium Data Transfer

 Source Server         : mysqlConnect
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : exam_system

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 29/05/2023 22:04:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for e_answer
-- ----------------------------
DROP TABLE IF EXISTS `e_answer`;
CREATE TABLE `e_answer`  (
  `answer_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `exam_id` int(0) NOT NULL,
  `answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `status` int(0) NULL DEFAULT NULL,
  `score` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`answer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of e_answer
-- ----------------------------
INSERT INTO `e_answer` VALUES (8, 8, 6, '[{\"questionId\":21,\"questionType\":1,\"statementAnswer\":\"\",\"subanswers\":[{\"answer\":\"A\",\"score\":2}]},{\"questionId\":22,\"questionType\":2,\"statementAnswer\":\"\",\"subanswers\":[{\"answer\":\"A\",\"score\":3}]},{\"questionId\":28,\"questionType\":3,\"statementAnswer\":\"\",\"subanswers\":[{\"answer\":\"B\",\"score\":2}]},{\"questionId\":24,\"questionType\":4,\"statementAnswer\":\"\",\"subanswers\":[{\"answer\":\"B\",\"score\":2}]},{\"questionId\":20,\"questionType\":0,\"statementAnswer\":\"ffasfsa\",\"subanswers\":[{\"answer\":\"\",\"score\":0}]},{\"questionId\":25,\"questionType\":5,\"statementAnswer\":\"afsfasdf\",\"subanswers\":[{\"answer\":\"\",\"score\":0}]}]', 0, 9);
INSERT INTO `e_answer` VALUES (9, 8, 7, '[{\"questionId\":21,\"questionType\":1,\"statementAnswer\":\"\",\"subanswers\":[{\"answer\":\"B\",\"score\":0}]},{\"questionId\":22,\"questionType\":2,\"statementAnswer\":\"\",\"subanswers\":[{\"answer\":\"B\",\"score\":0}]},{\"questionId\":28,\"questionType\":3,\"statementAnswer\":\"\",\"subanswers\":[{\"answer\":\"A\",\"score\":0}]},{\"questionId\":24,\"questionType\":4,\"statementAnswer\":\"\",\"subanswers\":[{\"answer\":\"C\",\"score\":0}]},{\"questionId\":20,\"questionType\":0,\"statementAnswer\":\"la la la la\",\"subanswers\":[{\"answer\":\"\",\"score\":5}]},{\"questionId\":25,\"questionType\":5,\"statementAnswer\":\"asdfsfad\",\"subanswers\":[{\"answer\":\"\",\"score\":6}]}]', 1, 11);

-- ----------------------------
-- Table structure for e_exam
-- ----------------------------
DROP TABLE IF EXISTS `e_exam`;
CREATE TABLE `e_exam`  (
  `exam_id` int(0) NOT NULL AUTO_INCREMENT,
  `exam_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `exam_start_time` datetime(0) NOT NULL,
  `exam_last_time` datetime(0) NOT NULL,
  `exam_paper_id` int(0) NOT NULL,
  PRIMARY KEY (`exam_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of e_exam
-- ----------------------------
INSERT INTO `e_exam` VALUES (2, '3', '2023-05-03 00:00:00', '2023-06-06 00:00:00', 3);
INSERT INTO `e_exam` VALUES (3, '3', '2023-05-18 00:00:00', '2023-06-21 00:00:00', 2);
INSERT INTO `e_exam` VALUES (4, '6级考试', '2023-05-18 00:00:00', '2023-06-15 00:00:00', 5);
INSERT INTO `e_exam` VALUES (5, '142', '2023-05-10 00:00:00', '2023-06-21 00:00:00', 5);
INSERT INTO `e_exam` VALUES (6, '666', '2023-05-17 00:00:00', '2023-06-23 00:00:00', 8);
INSERT INTO `e_exam` VALUES (7, '111', '2023-05-10 00:00:00', '2023-06-15 00:00:00', 8);

-- ----------------------------
-- Table structure for e_paper
-- ----------------------------
DROP TABLE IF EXISTS `e_paper`;
CREATE TABLE `e_paper`  (
  `paper_id` int(0) NOT NULL AUTO_INCREMENT,
  `paper_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `paper_question_list` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`paper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of e_paper
-- ----------------------------
INSERT INTO `e_paper` VALUES (2, '1', '[{\"moduleName\":\"2\",\"questionTyep\":null,\"questions\":[{\"questionPrefix\":\"3\",\"question\":null},{\"questionPrefix\":\"4\",\"question\":null}]}]');
INSERT INTO `e_paper` VALUES (3, '888', '[{\"moduleName\":\"3\",\"questionTyep\":null,\"questions\":[{\"questionPrefix\":\"3\",\"question\":null},{\"questionPrefix\":\"5\",\"question\":null}]}]');
INSERT INTO `e_paper` VALUES (5, '6级考试', '[{\"moduleName\":\"听力部分\",\"questionTyep\":null,\"questions\":[{\"questionPrefix\":\"阅读1-4题\",\"question\":{\"questionId\":2,\"questionType\":1,\"article\":\"this a articlerafs34\",\"questionCorrect\":null,\"analysisTotal\":null,\"score\":0,\"subQuestions\":[{\"questionTitle\":\"sub1\",\"items\":[{\"prefix\":\"A\",\"content\":\"hello\"},{\"prefix\":\"B\",\"content\":\"hello\"}],\"questionScore\":3,\"analysis\":\"nothing\",\"correct\":\"A\"},{\"questionTitle\":\"sub2\",\"items\":[{\"prefix\":\"A\",\"content\":\"hello\"},{\"prefix\":\"B\",\"content\":\"hello\"},{\"prefix\":\"C\",\"content\":\"hello\"},{\"prefix\":\"D\",\"content\":\"hello\"}],\"questionScore\":2,\"analysis\":\"nothing3\",\"correct\":\"C\"},{\"questionTitle\":null,\"items\":[{\"prefix\":\"A\",\"content\":\"\"},{\"prefix\":\"B\",\"content\":\"\"},{\"prefix\":\"C\",\"content\":\"\"},{\"prefix\":\"D\",\"content\":\"\"}],\"questionScore\":null,\"analysis\":null,\"correct\":null}],\"statements\":null}},{\"questionPrefix\":\"阅读5-8题\",\"question\":{\"questionId\":3,\"questionType\":1,\"article\":\"12\",\"questionCorrect\":null,\"analysisTotal\":null,\"score\":0,\"subQuestions\":[{\"questionTitle\":\"12\",\"items\":[{\"prefix\":\"A\",\"content\":\"1\"},{\"prefix\":\"B\",\"content\":\"1\"}],\"questionScore\":1,\"analysis\":\"1\",\"correct\":\"1\"}],\"statements\":null}}]},{\"moduleName\":\"选词部分\",\"questionTyep\":null,\"questions\":[{\"questionPrefix\":\"阅读9-12题\",\"question\":{\"questionId\":3,\"questionType\":1,\"article\":\"12\",\"questionCorrect\":null,\"analysisTotal\":null,\"score\":0,\"subQuestions\":[{\"questionTitle\":\"12\",\"items\":[{\"prefix\":\"A\",\"content\":\"1\"},{\"prefix\":\"B\",\"content\":\"1\"}],\"questionScore\":1,\"analysis\":\"1\",\"correct\":\"1\"}],\"statements\":null}},{\"questionPrefix\":\"阅读13-16\",\"question\":{\"questionId\":3,\"questionType\":1,\"article\":\"12\",\"questionCorrect\":null,\"analysisTotal\":null,\"score\":0,\"subQuestions\":[{\"questionTitle\":\"12\",\"items\":[{\"prefix\":\"A\",\"content\":\"1\"},{\"prefix\":\"B\",\"content\":\"1\"}],\"questionScore\":1,\"analysis\":\"1\",\"correct\":\"1\"}],\"statements\":null}}]}]');
INSERT INTO `e_paper` VALUES (6, '1423', '[{\"moduleName\":\"51125\",\"questionType\":null,\"questions\":[{\"questionPrefix\":\"2413\",\"question\":null},{\"questionPrefix\":\"412\",\"question\":null}]}]');
INSERT INTO `e_paper` VALUES (7, '六级考试', '[{\"moduleName\":\"听力部分\",\"questionType\":null,\"questions\":[{\"questionPrefix\":\"基于你听到的作答\",\"question\":{\"questionId\":21,\"questionType\":1,\"article\":\"Mike : I am going to travel to China\\nMary : Wow! cool\",\"questionCorrect\":null,\"analysisTotal\":null,\"score\":0,\"subQuestions\":[{\"questionTitle\":\"where is Mike going to travel ?\",\"items\":[{\"prefix\":\"A\",\"content\":\"Chian\"},{\"prefix\":\"B\",\"content\":\"Japan\"},{\"prefix\":\"C\",\"content\":\"America\"},{\"prefix\":\"D\",\"content\":\"England\"}],\"questionScore\":2,\"analysis\":\"none\",\"correct\":\"A\"}],\"corrects\":null}}]},{\"moduleName\":\"阅读理解\",\"questionType\":null,\"questions\":[{\"questionPrefix\":\"阅读文章并作答\",\"question\":{\"questionId\":22,\"questionType\":2,\"article\":\"this is a city___is beautiful\",\"questionCorrect\":\"\",\"analysisTotal\":null,\"score\":3,\"subQuestions\":[{\"questionTitle\":\"\",\"items\":[{\"prefix\":\"A\",\"content\":\"which\"},{\"prefix\":\"B\",\"content\":\"who\"}],\"questionScore\":null,\"analysis\":\"\",\"correct\":\"\"}],\"corrects\":[\"A\"]}},{\"questionPrefix\":\"阅读文章并作答\",\"question\":{\"questionId\":23,\"questionType\":3,\"article\":\"A_this is lxy|B_this is cyx|\",\"questionCorrect\":null,\"analysisTotal\":null,\"score\":0,\"subQuestions\":[{\"questionTitle\":\"which one is cyx\",\"items\":[],\"questionScore\":2,\"analysis\":\"\",\"correct\":\"B\"}],\"corrects\":null}},{\"questionPrefix\":\"阅读文章并作答\",\"question\":{\"questionId\":24,\"questionType\":4,\"article\":\"I am a jerk\",\"questionCorrect\":null,\"analysisTotal\":null,\"score\":0,\"subQuestions\":[{\"questionTitle\":\"who are you ?\",\"items\":[{\"prefix\":\"A\",\"content\":\"good man\"},{\"prefix\":\"B\",\"content\":\"jerk\"},{\"prefix\":\"C\",\"content\":\"great man\"},{\"prefix\":\"D\",\"content\":\"trash\"}],\"questionScore\":2,\"analysis\":null,\"correct\":\"B\"}],\"corrects\":null}}]},{\"moduleName\":\"作文题\",\"questionType\":null,\"questions\":[{\"questionPrefix\":\"按照要求写作\",\"question\":{\"questionId\":20,\"questionType\":0,\"article\":\"write an article about love\",\"questionCorrect\":null,\"analysisTotal\":\"nothing\",\"score\":20,\"subQuestions\":[],\"corrects\":null}}]},{\"moduleName\":\"翻译题\",\"questionType\":null,\"questions\":[{\"questionPrefix\":\"翻译下面文章\",\"question\":{\"questionId\":25,\"questionType\":5,\"article\":\"我叫小明\",\"questionCorrect\":null,\"analysisTotal\":null,\"score\":15,\"subQuestions\":[],\"corrects\":null}}]}]');
INSERT INTO `e_paper` VALUES (8, '666级考试', '[{\"moduleName\":\"听力部分\",\"questionType\":null,\"questions\":[{\"questionPrefix\":\"基于你听到的作答\",\"question\":{\"questionId\":21,\"questionType\":1,\"article\":\"Mike : I am going to travel to China\\nMary : Wow! cool\",\"questionCorrect\":null,\"analysisTotal\":null,\"score\":0,\"subQuestions\":[{\"questionTitle\":\"where is Mike going to travel ?\",\"items\":[{\"prefix\":\"A\",\"content\":\"Chian\"},{\"prefix\":\"B\",\"content\":\"Japan\"},{\"prefix\":\"C\",\"content\":\"America\"},{\"prefix\":\"D\",\"content\":\"England\"}],\"questionScore\":2,\"analysis\":\"none\",\"correct\":\"A\"}],\"corrects\":null}}]},{\"moduleName\":\"阅读理解\",\"questionType\":null,\"questions\":[{\"questionPrefix\":\"阅读选词\",\"question\":{\"questionId\":22,\"questionType\":2,\"article\":\"this is a city___is beautiful\",\"questionCorrect\":\"\",\"analysisTotal\":null,\"score\":3,\"subQuestions\":[{\"questionTitle\":\"\",\"items\":[{\"prefix\":\"A\",\"content\":\"which\"},{\"prefix\":\"B\",\"content\":\"who\"}],\"questionScore\":null,\"analysis\":\"\",\"correct\":\"\"}],\"corrects\":[\"A\"]}},{\"questionPrefix\":\"阅读选段\",\"question\":{\"questionId\":28,\"questionType\":3,\"article\":\"A_it is a|B_it is b\",\"questionCorrect\":null,\"analysisTotal\":null,\"score\":0,\"subQuestions\":[{\"questionTitle\":\"who is b\",\"items\":[],\"questionScore\":2,\"analysis\":\"\",\"correct\":\"b\"}],\"corrects\":null}},{\"questionPrefix\":\"阅读选择\",\"question\":{\"questionId\":24,\"questionType\":4,\"article\":\"I am a jerk\",\"questionCorrect\":null,\"analysisTotal\":null,\"score\":0,\"subQuestions\":[{\"questionTitle\":\"who are you ?\",\"items\":[{\"prefix\":\"A\",\"content\":\"good man\"},{\"prefix\":\"B\",\"content\":\"jerk\"},{\"prefix\":\"C\",\"content\":\"great man\"},{\"prefix\":\"D\",\"content\":\"trash\"}],\"questionScore\":2,\"analysis\":null,\"correct\":\"B\"}],\"corrects\":null}}]},{\"moduleName\":\"作文题\",\"questionType\":null,\"questions\":[{\"questionPrefix\":\"按照要求写回答\",\"question\":{\"questionId\":20,\"questionType\":0,\"article\":\"write an article about love\",\"questionCorrect\":null,\"analysisTotal\":\"nothing\",\"score\":20,\"subQuestions\":[],\"corrects\":null}}]},{\"moduleName\":\"翻译题\",\"questionType\":null,\"questions\":[{\"questionPrefix\":\"翻译下面的中文为英文\",\"question\":{\"questionId\":25,\"questionType\":5,\"article\":\"我叫小明\",\"questionCorrect\":null,\"analysisTotal\":null,\"score\":15,\"subQuestions\":[],\"corrects\":null}}]}]');
INSERT INTO `e_paper` VALUES (9, '5.29考试', '[{\"moduleName\":\"听力部分\",\"questionType\":null,\"questions\":[{\"questionPrefix\":\"基于你听到的作答\",\"question\":{\"questionId\":30,\"questionType\":1,\"article\":\"afssadfsad\",\"questionCorrect\":null,\"analysisTotal\":null,\"score\":0,\"subQuestions\":[{\"questionTitle\":\"fadsds\",\"items\":[{\"prefix\":\"A\",\"content\":\"df\"},{\"prefix\":\"B\",\"content\":\"dsf\"},{\"prefix\":\"C\",\"content\":\"dsf\"},{\"prefix\":\"D\",\"content\":\"ds\"}],\"questionScore\":3,\"analysis\":\"asdf\",\"correct\":\"B\"}],\"corrects\":null}}]}]');
INSERT INTO `e_paper` VALUES (10, 'afdsffds', '[{\"moduleName\":\"\",\"questionType\":null,\"questions\":[]}]');

-- ----------------------------
-- Table structure for e_question
-- ----------------------------
DROP TABLE IF EXISTS `e_question`;
CREATE TABLE `e_question`  (
  `question_id` int(0) NOT NULL AUTO_INCREMENT,
  `question_title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `question_type` int(0) NOT NULL COMMENT '1为选择题',
  `question_description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `question_score` int(0) NULL DEFAULT 0,
  `question_correct` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `question_analysis_total` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  PRIMARY KEY (`question_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of e_question
-- ----------------------------
INSERT INTO `e_question` VALUES (20, 'write an article about love', 0, '{\"subQuestions\":[],\"statements\":null}', 20, NULL, 'nothing');
INSERT INTO `e_question` VALUES (21, 'Mike : I am going to travel to China\nMary : Wow! cool', 1, '{\"subQuestions\":[{\"questionTitle\":\"where is Mike going to travel ?\",\"items\":[{\"prefix\":\"A\",\"content\":\"Chian\"},{\"prefix\":\"B\",\"content\":\"Japan\"},{\"prefix\":\"C\",\"content\":\"America\"},{\"prefix\":\"D\",\"content\":\"England\"}],\"questionScore\":2,\"analysis\":\"none\",\"correct\":\"A\"}],\"statements\":null}', 0, NULL, NULL);
INSERT INTO `e_question` VALUES (22, 'this is a city___is beautiful', 2, '{\"subQuestions\":[{\"questionTitle\":\"\",\"items\":[{\"prefix\":\"A\",\"content\":\"which\"},{\"prefix\":\"B\",\"content\":\"who\"}],\"questionScore\":null,\"analysis\":\"\",\"correct\":\"\"}],\"statements\":[\"A\"]}', 3, '', NULL);
INSERT INTO `e_question` VALUES (23, 'A_this is lxy|B_this is cyx|', 3, '{\"subQuestions\":[{\"questionTitle\":\"which one is cyx\",\"items\":[],\"questionScore\":2,\"analysis\":\"\",\"correct\":\"B\"}],\"statements\":null}', 0, NULL, NULL);
INSERT INTO `e_question` VALUES (24, 'I am a jerk', 4, '{\"subQuestions\":[{\"questionTitle\":\"who are you ?\",\"items\":[{\"prefix\":\"A\",\"content\":\"good man\"},{\"prefix\":\"B\",\"content\":\"jerk\"},{\"prefix\":\"C\",\"content\":\"great man\"},{\"prefix\":\"D\",\"content\":\"trash\"}],\"questionScore\":2,\"analysis\":null,\"correct\":\"B\"}],\"statements\":null}', 0, NULL, NULL);
INSERT INTO `e_question` VALUES (25, '我叫小明', 5, '{\"subQuestions\":[],\"statements\":null}', 15, NULL, NULL);
INSERT INTO `e_question` VALUES (26, 'write an artice about people', 0, '{\"subQuestions\":[],\"statements\":null}', 20, NULL, NULL);
INSERT INTO `e_question` VALUES (27, '翻译翻译', 5, '{\"subQuestions\":[],\"statements\":null}', 15, NULL, 'translate');
INSERT INTO `e_question` VALUES (28, 'A_it is a|B_it is b', 3, '{\"subQuestions\":[{\"questionTitle\":\"who is b\",\"items\":[],\"questionScore\":2,\"analysis\":\"\",\"correct\":\"b\"}],\"statements\":null}', 0, NULL, NULL);
INSERT INTO `e_question` VALUES (29, 'write an article about kid', 0, '{\"subQuestions\":[],\"statements\":null}', 20, NULL, 'nothing');
INSERT INTO `e_question` VALUES (30, 'afssadfsad', 1, '{\"subQuestions\":[{\"questionTitle\":\"fadsds\",\"items\":[{\"prefix\":\"A\",\"content\":\"df\"},{\"prefix\":\"B\",\"content\":\"dsf\"},{\"prefix\":\"C\",\"content\":\"dsf\"},{\"prefix\":\"D\",\"content\":\"ds\"}],\"questionScore\":3,\"analysis\":\"asdf\",\"correct\":\"B\"}],\"statements\":null}', 0, NULL, NULL);

-- ----------------------------
-- Table structure for e_user
-- ----------------------------
DROP TABLE IF EXISTS `e_user`;
CREATE TABLE `e_user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_role` int(0) NOT NULL COMMENT '1为admin，2为teacher，3为student',
  `user_id_number` int(0) NOT NULL,
  `user_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `useridnumber`(`user_id_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of e_user
-- ----------------------------
INSERT INTO `e_user` VALUES (1, 'admin', '123456', 1, 888, '123456789');
INSERT INTO `e_user` VALUES (2, 'lxy', '123456', 1, 2, '123');
INSERT INTO `e_user` VALUES (3, 'cyx', '123456', 3, 1, '123');
INSERT INTO `e_user` VALUES (4, '123', '123456', 1, 123567, '10086');
INSERT INTO `e_user` VALUES (6, '123', '123', 3, 123, '123');
INSERT INTO `e_user` VALUES (7, '111', '111', 2, 111, '111345');
INSERT INTO `e_user` VALUES (8, 'lxy1123', '123456', 3, 123456, '10086');
INSERT INTO `e_user` VALUES (9, '333', '333', 3, 333, '333');

-- ----------------------------
-- Table structure for e_user_exam
-- ----------------------------
DROP TABLE IF EXISTS `e_user_exam`;
CREATE TABLE `e_user_exam`  (
  `user_exam_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `exam_id` int(0) NOT NULL,
  `score` int(0) NOT NULL,
  PRIMARY KEY (`user_exam_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of e_user_exam
-- ----------------------------
INSERT INTO `e_user_exam` VALUES (1, 8, 3, -1);
INSERT INTO `e_user_exam` VALUES (2, 8, 2, -1);
INSERT INTO `e_user_exam` VALUES (4, 8, 4, -1);
INSERT INTO `e_user_exam` VALUES (5, 8, 6, -1);
INSERT INTO `e_user_exam` VALUES (6, 8, 7, -1);

SET FOREIGN_KEY_CHECKS = 1;
