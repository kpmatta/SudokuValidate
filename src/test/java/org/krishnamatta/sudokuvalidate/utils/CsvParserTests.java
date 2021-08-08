package org.krishnamatta.sudokuvalidate.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.krishnamatta.sudokuvalidate.utils.SudokuConstants.MAX_NUMBER;

public class CsvParserTests {

  @Test
  public void testGetCharWithIllegalNull() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          CsvParser cp = new CsvParser();
          cp.getChar(null, 0, 0);
        });
  }

  @Test
  public void testGetCharWithIllegalInputNumber() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          CsvParser cp = new CsvParser();
          cp.getChar("12", 0, 0);
        });
  }

  @Test
  public void testGetCharWithIllegalInputNumberString() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          CsvParser cp = new CsvParser();
          cp.getChar("1a", 0, 0);
        });
  }

  @Test
  public void testGetCharWithIllegalInputString() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          CsvParser cp = new CsvParser();
          cp.getChar("Ab", 0, 0);
        });
  }

  @Test
  public void testGetCharWithIllegalInputZeroString() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> {
          CsvParser cp = new CsvParser();
          cp.getChar("0", 0, 0);
        });
  }

  @Test
  public void testGetCharWithBlank() {
    CsvParser cp = new CsvParser();
    assertEquals('\0', cp.getChar("", 0, 0));
    assertEquals('\0', cp.getChar(" ", 0, 0));
    assertEquals('\0', cp.getChar("     ", 0, 0));
    assertEquals('\0', cp.getChar("\t", 0, 0));
  }

  @Test
  public void testGetCharWithRightInput() {
    CsvParser cp = new CsvParser();
    for (char c = '1'; c <= MAX_NUMBER; c++) {
      assertEquals(c, cp.getChar(Character.toString(c), 0, 0));
      assertEquals(c, cp.getChar(String.format(" %c", c), 0, 0));
      assertEquals(c, cp.getChar(String.format("%c ", c), 0, 0));
      assertEquals(c, cp.getChar(String.format(" %c ", c), 0, 0));
    }
  }

  @Test
  public void testSplitLineToArray() {
    String inLineStr = "1,,3,4,5,6,7,8,9";
    char[] exp = new char[] {'1', '\0', '3', '4', '5', '6', '7', '8', '9'};
    char[] lineArr = new CsvParser().splitLineToArray(inLineStr, ",", 0);
    assertArrayEquals(exp, lineArr);
  }

  @Test
  public void testParseFile() {
    String projectPath = System.getProperty("user.dir");
    try {
      char[][] expected = {
        {'1', '2', '3', '4', '5', '6', '7', '8', '9'},
        {'1', '2', '3', '4', '5', '6', '7', '8', '9'},
        {'1', '2', '3', '4', '5', '6', '7', '8', '9'},
        {'1', '2', '3', '4', '5', '6', '7', '8', '9'},
        {'1', '2', '3', '4', '5', '6', '7', '8', '9'},
        {'1', '2', '3', '4', '5', '6', '7', '8', '9'},
        {'1', '2', '3', '4', '5', '6', '7', '8', '9'},
        {'1', '2', '3', '4', '5', '6', '7', '8', '9'},
        {'1', '2', '3', '4', '5', '6', '7', '8', '9'}
      };

      String filePath = Paths.get(projectPath, "/TestFiles/sudoku.csv").toString();
      char[][] data = new CsvParser().parse(filePath);

      for (int i = 0; i < 9; i++) {
        assertArrayEquals(expected[i], data[i]);
      }
    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
  }

  @Test
  public void testParseReadPartialData() {
    String projectPath = System.getProperty("user.dir");
    try {
      char[][] expected = {
        {'9', '\0', '4', '\0', '6', '\0', '7', '\0', '1'},
        {'\0', '2', '\0', '4', '\0', '3', '\0', '8', '\0'},
        {'8', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '4'},
        {'\0', '\0', '1', '8', '4', '9', '6', '\0', '\0'},
        {'\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0'},
        {'\0', '\0', '3', '2', '5', '7', '9', '\0', '\0'},
        {'4', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '7'},
        {'\0', '8', '\0', '6', '\0', '4', '\0', '5', '\0'},
        {'5', '\0', '6', '\0', '8', '\0', '2', '3', '\0'}
      };

      String filePath = Paths.get(projectPath, "/TestFiles/SampleValidFile.csv").toString();
      char[][] data = new CsvParser().parse(filePath);

      for (int i = 0; i < 9; i++) {
        assertArrayEquals(expected[i], data[i]);
      }
    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
  }
}