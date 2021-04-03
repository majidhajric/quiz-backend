# Examples

## Gateway

Gateway service which obtains service adresses from Eureka Server

## Discovery Service

Eureka Server

## Caller Service

Client side load balancing which calls greeting-service,  
and it obtains addresses from discovery-service.

## Greetings Service

A simple service which registers itself with discovery-service.  
Run multiple instances on different ports.
It is used by gateway and caller-sevice.
