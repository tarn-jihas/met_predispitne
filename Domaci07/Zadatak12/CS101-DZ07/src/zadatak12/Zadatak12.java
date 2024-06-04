package zadatak12;

import java.util.Scanner;

public class Zadatak12 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Unesite velicinu niza: ");
    int velicina = scanner.nextInt();

    if (velicina <= 0) {
      System.out.println("Velicina niza mora biti pozitivna.");
      return;
    }

    int[] niz = new int[velicina];

    System.out.println("Unesite elemente niza: ");
    for (int i = 0; i < velicina; i++) {
      niz[i] = scanner.nextInt();
    }

    int najduzaSerija = pronadjiNajduzuSeriju(niz);
    System.out.println("Duzina najduze serije jednakih uzastopnih elemenata je: " + najduzaSerija);
  }

  public static int pronadjiNajduzuSeriju(int[] niz) {
    if (niz.length == 0) return 0;

    int trenutnaSerija = 1;
    int najduzaSerija = 1;

    for (int i = 1; i < niz.length; i++) {
      if (niz[i] == niz[i - 1]) {
        trenutnaSerija++;
        najduzaSerija = Math.max(najduzaSerija, trenutnaSerija);
      } else {
        trenutnaSerija = 1;
      }
    }

    return najduzaSerija;
  }
}