package env;

public enum DirectoryPaths {
  Databases("/src/databases");



  private final String path;

  DirectoryPaths(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }
}