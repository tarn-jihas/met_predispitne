package zadatak11;

import java.util.Scanner;

public class Zadatak11 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Unesite prvi broj: ");
    int broj1 = scanner.nextInt();
    System.out.print("Unesite drugi broj: ");
    int broj2 = scanner.nextInt();

    int razlikaKvadrata = Math.abs((broj1 * broj1) - (broj2 * broj2));

    int modul = razlikaKvadrata % 26;

    int redniBroj = modul == 0 ? 26 : modul;

    char slovo = modul == 0 ? 'z' : (char) ('a' + modul - 1);

    System.out.println(redniBroj + " - " + slovo);
  }
}