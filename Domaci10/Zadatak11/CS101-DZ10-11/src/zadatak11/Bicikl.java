package zadatak11;

class Bicikl {
  private String model;
  private double cenaPoSatu;
  private int maxSati;
  private boolean iznajmljen;

  public Bicikl(String model, double cenaPoSatu, int maxSati) {
    this.model = model;
    this.cenaPoSatu = cenaPoSatu;
    this.maxSati = maxSati;
    this.iznajmljen = false;
  }


  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public double getCenaPoSatu() {
    return cenaPoSatu;
  }

  public void setCenaPoSatu(double cenaPoSatu) {
    this.cenaPoSatu = cenaPoSatu;
  }

  public int getMaxSati() {
    return maxSati;
  }

  public void setMaxSati(int maxSati) {
    this.maxSati = maxSati;
  }

  public boolean isIznajmljen() {
    return iznajmljen;
  }

  public void setIznajmljen(boolean iznajmljen) {
    this.iznajmljen = iznajmljen;
  }
}
