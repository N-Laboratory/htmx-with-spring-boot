package jp.nlaboratory.MyBatisWithThreeTierArchitecture.application.controller;

import java.time.LocalDateTime;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.dto.UserRequest;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.dto.UserResponse;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.entity.User;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Api for user data CRUD.
 */
@RestController
@RequestMapping("api/v1/user")
public class UserController {

  @Autowired
  UserService userService;

  /**
   * Search user.
   *
   * @param userRequest UserSearchRequest (id)
   * @return UserResponse (id, name, email, delFlg)
   */
  @GetMapping(value = "/search")
  public UserResponse search(@ModelAttribute UserRequest userRequest)
      throws Exception {
    User user = userService.getUser(userRequest.getId());

    return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.isDelFlg());
  }

  /**
   * Create user.
   *
   * @param userRequest UserSearchRequest (name, email)
   * @return UserResponse (id, name, email, delFlg)
   */
  @PostMapping(value = "/insert")
  public UserResponse insert(@ModelAttribute UserRequest userRequest)
      throws Exception {
    User user =
        new User(null, userRequest.getName(), userRequest.getEmail(), LocalDateTime.now(), null,
            false);
    userService.createUser(user);

    return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.isDelFlg());
  }

  /**
   * Update user.
   *
   * @param userRequest UserSearchRequest (id, name, email, delFlg)
   * @return UserResponse (id, name, email, delFlg)
   */
  @PostMapping(value = "/update")
  public UserResponse update(@ModelAttribute UserRequest userRequest)
      throws Exception {
    User user = userService.getUser(userRequest.getId());
    userService.updateUser(user, userRequest);

    return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.isDelFlg());
  }

  /**
   * Delete user.
   *
   * @param userRequest UserSearchRequest (id)
   * @return UserResponse (id, name, email, delFlg)
   */
  @PostMapping(value = "/delete")
  public UserResponse delete(@ModelAttribute UserRequest userRequest)
      throws Exception {
    User user = userService.getUser(userRequest.getId());
    userService.deleteUser(user.getId());

    return new UserResponse(user.getId(), user.getName(), user.getEmail(), true);
  }
}
