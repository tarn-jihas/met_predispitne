package model;

import java.util.*;

public class Order implements IHandleOrder {

  private UUID id;
  private UUID userId;
  private UUID deliveryUserId;
  private String address;
  private List<Product> productList = new ArrayList<>();
  private OrderStatus status;

  public Order() {
  }

  public Order(UUID userId) {
    this.id = UUID.randomUUID();
    this.status = OrderStatus.CREATED;
    this.userId = userId;
  }

  @Override
  public void addProduct(Product product) {
    productList.add(product);
  }

  @Override
  public void removeProduct(Product product) {
    productList.remove(product);
  }

  @Override
  public double calculateTotal() {
    double total = 0;
    for (Product product : productList) {
      total += product.getPrice();
    }
    return total;
  }

  @Override
  public void changeStatus(OrderStatus newStatus) {
    this.status = newStatus;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public List<Product> getProductList() {
    return productList;
  }

  public void setProductList(List<Product> productList) {
    this.productList = productList;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public UUID getDeliveryUserId() {
    return deliveryUserId;
  }

  public void setDeliveryUserId(UUID deliveryUserId) {
    this.deliveryUserId = deliveryUserId;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String convertOrderToString() {
    StringBuilder sb = new StringBuilder();
    sb.append("---ORDER START---\n");
    sb.append("Order ID: ").append(this.getId()).append("\n");
    sb.append("User ID: ").append(this.getUserId()).append("\n");
    sb.append("Address: ").append(this.getAddress()).append("\n");
    sb.append("Delivery User ID: ").append(this.getDeliveryUserId()).append("\n");
    sb.append("Status: ").append(this.getStatus()).append("\n");
    sb.append("Products:\n");
    for (Product product : this.getProductList()) {
      sb.append("  - ").append(product.toString()).append("\n");
    }
    sb.append("Total: ").append(this.calculateTotal()).append("\n");
    sb.append("---ORDER END---\n");
    return sb.toString();
  }

  public String convertOrderToFriendlyString() {
    StringBuilder sb = new StringBuilder();
    sb.append("---ORDER---\n");
    sb.append("Order ID: ").append(this.getId()).append("\n");
    sb.append("Address: ").append(this.getAddress()).append("\n");
    sb.append("Status: ").append(this.getStatus()).append("\n");
    sb.append("Products:\n");

    Map<Product, Integer> productCount = new HashMap<>();
    for (Product product : this.getProductList()) {
      productCount.put(product, productCount.getOrDefault(product, 0) + 1);
    }

    for (Map.Entry<Product, Integer> entry : productCount.entrySet()) {
      sb.append("  - ").append(entry.getKey().getName()).append(" x ").append(entry.getValue())
          .append("\n");
    }

    sb.append("Total: ").append(this.calculateTotal()).append("\n");
    sb.append("------\n");
    return sb.toString();
  }

  public static List<Order> convertStringToOrder(String data) {
    List<Order> orders = new ArrayList<>();
    String[] orderStrings = data.split("---ORDER START---");
    for (String orderString : orderStrings) {
      if (orderString.trim().isEmpty()) {
        continue;
      }
      Order order = parseOrder(orderString);
      orders.add(order);
    }
    return orders;
  }

  private static Order parseOrder(String orderString) {
    Order order = new Order();
    String[] lines = orderString.split("\n");
    for (String line : lines) {
      parseLine(line, order);
    }
    return order;
  }

  private static void parseLine(String line, Order order) {
    if (line.startsWith("Order ID: ")) {
      order.setId(UUID.fromString(line.substring(10)));
    } else if (line.startsWith("User ID: ")) {
      order.setUserId(UUID.fromString(line.substring(9)));
    } else if (line.startsWith("Address: ")) {
      order.setAddress(line.substring(9));
    } else if (line.startsWith("Delivery User ID: ")) {
      String deliveryUserId = line.substring(18);
      if (!deliveryUserId.equals("null")) {
        order.setDeliveryUserId(UUID.fromString(deliveryUserId));
      }
    } else if (line.startsWith("Status: ")) {
      order.setStatus(OrderStatus.valueOf(line.substring(8).toUpperCase()));
    } else if (line.startsWith("  - Product{id=")) {
      Product product = parseProduct(line);
      order.addProduct(product);
    }
  }

  private static Product parseProduct(String line) {
    String productString = line.substring(13, line.length() - 1);
    String[] productParts = productString.split(", ");
    UUID productId = UUID.fromString(productParts[0].split("=")[1]);
    String productName = productParts[1].split("=")[1].replace("'", "");
    double productPrice = Double.parseDouble(productParts[2].split("=")[1]);
    ProductType productCategory = ProductType.valueOf(productParts[3].split("=")[1].toUpperCase());
    return new Product(productId, productName, productPrice, productCategory);
  }
}
