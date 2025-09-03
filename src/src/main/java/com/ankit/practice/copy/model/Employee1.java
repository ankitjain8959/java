package com.ankit.practice.copy.model;

public class Employee1 {

  private int employeeId;
  private String employeeName;
  private Address employeeAddress;

  public Employee1(int employeeId, String employeeName, Address employeeAddress) {
    this.employeeId = employeeId;
    this.employeeName = employeeName;
    this.employeeAddress = employeeAddress;             // Shallow Copy: Copies the object’s fields as-is
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