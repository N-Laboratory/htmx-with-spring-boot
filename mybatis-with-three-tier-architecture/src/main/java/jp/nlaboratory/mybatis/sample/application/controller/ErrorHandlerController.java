package jp.nlaboratory.mybatis.sample.application.controller;

import java.net.ConnectException;
import jp.nlaboratory.mybatis.sample.application.exception.DataNotFoundException;
import jp.nlaboratory.mybatis.sample.application.exception.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Handle exception.
 */
@ControllerAdvice
@Slf4j
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

  /**
   * Create HTTP response for DataNotFoundException.
   *
   * @param e DataNotFoundException
   * @return error page
   */
  @ExceptionHandler({DataNotFoundException.class})
  public String handleDataNotFoundException(DataNotFoundException e, Model model) {
    log.error("Error: {}", e.getMessage());
    model.addAttribute("errorMsg", "User not found.");
    return "modal/error";
  }

  /**
   * Create HTTP response for ConnectException, CannotGetJdbcConnectionException.
   *
   * @param e ConnectException or CannotGetJdbcConnectionException
   * @return error page
   */
  @ExceptionHandler({ConnectException.class, CannotGetJdbcConnectionException.class})
  public String handleConnectionException(Exception e) {
    log.error("Error: {}", e.getMessage());
    return "modal/error";
  }

  /**
   * Create HTTP response for MyBatisSystemException.
   *
   * @param e MyBatisSystemException
   * @return error page
   */
  @ExceptionHandler({MyBatisSystemException.class})
  public String handleMyBatisException(MyBatisSystemException e) {
    log.error("Error: {}", e.getMessage());
    return "modal/error";
  }

  /**
   * Create HTTP response for InvalidParameterException.
   *
   * @param e InvalidParameterException
   * @return error page
   */
  @ExceptionHandler({InvalidParameterException.class})
  public String handleInvalidParameterException(InvalidParameterException e) {
    log.error("Error: {}", e.getMessage());
    return "modal/error";
  }

  /**
   * Create HTTP response for Exception.
   *
   * @param e Exception
   * @return error page
   */
  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
    log.error("Error: {}", e.getMessage());
    return "modal/error";
  }

  /**
   * Create HTTP response for Exception.
   *
   * @param e Exception
   * @return error page
   */
  @ExceptionHandler({Exception.class})
  public String handleException(Exception e) {
    log.error("Error: {}", e.getMessage());
    return "modal/error";
  }
}
