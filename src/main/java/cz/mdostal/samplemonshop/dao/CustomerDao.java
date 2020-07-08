package cz.mdostal.samplemonshop.dao;

import cz.mdostal.samplemonshop.model.Customer;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
public interface CustomerDao {

    public Long createCustomer(Customer customer);

    public Customer getCustomerById(Long id);

    public Customer getCustomerByUsername(String username);

    public List getAllCustomers();

    public void updateCustomer(Customer customer);

    public void deleteCustomer(Customer customer);
}
