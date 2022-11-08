package com.expeditors.household;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class DataCleanerTests {

  @ParameterizedTest
  @MethodSource("provideStringsForSanitizedAddress")
  public void testSanitizeAddress_Success(String input, String expected){
      assertThat(DataCleaner.sanitizeAddress(input)).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource("provideStringsForFailedSanitizedAddress")
  public void testSanitizeAddress_Fails(String input1, String input2){
    assertThat(DataCleaner.sanitizeAddress(input1)).isNotEqualTo(DataCleaner.sanitizeAddress(input2));
  }

  @ParameterizedTest
  @MethodSource("provideStringsForSanitizedName")
  public void testSanitizeName_Success(String input, String expected){
    assertThat(DataCleaner.sanitizeAddress(input)).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource("provideStringsForFailedSanitizedName")
  public void testSanitizeName_Fails(String input1, String input2){
    assertThat(DataCleaner.sanitizeName(input1)).isNotEqualTo(DataCleaner.sanitizeName(input2));
  }

  @ParameterizedTest
  @MethodSource("provideStringsForSanitizedAge")
  public void testSanitizeAge_Success(String input, String expected){
    assertThat(DataCleaner.sanitizeAddress(input)).isEqualTo(expected);
  }

  @ParameterizedTest
  @MethodSource("provideStringsForFailedSanitizedAge")
  public void testSanitizeAge_Fails(String input1, String input2){
    assertThat(DataCleaner.sanitizeAge(input1)).isNotEqualTo(DataCleaner.sanitizeAge(input2));
  }

  private static Stream<Arguments> provideStringsForSanitizedAddress() {
    return Stream.of(
        Arguments.of("234 2nd Ave., Apt. 200, Tacoma WA", "234 2ND AVE APT 200 TACOMA WA"),
        Arguments.of("234 2nd ave. Tacoma WA", "234 2ND AVE TACOMA WA"),
        Arguments.of("234 2nd ave. Tacoma, WA", "234 2ND AVE TACOMA WA"),
        Arguments.of("234 2nd ave Tacoma WA", "234 2ND AVE TACOMA WA"),
        Arguments.of("234 2ND AVE TACOMA WA ", "234 2ND AVE TACOMA WA"),
        Arguments.of("234 2ND  AVE   TACOMA WA ", "234 2ND AVE TACOMA WA"),
        Arguments.of("  234 2ND AVE TACOMA WA  ", "234 2ND AVE TACOMA WA")
    );
  }

  private static Stream<Arguments> provideStringsForFailedSanitizedAddress() {
    return Stream.of(
        Arguments.of("234 2nd Ave., Apt. 200, Tacoma WA", "234 2nd Avenue, Apt. 200, Tacoma WA"),
        Arguments.of("234 2nd Ave., Apt. 200, Tacoma WA", "Apt. 200, 234 2nd Avenue, Tacoma WA")
    );
  }

  private static Stream<Arguments> provideStringsForSanitizedName() {
    return Stream.of(
        Arguments.of("Ian Smith", "IAN SMITH"),
        Arguments.of("  Ian Smith  ", "IAN SMITH"),
        Arguments.of("IAN SMITH", "IAN SMITH"),
        Arguments.of("IAN  SMITH", "IAN SMITH"),
        Arguments.of("\"Ian\" \"Smith\"", "IAN SMITH")
    );
  }

  private static Stream<Arguments> provideStringsForFailedSanitizedName() {
    return Stream.of(
        Arguments.of("Ian Smith", "Smith Ian"),
        Arguments.of("Ian Smith", "Ian' Smith")
    );
  }

  private static Stream<Arguments> provideStringsForSanitizedAge() {
    return Stream.of(
        Arguments.of("23", "23"),
        Arguments.of("  23   ", "23"),
        Arguments.of("\"23\"", "23")
    );
  }

  private static Stream<Arguments> provideStringsForFailedSanitizedAge() {
    return Stream.of(
        Arguments.of("23", "32"),
        Arguments.of("23", "23.")
    );
  }

}
