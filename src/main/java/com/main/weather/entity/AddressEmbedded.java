package com.main.weather.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Builder;

@Embeddable
public class AddressEmbedded {
  @Column private String address1;
  @Column private String address2;
  @Column private String city;
  @Column private String state;
  @Column private String postalCode;
  @Column private String country;

  @Builder
  public AddressEmbedded(
      String address1,
      String address2,
      String city,
      String state,
      String postalCode,
      String country) {
    this.address1 = address1;
    this.address2 = address2;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.country = country;
  }
}
