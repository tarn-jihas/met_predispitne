package service;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;
import handlers.exceptions.ServiceException;
import model.*;
import repository.IUserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {

  private final IUserRepository<UserBase> userRepo;

  public UserService(IUserRepository<UserBase> userRepository) {
    this.userRepo = userRepository;
  }

  @Override
  public void registerUser(String username, String password, UserRole role) {
    try {
      switch (role) {
        case ADMIN:
          userRepo.save(new AdminUser(username, password, role));
          break;

        case DELIVERY:
          userRepo.save(new DeliveryUser(username, password, true, role));
          break;

        default:
          throw new IllegalArgumentException("Invalid user role: " + role);
      }
    } catch (IllegalArgumentException e) {
      throw new ServiceException("Invalid user role: " + role, e);
    } catch (Exception e) {
      throw new ServiceException("Error while registering user", e);
    }
  }

  @Override
  public void registerUser(String username, String password, String address, UserRole role) {
    try {
      if (role != UserRole.CUSTOMER) {
        throw new IllegalArgumentException("Invalid user role: " + role);
      }
      userRepo.save(new CustomerUser(username, password, address, role));
    } catch (IllegalArgumentException e) {
      throw new ServiceException("Invalid user role: " + role, e);
    } catch (Exception e) {
      throw new ServiceException("Error while registering user", e);
    }
  }

  @Override
  public UserBase login(String username, String password) throws FileReadException {

      return userRepo.findByUsernameAndPassword(username, password);

  }

  @Override
  public List<DeliveryUser> showAllDeliveryUsers() {
    try {
      List<DeliveryUser> deliveryUsers = new ArrayList<>();
      for (UserBase user : userRepo.findAll()) {
        if (user.getRole() == UserRole.DELIVERY) {
          DeliveryUser deliveryUser = (DeliveryUser) user;
          deliveryUsers.add(deliveryUser);
        }
      }
      return deliveryUsers;
    } catch (Exception e) {
      throw new ServiceException("Error while retrieving delivery users", e);
    }
  }

  @Override
  public List<DeliveryUser> showAllActiveDeliveryUsers() {
    try {
      List<DeliveryUser> activeDeliveryUsers = new ArrayList<>();
      for (UserBase user : userRepo.findAll()) {
        if (user.getRole() == UserRole.DELIVERY) {
          DeliveryUser deliveryUser = (DeliveryUser) user;
          if (deliveryUser.isAvailable()) {
            activeDeliveryUsers.add(deliveryUser);
          }
        }
      }
      return activeDeliveryUsers;
    } catch (Exception e) {
      throw new ServiceException("Error while retrieving active delivery users", e);
    }
  }

  @Override
  public void updateUser(UserBase user) throws FileWriteException, FileReadException {

      userRepo.update(user);
  }

  @Override
  public void clearUsers() throws FileWriteException {
    userRepo.clearUsers();
  }
}
