## 项目介绍

音乐社交网站，一个基于微服务架构的前后端分离社交网站，算是最初版本，有很多东西不是特别完善。前台使用`Vue`+`Vuetify`，后端使用`Spring Boot`+`Spring Cloud`+`Mybatis`开发，使用`Jwt`做登录验证，使用`ElasticSearch`作为动态和音乐的检索服务，使用`RabbitMQ`作为服务间消息传递服务，使用`RabbitMQ`+`Stomp.js`实现后端和前端通信。

* 本系统为毕业设计项目，原本打算使用SpringBoot为主要框架进行开发，但是由于这次疫情的影响，找工作雪上加霜，在家里的时间就多了起来，所以打算好好利用这段时间，做个稍微有挑战性的项目，结合目前的流行技术，就采用了前后端分离式的微服务架构进行项目开发。
* 本系统基本都是我一个人开发，因能力有限，之前也从未接触过前后端分离和微服务架构开发，本系统也是根据网上的一个商城项目教程的文档和视频进行开发，有很多东西都是现学现卖，比如Vue，微服务的相关知识等，且由于是毕设，时间关系的原因有很多想要使用的技术也没有加上，比如文件系统和一些需要使用缓存的地方也没有使用。所以应该存在不少bug。故有能改正的地方，希望各位大佬能够指出。

## 项目特点

* 实现前后端分离，前端不用关注后端实现技术
* 页面交互使用Vue2.x和Vuetify，提高了开发效率
* 页面使用Vuex进行状态管理，对某些数据进行统一
* 引入RabbitMQ消息队列，用于邮件发送，服务消息传递
* 引入RabbitMQ和Stomp.js实现前后端通信
* 引入ElasticSearch作为动态和音乐的检索服务
* 自己摸Re索的评论模块，实现子评论，新消息提醒
* 使用Cookie和JWT进行登录验证

## 目录介绍

* msns，一款基于前后端分离微服务架构的简易社交网站
* msns-admin：提供admin端api接口和服务
* msns-auth：验证中心，提供登录验证
* msns-comment：提供评论端api接口和服务
* msns-common：存放项目的一些通用工具类接口
* msns-dynamic：提供动态端的api接口和服务
* msns-favor：提供动态和音乐关注收藏的api接口和服务
* msns-gateway：整个系统的网关服务，使用zuul网关
* msns-like：提供动态点赞的api接口和服务
* msns-music：提供音乐端的api接口和服务
* msns-register：服务注册中心，采用Eureka
* msns-search：搜索服务中心，采用ElasticSearch搜索引擎
* msns-sms：消息服务中心，实现邮件发送
* msns-upload：文件上传服务，系统文件上传保存至本地
* msns-user：提供用户端的api接口和服务

## 技术选型

#### 系统架构

![image-20200411214423289](https://s1.ax1x.com/2020/04/11/GqkAvn.png)

#### 后端技术

| 技术           | 说明                    |
| -------------- | ----------------------- |
| SpringBoot     | MVC框架，简化Spring开发 |
| SpringCloud    | 微服务框架              |
| Mybatis        | ORM持久层框架           |
| 通用Mapper     | 简化Mybatis开发         |
| ElasticSearch  | Redis搜索引擎           |
| RabbitMQ       | 消息队列                |
| Redis          | 缓存                    |
| JWT            | jwt登录验证             |
| JavaMailSender | 邮件发送                |

#### 前端技术

| 技术       | 说明              |
| ---------- | ----------------- |
| Vue.js     | 前端框架          |
| Vuetify    | 前端UI框架        |
| vue-cli    | 前端项目脚手架    |
| Vue-router | 前端路由框架      |
| Vuex       | 全局状态管理框架  |
| Axios      | 前端异步请求框架  |
| Stomp.js   | STOMP协议消息框架 |

## 数据库

表4.1 管理员表

| 字段名    | 数据类型     | 空/非空 | 约束条件 | 字段说明   |
| --------- | ------------ | ------- | -------- | ---------- |
| id        | int(11)      | NO      | 主键     | 主键id自增 |
| adminId   | varchar(255) | YES     |          | 管理员id   |
| aname     | varchar(255) | YES     |          | 管理员昵称 |
| sex       | varchar(255) | YES     |          | 管理员性别 |
| birthday  | date         | YES     |          | 管理员生日 |
| phone     | varchar(255) | YES     |          | 管理员手机 |
| email     | varchar(255) | YES     |          | 管理员邮箱 |
| apassword | varchar(255) | YES     |          | 管理员密码 |
| status    | varchar(255) | YES     |          | 状态       |


 表4.2 评论表

| 字段名          | 数据类型     | 空/非空 | 约束条件 | 字段说明   |
| --------------- | ------------ | ------- | -------- | ---------- |
| id              | bigint(200)  | NO      | 主键     | 评论id     |
| pid             | bigint(200)  | YES     |          | 父id       |
| lid             | bigint(200)  | YES     |          | 回复评论id |
| dynamic_id      | int(255)     | YES     |          | 动态id     |
| reply_id        | varchar(255) | YES     |          | 回复者id   |
| respondent_id   | varchar(255) | YES     |          | 被评论者id |
| comment_date    | datetime     | YES     |          | 评论日期   |
| comment_content | text         | YES     |          | 评论内容   |
| status          | varchar(255) | YES     |          | 状态       |


 表4.3 动态表

| 字段名            | 数据类型     | 空/非空 | 约束条件 | 字段说明     |
| ----------------- | ------------ | ------- | -------- | ------------ |
| id                | int(11)      | NO      | PRI      | 主键自增     |
| dynamic_id        | varchar(255) | YES     |          | 动态id       |
| uid               | varchar(255) | YES     |          | 发布者id     |
| uname             | varchar(255) | YES     |          | 发布者昵称   |
| avatar_url        | varchar(255) | YES     |          | 用户头像     |
| dynamic_content   | longtext     | YES     |          | 动态内容     |
| img_urls          | longtext     | YES     |          | 图片路径     |
| dynamic_type_id   | int(11)      | YES     |          | 动态类型id   |
| publish_date      | datetime     | YES     |          | 发布日期     |
| music_id          | varchar(255) | YES     |          | 音乐id       |
| view_count        | bigint(255)  | YES     |          | 浏览次数     |
| report_count      | varchar(255) | YES     |          | 举报次数     |
| status            | varchar(255) | YES     |          | 状态         |
| recentCommentDate | datetime     | YES     |          | 最新评论时间 |


 表4.4 动态关注表

| 字段名       | 数据类型     | 空/非空 | 约束条件 | 字段说明   |
| ------------ | ------------ | ------- | -------- | ---------- |
| id           | int(11)      | NO      | PRI      | 自增主键id |
| dynamic_id   | int(255)     | YES     |          | 动态id     |
| collector_id | varchar(255) | YES     |          | 收藏者id   |
| favor_date   | datetime     | YES     |          | 收藏时间   |
| status       | varchar(255) | YES     |          | 状态       |


 表4.5 动态点赞表

| 字段名           | 数据类型     | 空/非空 | 约束条件 | 字段说明     |
| ---------------- | ------------ | ------- | -------- | ------------ |
| id               | int(11)      | NO      | PRI      | 主键id自增   |
| liker_id         | varchar(255) | YES     |          | 点赞者id     |
| dynamic_id       | varchar(255) | YES     |          | 动态id       |
| dynamic_authorId | varchar(255) | YES     |          | 文章发布者id |
| like_date        | datetime     | YES     |          | 点赞时间     |
| status           | varchar(255) | YES     |          | 状态         |


 表4.6 动态类型表

| 字段名      | 数据类型     | 空/非空 | 约束条件 | 字段说明   |
| ----------- | ------------ | ------- | -------- | ---------- |
| id          | int(11)      | NO      | PRI      | 动态分类id |
| type_name   | varchar(255) | YES     |          | 分类名     |
| type_status | varchar(255) | YES     |          | 分类状态   |


 表4.7 用户关注表

| 字段名         | 数据类型     | 空/非空 | 约束条件 | 字段说明   |
| -------------- | ------------ | ------- | -------- | ---------- |
| id             | int(11)      | NO      | PRI      | 主键id自增 |
| Followers_id   | varchar(255) | YES     |          | 关注者id   |
| beFollowers_id | varchar(255) | YES     |          | 被关注者id |
| follower_date  | datetime     | YES     |          | 关注时间   |


 表4.8 音乐表

| 字段名            | 数据类型     | 空/非空 | 约束条件 | 字段说明     |
| ----------------- | ------------ | ------- | -------- | ------------ |
| id                | int(11)      | NO      | PRI      | 主键id自增   |
| music_id          | varchar(255) | YES     |          | 音乐id       |
| music_cover_url   | varchar(255) | YES     |          | 音乐封面     |
| music_name        | varchar(255) | YES     |          | 音乐名       |
| music_author      | varchar(255) | YES     |          | 音乐作者     |
| music_album       | varchar(255) | YES     |          | 专辑名称     |
| music_publis_date | varchar(255) | YES     |          | 音乐发布日期 |
| music_push_date   | datetime     | YES     |          | 音乐上传日期 |
| music_length      | varchar(255) | YES     |          | 音乐时长     |
| music_type_id     | int(11)      | YES     |          | 音乐类型     |
| music_url         | varchar(255) | YES     |          | 音乐文件路径 |
| status            | varchar(255) | YES     |          | 状态         |


 表4.9 音乐收藏表

| 字段名       | 数据类型     | 空/非空 | 约束条件 | 字段说明 |
| ------------ | ------------ | ------- | -------- | -------- |
| id           | int(11)      | NO      | PRI      | 主键自增 |
| music_id     | int(255)     | YES     |          | 音乐id   |
| collector_id | varchar(255) | YES     |          | 收藏者id |
| favor_date   | datetime     | YES     |          | 收藏时间 |


 表4.10 音乐类型表

| 字段名      | 数据类型     | 空/非空 | 约束条件 | 字段说明   |
| ----------- | ------------ | ------- | -------- | ---------- |
| id          | int(11)      | NO      | PRI      | 音乐分类id |
| type_name   | varchar(255) | YES     |          | 分类名     |
| type_status | varchar(255) | YES     |          | 分类状态   |


 表4.11 动态举报表

| 字段名     | 数据类型     | 空/非空 | 约束条件 | 字段说明 |
| ---------- | ------------ | ------- | -------- | -------- |
| id         | int(11)      | NO      | PRI      | 主键自增 |
| dynamic_id | varchar(255) | YES     |          | 动态id   |
| reason     | varchar(255) | YES     |          | 举报原因 |


 表4.12 用户表

| 字段名      | 数据类型     | 空/非空 | 约束条件 | 字段说明     |
| ----------- | ------------ | ------- | -------- | ------------ |
| id          | int(11)      | NO      | PRI      | 主键id，自增 |
| uid         | varchar(255) | YES     |          | 用户id       |
| uname       | varchar(255) | YES     |          | 用户昵称     |
| email       | varchar(255) | YES     |          | 用户邮箱     |
| phone       | varchar(255) | YES     |          | 手机         |
| upassword   | varchar(255) | YES     |          | 加密后的密码 |
| sex         | varchar(255) | YES     |          | 性别         |
| birthday    | date         | YES     |          | 生日         |
| avatar_url  | varchar(255) | YES     |          | 头像         |
| description | varchar(255) | YES     |          | 描述         |
| status      | varchar(255) | YES     |          | 状态         |
| salt        | varchar(255) | YES     |          | 密码的salt值 |

## 相关截图
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_1WSw206yrG.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_CLiRZ0JqkC.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_IxpWnBDBLJ.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_J9pKmlsvPh.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_JtLaP6jnLK.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_PVLKf7oCVM.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_QpStgFNqiy.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_R1tNXhiibQ.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_RicAjdoFMl.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_VqofCF669T.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_caYXro8hjJ.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_d96g6JZdJF.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_djMgErbBrO.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_ei4QDpQ0xg.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_fy62yWXCZf.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_iAEOGcyYxA.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_j6dccM0YfU.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_jhh1Y5Nb5y.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_jrSkPLMAyN.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_skUaHk08YN.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_tpfL3XOSCA.png)
![](https://github.com/dodudo/msns/blob/master/msns-image/chrome_yyDHaDMGRS.jpg)



















