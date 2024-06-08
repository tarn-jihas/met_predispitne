package zadatak11;

public class Main {
  public static void main(String[] args) {
    Firma firma = new Firma("Iznajmljivanje bicikala", "Glavna ulica 123");

    Bicikl bicikl1 = new Bicikl("Model 1", 10.0, 5);
    Bicikl bicikl2 = new Bicikl("Model 2", 12.0, 4);
    Bicikl bicikl3 = new Bicikl("Model 3", 8.0, 6);

    firma.dodajBicikl(bicikl1);
    firma.dodajBicikl(bicikl2);
    firma.dodajBicikl(bicikl3);

    Korisnik korisnik1 = new Korisnik("Marko", "Markov", "1234567890123");
    Korisnik korisnik2 = new Korisnik("Ana", "Annie", "9876543210987");

    firma.iznajmiBicikl(korisnik1, bicikl1, 3);
    firma.iznajmiBicikl(korisnik2, bicikl2, 2);
    firma.iznajmiBicikl(korisnik1, bicikl3, 4);

    int vreme = firma.vremeDoSlobodnihBicikala();
    System.out.println("Svi bicikli će biti slobodni za " + vreme + " sati.");
  }
}