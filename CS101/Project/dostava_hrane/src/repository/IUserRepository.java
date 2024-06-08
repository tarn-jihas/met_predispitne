package repository;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;

import java.util.List;
import java.util.UUID;

public interface IUserRepository<UserBase> {

  UserBase findByUsernameAndPassword(String username, String password) throws FileReadException;

  void save(UserBase entity) throws FileWriteException;

  List<UserBase> findAll() throws FileReadException;

  void update(UserBase entity) throws FileReadException, FileWriteException;

  void clearUsers() throws FileWriteException;
}
