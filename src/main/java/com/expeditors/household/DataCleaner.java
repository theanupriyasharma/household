package com.expeditors.household;

public class DataCleaner {

  public static String sanitizeAddress(String str) {
   // we use address to group family members hence each of the address needs
   // to be as close as possible to each other for grouping to be successful
    return str.replaceAll("\"", "")
        .replaceAll(",", "")
        .replaceAll("\\.", "")
        .trim()
        .replaceAll(" +", " ")
        .toUpperCase();
  }

  public static String sanitizeName(String str) {
//  Since the separator in line is "," quote comma quote , hence the firstName
//  has an additional quote in the beginning hence we need to sanitize it
    return str.replaceAll("\"", "")
        .trim()
        .replaceAll(" +", " ")
        .toUpperCase();
  }

  public static String sanitizeAge(String str) {
//  Since the separator in line is "," quote comma quote , hence the
//  age has an additional quote in the end hence we need to sanitize it
    return str.replaceAll("\"", "")
        .trim();
  }
}
