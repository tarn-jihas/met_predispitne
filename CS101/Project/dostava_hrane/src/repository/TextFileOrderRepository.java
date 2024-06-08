package repository;// TextFileNarudzbinaRepository.java

import java.util.List;
import java.util.UUID;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;
import model.Order;
import service.IDatabaseService;

public class TextFileOrderRepository implements IOrderRepository<Order> {

  private final IDatabaseService databaseService;
  private final String filePath;

  public TextFileOrderRepository(IDatabaseService databaseService, String filePath) {
    this.databaseService = databaseService;
    this.filePath = filePath;
  }

  @Override
  public void save(Order order) throws FileWriteException {
    String data = order.convertOrderToString();
    databaseService.saveData(filePath, data, true);
  }

  @Override
  public void update(Order entity) throws FileWriteException, FileReadException {
    List<Order> orders = findAll();
    for (int i = 0; i < orders.size(); i++) {
      if (orders.get(i).getId().equals(entity.getId())) {
        orders.set(i, entity);
        break;
      }
    }
    StringBuilder sb = new StringBuilder();
    for (Order order : orders) {
      sb.append(order.convertOrderToString()).append("\n");
    }
    databaseService.saveData(filePath, sb.toString(), false);
  }


  @Override
  public Order findById(UUID id) throws FileReadException {
    String data = databaseService.retrieveData(filePath);
    List<Order> order = Order.convertStringToOrder(data);
    return order.stream().filter(n -> n.getId() == id).findFirst().orElse(null);
  }

  @Override
  public List<Order> findAll() throws FileReadException {
    String data = databaseService.retrieveData(filePath);

    return Order.convertStringToOrder(data);
  }

  @Override
  public void deleteAll() throws FileWriteException {
    databaseService.clearData(filePath);
  }
}