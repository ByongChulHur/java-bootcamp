# Lab 26 — Property Override Order

## Highest to lowest
1. Command-line args (-D / --)
2. Environment variables
3. application-{profile}.yml
4. application.yml
5. Code defaults

## Property you will measure in lab
northstar.integration.timeout-ms — will be set differently in application.yml,
application-dev.yml, and via NORTHSTAR_INTEGRATION_TIMEOUT_MS env var, then
confirmed at runtime to prove env > profile YAML > base YAML.

## Scope
Pre-lab only.