package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;
import model.Product;
import model.ProductType;
import service.IDatabaseService;

public class TextFileProductRepository implements IProductRepository<Product> {

  private final IDatabaseService databaseService;
  private final String filePath;


  public TextFileProductRepository(IDatabaseService databaseService, String filePath) {
    this.databaseService = databaseService;
    this.filePath = filePath;
  }

  @Override
  public void save(Product entity) throws FileWriteException {
    String data = convertProductToString(entity);
    databaseService.saveData(filePath, data, true);
  }

  @Override
  public List<Product> findAll() throws FileReadException {
    String data = databaseService.retrieveData(filePath);
    if (data.isEmpty() || data.isBlank()) {
      throw new FileReadException("No products found", new Exception());
    }
    return convertStringToProducts(data);
  }

  @Override
  public void deleteAll() throws FileWriteException {
    databaseService.clearData(filePath);
  }

  private List<Product> convertStringToProducts(String data) {
    List<Product> products = new ArrayList<>();
    String[] lines = data.split(System.lineSeparator());
    for (String line : lines) {
      String[] parts = line.split("\\|");
      UUID id = UUID.fromString(parts[0]);
      String name = parts[1];
      double price = Double.parseDouble(parts[2]);
      ProductType category = ProductType.valueOf(parts[3].toUpperCase());
      Product product = new Product(id, name, price, category);
      products.add(product);
    }
    return products;
  }

  private String convertProductToString(Product entity) {
    return entity.getId() + "|" + entity.getName() + "|" + entity.getPrice() + "|"
        + entity.getCategory();
  }
}
