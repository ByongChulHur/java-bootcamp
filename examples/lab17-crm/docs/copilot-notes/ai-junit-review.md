# AI JUnit Review Log

## lab17-001

Prompt: Requested tests for duplicate email, listAll, and correlation ID (fixtures: CUS-1001, CUS-1002)

Review:
Can every assert fail if production regresses? (Pass)
->All asserts check specific values

Shared CRM fixture IDs (not random PII)? (Pass)
It uses fixtures such as CUS-1001/1002

No phantom Spring/JPA imports? (Pass)
No phantom Spring or JPA imports

Independent @BeforeEach? (Pass)
@BeforeEach is independent

mvn -q test after edits? (Pass)
uplicateEmailThrowsConflict reused Customer.ravi() which obscured intent
