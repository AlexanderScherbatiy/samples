# H2

application.yml
```yml
spring:
  h2:
    console:
      enabled: true
      path: /h2-console
```
URL: http://localhost:8080/h2-console

Console:
```text
JDBC URL: jdbc:h2:mem:testdb
User Name: sa
Pasword: <leave this empty>
```
