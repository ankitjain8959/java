package com.ankit.practice.copy.model;

public class Address {

  private int houseId;
  private String streetName;

  public Address(int houseId, String streetName) {
    this.houseId = houseId;
    this.streetName = streetName;
  }

  public int getHouseId() {
    return this.houseId;
  }

  public String getStreetName() {
    return this.streetName;
  }

  public void setHouseId(int houseId) {
    this.houseId = houseId;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }
}