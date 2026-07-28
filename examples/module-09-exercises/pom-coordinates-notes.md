Question	Answer
groupId	com.northstar
artifactId	customer-service
version	0.1.0-SNAPSHOT
packaging	jar
GAV	com.northstar:customer-service:0.1.0-SNAPSHOT


Explain Snapshot
It is a version means the artifact is still under development and can be updated or change without a new release number.

Spot the mistakes

groupId set to com.example while the Java packages are com.northstar.crm;
The groupID should match with the package namespace.

artifactId set to CustomerService (PascalCase);
Maven convention expects lowercase and it should not be in  Pascal case it have to be in hypen-separated artifacts ID
like "customer-service"

omitting <packaging> and assuming WAR for a plain Java library/app JAR;
Maven should defaults to jar not WAR. Assuming WAT without declaring it is incorrect and could break the build output.

committing a different version on every laptop with no team agreement.
This breaks reproducibility, since teammates and CI would be resolving different, inconsistent artifacts instead of the same one.