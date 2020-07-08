package cz.mdostal.samplemonshop.facade;

import cz.mdostal.samplemonshop.model.Item;

import java.util.List;

public interface ItemFacade {

    public Long createItem(Item item);

    public Item getItemById(Long id);

    public List getAllItems();

    public void updateItem(Item item);

    public void deleteItem(Item item);

    public void initializeTestData();
}
