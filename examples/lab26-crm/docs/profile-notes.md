## Step 5 — Profile activation (two ways)

### Method 1: CLI flag
Command: `mvn spring-boot:run "-Dspring-boot.run.profiles=dev"`
Banner: `The following 1 profile is active: "dev"`

### Method 2: Environment variable
Command: `$env:SPRING_PROFILES_ACTIVE="test"` then `mvn spring-boot:run` (no -D flag)
Banner: `The following 1 profile is active: "test"`
Cleanup: `Remove-Item Env:SPRING_PROFILES_ACTIVE`

1. **Override winner:** CLI (`-D`) beats environment variables, which beat
   `application-{profile}.yml`, which beats base `application.yml`, which beats
   code defaults. Verified two ways: experiment 2 (CLI `dev` won over env `test`)
   and ProfileBindingTest (`connectTimeoutMs == 100` from `application-test.yml`,
   overriding the base value of `2000`).

2. **Prod fail-fast evidence:** `mvn spring-boot:run "-Dspring-boot.run.profiles=prod"`
   with no `DB_PASSWORD` / `NORTHSTAR_API_KEY` set → banner confirms
   `The following 1 profile is active: "prod"`, then `BUILD FAILURE`. The app
   refused to start and never connected with a blank password.


## Step 8 — Test + smoke evidence

`mvn -B test "-Dspring.profiles.active=test"` → Tests run: 1, Failures: 0, BUILD SUCCESS (twice)

ProfileBindingTest verifies:
- `connectTimeoutMs == 100` (application-test.yml beats application.yml's 2000)
- `apiBaseUrl == "http://localhost:9090"` (base YAML applies when profile doesn't override)
- `CUS-1001` → "Amina Khan" (CRM fixtures intact after config changes)

Dev smoke: GET /api/customers/CUS-1001 with `X-Correlation-Id: lab-request-001` → 200 OK

## Step 9 — Failure experiments + secret hygiene

| # | Experiment | Observed |
| - | ---------- | -------- |
| 1 | `prod` without DB_PASSWORD / NORTHSTAR_API_KEY | BUILD FAILURE — `Failed to load driver class org.postgresql.Driver`; app never started |
| 2 | Env `SPRING_PROFILES_ACTIVE=test` + CLI `-Dspring-boot.run.profiles=dev` | Banner showed `dev` — CLI beats env var |
| 4 | Temporarily set `password: hunter2` in application-prod.yml | Caught by `git status --short` + `git diff` before commit; reverted immediately |
| 5 | No profile flag at all | Started on `dev` via `spring.profiles.default: dev` in base YAML |
