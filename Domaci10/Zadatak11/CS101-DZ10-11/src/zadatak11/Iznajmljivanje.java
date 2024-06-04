package zadatak11;

class Iznajmljivanje {
  private Korisnik korisnik;
  private Bicikl bicikl;
  private int vreme;

  public Iznajmljivanje(Korisnik korisnik, Bicikl bicikl, int vreme) {
    this.korisnik = korisnik;
    this.bicikl = bicikl;
    this.vreme = vreme;
  }

  public Korisnik getKorisnik() {
    return korisnik;
  }

  public void setKorisnik(Korisnik korisnik) {
    this.korisnik = korisnik;
  }

  public Bicikl getBicikl() {
    return bicikl;
  }

  public void setBicikl(Bicikl bicikl) {
    this.bicikl = bicikl;
  }

  public int getVreme() {
    return vreme;
  }

  public void setVreme(int vreme) {
    this.vreme = vreme;
  }
}