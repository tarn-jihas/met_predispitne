package zadatak10;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Zadatak10 {
/*

Zadatak 10:
Kreirajte projekat koji će se zvati CS101-DZ03-110. U okviru projekta treba da postoji paket
zadatak10 i pokretačka klasa Zadatak10.
Napisati program gde korisnik unosi cenu karte za bioskop kao i dan (broj od 1 do 7 – 1 je
ponedeljak, a 7 nedelja) za koji hoće da gleda film. Ukoliko je u pitanju radni dan, cena karte se
umanjuje za 5%, dok u slučaju da nije radni dan cena se povećava za 5%. U konzoli ispisati krajnju
cenu karte.


 */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Map<Integer, Boolean> dani = Map.of(
        1, true,  // Ponedeljak
        2, true,  // Utorak
        3, true,  // Sreda
        4, true,  // Cetvrtak
        5, true,  // Petak
        6, false, // Subota
        7, false  // Nedelja
    );

    System.out.print("Unesite cenu karte: ");
    double cenaKarte = scanner.nextDouble();

    System.out.print("Unesite dan (1-7): ");
    int dan = scanner.nextInt();

    if (dan < 1 || dan > 7) {
      System.out.println("Uneli ste nevalidan dan. Molimo unesite broj od 1 do 7.");
      return;
    }

    if (dani.get(dan)) {
      cenaKarte *= 0.95;
    } else {
      cenaKarte *= 1.05;
    }

    System.out.printf("Krajnja cena karte je: %.2f", cenaKarte);
  }
}