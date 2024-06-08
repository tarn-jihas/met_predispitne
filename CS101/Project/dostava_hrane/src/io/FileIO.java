package io;

import handlers.exceptions.FileReadException;
import handlers.exceptions.FileWriteException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIO implements IFileIO {

  public String readFile(String filePath) throws FileReadException {
    String projectDirectory = System.getProperty("user.dir");
    Path path = Paths.get(projectDirectory, filePath);
    StringBuilder content = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(path.toString()))) {
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line);
        content.append(System.lineSeparator());
      }
    } catch (IOException e) {
      throw new FileReadException("Error reading file: " + filePath, e);
    }
    return content.toString();
  }

  public void writeFile(String filePath, String content, boolean append) throws FileWriteException {
    String projectDirectory = System.getProperty("user.dir");
    Path path = Paths.get(projectDirectory, filePath);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString(), append))) {
      if (append && Files.size(path) > 0) {
        writer.newLine();
      }
      writer.write(content);
    } catch (IOException e) {
      throw new FileWriteException("Error writing to file: " + filePath, e);
    }
  }

  public void createFile(String fileName) throws IOException {
    String projectDirectory = System.getProperty("user.dir");
    Path path = Paths.get(projectDirectory, fileName);
    if (!Files.exists(path)) {
      Files.createDirectories(path.getParent());
      Files.createFile(path);
    }
  }

  @Override
  public void clearFile(String filePath) throws FileWriteException {
    writeFile(filePath, "", false);
  }
}