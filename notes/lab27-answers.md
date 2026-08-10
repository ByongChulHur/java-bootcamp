## Reflection Questions

1. Putting debit, the force-fail check, credit, and log all inside one transfer() method wrapped in @Transactional was the biggest thing. If debit and credit were split into separate methods, a failure in between wouldn't have rolled back both together.

2. The forceFailRollsBack test is the proof. It saves MAIN's balance before calling transfer(), then checks the balance is exactly the same after it fails — not just "an exception was thrown," but the actual balance stayed unchanged.

3. Self-invocation was the hardest part. Calling this.transfer() from inside the same class skips the proxy, so @Transactional just gets silently ignored — no error, no warning, so you'd never know why it's not working unless you already knew about it.