# api-call-counter(api调用次数统计)

## 使用
 1. 引入依赖
    ```xml
    <dependency>
        <groupId>com.fehead.open</groupId>
        <artifactId>api-call-counter</artifactId>
        <version>1.0.1</version>
    </dependency>
    ```
2. 配置redis 
    ```yml
    fehead:
      api-call-counter:
        redis:
          host: your redis host
    ```
## 说明
1. 默认统计controller包下所有方法
2. 其他方法使用注解@SetPointCut开启统计
3. 不想统计的方法使用@NotSetPointCut标记