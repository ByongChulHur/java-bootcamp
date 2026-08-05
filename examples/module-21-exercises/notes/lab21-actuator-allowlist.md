# Lab 21 — Actuator Allow-List

## Candidates
There are health, info, metrics, prometheus, env, beans, and configprops endpoints.

## Lab allow
health (+ metrics for demos); 

## Lock / deny
env, beans, configprops must stay locked even in the lab. For example, the env endpoint can reveal sensitive information such as database credentials.

## Prod auth note
Lab exposure is not the same as production exposure. In production, management endpoints must sit behind authentication/authorization

## Scope
Pre-lab only.