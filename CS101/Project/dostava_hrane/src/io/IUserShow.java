package io;

import java.util.List;
import model.DeliveryUser;
import model.Order;

public interface IUserShow {

  void showOrders(List<Order> narudzbine);

  void listAllDeliveryUsers(List<DeliveryUser> dostavljaci);

  void showMessage(String poruka);
}