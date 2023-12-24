package jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.service;

import jp.nlaboratory.MyBatisWithThreeTierArchitecture.application.exception.DataNotFoundException;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.dto.UserRequest;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.entity.User;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.infrastructure.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation for user data CRUD.
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public User getUser(Long id) throws Exception {
    return userMapper.findById(id)
        .orElseThrow(() -> new DataNotFoundException("User not found. User id = " + id));
  }

  @Override
  public void createUser(User user) throws Exception {
    boolean isSuccess = userMapper.insert(user);
    if (!isSuccess) {
      throw new Exception("An unexpected error has occurred.");
    }
  }

  @Override
  public void updateUser(User user, UserRequest userRequest) throws Exception {
    user.setName(userRequest.getName());
    user.setEmail(userRequest.getEmail());
    user.setDelFlg(userRequest.isDelFlg());

    boolean isSuccess = userMapper.update(user);
    if (!isSuccess) {
      throw new Exception("An unexpected error has occurred. User id = " + user.getId());
    }
  }

  @Override
  public void deleteUser(Long id) throws Exception {
    boolean isSuccess = userMapper.delete(id);
    if (!isSuccess) {
      throw new Exception("An unexpected error has occurred. User id = " + id);
    }
  }
}
