package model;

import handlers.IMenuHandler;
import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;

import java.util.Objects;
import java.util.UUID;

public class DeliveryUser extends UserBase {

  private boolean available;
  private Order order;

  public DeliveryUser(UUID id, String username, String password, boolean available, UserRole role) {
    super(id, username, password, role);
    this.available = available;
  }


  public DeliveryUser(String username, String password, boolean available, UserRole role) {
    super(username, password, role);
    this.available = available;
    this.order = new Order();
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public Order getOrders() {
    return this.order;
  }

  public void addOrder(Order order) {
    this.order = order;
    order.setDeliveryUserId(this.getId());
  }

  @Override
  public String convertUserToString() {
    StringBuilder sb = new StringBuilder(super.convertUserToString());
    sb.append("|").append(this.available);
    sb.append("|Order: ");
    if (this.order != null) {
      sb.append(this.order.getId());

    }
    return sb.toString();
  }

  @Override
  public void showMenu(IMenuHandler menuHandler) throws FileWriteException, FileReadException {
    menuHandler.showDeliveryMenu();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeliveryUser that = (DeliveryUser) o;
    return available == that.available && Objects.equals(order, that.order);
  }

  @Override
  public int hashCode() {
    return Objects.hash(available, order);
  }
}