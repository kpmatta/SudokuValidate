package org.krishnamatta.sudokuvalidate;

import org.junit.jupiter.api.Test;
import org.krishnamatta.sudokuvalidate.utils.Status;
import static org.junit.jupiter.api.Assertions.*;
import static org.krishnamatta.sudokuvalidate.utils.SudokuConstants.GRID_SIZE;

public class SudokuValidateTests extends Validate {

  private final char[][] valid_board = {
    {'8', '1', '2', '7', '5', '3', '6', '4', '9'},
    {'9', '4', '3', '6', '8', '2', '1', '7', '5'},
    {'6', '7', '5', '4', '9', '1', '2', '8', '3'},
    {'1', '5', '4', '2', '3', '7', '8', '9', '6'},
    {'3', '6', '9', '8', '4', '5', '7', '2', '1'},
    {'2', '8', '7', '1', '6', '9', '5', '3', '4'},
    {'5', '2', '1', '9', '7', '4', '3', '6', '8'},
    {'4', '3', '8', '5', '2', '6', '9', '1', '7'},
    {'7', '9', '6', '3', '1', '8', '4', '5', '2'}
  };

  private final char[][] in_valid_board = {
    {'9', '1', '2', '7', '5', '3', '6', '4', '9'},
    {'9', '4', '3', '6', '8', '2', '1', '7', '5'},
    {'6', '7', '5', '4', '9', '1', '2', '8', '3'},
    {'1', '5', '4', '2', '3', '7', '8', '9', '6'},
    {'3', '6', '9', '8', '4', '5', '7', '2', '1'},
    {'2', '8', '7', '1', '6', '9', '5', '3', '4'},
    {'5', '2', '1', '9', '7', '4', '3', '6', '8'},
    {'4', '3', '8', '5', '2', '6', '9', '1', '7'},
    {'7', '9', '6', '3', '1', '8', '4', '5', '2'}
  };

  private final char[][] in_valid_board_block1 = {
    {'8', '1', '2', '7', '5', '3', '6', '4', '9'},
    {'9', '4', '3', '6', '8', '2', '1', '7', '5'},
    {'6', '7', '4', '4', '9', '1', '2', '8', '3'},
    {'1', '5', '4', '2', '3', '7', '8', '9', '6'},
    {'3', '6', '9', '8', '4', '5', '7', '2', '1'},
    {'2', '8', '7', '1', '6', '9', '5', '3', '4'},
    {'5', '2', '1', '9', '7', '4', '3', '6', '8'},
    {'4', '3', '8', '5', '2', '6', '9', '1', '7'},
    {'7', '9', '6', '3', '1', '8', '4', '5', '2'}
  };

  @Test
  public void testValidateArgs() {
    assertThrows(
        IllegalArgumentException.class,
        () -> {
          String[] args = {};
          processData(args);
        });
  }

  @Test
  public void testValidateWithValidData() {
    Validate sv = new Validate();
    Status s = sv.validate(valid_board);
    s.printStatus();
    assertTrue(s.isSuccess());
  }

  @Test
  public void testValidateWithInvalidData() {
    Validate sv = new Validate();
    Status s = sv.validate(in_valid_board);
    s.printStatus();
    assertFalse(s.isSuccess());
  }

  @Test
  public void testValidateWithInvalidBlock() {
    Validate sv = new Validate();
    Status s = sv.validate(in_valid_board_block1);
    s.printStatus();
    assertFalse(s.isSuccess());
  }

  @Test
  public void testValidateNumberStatus() {
    Validate sv = new Validate();
    assertEquals(Status.INVALID_INPUT_ARGUMENT, sv.validateNumber('1', null, 1, "row"));
    assertEquals(Status.SUCCESS, sv.validateNumber('1', new boolean[10], 1, "row"));
    assertEquals(Status.INVALID_INPUT_ARGUMENT, sv.validateNumber('1', new boolean[8], 1, "row"));
    assertEquals(Status.INVALID_NUMBER, sv.validateNumber('-', new boolean[9], 2, "column"));
  }

  @Test
  public void testValidateInputDataWithNull() {
    Validate sv = new Validate();
    Status s = sv.validateArraySize(null);
    assertEquals(Status.INVALID_INPUT_ARGUMENT, s);
    assertTrue(s.isFailed());
  }

  @Test
  public void testValidateInputDataWithWrongColumns() {
    Validate sv = new Validate();
    Status s = sv.validateArraySize(new char[9][3]);
    assertEquals(Status.INVALID_COLUMN_COUNT, s);
    assertTrue(s.isFailed());
  }

  @Test
  public void testValidateInputDataWrongRows() {
    Validate sv = new Validate();
    Status s = sv.validateArraySize(new char[1][9]);
    assertEquals(Status.INVALID_ROW_COUNT, s);
    assertTrue(s.isFailed());
  }

  @Test
  public void testValidateInputDataRightSize() {
    Validate sv = new Validate();
    Status s = sv.validateArraySize(new char[GRID_SIZE][GRID_SIZE]);
    assertEquals(Status.SUCCESS, s);
    assertTrue(s.isSuccess());
  }

  @Test
  public void testValidateWithNull() {
    Validate sv = new Validate();
    Status s = sv.validate(null);
    assertEquals(Status.INVALID_INPUT_ARGUMENT, s);
    assertTrue(s.isFailed());
  }

  @Test
  public void testParseAndValidateExtraColumns() {
    assertThrows(RuntimeException.class, () -> {
      String[] args = {"src/test/resources/input/ExtraColumnsFile.csv"};
      Status s = processData(args);
      s.printStatus();
    });
  }

  @Test
  public void testParseAndValidateExtraRows() {
    assertThrows(RuntimeException.class, () -> {
      String[] args = {"src/test/resources/input/ExtraRowFile.csv"};
      Status s = processData(args);
      s.printStatus();
    });
  }

  @Test
  public void testParseAndValidateWithErrorFile() {
    String[] args = {"src/test/resources/input/SampleErrorFile.csv"};
    Status s = processData(args);
    s.printStatus();
    assertFalse(s.isSuccess());
  }

  @Test
  public void testParseAndValidateWithValidFile() {
    String[] args = {"src/test/resources/input/SampleValidFile.csv"};
    Status s = processData(args);
    s.printStatus();
    assertTrue(s.isSuccess());
  }
}