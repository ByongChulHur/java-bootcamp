# Lab 19 — regression notes

## TODO

- Chrome / Chromium version used
  Chrome 150.0.7871.187, resolved via WebDriverManager to
  chromedriver 150.0.7871.124 (headless mode, --window-size=1280,900).

CustomerApiIT have 3 tests, all pass:
- getAminaReturns200: GET CUS-1001 return 200.
- createEchoesCorrelationHeader: POST with header lab-request-001,
  server return 201 with same header echo back.
- missingCustomerReturns404: GET CUS-9999 return 404.

CustomerUiIT have 2 tests, all pass. Use headless Chrome and Page
Object CustomerFormPage.
- createCustomerViaUi: fill form CUS-2001, submit, result show
  CUS-2001.
- Screenshot saved at notes/screenshots/lab-19

## Negative cases
Blank name: I added check in CustomerService, Controller now return
400, UI show "Error: full name is required".

Missing customer: 404 already confirmed by missingCustomerReturns404.

Broken locator (on purpose): changed customer-id to wrong-id, both UI
test fail with timeout, build show FAILURE. After fix, build green
again.

## Scope: unit vs IT vs UI
Unit test only test one class alone, fastest.
Integration test (CustomerApiIT) start real server, send real HTTP,
no browser.
UI test (CustomerUiIT) use real headless browser, slowest but most
confidence.

## CI browser strategy
Use WebDriverManager instead of commit driver file, because Chrome
version different on each machine. Headless mode needed for CI.
Explicit wait used everywhere, no Thread.sleep, implicit wait set to 0.

## Regression checks performed
All mvn clean verify, all tests pass, screenshots saved. All lab 19 TODOs done.
