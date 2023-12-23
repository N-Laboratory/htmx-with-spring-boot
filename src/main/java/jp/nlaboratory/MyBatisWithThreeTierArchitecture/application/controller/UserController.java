package jp.nlaboratory.MyBatisWithThreeTierArchitecture.application.controller;

import java.time.LocalDateTime;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.dto.UserRequest;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.dto.UserResponse;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.entity.User;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

  @Autowired
  UserService userService;

  /**
   * Search user
   *
   * @param userRequest UserSearchRequest
   * @param model       Model
   * @return UserResponse
   */
  @GetMapping(value = "/search")
  public UserResponse userSearch(@ModelAttribute UserRequest userRequest, Model model)
      throws Exception {
    // Get user from DB by mybatis
    User user = userService.getUser(userRequest.getId());
    return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.isDelFlg(), 1);
  }

  /**
   * Create user
   *
   * @param userRequest UserSearchRequest
   * @param model       Model
   * @return UserResponse
   */
  @PostMapping(value = "/insert")
  public UserResponse insert(@ModelAttribute UserRequest userRequest, Model model) {
    // Get user from DB by mybatis
    User user =
        new User(null, userRequest.getName(), userRequest.getEmail(), LocalDateTime.now(), null,
            false);
    int count = userService.createUser(user);
    return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.isDelFlg(), count);
  }

  /**
   * Update user
   *
   * @param userRequest UserSearchRequest
   * @param model       Model
   * @return UserResponse
   */
  @PostMapping(value = "/update")
  public UserResponse update(@ModelAttribute UserRequest userRequest, Model model) {
    User user = new User(userRequest.getId(), userRequest.getName(), userRequest.getEmail(), null,
        LocalDateTime.now(), userRequest.isDelFlg());
    // Get user from DB by mybatis
    int count = userService.updateUser(user);
    return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.isDelFlg(), count);
  }

  /**
   * Delete user
   *
   * @param userRequest UserSearchRequest
   * @param model       Model
   */
  @PostMapping(value = "/delete")
  public UserResponse delete(@ModelAttribute UserRequest userRequest, Model model) {
    // Get user from DB by mybatis
    int count = userService.deleteUser(userRequest.getId());
    return new UserResponse(null, null, null, null, count);
  }
}
