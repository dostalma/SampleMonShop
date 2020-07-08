package cz.mdostal.samplemonshop.controller;

import com.google.common.base.Preconditions;
import cz.mdostal.samplemonshop.facade.CustomerFacade;
import cz.mdostal.samplemonshop.model.Customer;
import cz.mdostal.samplemonshop.util.RestPreconditions;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private Logger logger = Logger.getLogger(CustomerController.class);

    @Autowired
    CustomerFacade customerFacade;

    /**
     * Request for base url returns a list of customers
     *
     * @return List of customers
     */
    @GetMapping
    public List<Customer> findAll() {
        logger.info("Request to retrieve all customers");
        List<Customer> list = customerFacade.getAllCustomers();
        return list;
    }

    /**
     * GET Request with an ID parameter returns a single customer if existing
     *
     * @param id of the customer to retrieve
     * @return Found customer if existing
     */
    @GetMapping(value = "/{id}")
    public Customer findById(@PathVariable("id") Long id) {
        logger.info("Request to retrieve a customer with id: " + id);
        return customerFacade.getCustomerById(id);
    }

    /**
     * POST Request to create a customer
     *
     * @param customer payload of a Customer object
     * @return List of customers
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Customer customer) {
        Preconditions.checkNotNull(customer);
        logger.info("Request to create a customer: " + customer.toString());
        return customerFacade.createCustomer(customer);
    }

    /**
     * PUT Request to update a customer
     *
     * @param id of Customer to be updated
     * @param customer payload of a Customer object
     */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Customer customer) {
        Preconditions.checkNotNull(customer);
        logger.info("Request to update a customer: " + customer.toString() + " with id: " + id);

        Customer existingCustomer = customerFacade.getCustomerById(id);
        RestPreconditions.checkNotNull(existingCustomer);

        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setUsername(customer.getUsername());
        existingCustomer.setOrders(customer.getOrders());

        customerFacade.updateCustomer(existingCustomer);
    }

    /**
     * DELETE Request to delete a customer
     *
     * @param id of customer to delete
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        logger.info("Request to delete a customer with id: " + id);
            customerFacade.deleteCustomer(customerFacade.getCustomerById(id));
    }
}
