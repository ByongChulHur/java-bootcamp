Step 1
Type	Package	Fully qualified name
CustomerController	com.northstar.crm.controller	com.northstar.crm.controller.CustomerController
CustomerService	com.northstar.crm.service	com.northstar.crm.service.CustomerService
CustomerRepository	com.northstar.crm.repository	com.northstar.crm.repository.CustomerRepository
Customer	com.northstar.crm.entity	com.northstar.crm.entity.Customer
CustomerRequest	com.northstar.crm.dto	com.northstar.crm.dto.CustomerRequest
AppConfig	com.northstar.crm.config	com.northstar.crm.config.AppConfig
CustomerNotFoundException	com.northstar.crm.exception	com.northstar.crm.exception.CustomerNotFoundException


Step 3
src/main/java/com/northstar/crm/dto/CustomerRequest.java

Step 4

Bad -> Correct
com.Northstar.CRM.Service -> com.northstar.crm.service
package utils for customer business rules -> service or a focused domain package
customer_service.java -> CustomerService.java
package declaration does not match folders -> Make both paths identical