package jp.nlaboratory.mybatis.sample.domain.service;

import java.util.List;
import java.util.Optional;
import jp.nlaboratory.mybatis.sample.application.exception.DataBaseException;
import jp.nlaboratory.mybatis.sample.application.exception.DataNotFoundException;
import jp.nlaboratory.mybatis.sample.domain.dto.UserUpdateRequest;
import jp.nlaboratory.mybatis.sample.domain.entity.User;
import jp.nlaboratory.mybatis.sample.domain.model.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Implementation for user data CRUD.
 */
@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> getAllUser() throws DataBaseException {
    List<User> userList;
    try {
      userList = userRepository.findAll();
    } catch (Exception e) {
      throw new DataBaseException("DB access error", e);
    }
    return userList;
  }

  @Override
  public User getUser(Long id) throws DataBaseException, DataNotFoundException {
    Optional<User> user;

    try {
      user = userRepository.findById(id);
    } catch (Exception e) {
      throw new DataBaseException("DB access error", e);
    }

    return user.orElseThrow(() -> new DataNotFoundException("User not found. User id = " + id));
  }

  @Override
  public void createUser(User user) throws DataBaseException {
    try {
      boolean result = userRepository.insert(user);
      if (!result) {
        throw new Exception();
      }
    } catch (Exception e) {
      throw new DataBaseException("DB access error", e);
    }
  }

  @Override
  public void updateUser(User user, UserUpdateRequest request) throws DataBaseException {
    user.setEmail(request.getEmail());
    user.setPassword(request.getPassword());

    try {
      boolean result = userRepository.update(user);
      if (!result) {
        throw new Exception();
      }
    } catch (Exception e) {
      throw new DataBaseException("DB access error", e);
    }
  }

  @Override
  public void deleteUser(Long id) throws DataBaseException {
    try {
      boolean result = userRepository.delete(id);
      if (!result) {
        throw new Exception();
      }
    } catch (Exception e) {
      throw new DataBaseException("DB access error", e);
    }
  }
}
