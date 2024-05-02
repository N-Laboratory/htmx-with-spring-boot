package jp.nlaboratory.mybatis.sample.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Objects;
import jp.nlaboratory.mybatis.sample.domain.entity.User;
import jp.nlaboratory.mybatis.sample.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
})
public class ModalControllerTest {
  @InjectMocks()
  ModalController modalController;
  @Mock
  UserService userService;
  private MockMvc mockMvc;
  private final User expectedUser = User.builder().id(2L).email("test2@test.com").password("test2").build();

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(modalController)
        .setControllerAdvice(new ErrorHandlerController())
        .build();
  }

  @Test
  @DisplayName("should display user creation modal.")
  public void showCreateModal() throws Exception {
    // Assert
    mockMvc.perform(get("/api/v1/modal/create"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("modal/create"))
        .andExpect(view().name("modal/create"))
        .andExpect(model().hasNoErrors());
  }

  @Test
  @DisplayName("should display user edit modal.")
  public void showEditModal() throws Exception {
    // Arrange
    when(userService.getUser(2L)).thenReturn(expectedUser);

    // Assert
    mockMvc.perform(get("/api/v1/modal/edit").param("id", "2"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("modal/edit"))
        .andExpect(view().name("modal/edit"))
        .andExpect(model().hasNoErrors())
        .andExpect(model().attribute("user", expectedUser));
  }

  @Test
  @DisplayName("should display user deletion modal.")
  public void showDeleteModal() throws Exception {
    // Arrange
    when(userService.getUser(2L)).thenReturn(expectedUser);

    // Assert
    mockMvc.perform(get("/api/v1/modal/delete").param("id", "2"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("modal/delete"))
        .andExpect(view().name("modal/delete"))
        .andExpect(model().hasNoErrors())
        .andExpect(model().attribute("user", expectedUser));
  }

  @ParameterizedTest
  @ValueSource(strings = {"edit", "delete"})
  @DisplayName("should display error.html when user search failed.")
  public void searchException(String path) throws Exception {
    // Arrange
    doThrow(new Exception("an unexpected error has occurred.")).when(userService).getUser(anyLong());

    // Assert
    mockMvc.perform(get("/api/v1/modal/" + path).param("id", "2"))
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(Exception.class, result.getResolvedException()))
        .andExpect(result -> assertEquals("an unexpected error has occurred.", Objects.requireNonNull(
            result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));
  }

  @ParameterizedTest
  @ValueSource(strings = {"edit", "delete"})
  @DisplayName("should display error.html when user search request parameter is empty.")
  public void searchMissingServletRequestParameterException(String path) throws Exception {
    // Assert
    mockMvc.perform(get("/api/v1/modal/" + path))
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(MissingServletRequestParameterException.class, result.getResolvedException()))
        .andExpect(result -> assertEquals("Required request parameter 'id' for method parameter type Long is not present", Objects.requireNonNull(
            result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));
  }

  @ParameterizedTest
  @ValueSource(strings = {"edit", "delete"})
  @DisplayName("should display error.html when user search request parameter is invalid.")
  public void searchMethodArgumentTypeMismatchException(String path) throws Exception {
    // Assert
    mockMvc.perform(get("/api/v1/modal/" + path).param("id", "foo"))
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(MethodArgumentTypeMismatchException.class, result.getResolvedException()))
        .andExpect(result -> assertEquals("Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: \"foo\"", Objects.requireNonNull(
            result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));
  }
}
