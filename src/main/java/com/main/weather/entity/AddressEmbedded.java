package com.main.weather.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class AddressEmbedded {
  @Column private String address1;
  @Column private String address2;
  @Column private String city;
  @Column private String state;
  @Column private String postalCode;
  @Column private String country;
}
