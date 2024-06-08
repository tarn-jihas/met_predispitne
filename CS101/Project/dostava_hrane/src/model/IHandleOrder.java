package model;

interface IHandleOrder {

  void addProduct(Product product);

  void removeProduct(Product product);

  double calculateTotal();

  void changeStatus(OrderStatus newStatus);
}