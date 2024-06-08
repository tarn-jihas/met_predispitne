package model;

import java.util.Objects;
import java.util.UUID;

public class Product {

  private UUID id;
  private String name;
  private double price;
  private ProductType category;


  public Product(UUID id, String name, double price, ProductType productType) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.category = productType;
  }

  public Product(String name, double price, ProductType productType) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.price = price;
    this.category = productType;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public ProductType getCategory() {
    return category;
  }

  public void setCategory(ProductType category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", price=" + price +
        ", category=" + category +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Double.compare(product.price, price) == 0 && name.equals(product.name)
        && category == product.category;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, price, category);
  }
}