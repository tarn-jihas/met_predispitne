package handlers;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;

public interface IMenuHandler {

  void showAdminMenu() throws FileWriteException, FileReadException;

  void showClientMenu() throws FileWriteException, FileReadException;

  void showDeliveryMenu() throws FileWriteException, FileReadException;
}