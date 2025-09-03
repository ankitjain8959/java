package com.ankit.practice.copy.model;

public class Employee2 {

  private int employeeId;
  private String employeeName;
  private Address employeeAddress;

  public Employee2(int employeeId, String employeeName, Address employeeAddress) {
    this.employeeId = employeeId;
    this.employeeName = employeeName;

    Address deepCopyAddress = new Address(employeeAddress.getHouseId(), employeeAddress.getStreetName());       // Deep Copy: Creating a completely independent copy of the object
    this.employeeAddress = deepCopyAddress;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public Address getEmployeeAddress() {
    return employeeAddress;
  }

  public void setEmployeeAddress(Address employeeAddress) {
    this.employeeAddress = employeeAddress;
  }
}