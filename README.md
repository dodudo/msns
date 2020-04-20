## 项目介绍

  音乐社交网站，一个基于微服务架构的前后端分离社交网站，算是最初版本，有很多东西不是特别完善。前台使用`Vue`+`Vuetify`，后端使用`Spring Boot`+`Spring Cloud`+`Mybatis`开发，使用`Jwt`做登录验证，使用`ElasticSearch`作为动态和音乐的检索服务，使用`RabbitMQ`作为服务间消息传递服务，使用`RabbitMQ`+`Stomp.js`实现后端和前端通信。项目前端地址请见：https://github.com/dodudo/msns-web
  
* 本系统从内容上大致分为动态模块，音乐模块，用户模块和管理模块四个部分，含有13个微服务，微服务服务详见目录。从用户上分为用户、管理员两个个模块。用户主要功能有注册，登录，查看及修改个人信息，关注其他用户，发布动态，对动态可以搜索，点赞，收藏，评论。对音乐可以浏览，搜索，收藏，播放等；管理员的主要功能包括动态的管理、音乐的管理、用户的管理和管理员的管理等。经过努力，上述功能现已基本完成。

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

![image-20200412205503555](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412205503555.png)

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

## 相关截图

##### 前台

- 登陆页面

  ![image-20200412090346912](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412090346912.png)

* 管理员登录

  ![image-20200412091302795](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412091302795.png)

* 注册

  邮箱验证码

  ![image-20200412092845719](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412092845719.png)

  ![image-20200412092922922](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412092922922.png)

* 主页面

  ![image-20200412094815633](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412094815633.png)

* 更改头像

  ![image-20200412094932437](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412094932437.png)

* 修改个人信息

  ![image-20200412095021861](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412095021861.png)

* 修改密码

  ![image-20200412095058713](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412095058713.png)

* 发布动态

  选择音乐（系统所有音乐都可选择）

  ![image-20200412095431929](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412095431929.png)

  ![image-20200412095904105](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412095904105.png)

  发布后样式（当文字超过5行显示展开按钮）

  ![image-20200412095929359](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412095929359.png)

  播放音乐（顺序播放）

  ![image-20200412100040462](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412100040462.png)

* 评论

  发评论

  ![image-20200412100150061](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412100150061.png)

   子评论（新增子评论，子评论多于三条显示更多按钮）

  ![image-20200412100228769](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412100228769.png)

* 消息中心（有新消息时角标变化，未读头像旁有红点表示，可标记已读）

  ![image-20200412100315683](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412100315683.png)

* 收到赞（未读头像旁有红点表示，可标记已读）

  ![image-20200412100402177](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412100402177.png)

* 我的评论（用户的所有评论）

  ![image-20200412100947905](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412100947905.png)

* 我的动态（所用用户动态）

  ![image-20200412101139438](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412101139438.png)

* 查看收藏（收藏的动态）

  ![image-20200412101108681](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412101108681.png)

* 音乐收藏

  ![image-20200412101125828](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412101125828.png)

* 查看关注（所有关注的用户都显示在这里，粉丝同）

  ![image-20200412101202512](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412101202512.png)

* 查看关注用户动态（所有被关注的用户的动态都在动态页这里显示）

  ![image-20200412101342999](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412101342999.png)

* 音乐页（收藏、查询音乐）

  ![image-20200412101302412](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412101302412.png)

##### 后台

* 管理员登录

  ![image-20200412101537509](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412101537509.png)

* 动态管理

  ![image-20200412101603971](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412101603971.png)

* 分类管理

  ![image-20200412101635931](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412101635931.png)

* 音乐统计

  ![image-20200412101703368](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412101703368.png)

* 音乐管理（新增、修改、删除音乐）

  ![image-20200412101726016](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412101726016.png)

* 音乐新增（根据上传音乐文件信息自动填充音乐信息）

  ![image-20200412101806656](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412101806656.png)

* 用户管理（删除、修改、查询用户）

  ![image-20200412101839411](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412101839411.png)

* 管理员管理（新增、‘假删除’、修改、查询管理员）

  ![image-20200412101920506](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412101920506.png)

* 管理员、真删除、恢复

  ![image-20200412102056128](https://github.com/dodudo/msns/blob/master/msns-image/image-20200412102056128.png)
