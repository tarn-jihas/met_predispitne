package zadatak;

public class Zadatak1 {

  /*

  Kreirati projekat koji će se zvati CS101-DZ02-01. U okviru projekta treba da postoji paket zadatak1
  i pokretačka klasa Zadatak1.
  Napišite program koji će za abcd Kelvinovih stepeni izračunati vrednost u Celzijusovim stepenima.
  Uslovi:
    -Neka je abcd vaš broj indeksa. Ovu varijablu definisati kao konstantu.
    -Stepeni Kelvina = stepeni Celzijusa + 273.15
    -Rezultat ispisati na konzoli.


   */
  public static void main(String[] args) {
    final double KELVIN_CONST = 273.15;
    final double ABCD_CONST = 6159;

    // Mislim da postoji typo u zatku, posto je ABCD broj kelvina, trebalo bi da trazimo celzijus tako sto oduzmemo ABCD od KELVIN_CONST:
    double celzijus = ABCD_CONST - KELVIN_CONST;

    System.out.printf("%.2f Kelvinovih stepeni je %.2f Celzijusovih stepeni.%n", ABCD_CONST,
        celzijus);

  }
}