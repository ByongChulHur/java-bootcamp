# Lab 23 starter — timed path (~45 minutes)

**Theme:** Spring Boot — starters, application.yml, REST customers, health smoke

## Activity card

| | |
| --- | --- |
| **Objective** | Complete Boot YAML/API TODOs and prove health + CUS-1001 smoke |
| **Skills practiced** | Starters, CrmApplication, profiles teaser, Actuator health |
| **Expected outcome** | App on 8080 · health UP · ownership notes filled |
| **Estimated time** | ~45 minutes |
| **Files** | `examples/lab23-crm/` copied from this starter |

**Boilerplate reduced:** Initializr-style baseline + `// TODO` — keep constructor DI from Lab 22.

Pacing: [`../../PACING.md`](../../PACING.md) · Full steps: [`../LAB-23-GUIDE.md`](../LAB-23-GUIDE.md)

## Copy into your workspace

Do **not** grade work only inside the course `labs/` clone. Copy this `starter/` into your bootcamp examples tree as `lab23-crm`.

**Windows (PowerShell)** — from this lab folder:

```powershell
New-Item -ItemType Directory -Force -Path "$env:USERPROFILE\java-bootcamp\examples\lab23-crm" | Out-Null
Copy-Item -Recurse -Force ".\starter\*" "$env:USERPROFILE\java-bootcamp\examples\lab23-crm\"
cd $env:USERPROFILE\java-bootcamp\examples\lab23-crm
```

**macOS / Linux:**

```bash
mkdir -p ~/java-bootcamp/examples/lab23-crm
cp -R starter/. ~/java-bootcamp/examples/lab23-crm/
cd ~/java-bootcamp/examples/lab23-crm
```

Full GUIDE: [`../LAB-23-GUIDE.md`](../LAB-23-GUIDE.md)

## 45-minute checklist

- [ ] Confirm `pom.xml` has web + actuator + test starters
- [ ] Complete `application.yml` (name, port, Actuator exposure)
- [ ] Fill profile teasers `application-dev.yml` / `application-prod.yml`
- [ ] Implement create/get for CUS-1001 / CUS-1002 in service
- [ ] Smoke: health UP; note 3 auto-config gifts vs 3 ownership items in docs

## Smoke test

```bash
mvn -B test
mvn -B spring-boot:run
# then: curl http://localhost:8080/actuator/health
```

Evidence under `~/java-bootcamp/notes/screenshots/lab-23/` (redact secrets).

## Timed-path Pass criteria

| Criterion | Pass / Fail |
| --------- | ----------- |
| App starts on 8080 | Pass / Fail |
| /actuator/health returns UP | Pass / Fail |
| CUS-1001 create/get evidence (or IT green) | Pass / Fail |
| YAML + profile teasers present | Pass / Fail |
| Autoconfig vs ownership notes present | Pass / Fail |

## Runbook

1. `mvn spring-boot:run` (or run`-Dspring-boot.run.profiles=dev`)
2. Health check: `Invoke-RestMethod -Uri "http://localhost:8080/actuator/health"`
3. Create customer: POST `/api/customers` with `X-Correlation-Id: lab-request-001`
4. Get customer: GET `/api/customers/{id}`

## Security note

Unrestricted Actuator exposure (`health,info`) is lab-only. In production this
would be locked down further — Lab 26 covers real profile/secrets discipline.

## Test evidence

- `mvn test` → Tests run: 2 (contextLoads + createAndGetCus1001), run twice, both green
- Failure experiments: web starter removal, duplicate CUS-1001 create, health while stopped
