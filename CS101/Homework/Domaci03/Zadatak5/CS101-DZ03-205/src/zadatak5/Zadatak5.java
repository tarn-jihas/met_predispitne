package zadatak5;

import java.util.Scanner;
/*

Zadatak 5:
Kreirati projekat koji će se zvati CS101-DZ03-205. U okviru projekta treba da postoji paket
zadatak5 i pokretačka klasa Zadatak5.
Napišite program koji traži od korisnika da unese središnje koordinate (x,y) i poluprečnike dva
kruga i određuje da li je drugi krug unutar prvog ili se preklapa sa prvim, kao što je prikazano na
slici.
Krug2 je unutar kruga1 ako se rastojanje između dva centra < = r1 − r2 i krug2 se preklapa sa
krugom1 ako je rastojanje između dva centra <= r1 + r2. Testirajte svoj program da pokrijete sve
slučajeve.

 */
public class Zadatak5 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Unesite koordinate centra prvog kruga (x1, y1) i poluprecnik (r1):");
    double x1 = scanner.nextDouble();
    double y1 = scanner.nextDouble();
    double r1 = scanner.nextDouble();

    System.out.println("Unesite koordinate centra drugog kruga (x2, y2) i poluprecnik (r2):");
    double x2 = scanner.nextDouble();
    double y2 = scanner.nextDouble();
    double r2 = scanner.nextDouble();

    double rastojanje = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

    if (rastojanje <= Math.abs(r1 - r2)) {
      System.out.println("Drugi krug je unutar prvog kruga.");
    } else if (rastojanje <= (r1 + r2)) {
      System.out.println("Drugi krug se preklapa sa prvim krugom.");
    } else {
      System.out.println("Drugi krug se ne preklapa i nije unutar prvog kruga.");
    }

    scanner.close();
  }


}
