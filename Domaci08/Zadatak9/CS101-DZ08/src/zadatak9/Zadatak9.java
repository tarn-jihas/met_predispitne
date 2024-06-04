package zadatak9;

import java.util.Scanner;
import java.util.Random;

public class Zadatak9 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Unesite broj redova (n): ");
    int n = scanner.nextInt();
    System.out.print("Unesite broj kolona (m): ");
    int m = scanner.nextInt();

    int[][] array = new int[n][m];
    fillArrayWithRandomValues(array);

    System.out.println("Generisani niz:");
    printArray(array);

    int sum = calculateSumAboveMainDiagonal(array);

    System.out.println("Suma elemenata iznad glavne dijagonale je: " + sum);
  }
  public static void fillArrayWithRandomValues(int[][] array) {
    Random random = new Random();
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        array[i][j] = random.nextInt(100); // Random vrednosti od 0 do 99
      }
    }
  }

  public static void printArray(int[][] array) {
    for (int[] row : array) {
      for (int value : row) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
  }
  public static int calculateSumAboveMainDiagonal(int[][] array) {
    int sum = 0;
    for (int i = 0; i < array.length; i++) {
      for (int j = i + 1; j < array[i].length; j++) {
        sum += array[i][j];
      }
    }
    return sum;
  }
}
