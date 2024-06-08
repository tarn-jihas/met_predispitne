package service;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;
import model.DeliveryUser;
import model.UserBase;
import model.UserRole;

import java.util.List;

public interface IUserService {

  void registerUser(String username, String password, UserRole role);

  void registerUser(String username, String password, String address, UserRole role);

  UserBase login(String username, String password) throws FileReadException;

  List<DeliveryUser> showAllDeliveryUsers();

  List<DeliveryUser> showAllActiveDeliveryUsers();

  void updateUser(UserBase user) throws FileWriteException, FileReadException;

  void clearUsers() throws FileWriteException;
}