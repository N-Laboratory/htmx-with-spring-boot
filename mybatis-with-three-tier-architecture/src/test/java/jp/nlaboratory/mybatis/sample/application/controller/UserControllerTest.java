package jp.nlaboratory.mybatis.sample.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import jp.nlaboratory.mybatis.sample.domain.entity.User;
import jp.nlaboratory.mybatis.sample.domain.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(userController)
        .setControllerAdvice(new ErrorHandlerController())
        .build();
  }

  @Test
  @DisplayName("should go to userList.html.")
  public void searchAll() throws Exception {
    // Arrange
    User testUser1 = User.builder().id(1L).build();
    User testUser2 = User.builder().id(2L).build();
    List<User> expectedUserList = Arrays.asList(testUser1, testUser2);
    when(userService.getAllUser()).thenReturn(expectedUserList);

    // Assert
    mockMvc.perform(get("/api/v1/users"))
        .andExpect(status().isOk())
        .andExpect(view().name("userList"))
        .andExpect(model().hasNoErrors())
        .andExpect(model().attribute("users", expectedUserList));
  }

  @Test
  @DisplayName("should show error.html in modal.")
  public void searchAllException() throws Exception {
    // Arrange
    doThrow(new Exception("an unexpected error has occurred.")).when(userService).getAllUser();

    // Assert
    mockMvc.perform(get("/api/v1/users"))
        .andExpect(result -> assertInstanceOf(Exception.class, result.getResolvedException()))
        .andExpect(result -> assertEquals("an unexpected error has occurred.", Objects.requireNonNull(
            result.getResolvedException()).getMessage()))
        .andExpect(forwardedUrl("modal/error"))
        .andExpect(status().isOk());
  }

//  private ViewResolver viewResolver() {
//    return new InternalResourceViewResolver("classpath:templates/", ".html");
//  }

}
