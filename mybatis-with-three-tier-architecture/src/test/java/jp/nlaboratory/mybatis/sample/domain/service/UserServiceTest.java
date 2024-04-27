package jp.nlaboratory.mybatis.sample.domain.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import jp.nlaboratory.mybatis.sample.XmlDataLoader;
import jp.nlaboratory.mybatis.sample.application.exception.DataBaseException;
import jp.nlaboratory.mybatis.sample.application.exception.DataNotFoundException;
import jp.nlaboratory.mybatis.sample.domain.dto.UserUpdateRequest;
import jp.nlaboratory.mybatis.sample.domain.entity.User;
import jp.nlaboratory.mybatis.sample.domain.model.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionDbUnitTestExecutionListener.class
})
@Transactional
@DbUnitConfiguration(dataSetLoader = XmlDataLoader.class)
public class UserServiceTest {

  @InjectMocks
  private UserServiceImpl userService;
  @Mock()
  UserRepository userRepositoryMock;
  private AutoCloseable closeable;

  @BeforeEach
  public void setUp() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  public void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  @DisplayName("should get all user.")
  public void getAllUser() throws Exception {
    // Arrange
    User testUser1 = User.builder().id(1L).build();
    User testUser2 = User.builder().id(2L).build();
    List<User> expectedUserList = Arrays.asList(testUser1, testUser2);
    when(userRepositoryMock.findAll()).thenReturn(expectedUserList);

    // Act
    List<User> actualUserList = userService.getAllUser();

    // Assert
    assertIterableEquals(expectedUserList, actualUserList);
  }

  @Test
  @DisplayName("should throw DataBaseException when an error has occurred.")
  public void getAllUserException() throws Exception {
    // Arrange
    doThrow(new Exception()).when(userRepositoryMock).findAll();

    // Act
    DataBaseException actualError =
        assertThrows(DataBaseException.class, () -> userService.getAllUser());

    // Assert
    assertEquals("DB access error", actualError.getMessage());
  }

  @Test
  @DisplayName("should get user.")
  public void getUser() throws Exception {
    // Arrange
    Optional<User> expectedUser = Optional.of(User.builder().id(1L).build());
    when(userRepositoryMock.findById(anyLong())).thenReturn(expectedUser);

    // Act
    User actualUser = userService.getUser(anyLong());

    // Assert
    assertEquals(expectedUser.get(), actualUser);
  }

  @Test
  @DisplayName("should throw DataBaseException when an error has occurred.")
  public void getUserDataBaseException() throws Exception {
    // Arrange
    doThrow(new Exception()).when(userRepositoryMock).findById(anyLong());

    // Act
    DataBaseException actualError =
        assertThrows(DataBaseException.class, () -> userService.getUser(anyLong()));

    // Assert
    assertEquals("DB access error", actualError.getMessage());
  }

  @Test
  @DisplayName("should throw DataNotFoundException when user not found.")
  public void getUserDataNotFoundException() throws Exception {
    // Arrange
    when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());

    // Act
    DataNotFoundException actualError =
        assertThrows(DataNotFoundException.class, () -> userService.getUser(1L));

    // Assert
    assertEquals("User not found. User id = 1", actualError.getMessage());
  }

  @Test
  @DisplayName("should not throw DataBaseException when user creation succeeded.")
  public void createUser() throws Exception {
    // Arrange
    User user = User.builder().id(1L).build();
    when(userRepositoryMock.insert(user)).thenReturn(true);

    // Assert
    assertDoesNotThrow(() -> userService.createUser(user));
  }

  @Test
  @DisplayName("should throw DataBaseException when user creation failed.")
  public void createUserException() throws Exception {
    // Arrange
    User user = User.builder().id(1L).build();
    when(userRepositoryMock.insert(user)).thenReturn(false);

    // Act
    DataBaseException actualError =
        assertThrows(DataBaseException.class, () -> userService.createUser(user));

    // Assert
    assertEquals("DB access error", actualError.getMessage());
  }

  @Test
  @DisplayName("should not throw DataBaseException when user update succeeded.")
  public void updateUser() throws Exception {
    // Arrange
    User user = User.builder().id(1L).build();
    UserUpdateRequest request = UserUpdateRequest.builder().id(1L).build();
    when(userRepositoryMock.update(user)).thenReturn(true);

    // Assert
    assertDoesNotThrow(() -> userService.updateUser(user, request));
  }

  @Test
  @DisplayName("should throw DataBaseException when user update failed.")
  public void updateUserException() throws Exception {
    // Arrange
    User user = User.builder().id(1L).build();
    UserUpdateRequest request = UserUpdateRequest.builder().id(1L).build();
    when(userRepositoryMock.update(user)).thenReturn(false);

    // Act
    DataBaseException actualError =
        assertThrows(DataBaseException.class, () -> userService.updateUser(user, request));

    // Assert
    assertEquals("DB access error", actualError.getMessage());
  }


  @Test
  @DisplayName("should not throw DataBaseException when user deletion succeeded.")
  public void deleteUser() throws Exception {
    // Arrange
    when(userRepositoryMock.delete(anyLong())).thenReturn(true);

    // Assert
    assertDoesNotThrow(() -> userService.deleteUser(anyLong()));
  }

  @Test
  @DisplayName("should throw DataBaseException when user deletion failed.")
  public void deleteUserException() throws Exception {
    // Arrange
    when(userRepositoryMock.delete(anyLong())).thenReturn(false);

    // Act
    DataBaseException actualError =
        assertThrows(DataBaseException.class, () -> userService.deleteUser(anyLong()));

    // Assert
    assertEquals("DB access error", actualError.getMessage());
  }
}
