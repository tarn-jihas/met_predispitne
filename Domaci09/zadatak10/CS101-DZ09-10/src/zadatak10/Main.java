package zadatak10;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Valuta[] valute = new Valuta[5];
    valute[0] = new Valuta("Evro", "EUR", 117.5, 117.0);
    valute[1] = new Valuta("Američki dolar", "USD", 106.5, 106.0);
    valute[2] = new Valuta("Britanska funta", "GBP", 132.5, 132.0);
    valute[3] = new Valuta("Švajcarski franak", "CHF", 114.5, 114.0);
    valute[4] = new Valuta("Kanadski dolar", "CAD", 79.5, 79.0);

    Scanner scanner = new Scanner(System.in);

    System.out.println("Izaberite valutu za kupovinu: ");
    for (int i = 0; i < valute.length; i++) {
      System.out.println((i + 1) + ". " + valute[i].getNaziv());
    }

    System.out.println("Unesite redni broj valute: ");
    int izborKupovine = scanner.nextInt();
    Valuta izabranaValutaKupovina = valute[izborKupovine - 1];

    System.out.print("Unesite iznos u dinarima za kupovinu: ");
    double iznos = scanner.nextDouble();

    double dobijenIznos = izabranaValutaKupovina.kupovina(iznos);
    System.out.println("Dobijen iznos: " + dobijenIznos + " " + izabranaValutaKupovina.getOznaka());
    System.out.println(izabranaValutaKupovina);

    System.out.println("\nIzaberite valutu za prodaju:");
    for (int i = 0; i < valute.length; i++) {
      System.out.println((i + 1) + ". " + valute[i].getNaziv());
    }

    int izborProdaje = scanner.nextInt();
    Valuta izabranaValutaProdaja = valute[izborProdaje - 1];

    System.out.print("Unesite iznos u stranoj valuti za prodaju: ");
    double iznosStraneValute = scanner.nextDouble();

    double dobijenIznosUDinarima = izabranaValutaProdaja.prodaja(iznosStraneValute);
    System.out.println("Dobijen iznos u dinarima: " + dobijenIznosUDinarima);
    System.out.println(izabranaValutaProdaja);
  }

}