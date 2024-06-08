package env;

public enum FilePaths {
  ORDERS("/src/databases/orders.txt"),
  USERS("/src/databases/users.txt"),
  PRODUCTS("/src/databases/products.txt"),
  PRODUCTS_TEST("/src/databases/products_test.txt"),
  USERS_TEST("/src/databases/users_test.txt"),
  ORDERS_TEST("/src/databases/orders_test.txt");


  private final String path;

  FilePaths(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}