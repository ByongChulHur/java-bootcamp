# Lab 13 starter — timed path (~45 minutes)

**Theme:** Contract-first SOAP (XSD + WSDL + samples) — no Java server

## Copy into your workspace

**Windows (PowerShell)** — from this lab folder:

```powershell
New-Item -ItemType Directory -Force -Path "$env:USERPROFILE\java-bootcamp\examples\lab13-crm" | Out-Null
Copy-Item -Recurse -Force ".\starter\*" "$env:USERPROFILE\java-bootcamp\examples\lab13-crm\"
cd $env:USERPROFILE\java-bootcamp\examples\lab13-crm
```

**macOS / Linux:**

```bash
mkdir -p ~/java-bootcamp/examples/lab13-crm
cp -R starter/. ~/java-bootcamp/examples/lab13-crm/
cd ~/java-bootcamp/examples/lab13-crm
```

Full GUIDE: [`../LAB-13-GUIDE.md`](../LAB-13-GUIDE.md)

## 45-minute checklist

- [ ] Complete `contracts/customer.xsd` TODOs (types + request/response elements)
- [ ] Complete `contracts/CustomerService.wsdl` (3 ops, document/literal, placeholder address)
- [ ] Fill success + fault samples under `samples/`
- [ ] Finish operation-matrix + soap-design-notes TODOs
- [ ] Smoke-validate well-formed XML

## Smoke test

**Windows PowerShell:**

```powershell
Get-ChildItem contracts,samples -Filter *.xml | ForEach-Object { [xml](Get-Content -Raw $_.FullName) | Out-Null; "OK $($_.Name)" }
Get-ChildItem contracts -Filter *.xsd | ForEach-Object { [xml](Get-Content -Raw $_.FullName) | Out-Null; "OK $($_.Name)" }
Get-ChildItem contracts -Filter *.wsdl | ForEach-Object { [xml](Get-Content -Raw $_.FullName) | Out-Null; "OK $($_.Name)" }
```

**macOS / Linux** (if `xmllint` available):

```bash
xmllint --noout contracts/* samples/*
```

Do **not** start a server on port 8080.

## Timed-path Pass criteria

| Criterion | Pass / Fail |
| --------- | ----------- |
| XSD + WSDL well-formed; schemaLocation beside WSDL | Pass  |
| Three operations + success/fault samples | Pass  |
| Docs name CUS-1001 / CUS-1002 / lab-request-001 | Pass |
| Placeholder URL documented as not live | Pass  |

Continue remaining GUIDE steps as homework / full path if needed.

| # | Confirm | Your notes |
| - | ------- |------|
| 1 | Namespace URI published | Pass |
| 2 | WSDL location placeholder documented | Pass |
| 3 | Three operations named and described | Pass |
| 4 | Sample success envelopes for CUS-1001 / CUS-1002 | Pass |
| 5 | Fault examples for not-found and validation | Pass |
| 6 | Correlation ID convention (`lab-request-001` style) | Pass |
| 7 | Explicit note: implementation arrives in Lab 24 | Fail |
| 8 | Optional: screenshot of VS Code XSD/WSDL outline | Pass (or skip if no screenshot) |