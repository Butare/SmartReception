package com.smartreception.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VisitorRowMapperTest {
  
  @Test
  public void testSnakeCaseToCamelCase() throws Exception {
    VisitorRowMapper vmp = new VisitorRowMapper();
    assertEquals("userName", vmp.snakeCaseToCamelCase("user_name"));
  }
}
