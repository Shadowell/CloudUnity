# 基于Spring Cloud的架构设计问题汇总

## 1. 后端数据库的访问

### 1.1 关系型数据库（Mysql,Oracle）

#### 1.1.1 Mysql,Oracle操作
Spring Boot 可以整合JPA（Java Persistence APi）,它是一个数据持久化的类和方法的集合。
JPA的目标是定制一个有很多数据库供应商实现的API,可通过集成其API接口
（如：JpaRepository,MongoRepository）实现对数据库的访问，该接口提供了基本的CURD方法（也可自定义数据库查操作方法）
通过IoC将Dao层实例注入到Service层中，就可以通过接口对数据库进行访问，从而写对应的业务逻辑。

[依赖包]

    compile 'org.springframework.boot:spring-boot-starter-data-jpa'
    compile 'mysql:mysql-connector-java'
    
    compile 'org.springframework.boot:spring-boot-starter-data-jpa'
    compile 'com.oracle:ojdbc6'
    
[代码演示]

    Controller层:
    
```
   @RestController
   @RequestMapping(value = ["/product"])
   class ProductController (@Autowired var productService: ProductService) {
 
      @RequestMapping(value = [""],method = [RequestMethod.POST])
      @ResponseBody
      fun addProduct(@RequestBody productModel: ProductModel): Result {
          return productService.addProduct(productModel)
      }
```
    Service层:
    
```
    @Service
    class ProductService @Autowired constructor(private var productDao: ProductDao){
    
        fun addProduct(productModel: ProductModel): Result {
            val target = productDao.findProductByName(productModel.productName)
            if (!target.isPresent) {
                return Result.NOT_FOUNT
            }
            val newProduct = ProductEntity()
            newProduct.id = UUID.randomUUID().toString()
            newProduct.productName = productModel.productName
            newProduct.productCount = productModel.productCount
            productDao.save(newProduct)
            return Result.SUCCESS
        }
        
        //其他业务逻辑...
```
    Dao层：

```
    @Repository
    interface ProductDao: JpaRepository <ProductEntity, String> {
    
        @Query(value = "select product from ProductEntity product where id = ?1")
        fun findProductById(id: String): Optional<ProductEntity>
    
        @Query(value = "select product from ProductEntity product where productName = ?1")
        fun findProductByName(name: String): Optional<ProductEntity>
    }

```
    配置：
    
```
    spring:
      application:
        name: product-service
    
      datasource:
        driver-class-name: com.mysql.jdbc.Driver 
        url: jdbc:mysql://127.0.0.1:3306/datiip?useUnicode=true&useSSL=false
        username: datiip
        password: datiip
        
```


### 1.2 非关系型数据库 Redis,MongoDB）

#### 1.2.1 Redis操作

数据操作层的RedisDao通过RedisTemplate来访问Redis,通过注入StringRedisTemplate的Bean来对Redis数据库中的字符串类型进行数据操作。
同理，将注入ProductRedisDao注入到Service层，具体的业务逻辑则在Service层实现。

[依赖包]

    compile 'org.springframework.boot:spring-boot-data-redis'
    
[代码演示]
    Dao层：

```
    @Repository
    class ProductRedisDao {
        
        @Autoried
        lateinit var redisTemplate: StringRedisTemplate
        
        fun setKey(key: String, value: String) {
            var ops: ValueOpetation<String, String> = redisTemplate.opsForValue()
            ops.set(key,value,1,TimeUnit.MINUTES)
        }
        
        fun getValue(key: String): String {
            var ops: ValueOpetation<String, String> = redisTemplate.opsForValue()
            return ops.get(key)
        }

    }

```

#### 1.2.2 MongoDB操作

ProductMongoDBDao继承MongoRepository，就继承了MongoDBRepository的默认方法，将ProductMongoDBDao注入到Service层就可实现具体的业务操作逻辑

[依赖包]

    compile 'org.springframework.boot:spring-boot-starter-data-mongodb'
    
[代码演示]

Dao层：

```
    @Repository
        interface ProductMongoDBDao: MongoRepository <ProductEntity, String> {
        
        }

```

## 2. 构建多实例的高可用集群

### 2.1 构建Eureka集群

[依赖包]

     compile 'org.springframework.cloud:spring-cloud-starter-eureka-server'

[注册]

服务提供者（Eureka-Client）向Eureka-Server注册时，会提供自身的元数据，如IP，PORT，运行状况指标等，默认情况下，Eureka-Client每30s会向Eureka-Server
发送心跳来进行服务续约，通过心跳来告知Eureka-Server该Eureka-Client任然可用，如果Eureka-Server在90s内没有收到Eureka-Client的心跳
就会将该Eureka-Client的实例从注册列表中删除。同时Eureka-Client会从Eureka-Server获取注册表信息并缓存，该注册表中包含了所以向Eureka-Server注册的实例信息，
在进行远程调用时，Eureka-Client会从注册列表中找到其他服务的信息，从而实现远程调用。

[高可用]

当服务实例很多时，Eureka-Server承担了比较高的负载，由于其在整个微服务系统中起着关键性的作用，因此需要对其进行多实例集群部署，以提高可用性。

[配置]

实例1的配置：

```
    server:
      #本服务的监听端口，一般无需修改
      port: 8001
    eureka:
      instance:
        # 本服务所在的主机，一般无需修改
        hostname: localhost
      client:
        # 是否将本服务注册到Eureka中，默认为false
        registerWithEureka: false
        # 是否获取Eureka上的注册列表信息
        fetchRegistry: true
        # 本服务的地址
        serviceUrl:
          defaultZone: http://server2:8002/eureka/
```

实例2的配置：

```
    server:
      #本服务的监听端口，一般无需修改
      port: 8002
    eureka:
      instance:
        # 本服务所在的主机名称
        hostname: server2
      client:
        # 是否将本服务注册到Eureka中，默认为false
        registerWithEureka: false
        # 是否获取Eureka上的注册列表信息
        fetchRegistry: true
        # 本服务的地址
        serviceUrl:
          defaultZone: http://server1:8002/eureka/
```

Eureka-Client在配置Eureka-Server时，应将所有的Eureka-Server的地址都配置上去，
避免自己注册的Eureka-Server单点挂掉。只要自己注册的Eureka-Server列表还在，那么后续添加N个Eureka-Server，所有注册信息都会被复制过去。

```
    spring:
      application:
        name: Eureka-Client
    eureka:
      client:
        service-url:
          defaultZone: http://server1:8001/eureka/,http://server2:8002/eureka/
```

### 2.2 构建服务提供者集群

可以通过以下2中方式开启多实例：

- 同一台机器上，以不同的端口号启动多个服务实例
- 多台机器上，分别启动同一个微服务，即可开启多服务实例

这些服务实例都会在Eureka-Server进行注册，其作为服务提供者时，服务间的相互调用是通过Feign来调用，通过在@FeignClient注解的接口上
绑定需要调用的服务的ServiceId（即服务提供者的配置中${spring.application.name}的值），Feign集成了Ribbon,根据所获取到的服务注册列表信息，
在调用时会自动做负载均衡（默认的负载均衡策略是轮询，策略可配置），而通过web来调用微服务，则会通过zuul做反向代理和负载均衡。

## 3. 网关配置,负载均衡及熔断器

### 3.1 网关

Zuul作为spring cloud的核心组件，其提供的功能很多，比如反向代理，负载均衡还有权限控制等功能.Zuul网关的提供了开箱即用的功能，
只需要在配置文件中配置了所有服务提供者的相关信息就可做请求转发和负载均衡。

[依赖包]

    compile 'org.springframework.cloud:spring-cloud-starter-zuul'
    compile 'org.springframework.cloud:spring-cloud-starter-eureka'

[配置]

```
    eureka:
      client:
        serviceUrl:
          defaultZone: http://server1:8001/eureka/,http://server2:8002/eureka/
          
    server:
      port: 8003
    
    spring:
      application:
        name: Zuul-Server
    
    zuul:
      routes:
        productApi:
          path: /productApi/**
          serviceId: product-service
    
        customerApi:
          path: /customerApi/**
          serviceId: customer-service
```
配置完这个配置文件后，其实zuul已经实现了反向代理和负载均衡的两个功能,使用serviceId进行绑定后，
如果有多个相同的serviceId，则会进行轮询的方式进行访问。Web通过访问Zuul-Server服务，
由Zuul-Server对请求进行转发，从而实现对product-service和customer-service的访问，同时做了负载均衡。
而服务间内部调用则不需要经过Zuul-Server,内部服务间的调用只是通过Feign进行远程调用，
consumet-service在调用product-service的实现如下：

```
    @FeignClient(name = "\{service.product-service}",configuration = [UtilConfig::class])
    interface productServiceInterface {
    
        @GetMapping(value = ["/product/{id}"])
        fun findProductById(@PathVariable id: String): Optional<ProductEntity>
        
        @GetMapping(value = ["/product/{name}"])
        fun findProductByName(@PathVariable name: String): Optional<ProductEntity>
        
    }
    
```


### 3.2 熔断器

在一个分布式系统里，每个单元运行在不同的进程中，依赖服务通过远程调用的方式进行，有可能因为网络原因或者依赖服务自身的原因导致
调用失败，比如超时、异常等，这些问题会导致调用方的对外服务会出现延迟，若此时调用方的请求不断增加，最后会因为等待
出现故障的依赖方响应出现任务积压，还会导致服务调用的"雪崩"问题，最终导致服务瘫痪，如何能够保证在一个依赖出问题的情况下，
不会导致整体服务失败，这个就是熔断器Hystrix需要做的事情。Hystrix提供了熔断、隔离、Fallback、cache、监控等功能，
能够在一个、或多个依赖同时出现问题时保证系统依然可用。当服务调用失败的时候，通过熔断器的故障监控，会进行服务降级处理，向服务方返回一个
错误响应，而不是长时间的等待，这样就不会使得线程因调用故障服务被长时间占用不释放，避免了故障在分布式系统中的蔓延。
服务降级是在客户端处理的，是一个本地调用。

[依赖包]

    compile 'org.springframework.cloud:spring-cloud-starter-hystrix'
   
[代码演示]

    对product-service的接口：
        其中RetryConfig重写了FeignClientsConfiguration类中的feignRetryer方法，实现自定义的重试策略；
        ProductServiceFallback配置了在调用ProductService中的方法失败时的降级策略，实质是一个本地方法调用，以实现快速失败。
    
    ```
        @FeignClient(name = "product-service",fallback = ProductServiceFallback::class)
        interface ProductServiceInterface {
        
            @GetMapping(value = ["/product/{id}"])
            fun getProductById(@PathVariable id: String): ProductModel
        
            @DeleteMapping(value = ["/product/{id}"])
            fun deleteProduct(id: String): Result
        }
        
    ```   
    ProductServiceFallback.kt
    
    ```
        @Component
        class ProductServiceFallback: ProductServiceInterface {
        
            override fun getProductById(id: String): ProductModel {
                return ProductModel("unknow","unknow",0)
                //快速失败方法...,一般返回
            }
        
    ```
    RetryConfig.kt
    
    ```
        @Configuration
        open class RetryConfig {
        
            private val logger: Logger = LogManager.getLogger(RetryConfig::class)
        
            @Bean
            fun feignRetryer(): Retryer {
                logger.info("Retry to call")
                return Retryer.Default(100,TimeUnit.SECONDS.toMillis(1),2)
            }
        }
    ```

## 4. 并发访问的安全性

[线程安全]


>  有状态对象: 有状态就是有数据存储功能。有状态对象(Stateful Bean)，就是有实例变量的对象，
可以保存数据，是非线程安全的。

>  无状态对象: 无状态就是一次操作，在不同方法调用间不保留任何状态,不能保存数据。无状态对象(Stateless Bean)，就是没有实例
变量的对象.不能保存数据，是不变类，是线程安全的。

Spring Boot是基于Spring MVC,而Spring MVC默认是单例模式，默认情况下托管到Spring容器的Bean都是单例的，
无状态，所以多个线程请求Controller引用的是同一个实例，Controller的成员变量肯定是同一个变量，
由于只有一个Controller的实例，当多个线程同时调用它的时候，它的成员变量就不是线程安全的。
用Controller 和Service里面的方法, 方法是在虚拟机栈中执行的 每个线程都有各自的栈空间, 
所以没有线程安全问题,但是如果在 Controller类中定义了属性, 并且在方法中使用, 这个时候就存在线程安全问题了。
因此Spring MVC的Contrller在编码时，尽量避免使用实例变量。如果一定要使用实例变量，
则可以改用以下方式： 

> 1.Controller中声明 scope=”prototype”，即设置为多例模式 
> 2.在Controller中使用ThreadLocal变量,如：private ThreadLocal<Integer> count = new ThreadLocal<Integer>(); 
spring对那些个有状态bean使用ThreadLocal维护变量(仅仅是变量,因为线程同步的问题就是成员变量的互斥访问出问题)时，
ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。

因此，只要不在Controller和Service中定义实例变量和有状态的Bean那么就不会出现线程安全问题。

[事务安全]

Spring boot默认嵌入的容器是tomcat，tomcat是用线程池来处理网络请求的，所以在后台开发业务接口时
需要考虑多线程环境下一些常见的问题，比如数据同步。如果涉及到数据库的操作时，常常会引入事务的问题，
这时又必须考虑事务的隔离级别、事务的传播级别。如果一个接口既涉及加锁、又涉及事务，那么在进行业务
逻辑设计时就必须要考虑或者说意识到以下几点：

>  1.通常事务的隔离级别是read-commited，一个事务只有提交后所做的改变才可以被其他事务读取到;

>  2.事务的传播级别一般是propagation-required，即子函数里的事务会加入到父函数里的事务中合成一个事务;

>  3.如果要通过加锁来实现数据同步，且在锁的范围内涉及到事务，请确保锁的范围一定要覆盖住事务的范围。比如如果一个service层的接口处于事务管理范围内，就需要在controller层加锁，才能确保一个线程里读到的数据一定是另一个线程里的事务提交之后的最新值。


参考：

- https://blog.csdn.net/darkdragonking/article/details/52367186

- https://blog.csdn.net/sunmingliang0603/article/details/79752074

- https://www.jianshu.com/p/037ef628a1a9

## 5. 用户管理及认证授权
单点登录（英语：Single sign-on，缩写为 SSO），又译为单一签入，一种对于许多相互关连，
但是又是各自独立的软件系统，提供访问控制的属性。当拥有这项属性时，当用户登录时，
就可以获取所有系统的访问权限，不用对每个单一系统都逐一登录。相同的，单一注销（single sign-off）
就是指，只需要单一的注销动作，就可以结束对于多个系统的访问权限。
在微服务架构中，单点登录可以理解为当用户登录成功后，就可以访问所有有权限访问的微服务。
API Gateway提供了客户端访问微服务的入口，Token实现了无状态的用户认证。结合这两种技术，
我们就可以为微服务应用实现一个单点登录方案。

微服务单点登录流程下，用户的认证和鉴权如下图：

![image](file:///E:\CloudProject\CloudUnity\Doc\Image\单点登录.png)

`用户登录：`
- 客户端发送登录请求到API Gateway
- API Gateway将登录请求转发到Security Service
- Security Service验证用户身份，并颁发Token

`用户请求：`
- 客户端请求发送到API Gateway
- API Gateway调用的Security Service对请求中的Token进行验证，检查用户的身份
- 如果请求中没有Token，Token过期或者Token验证非法，则拒绝用户请求。
- Security Service检查用户是否具有该操作权，如果用户具有该操作权限，则把请求发送到后端的Business Service，否则拒绝用户请求。

`用户权限控制:`
API Gateway处进行统一的权限控制
客户端发送的HTTP请求中包含有请求的Resource及HTTP Method。
如果系统遵循REST规范，以URI资源方式对访问对象进行建模，
则API Gateway可以从请求中直接截取到访问的资源及需要进行的操作，
然后调用Security Service进行权限判断，根据判断结果决定用户是否有权
限对该资源进行操作，并转发到后端的Business Service。这种实现方
式API Gateway处统一处理认证和鉴权逻辑，各个微服务不需要考虑用户
认证和鉴权，只需要处理业务逻辑，简化了各微服务的实现。

参考：

- https://www.jianshu.com/p/6307c89fe3fa/

- https://www.cnblogs.com/exceptioneye/p/9341011.html

## 6. 异步调用

FeignClient不支持异步调用，需要做异步调用需要使用异步的HTTP客户端：AsyncRestTemplate


```$xslt

    fun deleteConsumer(id: String): Result {
        val url: String = String.format("http://localhost:8400/product/%s",id)
        val asyncTemplate = AsyncRestTemplate()
        //调用完后立即返回（没有阻塞）
        val forEntity = asyncTemplate.getForEntity(url, String::class.java)
        logger.info("当前线程：name = %s".format(Thread.currentThread().name))
        logger.info("等待返回...,Time = %s".format(System.currentTimeMillis()))
        //异步调用后的回调函数
        forEntity.addCallback(object : ListenableFutureCallback<ResponseEntity<String>> {
            //调用失败
            override fun onFailure(ex: Throwable) {
                logger.info("失败线程：name = %s".format(Thread.currentThread().name))
                logger.error("=====rest response failure======,Time = %s".format(System.currentTimeMillis()))
            }

            //调用成功
            override fun onSuccess(result: ResponseEntity<String>?) {
                logger.info("成功线程：name = %s".format(Thread.currentThread().name))
                logger.info("=====async rest response success=====, result = " + result!!.body!!)
            }
        })
        return Result()
    }
    
```

日志打印结果：

    当前线程：name = http-nio-8500-exec-6
    等待返回...,Time = 1539937334965
    失败线程：name = SimpleAsyncTaskExecutor-1
    =====rest response failure======,Time = 1539937336042
    
    当前线程：name = http-nio-8500-exec-8
    等待返回...,Time = 1539937511207
    成功线程：name = SimpleAsyncTaskExecutor-1
    =====async rest response success=====, result = {"id":"1","productName":"apple","productCount":2}


参考：

- https://www.cnblogs.com/yzlpersonal/p/5737991.html

## 7. 配置中心





