package org.krishnamatta.sudokuvalidate.utils;

public enum Status {
  INVALID_INPUT_ARGUMENT(1, "Input argument is invalid"),
  INVALID_ROW_COUNT(2, "Rows count should be the size of grid"),
  INVALID_COLUMN_COUNT(3, "Columns count should be the size of grid"),
  INVALID_NUMBER(4, "Input data should be a number"),
  DUPLICATE_NUMBER(5, "Duplicate number"),
  SUCCESS(0, "");

  private final int code;
  private String msg;

  Status(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  // helper methods
  public boolean isFailed() {
    return !isSuccess();
  }

  public boolean isSuccess() {
    return this.code == 0;
  }

  public int getCode() {
    return this.code;
  }

  public String getMessage() {
    return this.msg;
  }

  public void setStatus(String msg) {
    this.msg = msg;
  }

  // print status
  public void printStatus() {
    if (this.isSuccess()) {
      System.out.println("VALID : " + this.toString());
    } else {
      System.out.println("INVALID : " + this.toString());
    }
  }

  @Override
  public String toString() {
    if (msg.isEmpty()) return String.format("StatusCode:%d", code);
    else return String.format("%s, StatusCode:%d", msg, code);
  }
}
