package test;

import app.OrderingSystem;
import env.FilePaths;
import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;
import io.*;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;
import service.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderingSystemTest {

    private OrderingSystem orderingSystem;
    private IOrderService<Order> orderService;
    private IProductService<Product> productService;
    private IUserService userService;


    @BeforeEach
    void setUp() {
        Scanner scanner = new Scanner(System.in);
        IUserInput userInput = new UserInput(scanner);
        IUserShow userShow = new UserShow();

        // repos
        IFileIO fileService = new FileIO();
        IDatabaseService databaseService = new TextFileDatabaseService(fileService);
        IOrderRepository<Order> orderRepository = new TextFileOrderRepository(databaseService, FilePaths.ORDERS_TEST.getPath());
        IUserRepository<UserBase> userRepository = new TextFileUserRepository(databaseService, FilePaths.USERS_TEST.getPath());
        IProductRepository<Product> productRepository = new TextFileProductRepository(databaseService, FilePaths.PRODUCTS_TEST.getPath());


        // servisi
        orderService = new OrderService(orderRepository);
        productService = new ProductService(productRepository);
        userService = new UserService(userRepository);

        orderingSystem = OrderingSystem.getInstance(orderService, productService, userShow, userInput, userService);
    }
    @AfterEach
    void tearDown() throws FileWriteException {
        orderService.clearOrders();
        productService.clearProducts();
        userService.clearUsers();
    }

    @Test
    void createProduct_ShouldAddProduct_WhenProductIsCreated() throws FileReadException, FileWriteException {

        Product product = new Product("Initial Product", 100.0, ProductType.FOOD);
        productService.addProduct(product);

        int initialProductCount = productService.showAllProducts().size();

        Product product2 = new Product("New Product", 100.0, ProductType.FOOD);
        productService.addProduct(product2);

        int finalProductCount = productService.showAllProducts().size();

        assertEquals(initialProductCount + 1, finalProductCount);

    }

    @Test
    void showAllDeliveryUsers_ShouldReturnAllDeliveryUsers() throws FileReadException {
        String username = "test_delivery"+UUID.randomUUID();
        String username2 = "test_delivery"+UUID.randomUUID();
        userService.registerUser(username, "test_delivery",  UserRole.DELIVERY);
        userService.registerUser(username2, "test_delivery",  UserRole.DELIVERY);
        DeliveryUser user1 = (DeliveryUser) userService.login(username, "test_delivery");
        DeliveryUser user2 = (DeliveryUser) userService.login(username2, "test_delivery");

        List<DeliveryUser> deliveryUsers = userService.showAllDeliveryUsers();

        assertEquals(2, deliveryUsers.size());
        assertTrue(deliveryUsers.containsAll(Arrays.asList(user1, user2)));
    }


    @Test
    void createOrder_ShouldAddOrder_WhenOrderIsCreated() throws FileReadException, FileWriteException {
        // Arrange
        UUID userId = UUID.randomUUID();
        String address = "Test Address";
        Product product = new Product("Test Product", 100.0, ProductType.FOOD);
        productService.addProduct(product);
        List<Product> products = new ArrayList<>();
        products.add(product);
        orderService.createOrder(products, userId, address);

        int initialOrderCount = orderService.showAllOrders().size();

        orderService.createOrder(products, userId, address);

        int finalOrderCount = orderService.showAllOrders().size();
        assertEquals(initialOrderCount + 1, finalOrderCount);
    }


}