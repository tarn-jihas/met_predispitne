package model;

import handlers.IMenuHandler;
import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;

import java.util.UUID;

public class CustomerUser extends UserBase {

  public CustomerUser(UUID id, String username, String password, String address, UserRole role) {
    super(id, username, password, address, role);
  }

  public CustomerUser(String username, String password, String address, UserRole role) {
    super(username, password, address, role);
  }

  @Override
  public void showMenu(IMenuHandler menuHandler) throws FileWriteException, FileReadException {
    menuHandler.showClientMenu();
  }

  @Override
  public String convertUserToString() {
    return this.getId() + "|" + this.getUsername() + "|" + this.getPassword() + "|" + this.getRole()
        + "|" + this.getAddress() + "|";
  }

}
