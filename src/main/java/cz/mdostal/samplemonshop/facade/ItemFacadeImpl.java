package cz.mdostal.samplemonshop.facade;

import cz.mdostal.samplemonshop.dao.CustomerDao;
import cz.mdostal.samplemonshop.dao.ItemDao;
import cz.mdostal.samplemonshop.model.Item;
import cz.mdostal.samplemonshop.util.TestDataCreator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemFacadeImpl implements ItemFacade {

    @Autowired
    ItemDao itemDao;

    @Autowired
    CustomerDao customerDao;

    @Override
    public Long createItem(Item item) {
        return itemDao.createItem(item);
    }

    @Override
    public Item getItemById(Long id) {
        return itemDao.getItemById(id);
    }

    @Override
    public List getAllItems() {
        return itemDao.getAllItems();
    }

    @Override
    public void updateItem(Item item) {
        itemDao.updateItem(item);
    }

    @Override
    public void deleteItem(Item item) {
        itemDao.deleteItem(item);
    }

    @Override
    public void initializeTestData() {
        TestDataCreator creator = new TestDataCreator();
        creator.setCustomerDao(customerDao);
        creator.generateData();
    }
}
