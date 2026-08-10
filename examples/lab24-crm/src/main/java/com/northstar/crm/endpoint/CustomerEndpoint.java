package com.northstar.crm.endpoint;

import com.northstar.crm.service.CustomerService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

@Endpoint
public class CustomerEndpoint {
  private static final String NAMESPACE = "http://northstar.com/crm/customers";

  private final CustomerService customerService;
  private final CustomerSoapMapper mapper;

  public CustomerEndpoint(CustomerService customerService, CustomerSoapMapper mapper) {
    this.customerService = customerService;
    this.mapper = mapper;
  }

  @PayloadRoot(namespace = NAMESPACE, localPart = "GetCustomerRequest")
  @ResponsePayload
  public Element getCustomer(@RequestPayload Element request) {
    String customerId = mapper.customerIdFromGetRequest(request);
    return mapper.toGetCustomerResponse(customerService.get(customerId));
  }

  @PayloadRoot(namespace = NAMESPACE, localPart = "CreateCustomerRequest")
  @ResponsePayload
  public Element createCustomer(@RequestPayload Element request) {
    var customer = mapper.customerFromCreateRequest(request);
    var created = customerService.create(customer, "lab24-001");
    return mapper.toCreateCustomerResponse(created);
  }

  @PayloadRoot(namespace = NAMESPACE, localPart = "UpdateCustomerStatusRequest")
  @ResponsePayload
  public Element updateCustomerStatus(@RequestPayload Element request) {
    String customerId = mapper.customerIdFromUpdateRequest(request);
    String status = mapper.statusFromUpdateRequest(request);
    var updated = customerService.updateStatus(customerId, status);
    return mapper.toUpdateCustomerStatusResponse(updated);
  }

  @PayloadRoot(namespace = NAMESPACE, localPart = "ListCustomersRequest")
  @ResponsePayload
  public Element listCustomers(@RequestPayload Element request) {
    return mapper.toListCustomersResponse(customerService.list());
  }
}