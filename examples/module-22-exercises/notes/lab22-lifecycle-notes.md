# Lab 22 — Bean Lifecycle Callbacks

## Lifecycle order
Create → Inject → @PostConstruct → Use → @PreDestroy.

## @PostConstruct purpose
Runs once, right after the constructor and dependency injection finish,


## @PreDestroy purpose
Runs once, right before the bean is destroyed, to perform cleanup

## What not to do in init
Do not create CUS-1001 inside @PostConstruct for every request.
This is because PostConstruct is called whenever it is initialized which mean is that whenever it is called it will be saved in repository and
when it calls again then it can duplicate.
## Scope
Pre-lab only.