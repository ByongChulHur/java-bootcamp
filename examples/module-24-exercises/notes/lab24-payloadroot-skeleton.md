# Lab 24 — PayloadRoot Skeleton

## Class annotation
@Endpoint

## @PayloadRoot localPart
"GetCustomerRequest" — must match customer.xsd targetNamespace + element name exactly.

## Method inputs/outputs
Input: @RequestPayload GetCustomerRequest req
Output: @ResponsePayload GetCustomerResponse

## Delegation line (words)
map request → customerService.get(id) → map response

## Scope
Pre-lab only.