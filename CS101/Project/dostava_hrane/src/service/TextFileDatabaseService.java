package service;// TextFileDatabaseService.java

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;
import io.IFileIO;

public class TextFileDatabaseService implements IDatabaseService {
  private final IFileIO fileService;

  public TextFileDatabaseService(IFileIO fileService){
    this.fileService = fileService;
  }

  @Override
  public void saveData(String filePath, String data, boolean append) throws FileWriteException {
      fileService.writeFile(filePath, data, append);

  }

  @Override
  public String retrieveData(String filePath) throws FileReadException {
      return fileService.readFile(filePath);
  }

  @Override
  public void clearData(String filePath) throws FileWriteException {
        fileService.clearFile(filePath);
  }
}