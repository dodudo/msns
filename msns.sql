/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : msns

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 11/04/2020 22:37:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id自增',
  `adminId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员id',
  `aname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员昵称',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员性别',
  `birthday` date NULL DEFAULT NULL COMMENT '管理员生日',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员手机',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员邮箱',
  `apassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员密码',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '1', 'admin', '男', '2020-03-12', '187458236841', '1258@qq.com', '666666', '1');
INSERT INTO `admin` VALUES (2, '99885edf95c145fda6d68ac99f969908', 'hhh', '女', '2020-02-05', '14752654845', '15@qq.com', NULL, '-1');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(200) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `pid` bigint(200) NULL DEFAULT NULL COMMENT '父id',
  `lid` bigint(200) NULL DEFAULT NULL COMMENT '回复评论id',
  `dynamic_id` int(255) NULL DEFAULT NULL COMMENT '动态id',
  `reply_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复者id',
  `respondent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被评论者id',
  `comment_date` datetime(0) NULL DEFAULT NULL COMMENT '评论日期',
  `comment_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (72, NULL, NULL, 25, '7d2e64b5af964f4b85854e9c48f0954d', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-05 19:22:42', 'hhh', '2');
INSERT INTO `comment` VALUES (73, NULL, NULL, 25, '7d2e64b5af964f4b85854e9c48f0954d', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-05 19:22:53', 'sdgsa', '2');
INSERT INTO `comment` VALUES (74, 72, 72, 25, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-05 19:23:12', 'gsdg', '2');
INSERT INTO `comment` VALUES (75, 72, 74, 25, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-05 20:00:54', 'gsdg', '2');
INSERT INTO `comment` VALUES (77, 72, 76, 25, '7d2e64b5af964f4b85854e9c48f0954d', '4f6bc03b569f4eaa8fbfed4875d36751', '2020-04-05 20:02:34', 'gsdg', '2');
INSERT INTO `comment` VALUES (78, 72, 76, 25, '7d2e64b5af964f4b85854e9c48f0954d', '4f6bc03b569f4eaa8fbfed4875d36751', '2020-04-05 20:10:30', 'fdsafsdagsad', '2');
INSERT INTO `comment` VALUES (79, 72, 76, 25, '7d2e64b5af964f4b85854e9c48f0954d', '4f6bc03b569f4eaa8fbfed4875d36751', '2020-04-05 20:10:52', 'gggggg', '2');
INSERT INTO `comment` VALUES (80, 73, 73, 25, '7d2e64b5af964f4b85854e9c48f0954d', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-05 20:11:09', 'ggg', '2');
INSERT INTO `comment` VALUES (83, NULL, NULL, 25, '7d2e64b5af964f4b85854e9c48f0954d', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-05 22:30:29', 'gggg', '-1');
INSERT INTO `comment` VALUES (84, NULL, NULL, 25, '7d2e64b5af964f4b85854e9c48f0954d', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-05 22:36:10', 'hhhhh', '2');
INSERT INTO `comment` VALUES (85, NULL, NULL, 25, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 16:16:31', 'gggg', '2');
INSERT INTO `comment` VALUES (86, NULL, NULL, 25, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 16:20:36', 'gdsag', '2');
INSERT INTO `comment` VALUES (87, NULL, NULL, 25, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 16:53:08', 'dgasdgsd', '2');
INSERT INTO `comment` VALUES (88, 73, 80, 25, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 17:05:52', 'gdad', '2');
INSERT INTO `comment` VALUES (89, NULL, NULL, 25, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 17:06:05', 'dgasdgsdf', '2');
INSERT INTO `comment` VALUES (90, NULL, NULL, 24, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 17:08:35', 'nihao', '2');
INSERT INTO `comment` VALUES (91, NULL, NULL, 24, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 17:09:20', 'gdsag', '2');
INSERT INTO `comment` VALUES (92, NULL, NULL, 22, '7d2e64b5af964f4b85854e9c48f0954d', '4f6bc03b569f4eaa8fbfed4875d36751', '2020-04-06 17:09:51', '换行', '2');
INSERT INTO `comment` VALUES (93, NULL, NULL, 22, '7d2e64b5af964f4b85854e9c48f0954d', '4f6bc03b569f4eaa8fbfed4875d36751', '2020-04-06 17:10:15', 'dads', '2');
INSERT INTO `comment` VALUES (94, NULL, NULL, 23, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 17:10:27', 'gggg', '2');
INSERT INTO `comment` VALUES (95, NULL, NULL, 22, '7d2e64b5af964f4b85854e9c48f0954d', '4f6bc03b569f4eaa8fbfed4875d36751', '2020-04-06 17:10:38', 'ads', '2');
INSERT INTO `comment` VALUES (96, NULL, NULL, 23, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 17:10:48', 'fdsaf', '2');
INSERT INTO `comment` VALUES (97, NULL, NULL, 22, '7d2e64b5af964f4b85854e9c48f0954d', '4f6bc03b569f4eaa8fbfed4875d36751', '2020-04-06 17:12:29', 'hhhh', '2');
INSERT INTO `comment` VALUES (98, NULL, NULL, 22, '7d2e64b5af964f4b85854e9c48f0954d', '4f6bc03b569f4eaa8fbfed4875d36751', '2020-04-06 17:14:01', 'ggg', '2');
INSERT INTO `comment` VALUES (99, 95, 95, 22, '7d2e64b5af964f4b85854e9c48f0954d', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 17:15:03', 'gggg', '2');
INSERT INTO `comment` VALUES (100, NULL, NULL, 24, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 17:15:24', 'gggg', '2');
INSERT INTO `comment` VALUES (101, NULL, NULL, 22, '7d2e64b5af964f4b85854e9c48f0954d', '4f6bc03b569f4eaa8fbfed4875d36751', '2020-04-06 17:16:08', 'dsagdsagsdh', '2');
INSERT INTO `comment` VALUES (102, NULL, NULL, 23, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 17:17:05', 'gggg', '2');
INSERT INTO `comment` VALUES (103, NULL, NULL, 22, '7d2e64b5af964f4b85854e9c48f0954d', '4f6bc03b569f4eaa8fbfed4875d36751', '2020-04-06 17:17:13', 'fdsgsdagsga', '2');
INSERT INTO `comment` VALUES (104, NULL, NULL, 24, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 22:46:58', 'ggg', '2');
INSERT INTO `comment` VALUES (105, NULL, NULL, 24, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 22:48:15', 'hhh', '2');
INSERT INTO `comment` VALUES (106, 103, 103, 22, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-08 14:28:49', 'fdgsdfg', '2');
INSERT INTO `comment` VALUES (107, 101, 101, 22, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-08 14:52:01', 'ggggg', '2');
INSERT INTO `comment` VALUES (108, 101, 107, 22, '7d2e64b5af964f4b85854e9c48f0954d', '4f6bc03b569f4eaa8fbfed4875d36751', '2020-04-08 20:59:20', 'sdfa', '1');

-- ----------------------------
-- Table structure for dynamic
-- ----------------------------
DROP TABLE IF EXISTS `dynamic`;
CREATE TABLE `dynamic`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `dynamic_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '动态id',
  `uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发布者id',
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发布者昵称',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `dynamic_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '动态内容',
  `img_urls` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '图片路径',
  `dynamic_type_id` int(11) NULL DEFAULT NULL COMMENT '动态类型id',
  `publish_date` datetime(0) NULL DEFAULT NULL COMMENT '发布日期',
  `music_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '音乐id',
  `view_count` bigint(255) NULL DEFAULT 0 COMMENT '浏览次数',
  `report_count` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '举报次数',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态',
  `recentCommentDate` datetime(0) NULL DEFAULT NULL COMMENT '最新评论时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dynamic
-- ----------------------------
INSERT INTO `dynamic` VALUES (1, '01', '01', 'hhh', NULL, '哈哈哈，这是第一条动态sdfaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaakjkllllllllllllllllllllllllllllllllllllllllllllllllllllsdfaaaaaaaaaaaaaaasdaffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffflllllllfsdaaaaaaaaaaaaaaagasdfhgrhgwsgeshgasehsaehehdafgasfgdfsaaaaaaaaaaasdgasdgasdgsdagasgdsfjklkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkuiodcxxxxxxxxxxxxxxxxfhjklsklajlkpiolwjgpioewjglikjwerklgjlkwe', 'http://localhost:10000/file/images/704d59e1ebfa48c0a3f85dbdb33e054c.jpg,http://localhost:10000/file/images/704d59e1ebfa48c0a3f85dbdb33e054c.jpg,http://localhost:10000/file/images/704d59e1ebfa48c0a3f85dbdb33e054c.jpg,http://localhost:10000/file/images/704d59e1ebfa48c0a3f85dbdb33e054c.jpg,http://localhost:10000/file/images/704d59e1ebfa48c0a3f85dbdb33e054c.jpg,http://localhost:10000/file/images/704d59e1ebfa48c0a3f85dbdb33e054c.jpg,', 1, '2020-02-25 19:36:49', '6793f87e5f00442fac04c606b8e861a9', 0, '2', '1', NULL);
INSERT INTO `dynamic` VALUES (3, '02', '01', 'hhh', NULL, '这是第二条', 'http://localhost:10000/file/images/4c781a7457754902b45f0da8908bac74.jpg,', 1, '2020-03-11 23:03:02', NULL, 0, '1', '1', NULL);
INSERT INTO `dynamic` VALUES (4, '03', '7d2e64b5af964f4b85854e9c48f0954d', 'eeee', NULL, '这是第三条哈哈哈哈', 'http://localhost:10000/file/images/71dab5e4eb9c4423b760cd64a557e00a.jpg', 1, '2020-03-20 15:02:22', '315c85ac52b6479bb62041e1dc907243', 0, '0', '1', NULL);
INSERT INTO `dynamic` VALUES (7, '05c3437670b24e499e65c4d421467f70', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, 'ffg😄', 'http://localhost:10000/file/images/eb93a86682f9407eaa6e34a06a381cf5.jpg,http://localhost:10000/file/images/6d18afcfe45e4e2eb380509bf5b075f8.jpg', 1, '2020-03-26 21:02:13', '84', 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (8, '99279b96f6244193957ad06e3391619c', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, 'hhh🥰', 'http://localhost:10000/file/images/25c07ddfb1dc4a908726f64175e69a1b.jpg,http://localhost:10000/file/images/e4c3898ff59844f282bce1c39bde11d3.jpg', 1, '2020-03-26 21:11:56', '84', 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (9, 'aaa8fff6e5ae4028a6c1a44eae165ebe', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, 'hhh😄', 'http://localhost:10000/file/images/5a4ae432bd654accb945bc342d7b5683.jpg,http://localhost:10000/file/images/2cf2c9dc207f4fc9a66ac81a73d5dc8e.jpg', 1, '2020-03-26 21:14:54', '85', 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (10, 'e7e1c803b9224fc9865c7c4ff77d7952', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, 'hhhh😄', 'http://localhost:10000/file/images/b01ee924cb34450386a157fb1ccbb95c.png,http://localhost:10000/file/images/b5e46d15113b41b490c921d6e49871f0.jpg', 1, '2020-03-26 21:23:24', 'e0667c602932486b921c1e903a1d6af3', 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (11, 'd7e4370c264d4234bbe9e8bc75395acd', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, '动态发布成功😃😄', 'http://localhost:10000/file/images/9d5b24428d3d463ebaf50a2354be6052.jpeg', 1, '2020-03-26 21:24:09', '141a579ecf79432ca1608053a4e00eed', 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (12, '70840e82484f46f2942ab847c8c16bd4', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, 'hhhhh🥰😍😅', 'http://localhost:10000/file/images/c8e76e6449cd40f39a5c60e07682087a.jpg,http://localhost:10000/file/images/bd8749786fb543fbb16a9a1327e66a5b.jpg,http://localhost:10000/file/images/2eea3fdba059407daff47d2089477c4e.jpg,http://localhost:10000/file/images/6f327d87d60c4b4894feeb3f7a64ae67.jpg,http://localhost:10000/file/images/5427c074927f4db9b1932fdef54b8f0b.png,http://localhost:10000/file/images/4553d3ca7f78458687b2973a8fb62953.jpg', 1, '2020-03-26 21:30:45', 'e0667c602932486b921c1e903a1d6af3', 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (13, '088366118cef464caa9fe9a23f9b72b5', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, 'hhhh', '', 1, '2020-03-26 21:37:12', NULL, 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (14, 'a66b63fc956c49bca1f968a91991d387', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, 'hhhh😘', 'http://localhost:10000/file/images/8cf220ebe9b34744a47101aff7744b20.jpg', 1, '2020-03-26 21:37:37', '141a579ecf79432ca1608053a4e00eed', 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (15, '99964cc647824a74a4e96d524235d8db', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, 'fhgsd😘😚', 'http://localhost:10000/file/images/c625ab73e1ef4f3b856bb5b6b12aadd6.jpg,http://localhost:10000/file/images/24aab314dbb04a7cbdad0f2636f1ebc5.jpg,http://localhost:10000/file/images/d7c09d56244543b393848f1bda605472.jpg,http://localhost:10000/file/images/d9c55d1c9366496991ab70d35fc38779.jpg,http://localhost:10000/file/images/0b6dab8a85b64d338bad271473e373c7.jpg,http://localhost:10000/file/images/7b63dd35456b44c88ad7635a677c3e34.jpg', 1, '2020-03-26 21:43:53', 'e98a874339d3427991808389caa66bed', 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (16, '3f88d51d92214a4982fabd4aa9671019', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, 'ggg', '', 1, '2020-03-26 21:44:35', 'e98a874339d3427991808389caa66bed', 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (17, '5499ba464a204ee59411a6c5777c27f1', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, 'hhh', '', 1, '2020-03-26 21:46:17', NULL, 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (18, '25c60c81ac2e47e198ac9b79d5df7b3e', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, 'hhhh', '', 1, '2020-03-26 21:50:47', NULL, 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (19, 'ddff2e54e0f34e00b05924475aa6dde7', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, 'ggg', '', 1, '2020-03-26 21:50:56', 'e98a874339d3427991808389caa66bed', 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (20, 'c4bebcdb14a846319b6a696dcfc9092d', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, '四张图片', 'http://localhost:10000/file/images/65656a32eb7643a09e909bb9bd68af80.jpg,http://localhost:10000/file/images/06d51860f9f24376956b39fa6c18ad17.jpg,http://localhost:10000/file/images/14fced310bb8453383b7e9ed550af6b1.jpg,http://localhost:10000/file/images/b221fcddc82d41878f6ba799a01def25.jpg', 1, '2020-03-26 21:52:16', NULL, 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (21, '47966dd95d564bb3a7201c8a62ab012d', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, '这歌真好听啊😃', '', 1, '2020-03-28 15:51:48', '315c85ac52b6479bb62041e1dc907243', 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (22, '2b3ec09433274de086c7080895c53ef7', '4f6bc03b569f4eaa8fbfed4875d36751', NULL, NULL, 'hhh,我也发一条😄', '', 1, '2020-03-28 15:55:10', 'e0667c602932486b921c1e903a1d6af3', 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (23, 'f080e28acd7d467ca786e254d9a3abd3', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, 'hhh', '', 1, '2020-03-28 17:12:04', NULL, 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (24, '9c8d0f1438f044c59f16bdd2e257ba0d', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, '对于消息发布者而言它只负责把消息发布出去，甚至它也不知道消息是发到哪个queue，消息通过exchange到达queue，exchange的职责非常简单，就是一边接收发布者的消息一边把这些消息推到queue中。\n\n而exchange是怎么知道消息应该推到哪个queue呢，这就要通过绑定queue与exchange时的routingkey了，通过代码进行绑定并且指定routingkey，下面有一张关系图,p(发布者) —> x(exchange) bindding(绑定关系也就是我们的routingkey) 红色代表着queue\n————————————————\n版权声明：本文为CSDN博主「张超帅」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。\n原文链接：https://blog.csdn.net/weixin_38070406/java/article/details/82903677', '', 1, '2020-03-28 19:26:14', NULL, 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (25, '5a7c7a630da347cf9cd4bb576eaafcc7', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, '😃', '', 1, '2020-03-29 15:27:47', NULL, 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (26, '260b340e74de438fa9cee6d1e1ed78e7', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, '😀', 'http://localhost:10000/file/images/7834915aba5244bca689757ed955b611.jpg,http://localhost:10000/file/images/eb8bf18561584f088a7ecd2d3a27aef5.jpg,http://localhost:10000/file/images/45fb68a537404cb3b4dd853cd259f9b9.jpg,http://localhost:10000/file/images/656c169ab5924a948de33e0d5ea8e215.jpg', 1, '2020-04-11 22:07:19', '657285d0b7a042ee9dbd6fc0023d0b5f', 0, NULL, '1', NULL);
INSERT INTO `dynamic` VALUES (27, 'bff6234c9ae644beb40cb9f3f7cfa578', '7d2e64b5af964f4b85854e9c48f0954d', NULL, NULL, '', 'http://localhost:10000/file/images/b7a57adefc864f55bfcc313056120894.jpg,http://localhost:10000/file/images/826b8fff9ff94346ad336ab006903d54.jpg,http://localhost:10000/file/images/cb96df74749b4d21a98312c5394c93db.jpg,http://localhost:10000/file/images/32be5c34660747a98cfaa8e89218f61d.jpg', 1, '2020-04-11 22:07:20', NULL, 0, NULL, '1', NULL);

-- ----------------------------
-- Table structure for dynamic_favor
-- ----------------------------
DROP TABLE IF EXISTS `dynamic_favor`;
CREATE TABLE `dynamic_favor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `dynamic_id` int(255) NULL DEFAULT NULL COMMENT '动态id',
  `collector_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收藏者id',
  `favor_date` datetime(0) NULL DEFAULT NULL COMMENT '收藏时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dynamic_favor
-- ----------------------------
INSERT INTO `dynamic_favor` VALUES (1, 1, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-20 20:03:12', '1');
INSERT INTO `dynamic_favor` VALUES (7, 22, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-29 10:06:47', NULL);
INSERT INTO `dynamic_favor` VALUES (12, 23, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-29 10:11:21', NULL);
INSERT INTO `dynamic_favor` VALUES (14, 24, '4f6bc03b569f4eaa8fbfed4875d36751', '2020-03-29 10:12:15', NULL);
INSERT INTO `dynamic_favor` VALUES (15, 25, '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 11:33:06', NULL);

-- ----------------------------
-- Table structure for dynamic_like
-- ----------------------------
DROP TABLE IF EXISTS `dynamic_like`;
CREATE TABLE `dynamic_like`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id自增',
  `liker_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '点赞者id',
  `dynamic_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动态id',
  `dynamic_authorId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章发布者id',
  `like_date` datetime(0) NULL DEFAULT NULL COMMENT '点赞时间',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态0表示不存在，1表示未读，2表示已读',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dynamic_like
-- ----------------------------
INSERT INTO `dynamic_like` VALUES (38, '4f6bc03b569f4eaa8fbfed4875d36751', '24', '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-29 10:26:33', '2');
INSERT INTO `dynamic_like` VALUES (39, '7d2e64b5af964f4b85854e9c48f0954d', '19', '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-29 10:27:24', '2');
INSERT INTO `dynamic_like` VALUES (46, '7d2e64b5af964f4b85854e9c48f0954d', '15', '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-30 20:59:49', '2');
INSERT INTO `dynamic_like` VALUES (47, '7d2e64b5af964f4b85854e9c48f0954d', '20', '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-30 21:03:24', '2');
INSERT INTO `dynamic_like` VALUES (58, '7d2e64b5af964f4b85854e9c48f0954d', '14', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-05 21:46:24', '2');
INSERT INTO `dynamic_like` VALUES (59, '7d2e64b5af964f4b85854e9c48f0954d', '13', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-05 21:46:26', '2');
INSERT INTO `dynamic_like` VALUES (60, '7d2e64b5af964f4b85854e9c48f0954d', '12', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-05 21:46:28', '2');
INSERT INTO `dynamic_like` VALUES (61, '7d2e64b5af964f4b85854e9c48f0954d', '11', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-05 21:46:29', '2');
INSERT INTO `dynamic_like` VALUES (72, '7d2e64b5af964f4b85854e9c48f0954d', '21', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 23:05:47', '2');
INSERT INTO `dynamic_like` VALUES (73, '7d2e64b5af964f4b85854e9c48f0954d', '23', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 23:05:59', '2');
INSERT INTO `dynamic_like` VALUES (78, '7d2e64b5af964f4b85854e9c48f0954d', '25', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-11 22:05:57', '1');

-- ----------------------------
-- Table structure for dynamic_type
-- ----------------------------
DROP TABLE IF EXISTS `dynamic_type`;
CREATE TABLE `dynamic_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '动态分类id',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名',
  `type_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dynamic_type
-- ----------------------------
INSERT INTO `dynamic_type` VALUES (1, '日常', '1');
INSERT INTO `dynamic_type` VALUES (2, '音乐', '0');
INSERT INTO `dynamic_type` VALUES (3, '流行', '1');
INSERT INTO `dynamic_type` VALUES (4, '音乐人', '0');
INSERT INTO `dynamic_type` VALUES (5, '最爱的音乐', '1');
INSERT INTO `dynamic_type` VALUES (6, '最爱的歌手', '1');
INSERT INTO `dynamic_type` VALUES (23, 'sdfa', '1');
INSERT INTO `dynamic_type` VALUES (24, 'dfsa', '0');
INSERT INTO `dynamic_type` VALUES (25, 'sdfa', '0');
INSERT INTO `dynamic_type` VALUES (26, 'dsfa', '0');
INSERT INTO `dynamic_type` VALUES (29, 'vaZXC', '-1');
INSERT INTO `dynamic_type` VALUES (30, 'svca', '0');
INSERT INTO `dynamic_type` VALUES (32, 'fdga', '0');
INSERT INTO `dynamic_type` VALUES (33, 'sdaf', '1');
INSERT INTO `dynamic_type` VALUES (34, '', '0');

-- ----------------------------
-- Table structure for follow
-- ----------------------------
DROP TABLE IF EXISTS `follow`;
CREATE TABLE `follow`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id自增',
  `Followers_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关注者id',
  `beFollowers_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被关注者id',
  `follower_date` datetime(0) NULL DEFAULT NULL COMMENT '关注时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of follow
-- ----------------------------
INSERT INTO `follow` VALUES (18, '01', '7d2e64b5af964f4b85854e9c48f0954d', NULL);
INSERT INTO `follow` VALUES (19, '7d2e64b5af964f4b85854e9c48f0954d', '02', NULL);
INSERT INTO `follow` VALUES (20, 'f8249dcc6f9a438b981516fba53abed7', '7d2e64b5af964f4b85854e9c48f0954d', NULL);
INSERT INTO `follow` VALUES (23, '7d2e64b5af964f4b85854e9c48f0954d', 'f8249dcc6f9a438b981516fba53abed7', '2020-03-23 17:37:57');
INSERT INTO `follow` VALUES (25, '7d2e64b5af964f4b85854e9c48f0954d', '01', '2020-03-23 21:30:49');
INSERT INTO `follow` VALUES (26, '4f6bc03b569f4eaa8fbfed4875d36751', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-05 21:59:44');
INSERT INTO `follow` VALUES (31, '7d2e64b5af964f4b85854e9c48f0954d', '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-08 20:37:47');

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id自增',
  `music_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音乐id',
  `music_cover_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音乐封面',
  `music_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音乐名',
  `music_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音乐作者',
  `music_album` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专辑名称',
  `music_publis_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音乐发布日期',
  `music_push_date` datetime(0) NULL DEFAULT NULL COMMENT '音乐上传日期',
  `music_length` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音乐时长',
  `music_type_id` int(11) NULL DEFAULT NULL COMMENT '音乐类型',
  `music_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音乐文件路径',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of music
-- ----------------------------
INSERT INTO `music` VALUES (60, '6793f87e5f00442fac04c606b8e861a9', 'http://localhost:10000/file/images/73cb2198533545069f51821769365337.jpg', '和你在一起', '李志', '洗心革面', '2019', '2020-03-08 20:22:29', '4:16', 2, 'http://localhost:10000/file/music/和你在一起.flac', '1');
INSERT INTO `music` VALUES (61, '29dcc12e933a4cc3bcb8e30b3a952bb1', 'http://localhost:10000/file/images/fc12c4de4d59435384da7017459d73ef.jpg', '方式', '李志', '洗心革面', '', '2020-03-08 20:23:27', '4:24', 2, 'http://localhost:10000/file/music/方式.flac', '1');
INSERT INTO `music` VALUES (62, 'ad44e3bfc58a45ddbe2b25a4af7af1b7', 'http://localhost:10000/file/images/4c781a7457754902b45f0da8908bac74.jpg', '你离开了南京，从此没有人和我说话', '李志', '洗心革面', '', '2020-03-08 20:23:58', '3:22', 2, 'http://localhost:10000/file/music/你离开了南京，从此没有人和我说话.flac', '1');
INSERT INTO `music` VALUES (63, 'fcfd21ea7ed44d3892f2957e03055c70', 'http://localhost:10000/file/images/ec34ce60c3104e16a6479658b9ef9ab8.jpg', '方式(2014i/O版)', '李志', 'i/O（2014 Live）', '', '2020-03-08 20:25:29', '4:21', 2, 'http://localhost:10000/file/music/李志 - 方式(2014i／O版).mp3', '1');
INSERT INTO `music` VALUES (64, '55e2263a28334d2ca23a23092621c37c', 'http://localhost:10000/file/images/e4b843f0163f40b98b274281d9217d12.jpg', '杭州(2014i/O版)', '李志', 'i/O（2014 Live）', '', '2020-03-08 20:25:38', '4:7', 2, 'http://localhost:10000/file/music/李志 - 杭州(2014i／O版).mp3', '1');
INSERT INTO `music` VALUES (65, '113d327d0a4f400faf3cc633945c9f93', 'http://localhost:10000/file/images/704d59e1ebfa48c0a3f85dbdb33e054c.jpg', '鸵鸟&天空之城&我们不能失去信仰(2014i/O版)', '李志', 'i/O（2014 Live）', '', '2020-03-08 20:25:48', '10:6', 2, 'http://localhost:10000/file/music/李志 - 鸵鸟&天空之城&我们不能失去信仰(2014i／O版).mp3', '-1');
INSERT INTO `music` VALUES (66, '98312054e64d4927b5da07be73e1444e', 'http://localhost:10000/file/images/7752330ee5d64e64b3bad18e45be4966.jpg', 'Escape', 'Dripice', 'Escape', '', '2020-03-08 20:51:25', '3:2', 2, 'http://localhost:10000/file/music/Dripice - Escape.mp3', '1');
INSERT INTO `music` VALUES (67, '4c4977ff7e3542e5bee628b2ff8d8960', 'http://localhost:10000/file/images/72cd7f29ec764e8fb1f569adbb52bafe.jpg', 'Hero', 'Family of the Year', 'Loma Vista', '', '2020-03-08 20:52:32', '3:10', 1, 'http://localhost:10000/file/music/Family of the Year - Hero.mp3', '1');
INSERT INTO `music` VALUES (68, '159426f3dc25463daaad69ca141d8294', 'http://localhost:10000/file/images/71b8fc57faa04a499ee65aac6aa09e31.jpg', '过来人 (Live)', 'Hush!', 'Everyone\'s Gonna Miss You', '', '2020-03-08 20:53:44', '4:9', 2, 'http://localhost:10000/file/music/Hush! - 过来人 (Live).mp3', '1');
INSERT INTO `music` VALUES (69, '8c59cd9faccc4b2ca4bd5b57c8f54307', 'http://localhost:10000/file/images/1de3e52c14554ec4a27d5e43d8912ecf.jpg', 'In the City', 'Eagles', 'The Studio Albums 1972-1979 Limited Edition', '1979', '2020-03-08 20:54:01', '3:45', 2, 'http://localhost:10000/file/music/Eagles - In the City.mp3', '1');
INSERT INTO `music` VALUES (70, '291a8bca1998425eb4a2881d1257be54', 'http://localhost:10000/file/images/9975d93bb4fb42a4ada1547d271a41ce.jpg', 'California Dreaming (重庆森林)', 'The Mamas & the Papas', '声光回忆', '', '2020-03-08 20:55:04', '2:40', 1, 'http://localhost:10000/file/music/The Mamas & the Papas - California Dreaming (重庆森林).mp3', '-1');
INSERT INTO `music` VALUES (71, '9d7549085a1a4e75819ef25e5c60a387', 'http://localhost:10000/file/images/203cb04da6fe49ff86564d1ab3cbec69.jpg', 'Distance', 'KillerSoap', '月球背面的日光', '', '2020-03-08 20:55:59', '5:1', 2, 'http://localhost:10000/file/music/KillerSoap - Distance.mp3', '1');
INSERT INTO `music` VALUES (72, '072235b8b6b04d76a773118de362b2a2', 'http://localhost:10000/file/images/3a4f6322169544d3ad4df3bc3801d7a1.jpg', 'Hotel California', 'Eagles', 'The Studio Albums 1972-1979 Limited Edition', '', '2020-03-08 20:57:13', '6:30', 2, 'http://localhost:10000/file/music/Eagles - Hotel California.mp3', '1');
INSERT INTO `music` VALUES (73, '15a7641a4ce1420695cc5f96b4f99439', 'http://localhost:10000/file/images/75584dd4ffb14be8a8eb8f161b9e3fdb.jpg', '凌晨三点（demo）', '陈硕子', '凌晨三点', '', '2020-03-09 09:37:07', '1:32', 3, 'http://localhost:10000/file/music/陈硕子 - 凌晨三点（demo）.mp3', '1');
INSERT INTO `music` VALUES (74, 'eb63d062fc464475a323d8643bfbecf8', 'http://localhost:10000/file/images/5b0e1857984a5.jpg', '浪味仙地', '陈粒', '玩', '', '2020-03-09 11:38:33', '3:10', 1, 'http://localhost:10000/file/music/陈粒 - 浪味仙地.mp3', '1');
INSERT INTO `music` VALUES (75, 'd09dea5befdc417caf673430b0179702', 'http://localhost:10000/file/images/5cc81c09f2044.jpg', '杭州 (2016 unplugged)', '李志', '李志北京不插电现场 2016.5.29', '', '2020-03-09 11:41:26', '3:42', 1, 'http://localhost:10000/file/music/杭州 (2016 unplugged).flac', '1');
INSERT INTO `music` VALUES (76, '3eb75fa01d9f4de193919f0b684bcb8c', 'http://localhost:10000/file/images/cac8eaba869c4d19a9ebea359c33ba51.jpg', '夜鸟', '程璧', '诗遇上歌', '2014', '2020-03-09 17:18:37', '4:26', 3, 'http://localhost:10000/file/music/程璧 - 夜鸟.mp3', '1');
INSERT INTO `music` VALUES (77, '15fbb9cee314461690b893e15f07614d', 'http://localhost:10000/file/images/851f8445595f476795feed3c5e3bfa9e.jpg', '心想唱歌就唱歌', '程璧/符瑞', '心想唱歌就唱歌', '2013', '2020-03-09 17:19:17', '3:9', 1, 'http://localhost:10000/file/music/程璧 符瑞 - 心想唱歌就唱歌.mp3', '1');
INSERT INTO `music` VALUES (78, '694739aaf46e4d81a011fc5daccbcb75', 'http://localhost:10000/file/images/816a9246240544a68208264cc7dc085f.jpg', '奇妙能力歌', '陈粒', '如也', '', '2020-03-09 17:20:35', '4:13', 3, 'http://localhost:10000/file/music/陈粒 - 奇妙能力歌.mp3', '1');
INSERT INTO `music` VALUES (79, '5adda7bde0af4ff9804502e33d593123', 'http://localhost:10000/file/images/32889ed686d8482baec6c15447348c46.jpg', '定西 (2015动静版)', '李志', '动静（2015Live）', '', '2020-03-09 17:21:14', '4:25', 2, 'http://localhost:10000/file/music/李志 - 定西 (2015动静版).mp3', '1');
INSERT INTO `music` VALUES (80, '3d7d7e4c9cad47f1b9166de67078ea3b', 'http://localhost:10000/file/images/520401f539af4e398544be814cc25ff0.jpg', '关于郑州的记忆 (2016 unplugged)', '李志', '李志北京不插电现场 2016.5.29', '', '2020-03-09 17:21:29', '4:47', 1, 'http://localhost:10000/file/music/李志 - 关于郑州的记忆 (2016 unplugged).mp3', '1');
INSERT INTO `music` VALUES (81, 'bcb370c39ead44899f376fa6c3abd2af', 'http://localhost:10000/file/images/5d2e4f5b00b34492af9daed39c22d512.jpg', '关于郑州的记忆', '李志', '你好,郑州', '', '2020-03-09 17:21:41', '3:48', 1, 'http://localhost:10000/file/music/李志 - 关于郑州的记忆.mp3', '1');
INSERT INTO `music` VALUES (82, '320858d2be944b5a9d32a9def244109a', 'http://localhost:10000/file/images/5ea89c20bc0b4bfaa703f308ac0dfd52.jpg', '忽然（2012 Live）', '李志', '108个关键词（2012 Live）', '', '2020-03-09 17:21:52', '3:13', 1, 'http://localhost:10000/file/music/李志 - 忽然（2012 Live）.mp3', '1');
INSERT INTO `music` VALUES (83, '0dc36742120c462e921ca1a7aa2626e8', 'http://localhost:10000/file/images/e4aa78c0186f4a96a404944064963a54.jpg', '你离开了南京，从此没有人和我说话 2015现场版', '李志', '看见', '', '2020-03-09 17:22:03', '2:4', 1, 'http://localhost:10000/file/music/李志 - 你离开了南京，从此没有人和我说话 2015现场版.mp3', '1');
INSERT INTO `music` VALUES (84, '315c85ac52b6479bb62041e1dc907243', 'http://localhost:10000/file/images/71dab5e4eb9c4423b760cd64a557e00a.jpg', '你离开了南京，从此没有人和我说话', '李志', '梵高先生', '', '2020-03-09 17:22:44', '1:11', 1, 'http://localhost:10000/file/music/李志 - 你离开了南京，从此没有人和我说话.mp3', '1');
INSERT INTO `music` VALUES (85, 'e0667c602932486b921c1e903a1d6af3', 'http://localhost:10000/file/images/d299eb8f4c4147f096b02d9f189a96c2.jpg', '墙上的向日葵(2014i/O版)', '李志', 'i/O（2014 Live）', '', '2020-03-09 17:22:58', '8:2', 1, 'http://localhost:10000/file/music/李志 - 墙上的向日葵(2014i O版).mp3', '1');
INSERT INTO `music` VALUES (86, '141a579ecf79432ca1608053a4e00eed', 'http://localhost:10000/file/images/50446966ed9c42c58ade2c5413007880.jpg', '为你唱首歌', '痛仰乐队', '不要停止我的音乐', '', '2020-03-09 17:23:11', '4:13', 1, 'http://localhost:10000/file/music/痛仰乐队 - 为你唱首歌.mp3', '1');
INSERT INTO `music` VALUES (87, 'e98a874339d3427991808389caa66bed', 'http://localhost:10000/file/images/a79bdaa8fd9f409e915f81338a05fa9c.jpg', '这个世界会好吗 (2016 unplugged)', '李志', '李志北京不插电现场 2016.5.29', '', '2020-03-09 17:23:56', '4:33', 1, 'http://localhost:10000/file/music/李志 - 这个世界会好吗 (2016 unplugged).mp3', '1');
INSERT INTO `music` VALUES (88, '657285d0b7a042ee9dbd6fc0023d0b5f', 'http://localhost:10000/file/images/bf35c99087cc43cbadce040d9297cc1c.jpg', '相信未来序曲 (乐曲)', '李志', '李志、电声与管弦乐II', '', '2020-03-25 16:55:17', '05:06', 1, 'http://localhost:10000/file/music/相信未来序曲 (乐曲) - 李志.flac', '1');
INSERT INTO `music` VALUES (89, '11d3f095f922484a85b54e6e9cf796a3', 'http://localhost:10000/file/images/e8b7f9d2309e4d0baf4fa2b3cd3e0130.jpg', '你好明天', '李志', '在每一条伤心的应天大街上', '', '2020-04-11 22:10:35', '04:27', 1, 'http://localhost:10000/file/music/李志 - 你好明天.mp3', '1');

-- ----------------------------
-- Table structure for music_favor
-- ----------------------------
DROP TABLE IF EXISTS `music_favor`;
CREATE TABLE `music_favor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `music_id` int(255) NULL DEFAULT NULL COMMENT '音乐id',
  `collector_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收藏者id',
  `favor_date` datetime(0) NULL DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of music_favor
-- ----------------------------
INSERT INTO `music_favor` VALUES (1, 60, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-25 16:23:57');
INSERT INTO `music_favor` VALUES (2, 61, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-25 16:24:35');
INSERT INTO `music_favor` VALUES (3, 62, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-25 16:24:43');
INSERT INTO `music_favor` VALUES (5, 64, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-25 16:25:36');
INSERT INTO `music_favor` VALUES (6, 65, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-25 16:25:38');
INSERT INTO `music_favor` VALUES (11, 70, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-25 16:25:50');
INSERT INTO `music_favor` VALUES (23, 69, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-25 18:33:12');
INSERT INTO `music_favor` VALUES (28, 82, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-25 19:11:20');
INSERT INTO `music_favor` VALUES (34, 80, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-25 22:22:34');
INSERT INTO `music_favor` VALUES (44, 87, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-25 22:34:50');
INSERT INTO `music_favor` VALUES (47, 86, '4f6bc03b569f4eaa8fbfed4875d36751', '2020-03-28 16:10:17');
INSERT INTO `music_favor` VALUES (49, 84, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-28 16:35:02');
INSERT INTO `music_favor` VALUES (52, 84, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-28 16:37:22');
INSERT INTO `music_favor` VALUES (58, 85, '7d2e64b5af964f4b85854e9c48f0954d', '2020-03-28 16:43:02');
INSERT INTO `music_favor` VALUES (59, 85, '7d2e64b5af964f4b85854e9c48f0954d', '2020-04-06 11:32:40');

-- ----------------------------
-- Table structure for music_type
-- ----------------------------
DROP TABLE IF EXISTS `music_type`;
CREATE TABLE `music_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '音乐分类id',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名',
  `type_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of music_type
-- ----------------------------
INSERT INTO `music_type` VALUES (1, '流行', '1');
INSERT INTO `music_type` VALUES (2, '摇滚', '1');
INSERT INTO `music_type` VALUES (3, '民谣', '1');
INSERT INTO `music_type` VALUES (4, '电子', '1');
INSERT INTO `music_type` VALUES (5, '轻音乐', '1');
INSERT INTO `music_type` VALUES (6, 'ACG', '1');

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `dynamic_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '动态id',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '举报原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of report
-- ----------------------------
INSERT INTO `report` VALUES (1, '01', 'hhh');
INSERT INTO `report` VALUES (2, '01', 'eee');
INSERT INTO `report` VALUES (3, '02', 'hhh');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自动递增',
  `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `upassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码，加密存储',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码加密的salt值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '01', 'h哈哈哈', '24@qq.com', '17852158421', '1234', '男', '2020-02-04', NULL, NULL, '1', NULL);
INSERT INTO `user` VALUES (3, 'f8249dcc6f9a438b981516fba53abed7', 'dododo', '2472385260@qq.com', NULL, '23cc2702f9c199c5cafb9f3bf1ba686f', NULL, NULL, NULL, NULL, '1', 'fe5dd4c1f4b84cd68856b7a74616a5fe');
INSERT INTO `user` VALUES (4, '7d2e64b5af964f4b85854e9c48f0954d', 'test', '2472385260@qq.com', NULL, '7f1b5cc6e241bd175041d1e9fa9db114', '女', '2020-03-23', 'http://localhost:10000/file/images/a86468f68a2440b6998931f599446dd5.jpg', '哈哈哈哈，test用户1', '1', 'beb01ca093d14f77b98624d6ec0fd1e2');
INSERT INTO `user` VALUES (5, '4f6bc03b569f4eaa8fbfed4875d36751', 'dodudo', '1530613365@qq.com', NULL, 'cd4f4df85b172d2574718ba74de739fa', NULL, '1969-05-09', 'http://localhost:10000/file/images/ba26f88f88f245cb87a4e2472454df70.jpg', NULL, '1', 'cfc60380d3024801b4f6879aa4157610');

SET FOREIGN_KEY_CHECKS = 1;
