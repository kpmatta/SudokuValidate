package org.krishnamatta.sudokuvalidate.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.krishnamatta.sudokuvalidate.utils.CharUtils.isCharInRange;

public class CharUtilsTests {

  @Test
  public void testIsCharInRange() {
    char min = '5';
    char max = '9';
    for (char c = '5'; c <= '9'; c++) {
      assertTrue(isCharInRange(c, min, max));
    }

    assertTrue(isCharInRange('1', '1', '1'));
    assertTrue(isCharInRange('2', '6', '1'));
    assertFalse(isCharInRange('0', min, max));
    assertFalse(isCharInRange('.', min, max));
    assertFalse(isCharInRange('-', min, max));
    assertFalse(isCharInRange('\0', min, max));
  }
}