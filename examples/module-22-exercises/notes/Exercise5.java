interface CustomerRepository {
    String findName(String id);
}
class InMemoryCustomerRepository implements CustomerRepository {
    public String findName(String id) {
// TODO: return "Amina Khan" for "CUS-1001", else "UNKNOWN"
        if ("CUS-1001".equals(id)) return _____;
        return "UNKNOWN";
    }
}
class CustomerService {
    private final CustomerRepository _____;  // TODO: field name repo
    CustomerService(CustomerRepository repo) {
        this._____ = _____;  // TODO: assign
    }
}