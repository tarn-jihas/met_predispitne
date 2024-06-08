package service;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;
import model.Order;
import model.Product;
import repository.IOrderRepository;

import java.util.List;
import java.util.UUID;

public class OrderService implements IOrderService<Order> {
  private final IOrderRepository<Order> orderRepository;

  public OrderService(IOrderRepository<Order> orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public void createOrder(List<Product> products, UUID userId, String address) throws FileWriteException {
    Order newOrder = new Order(userId);
    newOrder.setProductList(products);
    newOrder.setAddress(address);
    orderRepository.save(newOrder);
  }

  @Override
  public void updateOrder(Order order) throws FileWriteException, FileReadException {
      orderRepository.update(order);
  }


  @Override
  public void addProductToOrder() {

  }

  @Override
  public List<Order> showAllOrders() throws FileReadException {

    return orderRepository.findAll();
  }

  @Override
  public void clearOrders() throws FileWriteException {
    orderRepository.deleteAll();
  }
}