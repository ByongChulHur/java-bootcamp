# Lab 29 — GlobalExceptionHandler TODOs

## Advice annotation

Use `@RestControllerAdvice` on one class called `GlobalExceptionHandler`.
It applies to all controllers and always returns JSON.

## Handlers (list)

- `handleMethodArgumentNotValid` → 400
  When `@Valid` fails (blank field, bad email format, etc).

- `handleNotFound` → 404
  When a lookup finds nothing (e.g. GET a customer that doesn't exist).

- `handleDuplicate` → 409
  When trying to create a customer ID that already exists.

- `handleIllegalTransition` → 400/422
  When a status change breaks the allowed rules.

- `handleGeneric` → 500
  Catch-all for anything unexpected.

## 500 rule

Never send a stack trace, class name, or SQL text to the client.
Just give a safe generic message. Log the real details on the server only.

## Scope
Planning only, not real code yet.