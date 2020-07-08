package cz.mdostal.samplemonshop.controller;

import cz.mdostal.samplemonshop.configuration.AppConfiguration;
import cz.mdostal.samplemonshop.configuration.WebConfiguration;
import cz.mdostal.samplemonshop.dao.CustomerDao;
import cz.mdostal.samplemonshop.facade.CustomerFacade;
import cz.mdostal.samplemonshop.util.TestDataCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfiguration.class, AppConfiguration.class})
@WebAppConfiguration
public class SmokeTest {

    private

    @Autowired
    CustomerController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

}
