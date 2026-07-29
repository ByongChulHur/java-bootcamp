Step 1

The service method has inline System.out.println statements and
direct emailClient.send calls inside its business logic. This means
testing "activate" will trigger a real console print and a real
email send, which makes the test slow and messy.

Step 2

interface CustomerNotifier { void notifyActivated(String id); }

Step 3

Naming the collaborator ahead of time prevents Copilot from
burying I/O inside the service.

Step 4

Mark: do not implement Spring events or Kafka yet — prep sketch only.