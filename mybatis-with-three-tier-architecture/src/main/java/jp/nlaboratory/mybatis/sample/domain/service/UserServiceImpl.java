package jp.nlaboratory.mybatis.sample.domain.service;

import jp.nlaboratory.mybatis.sample.application.exception.DataNotFoundException;
import jp.nlaboratory.mybatis.sample.domain.dto.UserUpdateRequest;
import jp.nlaboratory.mybatis.sample.domain.entity.User;
import jp.nlaboratory.mybatis.sample.infrastructure.mapper.UserMapper;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Implementation for user data CRUD.
 */
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  public UserServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public List<User> getAllUser() throws Exception {
    return userMapper.findAll();
  }

  @Override
  public User getUser(Long id) throws Exception {
    return userMapper.findById(id)
        .orElseThrow(() -> new DataNotFoundException("User not found. User id = " + id));
  }

  @Override
  public void createUser(User user) throws Exception {
    boolean isSuccess = userMapper.insert(user);
    if (!isSuccess) {
      throw new Exception();
    }
  }

  @Override
  public void updateUser(User user, UserUpdateRequest request) throws Exception {
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());

    boolean isSuccess = userMapper.update(user);
    if (!isSuccess) {
      throw new Exception("User id = " + user.getId());
    }
  }

  @Override
  public void deleteUser(Long id) throws Exception {
    boolean isSuccess = userMapper.delete(id);
    if (!isSuccess) {
      throw new Exception("User id = " + id);
    }
  }
}
