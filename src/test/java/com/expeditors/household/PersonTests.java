package com.expeditors.household;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIterable;

import com.expeditors.household.model.Person;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PersonTests {

  @Test
  public void testPersonInit_Success() {
    Person person =
        new Person("\"Paul\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"22\"");

    assertThat(person.toString()).isEqualTo("SMITH PAUL, 123 MAIN ST, SEATTLE, WA, 22");
  }

  @ParameterizedTest
  @MethodSource("provideInputsForSortByLastNameAndFirstName")
  public void testSortByLastNameAndFirstName_Success(List<Person> personList,
      List<Person> expected) {

    List<Person> actual = personList.stream().sorted(Person.sortByLastNameAndFirstName())
        .collect(Collectors.toList());
    assertThatIterable(actual).isEqualTo(expected);

  }

  @Test
  public void testOlderThan_Success() {
    List<Person> personList = Arrays.asList(
        new Person("\"Paul\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"22\""),
        new Person("\"Brian\",\"Shannon\",\"123 Main St.\",\"Seattle\",\"WA\",\"28\""),
        new Person("\"Adam\",\"Kaul\",\"123 Main St.\",\"Seattle\",\"WA\",\"18\""),
        new Person("\"Ian\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"17\"")
    );

    List<Person> filteredList = personList.stream().filter(Person.olderThan(18))
        .collect(Collectors.toList());
    assertThat(filteredList).hasSize(2).allMatch(person -> person.getAge() > 18);
  }

  @Test
  public void testOlderThan_NoneFound_Success() {
    List<Person> personList = Arrays.asList(
        new Person("\"Paul\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"22\""),
        new Person("\"Brian\",\"Shannon\",\"123 Main St.\",\"Seattle\",\"WA\",\"28\""),
        new Person("\"Adam\",\"Kaul\",\"123 Main St.\",\"Seattle\",\"WA\",\"18\""),
        new Person("\"Ian\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"17\"")
    );

    List<Person> filteredList = personList.stream().filter(Person.olderThan(30))
        .collect(Collectors.toList());
    assertThat(filteredList.size()).isEqualTo(0);
  }

  @Test
  public void testOlderThan_AllOlder_Success() {
    List<Person> personList = Arrays.asList(
        new Person("\"Paul\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"22\""),
        new Person("\"Brian\",\"Shannon\",\"123 Main St.\",\"Seattle\",\"WA\",\"28\""),
        new Person("\"Adam\",\"Kaul\",\"123 Main St.\",\"Seattle\",\"WA\",\"18\""),
        new Person("\"Ian\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"17\"")
    );

    List<Person> filteredList = personList.stream().filter(Person.olderThan(15))
        .collect(Collectors.toList());
    assertThat(filteredList.size()).isEqualTo(personList.size());
  }

  private static Stream<Arguments> provideInputsForSortByLastNameAndFirstName() {
    return Stream.of(
        Arguments.of(
            Arrays.asList(
                new Person("\"Paul\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"22\""),
                new Person("\"Ian\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"21\"")),
            Arrays.asList(
                new Person("\"Ian\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"21\""),
                new Person("\"Paul\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"22\""))
        ),
        Arguments.of(
            Arrays.asList(
                new Person("\"Paul\",\"Swiss\",\"123 Main St.\",\"Seattle\",\"WA\",\"22\""),
                new Person("\"Ian\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"21\"")),
            Arrays.asList(
                new Person("\"Ian\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"21\""),
                new Person("\"Paul\",\"Swiss\",\"123 Main St.\",\"Seattle\",\"WA\",\"22\""))
        ),
        Arguments.of(
            Arrays.asList(
                new Person("\"Paul\",\"Swiss\",\"123 Main St.\",\"Seattle\",\"WA\",\"22\""),
                new Person("\"Paul\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"21\"")),
            Arrays.asList(
                new Person("\"Paul\",\"Smith\",\"123 Main St.\",\"Seattle\",\"WA\",\"21\""),
                new Person("\"Paul\",\"Swiss\",\"123 Main St.\",\"Seattle\",\"WA\",\"22\""))
        )
    );
  }

}
