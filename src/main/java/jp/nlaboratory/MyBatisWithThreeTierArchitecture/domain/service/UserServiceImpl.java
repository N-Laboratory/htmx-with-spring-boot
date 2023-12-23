package jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.service;

import jp.nlaboratory.MyBatisWithThreeTierArchitecture.application.exception.DataNotFoundException;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.entity.User;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.infrastructure.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public User getUser(Long id) throws DataNotFoundException {
    return userMapper.findById(id)
        .orElseThrow(() -> new DataNotFoundException("User not found. User id = " + id));
  }

  @Override
  public int createUser(User user) {
    return userMapper.insert(user);
  }

  @Override
  public int updateUser(User user) {
    return userMapper.update(user);
  }

  @Override
  public int deleteUser(Long id) {
    return userMapper.delete(id);
  }
}
