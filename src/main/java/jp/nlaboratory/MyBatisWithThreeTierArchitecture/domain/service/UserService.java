package jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.service;

import jp.nlaboratory.MyBatisWithThreeTierArchitecture.application.exception.DataNotFoundException;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.entity.User;

public interface UserService {

  User getUser(Long id) throws DataNotFoundException;

  int createUser(User user);

  int updateUser(User user);

  int deleteUser(Long id);
}
