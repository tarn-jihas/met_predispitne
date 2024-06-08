package zadatak1;

import java.util.Scanner;

public class Zadatak1 {
  private static int unesiOcenu(Scanner scanner) {
    while (true) {
      int ocena = scanner.nextInt();
      if (ocena >= 5 && ocena <= 10) {
        return ocena;
      } else {
        System.out.println("Ocena mora biti u intervalu od 5 do 10. Molimo unesite ocenu ponovo:");
      }
    }
  }

  private static double izracunajProsek(int[] ocene) {
    int suma = 0;
    for (int ocena : ocene) {
      suma += ocena;
    }
    return (double) suma / ocene.length;
  }

  private static void ispisiPorukuNaOsnovuProseka(double prosek) {
    if (prosek >= 5 && prosek < 7) {
      System.out.println("Los student");
    } else if (prosek >= 7 && prosek < 9) {
      System.out.println("Dobar student");
    } else if (prosek >= 9) {
      System.out.println("Odlican student");
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int[] ocene = new int[8];

    System.out.println("Unesite 8 ocena (od 5 do 10):");
    for (int i = 0; i < ocene.length; i++) {
      ocene[i] = unesiOcenu(scanner);
    }

    double prosek = izracunajProsek(ocene);
    ispisiPorukuNaOsnovuProseka(prosek);
  }
}
