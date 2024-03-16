package jp.nlaboratory.mybatis.sample.application.controller;

import java.net.ConnectException;
import jp.nlaboratory.mybatis.sample.application.exception.DataNotFoundException;
import jp.nlaboratory.mybatis.sample.application.exception.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handle exception.
 */
@RestControllerAdvice
@Slf4j
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

  /**
   * Create HTTP response for DataNotFoundException.
   *
   * @param e DataNotFoundException
   * @return HTTP response (HttpStatus.BAD_REQUEST + error message)
   */
  @ExceptionHandler({DataNotFoundException.class})
  public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException e) {
    log.error("Error: {}", e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(e.getMessage());
  }

  /**
   * Create HTTP response for ConnectException, CannotGetJdbcConnectionException.
   *
   * @param e ConnectException or CannotGetJdbcConnectionException
   * @return HTTP response (HttpStatus.INTERNAL_SERVER_ERROR + error message)
   */
  @ExceptionHandler({ConnectException.class, CannotGetJdbcConnectionException.class})
  public ResponseEntity<Object> handleConnectionException(Exception e) {
    log.error("Error: {}", e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(e.getMessage());
  }

  /**
   * Create HTTP response for MyBatisSystemException.
   *
   * @param e MyBatisSystemException
   * @return HTTP response (HttpStatus.INTERNAL_SERVER_ERROR + error message)
   */
  @ExceptionHandler({MyBatisSystemException.class})
  public ResponseEntity<Object> handleMyBatisException(MyBatisSystemException e) {
    log.error("Error: {}", e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(e.getMessage());
  }

  /**
   * Create HTTP response for InvalidParameterException.
   *
   * @param e InvalidParameterException
   * @return HTTP response (HttpStatus.BAD_REQUEST + error message)
   */
  @ExceptionHandler({InvalidParameterException.class})
  public ResponseEntity<Object> handleInvalidParameterException(InvalidParameterException e) {
    log.error("Error: {}", e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("Request parameter is invalid.\n" + e.getMessage());
  }

  /**
   * Create HTTP response for Exception.
   *
   * @param e Exception
   * @return HTTP response (HttpStatus.INTERNAL_SERVER_ERROR + error message)
   */
  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleException(Exception e) {
    log.error("Error: {}", e.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("An unexpected error has occurred. " + e.getMessage());
  }
}
