package com.ankit.practice.immutable;


import com.ankit.practice.immutable.model.Address;

/*
* Immutable class/object: cannot be changed once created
*
* 1. final class
* 2. Make all fields as private & final
* 3. Initialize all fields in the constructor
* 4. Use defensive copying of mutable objects in the constructor & return a copy from the getter method
* 5. Avoid setters
* */
public final class ImmutableEmployee {

  private final int employeeId;
  private final String employeeName;
  private final Address address;

  public ImmutableEmployee(int employeeId, String employeeName, Address address) {
    this.employeeId = employeeId;
    this.employeeName = employeeName;

    Address deepCopyAddress = new Address(address.getHouseId(), address.getStreetName());
    this.address = deepCopyAddress;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  // To return a defensive copy of mutable object in the getter
  public Address getAddress() {
    return new Address(address.getHouseId(), address.getStreetName());
  }


  /* if you directly return the original reference of mutable object (i.e. Address) from the getter method,
  * public Address getAddress() { return address; }
  *
  * and since Address has setters or mutable fields, then callers can still do this:
  * ImmutableEmployee emp = new ImmutableEmployee(1, "Alice", new Address(101, "Main St"));
  * emp.getAddress().setStreetName("Changed Street");  // <-- modifies internal state
  *
  * This changes the internal state of ImmutableEmployee, so immutability is broken.
  * */
}
