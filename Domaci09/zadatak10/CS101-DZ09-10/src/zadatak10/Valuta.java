package zadatak10;

class Valuta {
  private String naziv;
  private String oznaka;
  private double prodajniKurs;
  private double kupovniKurs;

  public Valuta() {
  }

  public Valuta(String naziv, String oznaka, double prodajniKurs, double kupovniKurs) {
    this.naziv = naziv;
    this.oznaka = oznaka;
    this.prodajniKurs = prodajniKurs;
    this.kupovniKurs = kupovniKurs;
  }


  public String getNaziv() {
    return naziv;
  }

  public void setNaziv(String naziv) {
    this.naziv = naziv;
  }

  public String getOznaka() {
    return oznaka;
  }

  public void setOznaka(String oznaka) {
    this.oznaka = oznaka;
  }

  public double getProdajniKurs() {
    return prodajniKurs;
  }

  public void setProdajniKurs(double prodajniKurs) {
    this.prodajniKurs = prodajniKurs;
  }

  public double getKupovniKurs() {
    return kupovniKurs;
  }

  public void setKupovniKurs(double kupovniKurs) {
    this.kupovniKurs = kupovniKurs;
  }

  public double kupovina(double dinari) {
    return dinari / prodajniKurs;
  }

  public double prodaja(double stranaValuta) {
    return stranaValuta * kupovniKurs;
  }

  @Override
  public String toString() {
    return "Valuta: " + naziv + " (" + oznaka + ")\n" +
        "Prodajni kurs: " + prodajniKurs + "\n" +
        "Kupovni kurs: " + kupovniKurs;
  }
}
