# Lab 23 — application.yml Sketch

```yaml
spring:
  application:
    name: lab23-crm
server:
  port: 8080
management:
  endpoints:
    web:
      exposure:
        include: health


# Lab 23 — application.yml Sketch

## Base keys
application name: lab23-crm
server.port: 8080
management exposure: health

## dev teaser
logging.level.root=DEBUG (more verbose logging for local development)

## prod teaser
logging.level.root=INFO

## Scope
Pre-lab only. No real passwords.