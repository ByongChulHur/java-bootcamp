package com.northstar.crm;

import com.northstar.crm.api.ApiResult;
import com.northstar.crm.api.CustomerApiFacade;
import com.northstar.crm.dto.CustomerRequestDTO;
import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.repository.CustomerRepository;
import com.northstar.crm.repository.InMemoryCustomerRepository;
import com.northstar.crm.service.CustomerService;
import com.northstar.crm.service.CustomerValidator;
import com.northstar.crm.service.DefaultCustomerService;

public class Main {
    public static void main(String[] args) {
        CustomerRepository repo = new InMemoryCustomerRepository();
        CustomerValidator validator = new CustomerValidator(repo);
        CustomerService service = new DefaultCustomerService(repo, validator);
        CustomerApiFacade api = new CustomerApiFacade(service);

        service.addCustomer(Customer.amina());
        service.addCustomer(Customer.ravi());

        CustomerRequestDTO badRequest = new CustomerRequestDTO(  "CUS-2001", "Test User", "not-an-email", "PROSPECT");
        ApiResult result = api.create(badRequest, "lab-request-001");

        if (result instanceof ApiResult.Fail fail) {
            System.out.println(fail.error().toJson());
        }
        ApiResult result2 = api.getById("CUS-9999", "lab-request-001");

        if (result2 instanceof ApiResult.Fail fail2) {
            System.out.println(fail2.error().toJson());
        }

        ApiResult result3 = api.changeStatus("CUS-1001", CustomerStatus.PROSPECT, "lab-request-001");

        if (result3 instanceof ApiResult.Fail fail3) {
            System.out.println(fail3.error().toJson());
        }

        service.findById("CUS-1001").ifPresent(c ->
                System.out.println("CUS-1001 status after failed transition: " + c.getStatus())
        );


    }
}
