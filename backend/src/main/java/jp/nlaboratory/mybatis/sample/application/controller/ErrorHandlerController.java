package jp.nlaboratory.mybatis.sample.application.controller;

import java.net.ConnectException;
import jp.nlaboratory.mybatis.sample.application.exception.DataBaseException;
import jp.nlaboratory.mybatis.sample.application.exception.DataNotFoundException;
import jp.nlaboratory.mybatis.sample.application.exception.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Handle exception.
 */
@ControllerAdvice
@Slf4j
public class ErrorHandlerController {

  /**
   * Handle DataBaseException.
   *
   * @param e DataBaseException
   * @return error page
   */
  @ExceptionHandler({DataBaseException.class})
  public String handleDataBaseException(DataBaseException e) {
    log.error("Error: {}", e.getMessage());
    return "modal/error";
  }

  /**
   * Handle DataNotFoundException.
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
   * Handle MissingServletRequestParameterException.
   *
   * @param e MissingServletRequestParameterException
   * @return error page
   */
  @ExceptionHandler({MissingServletRequestParameterException.class})
  public String handleMissingServletRequestParameterException(
      MissingServletRequestParameterException e) {
    log.error("Error: {}", e.getMessage());
    return "modal/error";
  }

  /**
   * Handle ConnectException, CannotGetJdbcConnectionException.
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
   * Handle MyBatisSystemException.
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
   * Handle InvalidParameterException.
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
   * Handle Exception.
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
   * Handle Exception.
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
