package jp.nlaboratory.mybatis.sample.domain.service;

import java.util.List;

import jp.nlaboratory.mybatis.sample.domain.dto.UserUpdateRequest;
import jp.nlaboratory.mybatis.sample.domain.entity.User;

/**
 * Interface for user data CRUD.
 */
public interface UserService {

  List<User> getAllUser() throws Exception;

  User getUser(Long id) throws Exception;

  void createUser(User user) throws Exception;

  void updateUser(User user, UserUpdateRequest request) throws Exception;

  void deleteUser(Long id) throws Exception;
}
