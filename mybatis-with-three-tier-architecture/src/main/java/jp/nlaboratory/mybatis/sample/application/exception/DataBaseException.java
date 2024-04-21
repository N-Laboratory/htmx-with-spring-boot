package jp.nlaboratory.mybatis.sample.application.exception;

import java.io.Serial;

/**
 * If something had happened in database, then throw this error.
 */
public class DataBaseException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 3L;

  public DataBaseException(String msg, Throwable cause) {
    super(msg, cause);
  }
}
