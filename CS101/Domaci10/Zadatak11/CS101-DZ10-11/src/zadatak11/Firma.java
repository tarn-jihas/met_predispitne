package zadatak11;

import java.util.ArrayList;
import java.util.List;

class Firma {
  private String naziv;
  private String adresa;
  private List<Bicikl> bicikli;
  private List<Iznajmljivanje> iznajmljivanja;

  public Firma(String naziv, String adresa) {
    this.naziv = naziv;
    this.adresa = adresa;
    this.bicikli = new ArrayList<>();
    this.iznajmljivanja = new ArrayList<>();
  }

  public void dodajBicikl(Bicikl bicikl) {
    bicikli.add(bicikl);
  }

  public void iznajmiBicikl(Korisnik korisnik, Bicikl bicikl, int vreme) {
    if (!bicikl.isIznajmljen() && vreme <= bicikl.getMaxSati()) {
      Iznajmljivanje iznajmljivanje = new Iznajmljivanje(korisnik, bicikl, vreme);
      iznajmljivanja.add(iznajmljivanje);
      bicikl.setIznajmljen(true);
      System.out.println("Bicikl " + bicikl.getModel() + " je uspešno iznajmljen.");
    } else {
      System.out.println("Bicikl " + bicikl.getModel() + " nije moguće iznajmiti.");
    }
  }

  public int vremeDoSlobodnihBicikala() {
    int maxVreme = 0;
    for (Iznajmljivanje iznajmljivanje : iznajmljivanja) {
      if (iznajmljivanje.getVreme() > maxVreme) {
        maxVreme = iznajmljivanje.getVreme();
      }
    }
    return maxVreme;
  }

  public String getNaziv() {
    return naziv;
  }

  public void setNaziv(String naziv) {
    this.naziv = naziv;
  }

  public String getAdresa() {
    return adresa;
  }

  public void setAdresa(String adresa) {
    this.adresa = adresa;
  }

  public List<Bicikl> getBicikli() {
    return bicikli;
  }

  public void setBicikli(List<Bicikl> bicikli) {
    this.bicikli = bicikli;
  }

  public List<Iznajmljivanje> getIznajmljivanja() {
    return iznajmljivanja;
  }

  public void setIznajmljivanja(List<Iznajmljivanje> iznajmljivanja) {
    this.iznajmljivanja = iznajmljivanja;
  }
}
