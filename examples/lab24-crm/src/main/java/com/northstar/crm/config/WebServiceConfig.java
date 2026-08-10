package com.northstar.crm.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;
import java.util.Properties;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

  @Bean
  public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
          ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean<>(servlet, "/ws/*");
  }

  @Bean(name = "customers")
  public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema customersSchema) {
    DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
    wsdl.setPortTypeName("CustomersPort");
    wsdl.setLocationUri("/ws");
    wsdl.setTargetNamespace("http://northstar.com/crm/customers");
    wsdl.setSchema(customersSchema);
    return wsdl;
  }

  @Bean
  public XsdSchema customersSchema() {
    return new SimpleXsdSchema(new ClassPathResource("customer.xsd"));
  }

  @Bean
  public SoapFaultMappingExceptionResolver exceptionResolver() {
    SoapFaultMappingExceptionResolver resolver = new SoapFaultMappingExceptionResolver();

    SoapFaultDefinition defaultFault = new SoapFaultDefinition();
    defaultFault.setFaultCode(SoapFaultDefinition.SERVER);
    resolver.setDefaultFault(defaultFault);

    Properties mappings = new Properties();
    mappings.setProperty("java.lang.IllegalArgumentException", "CLIENT,Customer not found");
    resolver.setExceptionMappings(mappings);
    resolver.setOrder(1);
    return resolver;
  }

  @Bean
  public SimplePasswordValidationCallbackHandler callbackHandler() {
    SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
    Properties users = new Properties();
    users.setProperty("crm-partner", "lab24-shared-secret"); // lab-only, never real prod
    handler.setUsers(users);
    return handler;
  }

  @Bean
  public Wss4jSecurityInterceptor securityInterceptor() {
    Wss4jSecurityInterceptor interceptor = new Wss4jSecurityInterceptor();
    interceptor.setValidationActions("UsernameToken");
    interceptor.setValidationCallbackHandler(callbackHandler());
    return interceptor;
  }

  @Override
  public void addInterceptors(List<EndpointInterceptor> interceptors) {
    interceptors.add(securityInterceptor());
  }
}