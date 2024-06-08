import app.OrderingSystem;
import env.FilePaths;
import handlers.IMenuHandler;
import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;
import io.*;

import java.util.Scanner;

import model.*;
import repository.*;
import service.*;
import service.IDatabaseService;
import service.IOrderService;
import service.IProductService;

public class Main {

  private Scanner scanner;
  private IUserInput userInput;
  private IUserShow userShow;
  private OrderingSystem orderingSystem;
  private UserService userService;
  private UserBase loggedUser;

  public static void main(String[] args) throws FileWriteException, FileReadException {
    Main main = new Main();
    main.init();
    main.run();
  }

  private void init() {
    scanner = new Scanner(System.in);
    userInput = new UserInput(scanner);
    userShow = new UserShow();

    // Database repos
    IFileIO fileService = new FileIO();
    IDatabaseService databaseService = new TextFileDatabaseService(fileService);
    IOrderRepository<Order> orderRepository = new TextFileOrderRepository(databaseService,
        FilePaths.ORDERS.getPath());
    IUserRepository<UserBase> userRepository = new TextFileUserRepository(databaseService,
        FilePaths.USERS.getPath());
    IProductRepository<Product> productRepository = new TextFileProductRepository(databaseService,
        FilePaths.PRODUCTS.getPath());

    // Initialize the services
    IOrderService<Order> orderService = new OrderService(orderRepository);
    IProductService<Product> productService = new ProductService(productRepository);
    userService = new UserService(userRepository);

    orderingSystem = OrderingSystem.getInstance(orderService, productService, userShow, userInput,
        userService);
  }

  private void run() throws FileWriteException, FileReadException {
    boolean running = true;

    while (running) {
      switch (showLoginMenu()) {
        case 1:
          login();
          break;
        case 2:
          registerUser();
          break;
        case 3:
          running = false;
          userShow.showMessage("Exiting the application.");
          break;
        default:
          userShow.showMessage("Unknown option, please try again.");
          break;
      }
    }

    scanner.close();
  }

  private int showLoginMenu() {
    int option;
    while (true) {
      userShow.showMessage("\nLogin Menu");
      userShow.showMessage("1. Login");
      userShow.showMessage("2. Register");
      userShow.showMessage("3. Exit");

      option = userInput.intInput("Select an option: ");

      if (option >= 1 && option <= 3) {
        break;
      } else {
        userShow.showMessage("Invalid option, please enter a number between 1 and 3.");
      }
    }
    return option;
  }

  private void login() throws FileWriteException, FileReadException {
    String username = userInput.stringInput("Enter username: ");
    String password = userInput.stringInput("Enter password: ");
    UserBase user;
    try {
      user = userService.login(username, password);
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }

    if (user != null) {
      loggedUser = user;
      user.showMenu(new MenuHandler());
    } else {
      userShow.showMessage("Invalid username or password.");
    }
  }

  private void adminMenu() throws FileWriteException, FileReadException {
    boolean running = true;

    while (running) {
      switch (showAdminMenu()) {
        case 1:
          orderingSystem.createProduct();
          break;
        case 2:
          orderingSystem.createOrder(loggedUser.getId(), loggedUser.getAddress());
          break;
        case 3:
          orderingSystem.showAllOrders();
          break;
        case 4:
          orderingSystem.assignDeliveryUserToOrder(loggedUser);
          break;
        case 5:
          orderingSystem.showAllDeliveryUsers();
          break;
        case 6:
          registerUser();
          break;
        case 7:
          loggedUser = null;
          running = false;
          break;
        default:
          userShow.showMessage("Unknown option, please try again.");
          break;
      }
    }
  }

  private int showAdminMenu() {
    userShow.showMessage("\nAdmin Menu");
    userShow.showMessage("1. Create Product");
    userShow.showMessage("2. Create Order");
    userShow.showMessage("3. View All Orders");
    userShow.showMessage("4. Assign Delivery User to Order");
    userShow.showMessage("5. View All Delivery Users");
    userShow.showMessage("6. Create a User");
    userShow.showMessage("7. Back to Main Menu");

    return userInput.intInput("Select an option: ");
  }


  private void clientMenu() throws FileReadException {
    boolean running = true;

    while (running) {
      switch (showClientMenu()) {
        case 1:
          try {
            orderingSystem.createOrder(loggedUser.getId(), loggedUser.getAddress());
          } catch (Exception e) {
            e.printStackTrace();
          }
          break;
        case 2:
          orderingSystem.showAllUserOrders(loggedUser, true);
          break;
        case 3:
          loggedUser = null;
          running = false;
          break;
        default:
          userShow.showMessage("Unknown option, please try again.");
          break;
      }
    }
  }


  private int showClientMenu() {
    userShow.showMessage("\nClient Menu");
    userShow.showMessage("1. Create Order");
    userShow.showMessage("2. View All Orders");
    userShow.showMessage("3. Back to Main Menu");

    return userInput.intInput("Select an option: ");
  }

  private void deliveryMenu() throws FileReadException, FileWriteException {
    boolean running = true;
    DeliveryUser deliveryUser = (DeliveryUser) loggedUser;
    while (running) {
      switch (showDeliveryMenu(deliveryUser)) {
        case 1:
          orderingSystem.setDeliveryStatus((DeliveryUser) loggedUser);
          break;
        case 2:
          orderingSystem.assignDeliveryUserToOrder(deliveryUser);
          break;
        case 3:
          orderingSystem.showAllUserOrders(deliveryUser, true);
          break;
        case 4:
          orderingSystem.setOrderStatus(deliveryUser);
          break;
        case 5:
          running = false;
          break;
        default:
          userShow.showMessage("Unknown option, please try again.");
          break;
      }
    }
  }

  private int showDeliveryMenu(DeliveryUser user) {
    userShow.showMessage("\nDelivery Menu");
    userShow.showMessage("1. Change Status." + " Current status: " + user.isAvailable());
    userShow.showMessage("2. Accept Order");
    userShow.showMessage("3. View My Orders");
    userShow.showMessage("4. Set Order Status");
    userShow.showMessage("5. Back to Main Menu");

    return userInput.intInput("Select an option: ");
  }

  private void registerUser() {
    String username = userInput.stringInput("Enter username: ");
    String password = userInput.stringInput("Enter password: ");
    String role = userInput.stringInput("Enter role (admin, customer, delivery): ");

    switch (role) {
      case "admin":
        userService.registerUser(username, password, UserRole.ADMIN);
        break;
      case "customer":
        String address = userInput.stringInput("Enter address: ");
        userService.registerUser(username, password, address, UserRole.CUSTOMER);
        break;
      case "delivery":
        userService.registerUser(username, password, UserRole.DELIVERY);
        break;
      default:
        userShow.showMessage("Invalid role, please try again.");
        break;
    }
  }

  private class MenuHandler implements IMenuHandler {

    @Override
    public void showAdminMenu() throws FileWriteException, FileReadException {
      adminMenu();
    }

    @Override
    public void showClientMenu() throws FileWriteException, FileReadException {
      clientMenu();
    }

    @Override
    public void showDeliveryMenu() throws FileWriteException, FileReadException {
      deliveryMenu();
    }
  }
}




