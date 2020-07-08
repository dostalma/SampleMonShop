package cz.mdostal.samplemonshop.facade;

import cz.mdostal.samplemonshop.model.Customer;

import java.util.List;

public interface CustomerFacade {

    public Long createCustomer(Customer customer);

    public Customer getCustomerById(Long id);

    public Customer getCustomerByUsername(String username);

    public List getAllCustomers();

    public void updateCustomer(Customer customer);

    public void deleteCustomer(Customer customer);

}

