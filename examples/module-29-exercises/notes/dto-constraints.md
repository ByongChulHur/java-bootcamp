# Lab 29 — DTO Constraint Plan

| Field | Constraints |
| --- | --- |
| fullName | @NotBlank, @Size(min = 2, max = 100) |
| email | @NotBlank, @Email |
| status | @NotNull |

## How triggered

Validation is triggered by placing `@Valid` on the `@RequestBody` parameter
of the controller's create method:

```java
@PostMapping
public ResponseEntity<CustomerResponse> create(
        @Valid @RequestBody CustomerRequest request) {
    return service.create(request);
}
```

Without `@Valid`, the Bean Validation annotations on the DTO fields
(`@NotBlank`, `@Email`, `@Size`, `@NotNull`) are never evaluated —
Spring simply binds the JSON into the object and passes it straight
to the controller method body, regardless of whether the values are valid.

## Scope
Pre-lab only.