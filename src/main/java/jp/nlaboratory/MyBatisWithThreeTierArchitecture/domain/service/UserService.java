package jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.service;

import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.entity.User;

public interface UserService {
  User getUser(Long id);
  int createUser(User user);
  int updateUser(User user);
  int deleteUser(Long id);
}
