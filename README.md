# Java常用分布式开发工具包

## config 
主要包含所有的基于springboot的配置文件。
#### 数据层
- config.data.hibernate 在基于hibernate开发的时候自动创建的datasource，factory等。
- config.data.mybatis 在基于mybatis开发的时候
- config.data.redis
- config.data.mongodb

#### RPC提供者
- config.provider.motan 基于motan的rpc框架服务提供者的配置

#### http接口层
- config.client.motan 基于motan的rpc消费者的http接口，附加token验证，参数自动加解密，跨域访问。
- config.session 使用redis的session分布式管理
- config.springfox 接口层的API文档生成

## parent
所有工程的parent pom

#### 数据层
- orm - 包括hibernate和orm。mybatis，用于开发dao层工程时候使用
- provider - 用于创建服务提供者，包括consul、dubbo、eureka、motan，



## server
### server.config
动态配置文件，通过此服务读取git或svn上的配置文件。
使用spring cloud config的方式

### server.eureka
启动eureka的服务端