package zadatak12;

import java.util.Scanner;
/*

Kreirajte projekat koji će se zvati CS101-DZ02-12. U okviru projekta treba da postoji paket zadatak12
i pokretačka klasa Zadatak12.
Sa standardnog ulaza se unosi za koje vreme atletičar prelazi 10 kilometara. Napisati program koji
računa njegovu brzinu u jedinicama milja/sat ako se zna da je 1 milja 1,6 kilometara. Prikazati
rezultat korisniku.


 */
public class Zadatak12 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Unesite vreme u minutima za koje atleticar prelazi 10 kilometara: ");
    double vremeUMinutima = scanner.nextDouble();

    double vremeUSatima = vremeUMinutima / 60;

    double kilometaraUMiljama = 10 / 1.6;

    double brzinaUMiljamaPoSatu = kilometaraUMiljama / vremeUSatima;

    System.out.printf("Atleticarova brzina je %.2f milja/sat.", brzinaUMiljamaPoSatu);
  }
}