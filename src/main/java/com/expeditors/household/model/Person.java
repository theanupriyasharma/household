package com.expeditors.household.model;


import com.expeditors.household.DataCleaner;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Predicate;

public class Person {

  private final String firstName;
  private final String lastName;
  private final int age;
  private final String address;

  public Person(String line) {
    // some data like address has comma hence had to use "," as a separator
    String[] fields = line.split("\",\"");

    this.firstName = DataCleaner.sanitizeName(fields[0]);
    this.lastName = DataCleaner.sanitizeName(fields[1]);
    this.address =
        DataCleaner.sanitizeAddress(fields[2]) + ", "
            + DataCleaner.sanitizeAddress(fields[3]) + ", "
            + DataCleaner.sanitizeAddress(fields[4]);
    this.age = Integer.parseInt(DataCleaner.sanitizeAge(fields[5]));
  }

  public static Comparator<Person> sortByLastNameAndFirstName() {
    return Comparator.comparing((Person p) -> p.getLastName())
        .thenComparing((Person p) -> p.getFirstName());
  }

  public static Predicate<Person> olderThan(Integer age) {
    return person -> person.getAge() > age;
  }

  public String toString() {
    return lastName + " " + firstName + ", " + address + ", " + age;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getAge() {
    return age;
  }

  public String getAddress() {
    return address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return age == person.age && Objects.equals(firstName, person.firstName)
        && Objects.equals(lastName, person.lastName) && Objects.equals(address,
        person.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, age, address);
  }

}
