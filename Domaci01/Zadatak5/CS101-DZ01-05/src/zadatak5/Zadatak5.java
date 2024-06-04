package zadatak5;

public class Zadatak5 {
/**
 * Kreirati projekat koji de se zvati CS101-DZ01-05. U okviru projekta treba da postoji paket zadatak5
 * i pokretačka klasa Zadatak5.
 * Neka je je abcd = Vaš broj indeksa. Napišite program koji će za brojeve x=ab i y=cd izračunati
 * (x+y)^2 i (x-y)^2 po sledećoj formuli:
 * (𝑥 ± 𝑦)2 = 𝑥2 ± 2 ∗ 𝑥 ∗ 𝑦 + 𝑦2
 */
  public static void main(String[] args) {
    int abcd = 6159;
    int x = abcd / 100; // x = ab = 61
    int y = abcd % 100; // y = cd = 59

    // (x+y)^2
    int x_plus_y_kvadrat = (x * x) + (2 * x * y) + (y * y);

    // (x-y)^2
    int x_minus_y_kvadrat = (x * x) - (2 * x * y) + (y * y);

    // Ispisujemo rezultate.
    System.out.println("(x+y)^2 = " + x_plus_y_kvadrat);
    System.out.println("(x-y)^2 = " + x_minus_y_kvadrat);
  }
}