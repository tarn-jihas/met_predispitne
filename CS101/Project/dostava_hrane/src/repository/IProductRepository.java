package repository;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;

import java.util.List;

public interface IProductRepository<Product> {

  void save(Product entity) throws FileWriteException;

  List<Product> findAll() throws FileReadException;

  void deleteAll() throws FileWriteException;
}
