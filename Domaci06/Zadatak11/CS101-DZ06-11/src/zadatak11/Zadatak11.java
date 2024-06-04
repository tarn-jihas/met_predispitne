package zadatak11;

public class Zadatak11 {
  public static int[] nadjiMinMax(int... brojevi) {
    int najveci = brojevi[0];
    int najmanji = brojevi[0];

    for (int broj : brojevi) {
      if (broj > najveci) {
        najveci = broj;
      }
      if (broj < najmanji) {
        najmanji = broj;
      }
    }

    return new int[]{najmanji, najveci};
  }

  public static void main(String[] args) {
    int[] rezultat = nadjiMinMax(3, 7, 9, 2, 5, 5, 8, 1, 4);
    System.out.println("Najmanji broj je: " + rezultat[0]);
    System.out.println("Najveci broj je: " + rezultat[1]);
  }
}