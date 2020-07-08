package cz.mdostal.samplemonshop.configuration;

import cz.mdostal.samplemonshop.dao.*;
import cz.mdostal.samplemonshop.facade.*;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Base configuration for application beans
 */
@Configuration
public class AppConfiguration {

    @Autowired
    private Environment env;

    @Autowired
    private ApplicationContext appContext;

    @Bean(name="itemFacade")
    public ItemFacade itemFacade() {
        return new ItemFacadeImpl();
    }

    @Bean(name="orderFacade")
    public OrderFacade orderFacade() {
        return new OrderFacadeImpl();
    }

    @Bean(name="customerFacade")
    public CustomerFacade customerFacade() {
        return new CustomerFacadeImpl();
    }

    @Bean(name="itemDao")
    public ItemDao itemDao() {
        ItemHibernateTemplateDaoImpl dao = new ItemHibernateTemplateDaoImpl();
        dao.setSessionFactory((SessionFactory) appContext.getBean("sessionFactory") );
        return dao;
    }

    @Bean(name="orderDao")
    public OrderDao orderDao() {
        OrderHibernateTemplateDaoImpl dao = new OrderHibernateTemplateDaoImpl();
        dao.setSessionFactory((SessionFactory) appContext.getBean("sessionFactory") );
        return dao;
    }

    @Bean(name="customerDao")
    public CustomerDao customerDao() {
        CustomerHibernateTemplateDaoImpl dao = new CustomerHibernateTemplateDaoImpl();
        dao.setSessionFactory((SessionFactory) appContext.getBean("sessionFactory") );
        return dao;
    }

    // @TODO fix liquibase integration
    //@Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("project_model.xml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }

    private DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.pass"));

        return dataSource;
    }
}
