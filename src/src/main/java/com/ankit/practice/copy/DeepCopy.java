package com.ankit.practice.copy;

import com.ankit.practice.copy.model.Address;
import com.ankit.practice.copy.model.Employee2;

/*
* `Deep copy`: Creates a completely independent copy of the object, including new copies of any referenced objects.
*  Changes in the original won’t affect the copy, and vice versa.
*
*  Deep Copy is also known as Defensive Copying and is important in creating immutable objects
* */
public class DeepCopy {

  public static void main(String[] args) {
   Address address = new Address(101, "A. Street");
   Employee2 employee2 = new Employee2(1, "Ankit", address);

    System.out.println("Employee's id: " + employee2.getEmployeeId());
    System.out.println("Employee's name: " + employee2.getEmployeeName());
    System.out.println("Employee's address house Id: " + employee2.getEmployeeAddress().getHouseId());
    System.out.println("Employee's address street name: " + employee2.getEmployeeAddress().getStreetName());

    System.out.println("---------------------------------------------------");

    // Changing address won't affect original address
   address.setHouseId(102);
   address.setStreetName("B. Street");

    System.out.println("Employee's id: " + employee2.getEmployeeId());
    System.out.println("Employee's name: " + employee2.getEmployeeName());
    System.out.println("Employee's address house Id: " + employee2.getEmployeeAddress().getHouseId());
    System.out.println("Employee's address street name: " + employee2.getEmployeeAddress().getStreetName());
  }
}