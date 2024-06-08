package service;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;
import model.Product;

import java.util.List;
import java.util.UUID;

public interface IOrderService<Order> {

  void createOrder(List<Product> products, UUID userI, String address) throws FileWriteException;

  void updateOrder(Order order) throws FileWriteException, FileReadException;

  void addProductToOrder();

  List<Order> showAllOrders() throws FileReadException;

  void clearOrders() throws FileWriteException;
}