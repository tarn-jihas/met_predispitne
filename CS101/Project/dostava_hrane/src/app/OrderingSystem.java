package app;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;
import io.IUserInput;
import model.*;
import service.IOrderService;
import service.IProductService;
import service.IUserService;

import java.util.*;

public class OrderingSystem {

  private final IUserInput userInput;

  private static OrderingSystem instance = null;
  private final IOrderService<Order> orderService;
  private final IProductService<Product> productService;
  private final IUserService userService;


  private OrderingSystem(IOrderService<Order> orderService, IProductService<Product> productService,
      IUserInput userInput, IUserService userService) {
    this.orderService = orderService;
    this.productService = productService;
    this.userInput = userInput;
    this.userService = userService;
  }

  public static OrderingSystem getInstance(IOrderService<Order> orderService,
      IProductService<Product> productService, IUserInput unosKorisnika,
      IUserService userService) {
    if (instance == null) {
      instance = new OrderingSystem(orderService, productService, unosKorisnika,
          userService);
    }
    return instance;
  }

  public void createOrder(UUID userId, String address)
      throws FileWriteException {
    List<Product> products;
    try {
      products = productService.showAllProducts();

    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
    // Display all products with indexes
    System.out.println("Available products: ");
    for (int i = 0; i < products.size(); i++) {
      System.out.println(
          (i + 1) + ". " + products.get(i).getName() + " - " + products.get(i).getPrice() + " RSD");
    }

    // Allow user to select products by index and quantity
    List<Product> selectedProducts = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println(
          "Enter the number of the product you want to add to the order, or 0 to finish:");
      int productNumber = scanner.nextInt();
      if (productNumber == 0) {
        break;
      }
      if (productNumber < 1 || productNumber > products.size()) {
        System.out.println("Invalid product number. Please try again.");
        continue;
      }
      System.out.println("Enter the quantity of the product you want to add to the order:");
      int quantity = scanner.nextInt();
      if (quantity < 1) {
        System.out.println("Invalid quantity. Please try again.");
        continue;
      }
      for (int i = 0; i < quantity; i++) {
        selectedProducts.add(products.get(productNumber - 1));
      }
      System.out.println(
          "Added to cart: " + products.get(productNumber - 1).getName() + " x" + quantity);
    }

    // Pass the selected products to the order service to create an order
    if (!selectedProducts.isEmpty()) {
      orderService.createOrder(selectedProducts, userId, address);
      System.out.println("\nOrder created successfully!");
    } else {
      System.out.println("No products selected to create an order.");
    }
  }

  public void createProduct() throws FileWriteException {
    String name = userInput.stringInput("Enter product name: ");
    double price = userInput.doubleInput("Enter product price: ");
    ProductType category = ProductType.valueOf(
        userInput.stringInput("Enter product category (food, drink): ").toUpperCase());
    Product product = new Product(name, price, category);
    productService.addProduct(product);
  }


  public void showAllOrders() throws FileReadException {
    List<Order> orders = orderService.showAllOrders();
    for (Order order : orders) {
      System.out.println(order.convertOrderToString());
    }
  }

  public void showAllUserOrders(UserBase user, boolean friendly) throws FileReadException {
    List<Order> orders = orderService.showAllOrders();

    if (orders.isEmpty()) {
      System.out.println("No orders available.");
      return;
    }
    for (Order order : orders) {
      if (Objects.requireNonNull(user.getRole()) == UserRole.DELIVERY) {
        if (order.getDeliveryUserId() != null && order.getDeliveryUserId().equals(user.getId())) {
          if (friendly) {
            System.out.println(order.convertOrderToFriendlyString());
          } else {
            System.out.println(order.convertOrderToString());
          }
        }
      } else {
        if (order.getUserId().equals(user.getId())) {
          if (friendly) {
            System.out.println(order.convertOrderToFriendlyString());
          } else {
            System.out.println(order.convertOrderToString());
          }
        }
      }
    }
  }

  public void showAllDeliveryUsers() {
    List<DeliveryUser> users = userService.showAllDeliveryUsers();
    for (DeliveryUser user : users) {
      System.out.println(user.convertUserToString());
    }
  }

  public void assignDeliveryUserToOrder(UserBase user)
      throws FileReadException, FileWriteException {
    Optional<Order> optionalOrder = selectOrder();
    if (optionalOrder.isEmpty()) {
      System.out.println("No orders available to assign a delivery user to.");
      return;
    }
    Order selectedOrder = optionalOrder.get();

    switch (user.getRole()) {
      case ADMIN:
        Optional<DeliveryUser> optionalUser = selectDeliveryUser();
        if (optionalUser.isEmpty()) {
          System.out.println("No active delivery users available.");
          return;
        }
        DeliveryUser selectedUser = optionalUser.get();
        assignUserToOrder(selectedUser, selectedOrder);
        break;
      case DELIVERY:
        DeliveryUser deliveryUser = (DeliveryUser) user;
        assignUserToOrder(deliveryUser, selectedOrder);
        break;
    }
  }

  private Optional<Order> selectDeliveryOrder(DeliveryUser user) throws FileReadException {
    List<Order> orders = orderService.showAllOrders();
    if (orders.isEmpty()) {
      return Optional.empty();
    }

    if (showDeliveryOrders(orders, user)) {
      int orderIndex = userInput.intInput(
          "Enter the index of the order you want update the status: ");
      if (orderIndex < 0 || orderIndex >= orders.size()) {
        System.out.println("Invalid order index.");
        return Optional.empty();
      }
      return Optional.of(orders.get(orderIndex));
    }

    return Optional.empty();
  }

  private boolean showDeliveryOrders(List<Order> orders, DeliveryUser user) {
    Iterator<Order> iterator = orders.iterator();
    while (iterator.hasNext()) {
      Order order = iterator.next();
      if (order.getStatus() == OrderStatus.ASSIGNED && !order.getDeliveryUserId()
          .equals(user.getId()) || order.getStatus() == OrderStatus.DELIVERED) {
        iterator.remove();
      }
    }
    if (orders.isEmpty()) {
      System.out.println("No orders available to update the status.");
      return false;
    }
    displayOrders(orders);
    return true;
  }

  private Optional<Order> selectOrder() throws FileReadException {
    List<Order> orders = orderService.showAllOrders();
    displayOrders(orders);
    if (orders.isEmpty()) {
      return Optional.empty();
    }
    int orderIndex = userInput.intInput(
        "Enter the index of the order you want to assign a delivery user to: ");
    if (orderIndex < 0 || orderIndex >= orders.size()) {
      System.out.println("Invalid order index.");
      return Optional.empty();
    }
    return Optional.of(orders.get(orderIndex));
  }

  private void displayOrders(List<Order> orders) {
    for (int i = 0; i < orders.size(); i++) {
      System.out.println(i + ": " + orders.get(i).convertOrderToString());
    }
  }

  private Optional<DeliveryUser> selectDeliveryUser() {
    List<DeliveryUser> deliveryUsers = userService.showAllActiveDeliveryUsers();
    displayDeliveryUsers(deliveryUsers);
    if (deliveryUsers.isEmpty()) {
      return Optional.empty();
    }
    int userIndex = userInput.intInput(
        "Enter the index of the delivery user you want to assign to the order: ");
    if (userIndex < 0 || userIndex >= deliveryUsers.size()) {
      System.out.println("Invalid user index.");
      return Optional.empty();
    }
    return Optional.of(deliveryUsers.get(userIndex));
  }

  private void displayDeliveryUsers(List<DeliveryUser> deliveryUsers) {
    for (int i = 0; i < deliveryUsers.size(); i++) {
      System.out.println(i + ": " + deliveryUsers.get(i).convertUserToString());
    }
  }

  private void assignUserToOrder(DeliveryUser user, Order order)
      throws FileWriteException, FileReadException {
    user.setAvailable(false);
    order.setDeliveryUserId(user.getId());
    order.setStatus(OrderStatus.ASSIGNED);
    user.addOrder(order);

    // Update db
    userService.updateUser(user);
    orderService.updateOrder(order);
    System.out.println(
        "Delivery user " + user.getUsername() + " has been assigned to order " + order.getId()
            + ".");
  }


  public void setDeliveryStatus(DeliveryUser user) {
    user.setAvailable(!user.isAvailable());
    try {
      userService.updateUser(user);
    } catch (Exception e) {
      e.printStackTrace();


    }
  }


  public void setOrderStatus(DeliveryUser user) throws FileReadException, FileWriteException {
    Optional<Order> order = selectDeliveryOrder(user);
    if (order.isPresent()) {
      Order selectedOrder = order.get();
      selectedOrder.setStatus(OrderStatus.DELIVERED);

      orderService.updateOrder(selectedOrder);
      System.out.println(selectedOrder.convertOrderToFriendlyString());
    } else {
      System.out.println("There was a problem updating the order status. Please try again.");
    }


  }
}