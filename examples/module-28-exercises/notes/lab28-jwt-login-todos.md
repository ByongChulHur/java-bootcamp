# Lab 28 — JWT Login TODOs

## Login path + body
POST /api/auth/login
Body: { "username": "agent1", "password": "..." }

## Token response
{ "accessToken": "<jwt>", "tokenType": "Bearer" }
JwtService: issueToken(username, role) / parseSubject(token) / parseRole(token)

## Bearer header form
Authorization: Bearer <accessToken>
Filter checks header starts with "Bearer " then strips it before parsing.

## Lab users/roles
agent1 → AGENT
admin1 → ADMIN

## Secret handling
Env var JWT_SECRET → northstar.security.jwt-secret
.env.example: JWT_SECRET=changeme-placeholder (never a real secret)

## Scope
Pre-lab only. No real secrets.