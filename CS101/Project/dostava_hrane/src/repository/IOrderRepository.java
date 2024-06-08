package repository;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;

import java.util.List;
import java.util.UUID;

public interface IOrderRepository<Order> {

  void save(Order entity) throws FileWriteException;

  void update(Order entity) throws FileWriteException, FileReadException;

  Order findById(UUID id) throws FileReadException;

  List<Order> findAll() throws FileReadException;

  void deleteAll() throws FileWriteException;
}
