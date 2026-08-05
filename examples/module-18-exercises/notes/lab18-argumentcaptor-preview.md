# Lab 18 — ArgumentCaptor Preview

## Declare
ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);


## Verify + capture
verify(repository).save(captor.capture());

## Assert
assertEquals(expectedCustomer, captor.getValue());

## Scope
Pre-lab only.