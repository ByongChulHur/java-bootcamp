package com.northstar.crm.endpoint;

import com.northstar.crm.model.Customer;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Collection;

@Component
public class CustomerSoapMapper {

  private static final String NS = "http://northstar.com/crm/customers";

  private Document newDocument() {
    try {
      return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    } catch (ParserConfigurationException e) {
      throw new IllegalStateException("Cannot create XML document", e);
    }
  }

  private String childText(Element parent, String localName) {
    return parent.getElementsByTagNameNS(NS, localName).item(0).getTextContent();
  }

  private void appendChild(Document doc, Element parent, String localName, String value) {
    Element el = doc.createElementNS(NS, localName);
    el.setTextContent(value);
    parent.appendChild(el);
  }

  // ---- GetCustomer ----

  public String customerIdFromGetRequest(Element request) {
    return childText(request, "customerId");
  }

  public Element toGetCustomerResponse(Customer customer) {
    Document doc = newDocument();
    Element response = doc.createElementNS(NS, "GetCustomerResponse");
    doc.appendChild(response);
    appendChild(doc, response, "customerId", customer.getId());
    appendChild(doc, response, "name", customer.getName());
    appendChild(doc, response, "email", customer.getEmail());
    appendChild(doc, response, "status", customer.getStatus());
    return response;
  }

  // ---- CreateCustomer ----

  public Customer customerFromCreateRequest(Element request) {
    return new Customer(
            childText(request, "customerId"),
            childText(request, "name"),
            childText(request, "email"),
            childText(request, "status"));
  }

  public Element toCreateCustomerResponse(Customer customer) {
    Document doc = newDocument();
    Element response = doc.createElementNS(NS, "CreateCustomerResponse");
    doc.appendChild(response);
    appendChild(doc, response, "customerId", customer.getId());
    appendChild(doc, response, "name", customer.getName());
    appendChild(doc, response, "email", customer.getEmail());
    appendChild(doc, response, "status", customer.getStatus());
    return response;
  }

  // ---- UpdateCustomerStatus ----

  public String customerIdFromUpdateRequest(Element request) {
    return childText(request, "customerId");
  }

  public String statusFromUpdateRequest(Element request) {
    return childText(request, "status");
  }

  public Element toUpdateCustomerStatusResponse(Customer customer) {
    Document doc = newDocument();
    Element response = doc.createElementNS(NS, "UpdateCustomerStatusResponse");
    doc.appendChild(response);
    appendChild(doc, response, "customerId", customer.getId());
    appendChild(doc, response, "name", customer.getName());
    appendChild(doc, response, "email", customer.getEmail());
    appendChild(doc, response, "status", customer.getStatus());
    return response;
  }

  // ---- ListCustomers ----

  public Element toListCustomersResponse(Collection<Customer> customers) {
    Document doc = newDocument();
    Element response = doc.createElementNS(NS, "ListCustomersResponse");
    doc.appendChild(response);
    for (Customer c : customers) {
      Element item = doc.createElementNS(NS, "customer");
      appendChild(doc, item, "customerId", c.getId());
      appendChild(doc, item, "name", c.getName());
      appendChild(doc, item, "email", c.getEmail());
      appendChild(doc, item, "status", c.getStatus());
      response.appendChild(item);
    }
    return response;
  }
}