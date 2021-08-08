package org.krishnamatta.sudokuvalidate.utils;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class StatusTests {

  @Test
  public void testIsSuccess() {
    Map<Integer, Status> mapStatus = new HashMap<>();
    mapStatus.put(1, Status.INVALID_INPUT_ARGUMENT);
    mapStatus.put(2, Status.INVALID_ROW_COUNT);
    mapStatus.put(3, Status.INVALID_COLUMN_COUNT);
    mapStatus.put(4, Status.INVALID_NUMBER);
    mapStatus.put(5, Status.DUPLICATE_NUMBER);

    for (Map.Entry<Integer, Status> item : mapStatus.entrySet()) {
      assertFalse(item.getValue().isSuccess());
    }

    assertTrue(Status.SUCCESS.isSuccess());
    assertFalse(Status.SUCCESS.isFailed());
  }

  @Test
  public void testGetCode() {
    Map<Integer, Status> mapStatus = new HashMap<>();
    mapStatus.put(1, Status.INVALID_INPUT_ARGUMENT);
    mapStatus.put(2, Status.INVALID_ROW_COUNT);
    mapStatus.put(3, Status.INVALID_COLUMN_COUNT);
    mapStatus.put(4, Status.INVALID_NUMBER);
    mapStatus.put(5, Status.DUPLICATE_NUMBER);
    mapStatus.put(0, Status.SUCCESS);

    for (Map.Entry<Integer, Status> item : mapStatus.entrySet()) {
      assertEquals(item.getKey().intValue(), item.getValue().getCode());
    }
  }
}