package cz.mdostal.samplemonshop.controller;

import com.google.common.base.Preconditions;
import cz.mdostal.samplemonshop.facade.CustomerFacade;
import cz.mdostal.samplemonshop.facade.ItemFacade;
import cz.mdostal.samplemonshop.model.Customer;
import cz.mdostal.samplemonshop.model.Item;
import cz.mdostal.samplemonshop.util.RestPreconditions;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private Logger logger = Logger.getLogger(ItemController.class);

    @Autowired
    ItemFacade itemFacade;

    /**
     * Request for base url returns a list of items
     *
     * @return List of items
     */
    @GetMapping
    public List<Item> findAll() {
        logger.info("Request to retrieve all items");
        List<Item> list = itemFacade.getAllItems();
        return list;
    }

    /**
     * GET Request with an ID parameter returns a single item if existing
     *
     * @param id of the item to retrieve
     * @return Found item if existing
     */
    @GetMapping(value = "/{id}")
    public Item findById(@PathVariable("id") Long id) {
        logger.info("Request to retrieve an item with id: " + id);
        return itemFacade.getItemById(id);
    }

    /**
     * POST Request to create a item
     *
     * @param item payload of a Item object
     * @return List of items
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody Item item) {
        Preconditions.checkNotNull(item);
        logger.info("Request to create an item: " + item.toString());
        return itemFacade.createItem(item);
    }


    /**
     * PUT Request to update an item
     *
     * @param id of Item to be updated
     * @param item payload of an Item object
     */
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Item item) {
        Preconditions.checkNotNull(item);
        logger.info("Request to update an item: " + item.toString() + "with id: " + id);

        Item existingItem = itemFacade.getItemById(id);
        RestPreconditions.checkNotNull(existingItem);

        existingItem.setName(item.getName());
        existingItem.setPrice(item.getPrice());

        itemFacade.updateItem(existingItem);
    }

    /**
     * DELETE Request to delete an item
     *
     * @param id of item to delete
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        logger.info("Request to delete an item with id: " + id);
        itemFacade.deleteItem(itemFacade.getItemById(id));
    }
}
