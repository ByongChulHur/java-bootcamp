# Lab 23 — REST Smoke Plan

## Start command
mvn spring-boot:run

## Health check
GET /actuator/health → expect UP

## CUS-1001 steps
POST /api/customers (Amina Khan, ACTIVE) → 201
GET /api/customers/CUS-1001 → 200

## CUS-1002 steps
POST /api/customers (Ravi Singh, PROSPECT) → 201
GET /api/customers/CUS-1002 → 200

## Correlation header/id
X-Correlation-Id: lab-request-001

## Scope
Pre-lab only.