# Lab 25 — Package Sketch

## Tree
com.northstar.crm
api/CustomerController
service/CustomerService
repository/CustomerRepository
repository/InMemoryCustomerRepository
model/Customer
CrmApplication

## Where does the controller live?
CustomerController는 `com.northstar.crm.api` 패키지 아래에 위치한다.
api 패키지는 HTTP 요청/응답을 다루는 진입점 역할만 하며, 비즈니스 로직이나
데이터 접근 코드를 포함하지 않는다.

## Where does InMemoryCustomerRepository live?
InMemoryCustomerRepository는 `com.northstar.crm.repository` 패키지 아래에,
CustomerRepository 인터페이스와 같은 패키지에 위치한다.
인터페이스와 구현체를 같은 패키지에 두어, 나중에 JPA 기반 구현체로
교체할 때도 패키지 구조를 바꿀 필요가 없게 한다.

## Scope
Pre-lab only.