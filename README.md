# 1. 说明

在写文章过程中，经常会使用一些示例工程来辅助说明，本仓库用于存放一些示例工程，以便于读者可以参考。

# 2. 示例说明

## 2.1 java应用监测：`java-monitor-example`

用于[java监测技术文章](https://mianshenglee.github.io/)的示例说明，此示例是一个`spring boot`工程，里面包含简单的`controller`和`service`类，`OOM`的接口等功能。

文章列表如下：

- [java应用监测(1)-java程序员应该知道的应用监测技术](https://mianshenglee.github.io/2019/08/23/java-monitor-1.html)
- [java应用监测(2)-java命令的秘密]( https://mianshenglee.github.io/2019/08/24/java-monitor-2.html )
- [java应用监测(3)-这些命令行工具你掌握了吗]( https://mianshenglee.github.io/2019/08/25/java-monitor-3.html )
- [java应用监测(4)-线上问题排查套路]( https://mianshenglee.github.io/2019/08/26/java-monitor-4.html )
- [java应用监测(5)-可视化监测工具]( https://mianshenglee.github.io/2019/08/27/java-monitor-5.html )
- [java应用监测(6)-第三方内存分析工具MAT]( https://mianshenglee.github.io/2019/08/29/java-monitor-6.html )
- [java应用监测(7)-在线动态诊断神器BTrace]( https://mianshenglee.github.io/2019/08/30/java-monitor-7.html )
- [java应用监测(8)-阿里诊断工具arthas]( https://mianshenglee.github.io/2019/08/31/java-monitor-8.html )

## 2.2 Swagger企业实践：`springboot-swagger-demo`

基于springboot2+swagger2，结合在企业中的实践，对接口文档的编写进行详细说明。

1. `hello-swagger-demo`

- 示例功能： 包括swagger介绍及文档生成说明，构建示例工程及配置描述，使用注解添加文档内容说明，使用全局参数进行接口认证。
- 文章：[springboot+swagger接口文档企业实践（上）](https://mianshenglee.github.io/2019/11/13/springboot-swagger1.html)

2. `advance-sagger-demo`

- 示例功能：包含对接口进行动态过滤，结合easymock进行数据模拟，对接口文档进行离线文档输出等功能。
- 文章：[springboot+swagger接口文档企业实践（下）](https://mianshenglee.github.io/2019/11/21/springboot-swagger2.html)

## 2.3 logback企业实践：`springboot-logback-demo`

基于springboot+logback，对日志输出框架的使用示例，结合在企业中的实践进行详细说明。

1. `logback-simple-demo`

- 示例功能： 使用springboot+logback构建示例工程及配置描述，对logback配置文件的详述及使用，实现按日志级别输出到文件功能。
- 文章：[springboot+logback日志输出企业实践（上）](https://mianshenglee.github.io/2019/11/28/logback1.html)

2. `logback-advance-demo`

- 示例功能：对 logback 的进阶使用进行描述，主要包括日志异步输出，多环境日志配置以及使用MDC进行分布式系统请求追踪。
- 文章：[springboot+logback日志输出企业实践（下）]( https://mianshenglee.github.io/2019/11/29/logback2.html )



## 2.4 java动态代理：`dynamic-proxy-demo`

对java的动态代理技术的使用示例，示例中包含了java反射、静态代理、动态代理、Spring AOP的使用实例。配套以下文章进行学习：

- 文章：[java开发必学知识:动态代理](https://mianshenglee.github.io/2019/12/20/dynamicproxy.html)



## 2.5 多数据源处理： `multi-datasource`

基于 Spring Boot 2+MyBatis Plus ，对多数据源的处理代码。

1. `basic-multi-datasource`
- 示例功能： 使用多套数据源的策略，实现一主一从的数据库逻辑。
- 文章：[搞定SpringBoot多数据源(1)：多套源策略](https://mianshenglee.github.io/2020/01/13/multi-datasource-1.html)

2. `dynamic-datasource`

- 示例功能： 使用动态数据源策略进行数据源切换，也可使用 AOP 方式进行数据源切换，实现一主一从的数据库逻辑。
- 文章：[搞定SpringBoot多数据源(2)：动态数据源](https://mianshenglee.github.io/2020/01/13/multi-datasource-2.html)

3. `parametric-dynamic-datasource`

- 示例功能： 根据参数动态添加数据源以及切换数据源，解决不确定数据源的问题。
- 文章：[搞定SpringBoot多数据源(3)：参数化变更源](https://mianshenglee.github.io/2020/01/13/multi-datasource-3.html)

## 2.6 使用python自动化生成数据库说明文档：` python/tool-gen-db-doc `

通过使用 SQL 读取数据库表及字段元信息，然后输出到 excel 文档的思路，以 python 的实现方式完成自动生成文档功能

- 文章：[还在手工生成数据库文档？3个步骤自动完成了解一下](https://mianshenglee.github.io/2020/08/30/db-doc-python.html)

# 如何找到我的更多文章

- [我的Blog](https://mianshenglee.github.io)：`https://mianshenglee.github.io/`

- 我的公众号（搜索`Mason技术记录`）：

![mason](https://gitee.com/mianshenglee/datastorage/raw/master/md-photo/myphoto/wx/wx-public.jpg)

- 各大博客平台（CSDN，掘金，开源中国，博客园，语雀，segmentfault，开发者头条），搜索`Mason技术记录`也可以找到









