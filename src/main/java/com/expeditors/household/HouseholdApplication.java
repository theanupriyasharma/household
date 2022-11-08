package com.expeditors.household;

import static com.expeditors.household.model.Person.olderThan;
import static com.expeditors.household.model.Person.sortByLastNameAndFirstName;

import com.expeditors.household.model.Person;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HouseholdApplication {

  public static void main(String[] args) throws IOException {
    HouseholdApplication householdApplication = new HouseholdApplication();

    List<String> fileList =
        args.length == 0 ? householdApplication.readFile("src/main/resources/data.txt")
            : householdApplication.readFile(args[0]);
    List<Person> personList = transformStringsToPersons(fileList);
    householdApplication.adultMembersOfAHouseHold(personList);
  }

  public Map<String, List<Person>> adultMembersOfAHouseHold(List<Person> personList) {
    Map<String, List<Person>> householdByAddress = groupPersonByAddress(personList);

    for (Map.Entry<String, List<Person>> household : householdByAddress.entrySet()) {

      System.out.println("Household Address :" + household.getKey());
      System.out.println("Household Size :" + household.getValue().size());
      System.out.println("");

      List<Person> sortedMemberOlderThanEighteen = household.getValue().stream()
          .sorted(sortByLastNameAndFirstName())
          .filter(olderThan(18))
          .collect(Collectors.toList());

      printPersonList(sortedMemberOlderThanEighteen);

      System.out.println("");

      //for each household update the filtered list to ordered person for only adults
      household.setValue(sortedMemberOlderThanEighteen);
    }

    return householdByAddress;
  }

  private void printPersonList(List<Person> sortedMemberOlderThanEighteen) {
    for (Person person : sortedMemberOlderThanEighteen) {
      System.out.println(person);
    }
  }

  private List<String> readFile(String file) throws IOException {
    try (InputStream inputStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

      return reader.lines().collect(Collectors.toList());
    }
  }

  private Map<String, List<Person>> groupPersonByAddress(List<Person> personList) {
    Map<String, List<Person>> grouping = new HashMap<>();
    for (Person person : personList) {

      if (grouping.containsKey(person.getAddress())) {
        grouping.get(person.getAddress()).add(person);
      } else {
        List<Person> newHousehold = new ArrayList<>();
        newHousehold.add(person);
        grouping.put(person.getAddress(), newHousehold);
      }
    }
    return grouping;
  }

  private static List<Person> transformStringsToPersons(List<String> lines) {

    List<Person> personList = new ArrayList<>();

    for (String eachLine : lines) {
      Person p = new Person(eachLine);
      personList.add(p);
    }

    return personList;
  }

}
