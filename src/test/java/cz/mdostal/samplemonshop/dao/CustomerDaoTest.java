package cz.mdostal.samplemonshop.dao;

import cz.mdostal.samplemonshop.configuration.AppConfiguration;
import cz.mdostal.samplemonshop.configuration.PersistenceConfiguration;
import cz.mdostal.samplemonshop.model.Customer;
import cz.mdostal.samplemonshop.model.Item;
import cz.mdostal.samplemonshop.model.Order;
import cz.mdostal.samplemonshop.util.TestDataCreator;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { PersistenceConfiguration.class, AppConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class CustomerDaoTest {

    private Logger logger = LogManager.getLogger(CustomerDaoTest.class);

    @Autowired
    private CustomerDao customerDao;

    @Before
    public  void init () {
        TestDataCreator creator = new TestDataCreator();
        creator.setCustomerDao(customerDao);
        creator.generateData();
    }

    @Test
    public void testGetAllCustomers() {
        List<Customer> customers = customerDao.getAllCustomers();

        assertNotNull(customers);
        int size1 = customers.size();

        assertTrue(size1 > 0);

        Customer cust = new Customer("Jane", "Doe", "jane.doe@mail.com");
        customerDao.createCustomer(cust);

        customers = customerDao.getAllCustomers();
        assertNotNull(customers);
        assertTrue(customers.size() > size1);
    }

    @Test
    public void testGetCustomerByUsername() {
        Customer cust = customerDao.getCustomerByUsername("john.doe@mail.com");
        assertNotNull(cust);
        assertEquals("John", cust.getFirstName());
        assertEquals("Doe", cust.getLastName());
    }

    @Test
    public void testGetCustomerById() {
        Customer cust = customerDao.getCustomerById(1l);
        assertNotNull(cust);
        assertEquals("John", cust.getFirstName());
        assertEquals("Doe", cust.getLastName());
    }

    @Test
    public void testCreateCustomer()
    {
        Item item = new Item();
        item.setName("Cucumber");
        item.setPrice(0.79);

        Order order = new Order(Date.valueOf("2020-05-05"));
        order.getItems().add(item);

        Customer cust = new Customer("Jim", "Doe", "jim.doe@mail.com");
        cust.getOrders().add(order);

        Long key = customerDao.createCustomer(cust);

        Customer cust2 = customerDao.getCustomerById(key);
        assertNotNull(cust2);

        assertEquals("Jim", cust.getFirstName());
        assertEquals("Doe", cust.getLastName());
        assertEquals("jim.doe@mail.com", cust.getUsername());

        assertNotNull(cust2.getOrders());
        assertEquals(1, cust2.getOrders().size());

        Order order2 = cust2.getOrders().get(0);
        assertNotNull(order2);
        assertEquals("2020-05-05", order2.getDate().toString());
        assertNotNull(order2.getItems());
        assertEquals(1, order2.getItems().size());

        Item item2 = order2.getItems().get(0);
        assertNotNull(item2);
        assertEquals("Cucumber", item2.getName());
        assertEquals(Double.valueOf(0.79), item2.getPrice());
    }

    @Test
    public void testUpdateCustomer() {
        Customer cust = customerDao.getCustomerByUsername("john.doe@mail.com");
        assertNotNull(cust);
        cust.setFirstName("Jackson");
        customerDao.updateCustomer(cust);

        Customer cust2 = customerDao.getCustomerByUsername("john.doe@mail.com");
        assertNotNull(cust2);
        assertEquals("Jackson", cust2.getFirstName());
    }

    @Test
    public void testDeleteCustomer() {
        Customer cust = customerDao.getCustomerByUsername("john.doe@mail.com");
        assertNotNull(cust);
        customerDao.deleteCustomer(cust);

        Customer cust2 = customerDao.getCustomerByUsername("john.doe@mail.com");
        assertNull(cust2);
    }
}
