package jp.nlaboratory.mybatis.sample.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mybatis.spring.MyBatisSystemException;
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
  private MockMvc mockMvc;
  private static final Exception exception = new Exception();
  private static final String ERROR_MSG = "foo";

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(userController)
        .setControllerAdvice(new ErrorHandlerController())
        .build();
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
  @DisplayName("should display error.html when user list search failed.")
  public void searchAllException(Exception argument) throws Exception {
    // Arrange
    doThrow(argument).when(userService).getAllUser();

    // Assert
    mockMvc.perform(get("/api/v1/users"))
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(argument.getClass(), result.getResolvedException()))
        .andExpect(result -> assertEquals(argument.getMessage(), Objects.requireNonNull(
            result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));
  }
}
