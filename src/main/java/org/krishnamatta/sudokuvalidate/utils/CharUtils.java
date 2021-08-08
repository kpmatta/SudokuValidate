package org.krishnamatta.sudokuvalidate.utils;

public class CharUtils {
  public static boolean isCharInRange(char in, char minValue, char maxValue) {
    char min = minValue < maxValue ? minValue : maxValue;
    char max = minValue < maxValue ? maxValue : minValue;
    return (in >= min && in <= max);
  }
}
