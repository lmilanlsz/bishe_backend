/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50646
 Source Host           : localhost:3306
 Source Schema         : bishe

 Target Server Type    : MySQL
 Target Server Version : 50646
 File Encoding         : 65001

 Date: 11/04/2022 16:28:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `book_id` int(8) NOT NULL AUTO_INCREMENT,
  `book_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_rate` float(255, 1) NOT NULL DEFAULT 0.0,
  `book_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_category` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `book_is_liked` int(8) NULL DEFAULT 0,
  `book_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `book_status` int(8) NULL DEFAULT 1 COMMENT '1为存在，0为删除',
  `book_rate_num` int(8) NULL DEFAULT 1,
  PRIMARY KEY (`book_id`) USING BTREE,
  UNIQUE INDEX `book_id`(`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '人生海海', 5.0, '麦家', '纪实', 24, 'img1.jpg', 1, 5);
INSERT INTO `book` VALUES (2, '2001太空漫游', 5.0, '阿瑟·克拉克', '科幻', 0, 'img2.jpg', 1, 4);
INSERT INTO `book` VALUES (3, '美国陷阱', 3.5, '弗雷德里克·皮耶鲁齐&马修·阿伦', '纪实', 1, 'img3.jpg', 1, 5);
INSERT INTO `book` VALUES (4, '棉花帝国', 4.0, '斯文·贝克特', '历史', 0, 'img4.jpg', 1, 4);
INSERT INTO `book` VALUES (5, '喜鹊谋杀案', 4.5, '安东尼·霍洛维茨', '推理', 0, 'img5.jpg', 1, 4);
INSERT INTO `book` VALUES (6, '波尔多往事', 3.5, '科贝昂', '纪实', 1, 'img6.jpg', 1, 5);
INSERT INTO `book` VALUES (7, '野鸟形态图鉴', 4.0, '赤勘兵卫', '科普', 0, 'img7.jpg', 1, 1);
INSERT INTO `book` VALUES (8, '压裂的底层', 4.0, '伊丽莎·格里斯沃尔德', '纪实', 0, 'img8.jpg', 1, 1);
INSERT INTO `book` VALUES (9, '蚁群', 3.5, '汤问棘', '科幻', 0, 'img9.jpg', 1, 1);

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review`  (
  `review_id` int(8) NOT NULL AUTO_INCREMENT,
  `review_rate` float(8, 1) NOT NULL DEFAULT 0.0,
  `user_id` int(8) NOT NULL,
  `book_id` int(8) NOT NULL,
  `review_content` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `review_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`review_id`) USING BTREE,
  INDEX `fk_r_b_book_id`(`book_id`) USING BTREE,
  INDEX `fk_r_u_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_r_book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_r_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of review
-- ----------------------------
INSERT INTO `review` VALUES (1, 5.0, 1, 1, '是一本好书，阅读之后感慨良多。', '2022-03-31 14:50:54');
INSERT INTO `review` VALUES (2, 3.5, 2, 3, '内容尚可，总体一般。', '2022-04-11 10:56:01');
INSERT INTO `review` VALUES (3, 5.0, 2, 2, '绝对神作。', '2022-04-08 15:43:16');
INSERT INTO `review` VALUES (4, 5.0, 3, 1, '我愿意给满分，太好看了。', '2022-04-08 15:44:43');
INSERT INTO `review` VALUES (5, 4.0, 2, 7, '生动的科普著作，让我学到了很多，而且文笔优美，可读性很强。', '2022-04-09 13:11:11');
INSERT INTO `review` VALUES (7, 4.5, 2, 6, '动人心弦的故事，我很喜欢。', '2022-04-11 10:56:09');
INSERT INTO `review` VALUES (8, 5.0, 3, 1, '我觉得不错。', '2022-04-11 10:43:16');
INSERT INTO `review` VALUES (9, 5.0, 3, 2, '超级神作。', '2022-04-11 10:43:42');
INSERT INTO `review` VALUES (10, 3.5, 3, 3, '一般般，中庸之作。', '2022-04-11 10:54:33');
INSERT INTO `review` VALUES (11, 4.0, 3, 4, '还不赖。', '2022-04-11 10:45:20');
INSERT INTO `review` VALUES (12, 4.5, 3, 5, '瑕不掩瑜，算是佳作。', '2022-04-11 10:55:03');
INSERT INTO `review` VALUES (13, 3.5, 3, 6, '勉强高于及格线。', '2022-04-11 10:55:53');
INSERT INTO `review` VALUES (14, 5.0, 4, 1, '我很喜欢。', '2022-04-11 10:50:45');
INSERT INTO `review` VALUES (15, 5.0, 4, 2, '世纪神作。', '2022-04-11 10:50:59');
INSERT INTO `review` VALUES (16, 3.5, 4, 3, '不好看也不难看吧。', '2022-04-11 10:56:11');
INSERT INTO `review` VALUES (19, 4.0, 4, 4, '还行，有空可以看看。', '2022-04-11 10:57:01');
INSERT INTO `review` VALUES (20, 4.5, 4, 5, '还是很好看的，虽然是好多年前看的，如今还是记忆犹新。', '2022-04-11 10:58:28');
INSERT INTO `review` VALUES (21, 3.5, 4, 6, '食之无味弃之可惜，没书看了的话还是可以拿来打发一下时间的。', '2022-04-11 10:59:09');
INSERT INTO `review` VALUES (22, 5.0, 5, 1, '把我看哭了。', '2022-04-11 10:59:34');
INSERT INTO `review` VALUES (23, 5.0, 5, 2, '电影也很好看。', '2022-04-11 11:00:01');
INSERT INTO `review` VALUES (24, 3.5, 5, 3, '美国不行。', '2022-04-11 11:00:28');
INSERT INTO `review` VALUES (25, 4.0, 5, 4, '学到了很多，受益匪浅。', '2022-04-11 11:00:52');
INSERT INTO `review` VALUES (26, 4.5, 5, 5, '离神作差一口气。', '2022-04-11 11:01:27');
INSERT INTO `review` VALUES (27, 3.5, 5, 6, '乏善可陈。', '2022-04-11 11:01:54');
INSERT INTO `review` VALUES (28, 5.0, 6, 2, '还行', '2022-04-11 13:41:15');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(8) NOT NULL AUTO_INCREMENT,
  `user_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_admin` int(2) NOT NULL DEFAULT 0 COMMENT '1为管理员，0不为管理员',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '12345', 'admin', 1);
INSERT INTO `user` VALUES (2, '12345', 'hkc', 0);
INSERT INTO `user` VALUES (3, '12345', 'lsz', 0);
INSERT INTO `user` VALUES (4, '12345', 'hqy', 0);
INSERT INTO `user` VALUES (5, '12345', 'stl', 0);
INSERT INTO `user` VALUES (6, '12345', 'jcn', 0);

-- ----------------------------
-- Table structure for wishlist
-- ----------------------------
DROP TABLE IF EXISTS `wishlist`;
CREATE TABLE `wishlist`  (
  `wishlist_id` int(8) NOT NULL AUTO_INCREMENT,
  `user_id` int(8) NULL DEFAULT NULL,
  `book_id` int(8) NULL DEFAULT NULL,
  `wish_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `wish_status` int(8) NULL DEFAULT 0 COMMENT '0未读，1已读',
  PRIMARY KEY (`wishlist_id`) USING BTREE,
  INDEX `fk_w_b_book_id`(`book_id`) USING BTREE,
  INDEX `fk_w_u_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_w_book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_w_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of wishlist
-- ----------------------------
INSERT INTO `wishlist` VALUES (1, 2, 4, '2022-04-07 15:27:19', 1);
INSERT INTO `wishlist` VALUES (2, 2, 5, '2022-04-07 15:27:13', 1);
INSERT INTO `wishlist` VALUES (4, 2, 1, '2022-04-07 15:27:24', 1);
INSERT INTO `wishlist` VALUES (5, 2, 3, '2022-04-09 10:49:59', 1);
INSERT INTO `wishlist` VALUES (10, 2, 7, '2022-04-09 10:50:08', 1);
INSERT INTO `wishlist` VALUES (11, 2, 8, '2022-04-09 10:50:18', 1);
INSERT INTO `wishlist` VALUES (12, 2, 6, '2022-04-11 15:56:35', 1);

SET FOREIGN_KEY_CHECKS = 1;
