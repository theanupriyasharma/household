package com.expeditors.household;

import static org.assertj.core.api.Assertions.assertThat;

import com.expeditors.household.model.Person;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class HouseHoldApplicationTests {

  HouseholdApplication householdApplication = new HouseholdApplication();

  @Test
  public void testAllAdultsPerHousehold() {
    Person personHouseholdA = new Person(
        "\"Paul\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"22\"");
    Person personHouseholdB = new Person(
        "\"Brian\",\"Shannon\",\"234 2nd Ave.\",\"Tacoma\",\"WA\",\"28\"");

    Map<String, List<Person>> actualResult =
        householdApplication.adultMembersOfAHouseHold(List.of(personHouseholdA, personHouseholdB));

    Map<String, List<Person>> expectedResult =
        Map.of(
            "123 MAIN ST, SEATTLE, WA", List.of(personHouseholdA),
            "234 2ND AVE, TACOMA, WA", List.of(personHouseholdB)
        );

    assertThat(actualResult).hasSize(expectedResult.size());
    assertThat(actualResult).containsExactlyInAnyOrderEntriesOf(expectedResult);
  }

  @Test
  public void testNoAdultsPerHousehold() {
    Person personHouseholdA = new Person(
        "\"Paul\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"17\"");
    Person personHouseholdB = new Person(
        "\"Brian\",\"Shannon\",\"234 2nd Ave.\",\"Tacoma\",\"WA\",\"18\"");

    Map<String, List<Person>> actualResult =
        householdApplication.adultMembersOfAHouseHold(List.of(personHouseholdA, personHouseholdB));

    Map<String, List<Person>> expectedResult =
        Map.of(
            "123 MAIN ST, SEATTLE, WA", Collections.emptyList(),
            "234 2ND AVE, TACOMA, WA", Collections.emptyList()
        );

    assertThat(actualResult).hasSize(expectedResult.size());
    assertThat(actualResult).containsExactlyInAnyOrderEntriesOf(expectedResult);
  }

  @Test
  public void testSingleAdultPerHousehold() {
    Person personHouseholdA = new Person(
        "\"Paul\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"23\"");
    Person person2HouseholdA = new Person(
        "\"Adam\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"16\"");
    Person personHouseholdB = new Person(
        "\"Brian\",\"Shannon\",\"234 2nd Ave.\",\"Tacoma\",\"WA\",\"26\"");
    Person person2HouseholdB = new Person(
        "\"Ian\",\"Shannon\",\"234 2nd Ave.\",\"Tacoma\",\"WA\",\"18\"");

    Map<String, List<Person>> actualResult =
        householdApplication.adultMembersOfAHouseHold(
            List.of(personHouseholdA, person2HouseholdA, personHouseholdB, person2HouseholdB));

    Map<String, List<Person>> expectedResult =
        Map.of(
            "123 MAIN ST, SEATTLE, WA", List.of(personHouseholdA),
            "234 2ND AVE, TACOMA, WA", List.of(personHouseholdB)
        );

    assertThat(actualResult).hasSize(expectedResult.size());
    assertThat(actualResult).containsExactlyInAnyOrderEntriesOf(expectedResult);
  }

  @Test
  public void testMultipleAdultsPerHousehold() {
    Person personHouseholdA = new Person(
        "\"Paul\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"23\"");
    Person person2HouseholdA = new Person(
        "\"Adam\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"26\"");
    Person person3HouseholdA = new Person(
        "\"Ian\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"16\"");
    Person personHouseholdB = new Person(
        "\"Brian\",\"Shannon\",\"234 2nd Ave.\",\"Tacoma\",\"WA\",\"26\"");
    Person person2HouseholdB = new Person(
        "\"Charles\",\"Shannon\",\"234 2nd Ave.\",\"Tacoma\",\"WA\",\"28\"");
    Person person3HouseholdB = new Person(
        "\"Fiona\",\"Shannon\",\"234 2nd Ave.\",\"Tacoma\",\"WA\",\"18\"");

    Map<String, List<Person>> actualResult =
        householdApplication.adultMembersOfAHouseHold(
            List.of(
                personHouseholdA, person2HouseholdA, person3HouseholdA,
                personHouseholdB, person2HouseholdB, person3HouseholdB
            )
        );

    Map<String, List<Person>> expectedResult = Map.of(
        "123 MAIN ST, SEATTLE, WA",
        Stream.of(personHouseholdA, person2HouseholdA).
            sorted(Person.sortByLastNameAndFirstName()).collect(
                Collectors.toList()),
        "234 2ND AVE, TACOMA, WA",
        Stream.of(personHouseholdB, person2HouseholdB).
            sorted(Person.sortByLastNameAndFirstName()).
            collect(Collectors.toList())
    );

    assertThat(actualResult).hasSize(expectedResult.size());
    assertThat(actualResult).containsExactlyInAnyOrderEntriesOf(expectedResult);
  }

}
