package handlers.exceptions;


public class UserInputException extends RuntimeException {

  public UserInputException(String message, Throwable cause) {
    super(message, cause);
  }
}
