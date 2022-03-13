# Micro-services with Spring Cloud

### What's done:
1. Discovery Server - Eureka
2. API gateway: with token relay (auth server needed)
3. Greetings Service: (non)secured demo endpoint, multi-instance
4. Quiz Service: service for creating Questions, QuiTemplates and running Quiz, requires MongoDB and Auth Server

### Needs to be done in the future ( but not required ):
1. add Lombok
2. ...

## Services

## Gateway

Gateway service which obtains service addresses from Eureka Server

## Discovery Service

Eureka Server


## Greetings Service

A simple service which registers itself with discovery-service.  
Run multiple instances on different ports.
It is used by gateway and caller-service.

## Quiz Service

REST endpoints to create/list Questions, QuizTemplates and instantiate Quiz from QuizTemplate. Secured with OAuth2 (there is repo for Keycloak Docker container, and also MongoDB )

