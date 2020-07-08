package cz.mdostal.samplemonshop.dao;

import cz.mdostal.samplemonshop.model.Item;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
public interface ItemDao {

    public Long createItem(Item item);

    public Item getItemById(Long id);

    public List getAllItems();

    public void updateItem(Item item);

    public void deleteItem(Item item);
}