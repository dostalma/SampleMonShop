package cz.mdostal.samplemonshop.util;

import cz.mdostal.samplemonshop.dao.CustomerDao;
import cz.mdostal.samplemonshop.model.Customer;
import cz.mdostal.samplemonshop.model.Item;
import cz.mdostal.samplemonshop.model.Order;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Date;

public class TestDataCreator {

    private Logger logger = LogManager.getLogger(TestDataCreator.class);

    private CustomerDao customerDao;

    /**
     * Generates some sample test data
     * First requires a CustomerDao to be set in the instance
     */
    public void generateData() {
        Order order = new Order(Date.valueOf("2020-01-01"));
        Customer customer = new Customer("John", "Doe", "john.doe@mail.com");
        customer.getOrders().add(order);

        Item item1 = new Item("Red apple", 1.99);
        Item item2 = new Item("Green apple", 1.49);
        Item item3 = new Item("Carrot", 1.19);
        Item item4 = new Item("Banana", 2.39);
        Item item5 = new Item("Orange", 3.49);

        order.getItems().add(item1);
        order.getItems().add(item2);
        order.getItems().add(item3);
        order.getItems().add(item4);
        order.getItems().add(item5);

        customerDao.createCustomer(customer);
        logger.info("Test data imported");
        System.out.println("Finished");
    }


    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }
}