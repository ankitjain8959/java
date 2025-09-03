package com.ankit.practice.copy;


import com.ankit.practice.copy.model.Address;
import com.ankit.practice.copy.model.Employee1;

/*
* `Shallow copy`: Copies the object’s fields as-is. If a field is a reference to another object, the reference is copied and not the actual object. So, the new object is dependent on the original object.
*  Meaning, both original and copy points to the same referenced objects. Changes in one affect the other for those references.
* */
public class ShallowCopy {

  public static void main(String[] args) {
   Address address = new Address(101, "A. Street");
   Employee1 employee1 = new Employee1(1, "Ankit", address);

    System.out.println("Employee's id: " + employee1.getEmployeeId());
    System.out.println("Employee's name: " + employee1.getEmployeeName());
    System.out.println("Employee's address house Id: " + employee1.getEmployeeAddress().getHouseId());
    System.out.println("Employee's address street name: " + employee1.getEmployeeAddress().getStreetName());

    System.out.println("---------------------------------------------------");

    // Changing address will change the original object (because both the original & copy objects are pointing to the same referenced objects)
   address.setHouseId(102);
   address.setStreetName("B. Street");

    System.out.println("Employee's id: " + employee1.getEmployeeId());
    System.out.println("Employee's name: " + employee1.getEmployeeName());
    System.out.println("Employee's address house Id: " + employee1.getEmployeeAddress().getHouseId());
    System.out.println("Employee's address street name: " + employee1.getEmployeeAddress().getStreetName());
  }
}