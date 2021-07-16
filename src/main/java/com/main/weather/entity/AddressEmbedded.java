package com.main.weather.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AddressEmbedded {
    @Column
    private String address1;
    @Column
    private String address2;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private String postalCode;
    @Column
    private String country;
}
