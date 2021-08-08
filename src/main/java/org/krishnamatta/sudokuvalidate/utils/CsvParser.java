package org.krishnamatta.sudokuvalidate.utils;

import java.io.File;
import java.util.Scanner;

public class CsvParser {

  public char[][] parse(String filePath) {
    return parse(filePath, "UTF-8", ",");
  }

  public char[][] parse(String filePath, String charsetName, String delimiter) {
    File file = new File(filePath);
    char[][] data = new char[SudokuConstants.GRID_SIZE][SudokuConstants.GRID_SIZE];

    try (Scanner scanner = new Scanner(file, charsetName)) {
      boolean firstLine = true;
      int rowIndex = 0;

      // for each line
      while (scanner.hasNextLine()) {
        // if grid size already reached
        if (SudokuConstants.GRID_SIZE == rowIndex) {
          throw new IllegalArgumentException(
              String.format("Input data has more than %d lines", SudokuConstants.GRID_SIZE));
        }

        // get next line
        String line = scanner.nextLine();

        // for first line, check BOM char and remove it
        if (firstLine) {
          line = checkAndRemoveUTF8BOM(line);
          firstLine = false;
        }

        // get char array from line.
        data[rowIndex] = splitLineToArray(line, delimiter, rowIndex);
        rowIndex = rowIndex + 1;
      }

      // if not enough rows
      if (SudokuConstants.GRID_SIZE != rowIndex) {
        throw new IllegalArgumentException(
            String.format("Input data has less than %d lines", SudokuConstants.GRID_SIZE));
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return data;
  }

  public char[] splitLineToArray(String line, String delimiter, int rowIndex) {
    if (line == null) throw new IllegalArgumentException("Invalid input argument");

    String[] strArr = line.split(delimiter, -1);

    // throw exception, for wrong size array
    if (strArr.length != SudokuConstants.GRID_SIZE) {
      throw new IllegalArgumentException(
          String.format(
              "Invalid input line [%s] in row [%d] with length of [%d]",
              line, rowIndex+1, strArr.length));
    }

    // convert each string into character
    char[] charArr = new char[SudokuConstants.GRID_SIZE];
    for (int i = 0; i < SudokuConstants.GRID_SIZE; i++) {
      charArr[i] = getChar(strArr[i], rowIndex, i);
    }
    return charArr;
  }

  public char getChar(String strNumber, int row, int col) {
    if (strNumber == null) throw new IllegalArgumentException("Invalid input argument");

    String trimmed = strNumber.trim();
    char out = SudokuConstants.BLANK_CHAR;

    // only only char should in string
    if (trimmed.length() == 1) {
      out = trimmed.charAt(0);
      // check char in the expected range
      if (!CharUtils.isCharInRange(out, SudokuConstants.MIN_NUMBER, SudokuConstants.MAX_NUMBER)) {
        throw new IllegalArgumentException(
            String.format("%s is not a valid number in row [%d], column [%d]", trimmed, row, col));
      }
    }
    // else, exception
    else if (trimmed.length() > 1) {
      throw new IllegalArgumentException(
          String.format("%s is not a valid number in row [%d], column [%d]", trimmed, row, col));
    }
    return out;
  }

  private String checkAndRemoveUTF8BOM(String line) {
    final String BOM = "\uFEFF";
    if (line.startsWith(BOM)) {
      return line.substring(1);
    }
    return line;
  }
}
