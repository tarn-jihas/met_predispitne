package service;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;

import java.util.List;

public interface IProductService<Product> {

  List<Product> showAllProducts() throws FileReadException;

  void addProduct(Product product) throws FileWriteException;

  void clearProducts() throws FileWriteException;
}
