package jp.nlaboratory.mybatis.sample.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import java.util.List;
import jp.nlaboratory.mybatis.sample.XmlDataLoader;
import jp.nlaboratory.mybatis.sample.domain.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  @DisplayName("should get all user.")
  @DatabaseSetup("/dataset/User_find_all.xml")
  public void findAll() throws Exception {
    // Act
    List<User> userList = userRepository.findAll();

    // Assert
    assertEquals(2, userList.size());
  }

  @Test
  @DisplayName("should get user by user id.")
  @DatabaseSetup("/dataset/User_find_by_id.xml")
  public void findById() throws Exception {
    // Arrange
    User expectedUser = User.builder()
        .id(2L)
        .email("test2@test.com")
        .password("test2")
        .build();

    // Act
    User actualUser = userRepository.findById(expectedUser.getId()).orElse(null);

    // Assert
    assertNotNull(actualUser);
    assertEquals(expectedUser.getId(), actualUser.getId());
    assertEquals(expectedUser.getEmail(), actualUser.getEmail());
    assertEquals(expectedUser.getPassword(), actualUser.getPassword());
  }

  @Test
  @DisplayName("should get true when create user.")
  public void insert() throws Exception {
    // Arrange
    User user = User.builder()
        .id(2L)
        .email("test2@test.com")
        .password("test2")
        .build();

    // Act
    boolean result = userRepository.insert(user);

    // Assert
    assertTrue(result);
  }

  @Test
  @DisplayName("should get true when update user.")
  @DatabaseSetup("/dataset/User_update.xml")
  public void update() throws Exception {
    // Arrange
    User user = User.builder()
        .id(1L)
        .email("test1_updated@test.com")
        .password("test1_updated")
        .build();

    // Act
    boolean result = userRepository.update(user);

    // Assert
    assertTrue(result);
  }

  @Test
  @DisplayName("should get true when delete user.")
  @DatabaseSetup("/dataset/User_delete.xml")
  public void delete() throws Exception {
    // Act
    boolean result = userRepository.delete(1L);

    // Assert
    assertTrue(result);
  }
}
