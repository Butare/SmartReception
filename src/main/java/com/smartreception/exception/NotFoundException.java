package com.smartreception.exception;

/**
 * 
 * NotFoundException will be converted to 404 error by Spring.
 */
public class NotFoundException extends RuntimeException {
  
  /**
   * The serial version of UID
   */
  private static final long serialVersionUID = 6207453004692258306L;

  public NotFoundException(String msg) {
    super(msg);
  }
}
