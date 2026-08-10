# Lab 26 — ConfigurationProperties Sketch

## Class name
NorthstarIntegrationProperties

## Prefix
northstar.integration

## Fields
- apiBaseUrl (String) — bound from northstar.integration.api-base-url
- apiKey (String) — bound from northstar.integration.api-key; env-only in prod, no default
- connectTimeoutMs (int) — bound from northstar.integration.connect-timeout-ms; default 2000

## How enabled
@EnableConfigurationProperties(NorthstarIntegrationProperties.class) on a @Configuration class,
or @ConfigurationPropertiesScan on the main application class so it's picked up automatically.

## Scope
Pre-lab only.