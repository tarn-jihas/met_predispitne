package service;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;

// IDatabaseService.java
public interface IDatabaseService {

  void saveData(String filePath, String data, boolean append) throws FileWriteException;

  String retrieveData(String filePath) throws FileReadException;

  void clearData(String filePath) throws FileWriteException;
}