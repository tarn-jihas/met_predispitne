package zadatak8;

public class Zadatak8 {

  public static void main(String[] args) {
    final int PER_LINE = 10;
    int count = 0;

    for (int i = 100; i <= 200; i++) {
      if ((i % 5 == 0 || i % 6 == 0) && !(i % 5 == 0 && i % 6 == 0)) {
        System.out.print(i + " ");
        count++;

        if (count % PER_LINE == 0) {
          System.out.println();
        }
      }
    }
  }
}