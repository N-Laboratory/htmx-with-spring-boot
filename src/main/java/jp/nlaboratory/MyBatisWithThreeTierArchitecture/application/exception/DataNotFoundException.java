package jp.nlaboratory.MyBatisWithThreeTierArchitecture.application.exception;

import java.io.Serial;

/**
 * If data not found in database, then throw this error.
 */
public class DataNotFoundException extends Exception {

  @Serial
  private static final long serialVersionUID = 1L;

  public DataNotFoundException(String msg) {
    super(msg);
  }
}
