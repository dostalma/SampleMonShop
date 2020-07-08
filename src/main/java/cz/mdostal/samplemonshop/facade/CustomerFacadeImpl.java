package cz.mdostal.samplemonshop.facade;

import com.google.common.base.Optional;
import cz.mdostal.samplemonshop.dao.CustomerDao;
import cz.mdostal.samplemonshop.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerFacadeImpl implements CustomerFacade {

    @Autowired
    CustomerDao customerDao;

    @Override
    public Long createCustomer(Customer customer) {
        return customerDao.createCustomer(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerDao.getCustomerById(id);
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return customerDao.getCustomerByUsername(username);
    }

    @Override
    public List getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(Customer customer) {
        customerDao.deleteCustomer(customer);
    }
}