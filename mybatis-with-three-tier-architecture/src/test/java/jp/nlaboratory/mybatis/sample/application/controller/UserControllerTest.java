package jp.nlaboratory.mybatis.sample.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import jp.nlaboratory.mybatis.sample.application.exception.InvalidParameterException;
import jp.nlaboratory.mybatis.sample.domain.dto.UserUpdateRequest;
import jp.nlaboratory.mybatis.sample.domain.entity.User;
import jp.nlaboratory.mybatis.sample.domain.service.MessageService;
import jp.nlaboratory.mybatis.sample.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
public class UserControllerTest {
  @InjectMocks()
  UserController userController;
  @Mock
  UserService userService;
  @Mock
  MessageService messageService;
  private MockMvc mockMvc;
  private final ObjectMapper mapper = new ObjectMapper();


  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(userController)
        .setControllerAdvice(new ErrorHandlerController())
        .build();
  }

  @Test
  @DisplayName("should go to userList.html when user list search succeeded.")
  public void searchAll() throws Exception {
    // Arrange
    User user1 = getUser(1L, "test1@test.com", "password1");
    User user2 = getUser(2L, "test2@test.com", "password2");
    List<User> expectedUserList = Arrays.asList(user1, user2);
    when(userService.getAllUser()).thenReturn(expectedUserList);

    // Assert
    mockMvc.perform(get("/api/v1/users"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("userList"))
        .andExpect(view().name("userList"))
        .andExpect(model().hasNoErrors())
        .andExpect(model().attribute("users", expectedUserList));
  }

  @Test
  @DisplayName("should display error.html when user list search failed.")
  public void searchAllException() throws Exception {
    // Arrange
    doThrow(new Exception("an unexpected error has occurred.")).when(userService).getAllUser();

    // Assert
    mockMvc.perform(get("/api/v1/users"))
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(Exception.class, result.getResolvedException()))
        .andExpect(result -> assertEquals("an unexpected error has occurred.", Objects.requireNonNull(
            result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));
  }

  @Test
  @DisplayName("should display user info in result.html when user search succeeded.")
  public void search() throws Exception {
    // Arrange
    User expectedUser = getUser(2L, "test2@test.com", "password2");
    when(userService.getUser(2L)).thenReturn(expectedUser);

    // Assert
    mockMvc.perform(get("/api/v1/user").param("id", "2"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("modal/result"))
        .andExpect(view().name("modal/result"))
        .andExpect(model().hasNoErrors())
        .andExpect(model().attribute("user", expectedUser))
        .andExpect(model().attribute("message", "The following user was found:"));
  }

  @Test
  @DisplayName("should display error.html when user search failed.")
  public void searchException() throws Exception {
    // Arrange
    doThrow(new Exception("an unexpected error has occurred.")).when(userService).getUser(anyLong());

    // Assert
    mockMvc.perform(get("/api/v1/user").param("id", "2"))
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(Exception.class, result.getResolvedException()))
        .andExpect(result -> assertEquals("an unexpected error has occurred.", Objects.requireNonNull(
            result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));
  }

  @Test
  @DisplayName("should display error.html when user search request parameter is empty.")
  public void searchMissingServletRequestParameterException() throws Exception {
    // Assert
    mockMvc.perform(get("/api/v1/user"))
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(MissingServletRequestParameterException.class, result.getResolvedException()))
        .andExpect(result -> assertEquals("Required request parameter 'id' for method parameter type Long is not present", Objects.requireNonNull(
            result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));
  }

  @Test
  @DisplayName("should display error.html when user search request parameter is invalid.")
  public void searchMethodArgumentTypeMismatchException() throws Exception {
    // Assert
    mockMvc.perform(get("/api/v1/user").param("id", "foo"))
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(MethodArgumentTypeMismatchException.class, result.getResolvedException()))
        .andExpect(result -> assertEquals("Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: \"foo\"", Objects.requireNonNull(
            result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));
  }

  @Test
  @DisplayName("should display user info in result.html when user creation succeeded.")
  public void create() throws Exception {
    // Arrange
    User expectedUser = getUser(null, "test@test.com", "password");
    String jsonRequest = mapper.writeValueAsString(getUserUpdateRequest(null, "test@test.com", "password"));

    // Assert
    mockMvc.perform(
          post("/api/v1/user")
              .accept(MediaType.APPLICATION_JSON)
              .contentType(MediaType.APPLICATION_JSON)
              .content(jsonRequest)
        )
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("modal/result"))
        .andExpect(view().name("modal/result"))
        .andExpect(model().hasNoErrors())
        .andExpect(model().attribute("user", expectedUser))
        .andExpect(model().attribute("message", "The following user have been created:"));
  }

  @Test
  @DisplayName("should display error.html when user creation failed.")
  public void createException() throws Exception {
    // Arrange
    String jsonRequest = mapper.writeValueAsString(getUserUpdateRequest(null, "test@test.com", "password"));
    doThrow(new Exception("an unexpected error has occurred.")).when(userService).createUser(any(User.class));

    // Assert
    mockMvc.perform(
            post("/api/v1/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        )
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(Exception.class, result.getResolvedException()))
        .andExpect(result -> assertEquals("an unexpected error has occurred.", Objects.requireNonNull(
            result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));
  }


  @Test
  @DisplayName("should display error.html when user creation request body is invalid.")
  public void createInvalidParameterException() throws Exception {
    // Arrange
    String jsonRequest = mapper.writeValueAsString(getUserUpdateRequest(null, null, null));
    String jsonError = "{\"password\":\"[password] is required.\",\"email\":\"[email] is required.\"}";
    when(messageService.convertFieldErrorMsgListToJson(anyList())).thenReturn(jsonError);

    // Assert
    mockMvc.perform(
            post("/api/v1/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        )
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(InvalidParameterException.class, result.getResolvedException()))
        .andExpect(result -> assertEquals(jsonError, Objects.requireNonNull(result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));
  }

  @Test
  @DisplayName("should display user info in result.html when user update succeeded.")
  public void update() throws Exception {
    // Arrange
    User expectedUser = getUser(1L, "test@test.com", "password");
    String jsonRequest = mapper.writeValueAsString(getUserUpdateRequest(1L, "test@test.com", "password"));
    when(userService.getUser(1L)).thenReturn(expectedUser);

    // Assert
    mockMvc.perform(
            put("/api/v1/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        )
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("modal/result"))
        .andExpect(view().name("modal/result"))
        .andExpect(model().hasNoErrors())
        .andExpect(model().attribute("user", expectedUser))
        .andExpect(model().attribute("message", "The following user edits have been completed:"));
  }

  @Test
  @DisplayName("should display error.html when user update failed.")
  public void updateException() throws Exception {
    // Arrange
    User expectedUser = getUser(1L, "test@test.com", "password");
    UserUpdateRequest request = getUserUpdateRequest(1L, "test@test.com", "password");
    String jsonRequest = mapper.writeValueAsString(request);
    when(userService.getUser(anyLong())).thenReturn(expectedUser);
    doThrow(new Exception("an unexpected error has occurred.")).when(userService).updateUser(expectedUser, request);

    // Assert
    mockMvc.perform(
            put("/api/v1/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        )
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(Exception.class, result.getResolvedException()))
        .andExpect(result -> assertEquals("an unexpected error has occurred.", Objects.requireNonNull(
            result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));
  }

  @Test
  @DisplayName("should display error.html when user update request body is invalid.")
  public void updateInvalidParameterException() throws Exception {
    // Arrange
    String jsonRequest = mapper.writeValueAsString(getUserUpdateRequest(null, null, null));
    String jsonError = "{\"password\":\"[password] is required.\",\"id\":\"[id] is required.\",\"email\":\"[email] is required.\"}";
    when(messageService.convertFieldErrorMsgListToJson(anyList())).thenReturn(jsonError);

    // Assert
    mockMvc.perform(
            put("/api/v1/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        )
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(InvalidParameterException.class, result.getResolvedException()))
        .andExpect(result -> assertEquals(jsonError, Objects.requireNonNull(result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));
  }

  @Test
  @DisplayName("should display user info in result.html when user deletion succeeded.")
  public void delete() throws Exception {
    // Arrange
    User expectedUser = getUser(2L, "test@test.com", "password");
    when(userService.getUser(2L)).thenReturn(expectedUser);

    // Assert
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user").param("id", "2"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("modal/result"))
        .andExpect(view().name("modal/result"))
        .andExpect(model().hasNoErrors())
        .andExpect(model().attribute("user", expectedUser))
        .andExpect(model().attribute("message", "The following user have been deleted:"));
  }

  @Test
  @DisplayName("should display error.html when user deletion failed.")
  public void deleteException() throws Exception {
    // Arrange
    when(userService.getUser(2L)).thenReturn(getUser(2L, "test@test.com", "password"));
    doThrow(new Exception("an unexpected error has occurred.")).when(userService).deleteUser(2L);

    // Assert
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user").param("id", "2"))
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(Exception.class, result.getResolvedException()))
        .andExpect(result -> assertEquals("an unexpected error has occurred.", Objects.requireNonNull(
            result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));
  }

  @Test
  @DisplayName("should display error.html when user deletion request parameter is empty.")
  public void deleteMissingServletRequestParameterException() throws Exception {
    // Assert
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user"))
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(MissingServletRequestParameterException.class, result.getResolvedException()))
        .andExpect(result -> assertEquals("Required request parameter 'id' for method parameter type Long is not present", Objects.requireNonNull(
            result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));
  }

  @Test
  @DisplayName("should display error.html when user deletion request parameter is invalid.")
  public void deleteMethodArgumentTypeMismatchException() throws Exception {
    // Assert
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/user").param("id", "foo"))
        .andExpect(status().isOk())
        .andExpect(result -> assertInstanceOf(MethodArgumentTypeMismatchException.class, result.getResolvedException()))
        .andExpect(result -> assertEquals("Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long'; For input string: \"foo\"", Objects.requireNonNull(
            result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(view().name("modal/error"));
  }

  private User getUser(Long id, String email, String password) {
    return User.builder()
        .id(Objects.nonNull(id) ? id : null)
        .email(Objects.nonNull(email) ? email : null)
        .password(Objects.nonNull(password) ? password : null)
        .build();
  }

  private UserUpdateRequest getUserUpdateRequest(Long id, String email, String password) {
    return UserUpdateRequest.builder()
        .id(Objects.nonNull(id) ? id : null)
        .email(Objects.nonNull(email) ? email : null)
        .password(Objects.nonNull(password) ? password : null)
        .build();
  }
}
