package io;

import handlers.exceptions.UserInputException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput implements IUserInput {

  private Scanner scanner;

  public UserInput(Scanner scanner) {
    this.scanner = scanner;
  }


  public int intInput(String poruka) {
    int unos = 0;
    boolean validInput = false;
    while (!validInput) {
      System.out.print(poruka);
      try {
        unos = scanner.nextInt();
        scanner.nextLine();
        validInput = true;
      } catch (InputMismatchException e) {
        System.out.println("Entered value is not a number. Please try again.");
        scanner.nextLine();
      }
    }
    return unos;
  }

  public String stringInput(String poruka) {
    System.out.print(poruka);
    return scanner.nextLine();
  }

  public double doubleInput(String poruka) {
    System.out.print(poruka);
    try {
      double unos = scanner.nextDouble();
      scanner.nextLine();
      return unos;
    } catch (InputMismatchException e) {
      throw new UserInputException("Entered value is not a number. Please try again.", e);
    }
  }

  public boolean boolInput(String poruka) {
    System.out.print(poruka);
    try {
      boolean unos = scanner.nextBoolean();
      scanner.nextLine();
      return unos;
    } catch (InputMismatchException e) {
      throw new UserInputException("Entered value is not a number. Please try again.", e);
    }
  }
}