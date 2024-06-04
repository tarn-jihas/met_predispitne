package zadatak1;

import java.util.Scanner;

public class Zadatak1 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Unesite ime: ");
    String ime = scanner.nextLine();

    System.out.print("Unesite prezime: ");
    String prezime = scanner.nextLine();

    int duzinaImena = ime.length();
    int duzinaPrezimena = prezime.length();
    int razlikaDuzina = Math.abs(duzinaPrezimena - duzinaImena);

    if (razlikaDuzina == 0) {
      System.out.println("Razlika u broju karaktera je 0, pa deljenje nije moguce.");
    } else if (duzinaPrezimena % razlikaDuzina == 0) {
      System.out.println("Broj karaktera prezimena je deljiv sa apsolutnom vrednoscu razlike broja karaktera imena i prezimena.");
    } else {
      System.out.println("Broj karaktera prezimena nije deljiv sa apsolutnom vrednoscu razlike broja karaktera imena i prezimena.");
    }

    scanner.close();
  }
}