# Lab 28 — SecurityFilterChain Sketch

## Session policy

STATELESS — no `HttpSession` is created or used. The server does not remember prior requests. Every request must carry its own valid JWT in the `Authorization: Bearer <token>` header, and that token alone is what the server validates on each call.

## Login matcher

`/api/auth/login` → `permitAll()`

This route must stay open, because a client cannot possess a JWT before it has logged in. Requiring authentication here would create a chicken-and-egg problem — you cannot present a token to get a token.

## Customers matcher + roles

`/api/customers/**` → `hasAnyRole("AGENT", "ADMIN")`

Both agent1 (AGENT) and admin1 (ADMIN) can read/write customer records such as CUS-1001 (Amina Khan) and CUS-1002 (Ravi Singh). A valid JWT is required (authentication), and the token's role must be AGENT or ADMIN (authorization).

## Admin matcher + roles

`/api/admin/**` → `hasRole("ADMIN")`

Only admin1 (ADMIN) may access these routes. agent1 presenting a valid JWT here still receives 403 Forbidden, because authentication succeeds but authorization fails — the AGENT role does not satisfy `hasRole("ADMIN")`.

## Other routes

`anyRequest()` → `authenticated()`

Any route not explicitly matched above still requires a valid JWT by default (default deny). New controllers added later do not ship open by accident.

## Filter ordering

The custom `JwtAuthenticationFilter` is registered with `addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)`, so the Bearer token is read and validated before Spring's built-in username/password filter runs.

## Scope

Pre-lab only.