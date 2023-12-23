package jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.service;

import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.dto.UserRequest;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.entity.User;

public interface UserService {

  User getUser(Long id) throws Exception;

  void createUser(User user) throws Exception;

  void updateUser(User user, UserRequest userRequest) throws Exception;

  void deleteUser(Long id) throws Exception;
}
