package io;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;

public interface IFileIO {

  String readFile(String filePath) throws FileReadException;

  void writeFile(String filePath, String content, boolean append) throws FileWriteException;

  void createFile(String fileName) throws Exception;

  void clearFile(String filePath) throws FileWriteException;
}