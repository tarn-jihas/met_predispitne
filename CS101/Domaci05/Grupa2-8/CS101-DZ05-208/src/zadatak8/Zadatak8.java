package zadatak8;

import java.util.Scanner;

public class Zadatak8 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("Unesite broj ucenika: ");
    int numberOfStudents = input.nextInt();

    String highestName = "";
    int highestScore = 0;
    String secondHighestName = "";
    int secondHighestScore = 0;

    for (int i = 0; i < numberOfStudents; i++) {
      System.out.print("Unesite ime i rezultat " + (i + 1) + ". ucenika: ");
      String name = input.next();
      int score = input.nextInt();

      if (score > highestScore) {
        secondHighestName = highestName;
        secondHighestScore = highestScore;
        highestName = name;
        highestScore = score;
      } else if (score > secondHighestScore) {
        secondHighestName = name;
        secondHighestScore = score;
      }
    }

    input.close();

    System.out.println("Ucenik sa najvisim rezultatom je " + highestName + " sa " + highestScore + " poena.");
    System.out.println("Ucenik sa drugim najvećim rezultatom je " + secondHighestName + " sa " + secondHighestScore + " poena.");
  }
}