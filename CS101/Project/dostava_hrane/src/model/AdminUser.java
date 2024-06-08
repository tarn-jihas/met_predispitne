package model;


import handlers.IMenuHandler;
import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;

import java.util.UUID;

public class AdminUser extends UserBase {

  public AdminUser(UUID id, String username, String password, UserRole role) {
    super(id, username, password, role);
  }

  public AdminUser(String username, String password, UserRole role) {
    super(username, password, role);
  }

  @Override
  public void showMenu(IMenuHandler menuHandler) throws FileWriteException, FileReadException {
    menuHandler.showAdminMenu();
  }

}
