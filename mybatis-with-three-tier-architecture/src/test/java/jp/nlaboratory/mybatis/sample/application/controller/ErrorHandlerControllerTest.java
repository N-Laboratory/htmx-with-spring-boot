package jp.nlaboratory.mybatis.sample.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import java.net.ConnectException;
import java.util.Objects;
import java.util.stream.Stream;
import jp.nlaboratory.mybatis.sample.application.exception.DataBaseException;
import jp.nlaboratory.mybatis.sample.application.exception.DataNotFoundException;
import jp.nlaboratory.mybatis.sample.application.exception.InvalidParameterException;
import jp.nlaboratory.mybatis.sample.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mybatis.spring.MyBatisSystemException;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MissingServletRequestParameterException;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
})
public class ErrorHandlerControllerTest {
  @InjectMocks()
  UserController userController;
  @Mock
  UserService userService;
  @Captor
  private ArgumentCaptor<LoggingEvent> captorLoggingEvent;
  @Mock
  private Appender<ILoggingEvent> mockAppender;
  private MockMvc mockMvc;
  private static final Exception exception = new Exception();
  private static final String ERROR_MSG = "foo";
  Logger logger = (Logger)LoggerFactory.getLogger(ErrorHandlerController.class);

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(userController)
        .setControllerAdvice(new ErrorHandlerController())
        .build();
    logger.addAppender(mockAppender);
  }

  static Stream<Exception> exceptionStream() {
    return Stream.of(
            new DataBaseException(ERROR_MSG, exception),
            new DataNotFoundException(ERROR_MSG),
            new MissingServletRequestParameterException(ERROR_MSG, ERROR_MSG),
            new ConnectException(ERROR_MSG),
            new CannotGetJdbcConnectionException(ERROR_MSG),
            new MyBatisSystemException(exception),
            new InvalidParameterException(ERROR_MSG),
            new Exception(ERROR_MSG)
        );
  }

  @ParameterizedTest
  @MethodSource("exceptionStream")
  @DisplayName("should handle error when exception throw.")
  public void searchAllException(Exception exception) throws Exception {
    // Arrange
    doThrow(exception).when(userService).getAllUser();
    String errorMsg = ERROR_MSG;
    if (exception instanceof MissingServletRequestParameterException) {
      errorMsg = "Required request parameter '" + ERROR_MSG + "' for method parameter type foo is not present";
    } else if (exception instanceof MyBatisSystemException){
      errorMsg = "null";
    }

    // Assert
    mockMvc.perform(get("/api/v1/users"))
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(exception.getClass(), result.getResolvedException()))
        .andExpect(result -> assertEquals(exception.getMessage(), Objects.requireNonNull(
            result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));

    verify(mockAppender, times(1)).doAppend(captorLoggingEvent.capture());
    assertEquals(captorLoggingEvent.getValue().getLevel(), Level.ERROR);
    assertEquals(captorLoggingEvent.getValue().getFormattedMessage(), "Error: " + errorMsg);
  }
}
