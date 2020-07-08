package cz.mdostal.samplemonshop.dao;

import cz.mdostal.samplemonshop.model.Item;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ItemHibernateTemplateDaoImpl extends HibernateDaoSupport implements ItemDao {

    @Transactional
    @Override
    public Long createItem(Item item) {
        getHibernateTemplate().persist(item);
        return item.getId();
    }

    @Transactional
    @Override
    public Item getItemById(Long id) {
        return getHibernateTemplate().get(Item.class, id);
    }

    @Transactional
    @Override
    public List getAllItems() {
        return getHibernateTemplate().loadAll(Item.class);
    }

    @Transactional
    @Override
    public void updateItem(Item item) {
        getHibernateTemplate().update(item);
    }

    @Transactional
    @Override
    public void deleteItem(Item item) {
        getHibernateTemplate().delete(item);
    }
}
