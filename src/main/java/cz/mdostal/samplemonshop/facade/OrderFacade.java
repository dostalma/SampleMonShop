package cz.mdostal.samplemonshop.facade;

import cz.mdostal.samplemonshop.model.Order;

import java.util.List;

public interface OrderFacade {

    public Long createOrder(Order order);

    public Order getOrderById(Long id);

    public List getAllOrders();

    public void updateOrder(Order order);

    public void deleteOrder(Order order);

}
