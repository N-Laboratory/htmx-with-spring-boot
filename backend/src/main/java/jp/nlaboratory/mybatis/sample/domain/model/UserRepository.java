package jp.nlaboratory.mybatis.sample.domain.model;

import java.util.List;
import java.util.Optional;
import jp.nlaboratory.mybatis.sample.domain.entity.User;
import jp.nlaboratory.mybatis.sample.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

/**
 * Repository for user data CRUD.
 */
@Repository
public class UserRepository {

  private final UserMapper userMapper;

  public UserRepository(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  /**
   * Get all user data.
   *
   * @return user list
   */
  public List<User> findAll() throws Exception {
    return userMapper.findAll();
  }

  /**
   * Get user data.
   *
   * @param id user id
   * @return user
   */
  public Optional<User> findById(Long id) throws Exception {
    return userMapper.findById(id);
  }

  /**
   * Create user data.
   *
   * @param user user info
   */
  public boolean insert(User user) throws Exception {
    return userMapper.insert(user);
  }

  /**
   * Update user data.
   *
   * @param user user info
   */
  public boolean update(User user) throws Exception {
    return userMapper.update(user);
  }

  /**
   * Delete user data.
   *
   * @param id user id
   */
  public boolean delete(Long id) throws Exception {
    return userMapper.delete(id);
  }
}
