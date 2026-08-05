# Lab 18 — Stub vs Verify

## Stub (arrange)
when(repo.findById("CUS-1002")).thenReturn(raviProspect)

## Verify (assert collaboration)
verify(repo).findById("CUS-1002")

## One sentence — both roles
Stubbing sets up the behavior, while verification checks the interaction.

## Scope
Pre-lab only.