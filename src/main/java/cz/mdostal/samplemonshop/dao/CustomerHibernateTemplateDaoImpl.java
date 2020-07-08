package cz.mdostal.samplemonshop.dao;

import cz.mdostal.samplemonshop.model.Customer;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CustomerHibernateTemplateDaoImpl extends HibernateDaoSupport implements CustomerDao {

    @Transactional
    @Override
    public Long createCustomer(Customer customer) {
        getHibernateTemplate().persist(customer);
        return customer.getId();
    }

    @Transactional
    @Override
    public Customer getCustomerById(Long id) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        List results = session.createQuery("select distinct cust " +
                        "               from Customer cust " +
                        "               left join fetch cust.orders ord " +
                        "               left join fetch ord.items it " +
                        "               where cust.id = " + id,
                Customer.class).getResultList();

        return results.size() > 0 ? (Customer) results.get(0) : null;
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        List results = session.createQuery("select distinct cust " +
                        "               from Customer cust " +
                        "               left join fetch cust.orders ord " +
                        "               left join fetch ord.items it " +
                        "               where cust.username = '" + username + "'",
                Customer.class).getResultList();

        return results.size() > 0 ? (Customer) results.get(0) : null;
    }

    @Transactional
    @Override
    public List getAllCustomers() {
        Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
        List results = session.createQuery("select distinct cust " +
                "               from Customer cust " +
                "               left join fetch cust.orders ord " +
                "               left join fetch ord.items it",
                Customer.class).getResultList();
        return results;
    }

    @Transactional
    @Override
    public void updateCustomer(Customer customer) {
        getHibernateTemplate().update(customer);
    }

    @Transactional
    @Override
    public void deleteCustomer(Customer customer) {
        getHibernateTemplate().delete(customer);
    }
}