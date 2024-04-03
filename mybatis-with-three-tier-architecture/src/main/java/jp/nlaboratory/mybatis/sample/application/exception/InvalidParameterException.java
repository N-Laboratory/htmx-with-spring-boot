package jp.nlaboratory.mybatis.sample.application.exception;

import java.io.Serial;

/**
 * If request parameter is invalid, then throw this error.
 */
public class InvalidParameterException extends Exception {

  @Serial
  private static final long serialVersionUID = 2L;

  public InvalidParameterException(String msg) {
    super(msg);
  }
}
