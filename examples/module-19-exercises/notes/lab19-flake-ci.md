# Lab 19 — Flake and CI Note

## Flake sources
1. Timing/animation. There can be gap between the moment an element exists in the DOM and the moment it is actually clickable.
If a test only checks the presence of the button only instead of the ability, then it may click before the animation finishes.
2. Multiple tests sharing the same CRM data. For example, if two tests both touch the Ravi (CUS-1002) row, one test's status
change can affect the other test's expected result. The outcome then depends on execution order, which can change between runs.

## Mitigation
Use explicit waits instead of Thread.sleep(). Give each test its
own isolated data. Use data-testid locators.

## CI constraint
Headless Chrome and WebDriverManager driver version must match the
CI agent's actual Chrome version, or tests fail only in CI.

## Scope
Pre-lab only.