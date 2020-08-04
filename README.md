# 스프링 시큐리티 OAuth2.0 V2

- 페이스북, 구글 로그인 및 기본 시큐리티 연동

### 스프링 시큐리티 기본 V1 참고

- https://github.com/jg20228/Spring_securityex01

### JPA method names 참고

![1](https://user-images.githubusercontent.com/62128942/89245461-cedd8b80-d643-11ea-967c-1cfac71c688f.png)

### MYSQL DB 및 사용자 생성

```sql
create user 'cos'@'%' identified by 'cos1234';
GRANT ALL PRIVILEGES ON *.* TO 'cos'@'%';
create database security;
use security;
```

### SecurityConfig.java 설정 방법

```java
//protected void configure(HttpSecurity http) 함수 내부에 권한 설정법
.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
```

### 컨트롤러의 함수에 직접 권한 설정 하는 방법

```java
//특정 주소 접근시 권한 및 인증을 위한 어노테이션 활성화 SecurityConfig.java에 설정
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)

//컨트롤러에 어노테이션 활성화
@PostAuthorize("hasRole('ROLE_MANAGER')") //컨트롤 직후
@PreAuthorize("hasRole('ROLE_USER')") //컨트롤 전
@Secured("ROLE_MANAGER")
```

### application.yml 설정

```yml
server:
  port: 8080
  servlet:
    context-path: /
    encoding:
      charset: UTF-8
      enabled: true
      force: true

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/security?serverTimezone=Asia/Seoul
    username: cos
    password: cos1234

  mvc:
    view:
      prefix: /templates/
      suffix: .mustache

  jpa:
    hibernate:
      ddl-auto: update #create update none
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
    show-sql: true

  security:
    oauth2:
      client:
        registration:
          google: # /oauth2/authorization/google 이 주소를 동작하게 한다.
            client-id: 머시기머시기
            client-secret: 머시기머시기
            scope:
              - email
              - profile
```
