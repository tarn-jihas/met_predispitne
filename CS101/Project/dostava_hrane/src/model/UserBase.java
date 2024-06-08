package model;

import handlers.IMenuHandler;
import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class UserBase {


  private UUID id;

  private String username;
  private String password;
  private final UserRole role;
  private String address;


  public UserBase(String username, String password, String address, UserRole role) {
    this.id = UUID.randomUUID();

    this.username = username;
    this.password = password;
    this.role = role;
    this.address = address;
  }

  public UserBase(String username, String password, UserRole role) {
    this.id = UUID.randomUUID();

    this.username = username;
    this.password = password;
    this.role = role;
  }

  public UserBase(UUID id, String username, String password, UserRole role) {
    this.id = id;

    this.username = username;
    this.password = password;
    this.role = role;
  }

  public UserBase(UUID id, String username, String password, String address, UserRole role) {
    this.id = id;

    this.username = username;
    this.password = password;
    this.role = role;
    this.address = address;
  }

  public abstract void showMenu(IMenuHandler menuHandler)
      throws FileWriteException, FileReadException;

  public UUID getId() {
    return id;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UserRole getRole() {
    return role;
  }

  public static List<UserBase> convertStringToUsers(String data) {
    List<UserBase> users = new ArrayList<>();
    String[] lines = data.split(System.lineSeparator());
    for (String line : lines) {
      String[] parts = line.split("\\|");

      if (parts[0].trim().isEmpty()) {
        throw new IllegalArgumentException("Invalid username or password");
      }

      UUID id = UUID.fromString(parts[0]);
      String username = parts[1];
      String password = parts[2];
      UserRole role = UserRole.valueOf(parts[3].toUpperCase());
      UserBase user;

      switch (role) {
        case ADMIN:
          user = new AdminUser(id, username, password, role);
          break;
        case CUSTOMER:
          String address = parts[4];
          user = new CustomerUser(id, username, password, address, role);
          break;
        case DELIVERY:
          boolean available = Boolean.parseBoolean(parts[4]);
          user = new DeliveryUser(id, username, password, available,
              role); // the delivery guys set available to true when they are ready to deliver
          if (parts.length > 5) {
            String orderId = parts[5].substring(6).trim();
            if (orderId.equals("null") || orderId.isEmpty()) {
              break;
            } else {
              ((DeliveryUser) user).addOrder(new Order(UUID.fromString(orderId.trim())));
            }

          }
          break;
        default:
          throw new IllegalArgumentException("Invalid user role: " + role);
      }
      users.add(user);
    }
    return users;
  }


  public String convertUserToString() {
    return this.getId() + "|" + this.getUsername() + "|" + this.getPassword() + "|"
        + this.getRole();
  }
}
