package com.northstar.crm.endpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.ws.test.server.ResponseMatchers;
import org.springframework.xml.transform.StringSource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class CustomerEndpointTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient client;

    @BeforeEach
    void setUp() {
        client = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    void getCustomerReturnsCus1001() throws Exception {
        StringSource request = new StringSource(
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
                        + "xmlns:cus=\"http://northstar.com/crm/customers\" "
                        + "xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">"
                        + "<soapenv:Header>"
                        + "<wsse:Security>"
                        + "<wsse:UsernameToken>"
                        + "<wsse:Username>crm-partner</wsse:Username>"
                        + "<wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">lab24-shared-secret</wsse:Password>"
                        + "</wsse:UsernameToken>"
                        + "</wsse:Security>"
                        + "</soapenv:Header>"
                        + "<soapenv:Body>"
                        + "<cus:GetCustomerRequest><cus:customerId>CUS-1001</cus:customerId></cus:GetCustomerRequest>"
                        + "</soapenv:Body>"
                        + "</soapenv:Envelope>");

        StringSource expected = new StringSource(
                "<cus:GetCustomerResponse xmlns:cus=\"http://northstar.com/crm/customers\">"
                        + "<cus:customerId>CUS-1001</cus:customerId>"
                        + "<cus:name>Amina Khan</cus:name>"
                        + "<cus:email>amina.khan@example.com</cus:email>"
                        + "<cus:status>ACTIVE</cus:status>"
                        + "</cus:GetCustomerResponse>");

        client.sendRequest(RequestCreators.withSoapEnvelope(request))
                .andExpect(ResponseMatchers.payload(expected));
    }
}