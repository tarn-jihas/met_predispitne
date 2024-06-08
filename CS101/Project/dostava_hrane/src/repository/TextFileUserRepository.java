package repository;

import java.util.List;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;
import model.*;
import service.IDatabaseService;

import static model.UserBase.convertStringToUsers;

public class TextFileUserRepository implements IUserRepository<UserBase> {

  private final IDatabaseService databaseService;
  private final String filePath;

  public TextFileUserRepository(IDatabaseService databaseService, String filePath) {
    this.databaseService = databaseService;
    this.filePath = filePath;
  }

  @Override
  public void save(UserBase user) throws FileWriteException {
    String data = user.convertUserToString();
    databaseService.saveData(filePath, data, true);
  }

  @Override
  public List<UserBase> findAll() throws FileReadException {
    String data = databaseService.retrieveData(filePath);
    return convertStringToUsers(data);
  }

  @Override
  public void update(UserBase entity) throws FileReadException, FileWriteException {
    List<UserBase> users = findAll();
    for (int i = 0; i < users.size(); i++) {
      if (users.get(i).getId().equals(entity.getId())) {
        switch (users.get(i).getRole()) {
          case ADMIN:
            entity = new AdminUser(entity.getId(), entity.getUsername(), entity.getPassword(),
                entity.getRole());
            break;
          case CUSTOMER:
            entity = new CustomerUser(entity.getId(), entity.getUsername(), entity.getPassword(),
                entity.getAddress(), entity.getRole());
            break;
          case DELIVERY:
            DeliveryUser deliveryUser = (DeliveryUser) users.get(i);
            DeliveryUser deliveryEntity = (DeliveryUser) entity;

            deliveryUser.setAvailable(deliveryEntity.isAvailable());
            deliveryUser.setUsername(deliveryEntity.getUsername());
            deliveryUser.setPassword(deliveryEntity.getPassword());

            entity = deliveryUser;
            break;
        }
        users.set(i, entity);
        break;
      }
    }
    StringBuilder sb = new StringBuilder();
    for (UserBase user : users) {
      sb.append(user.convertUserToString()).append("\n");
    }
    databaseService.saveData(filePath, sb.toString(), false);
  }

  @Override
  public void clearUsers() throws FileWriteException {
    databaseService.clearData(filePath);
  }


  @Override
  public UserBase findByUsernameAndPassword(String username, String password)
      throws FileReadException {
    String data = databaseService.retrieveData(filePath);
    List<UserBase> users;
    try {
      users = convertStringToUsers(data);

    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid username or password: " + e.getMessage());
    }
    for (UserBase user : users) {
      if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
        return user;
      }
    }
    throw new IllegalArgumentException("Invalid username or password");

  }
}