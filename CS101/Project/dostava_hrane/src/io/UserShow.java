package io;

import model.DeliveryUser;
import model.Order;

import java.util.List;

public class UserShow implements IUserShow {

  public void showOrders(List<Order> orders) {
    for (Order n : orders) {
      System.out.println("Order ID: " + n.getId() + ", Status: " + n.getStatus() + ", Total: "
          + n.calculateTotal());
    }
  }

  public void listAllDeliveryUsers(List<DeliveryUser> dostavljaci) {
    for (DeliveryUser d : dostavljaci) {
      System.out.println(
          "Delivery ID: " + d.getId() + ", Available: " + (d.isAvailable() ? "Yes" : "No"));
    }
  }

  public void showMessage(String poruka) {
    System.out.println(poruka);
  }
}
