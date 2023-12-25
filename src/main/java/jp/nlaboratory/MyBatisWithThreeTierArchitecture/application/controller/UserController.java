package jp.nlaboratory.MyBatisWithThreeTierArchitecture.application.controller;

import java.time.LocalDateTime;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.application.exception.InvalidParameterException;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.dto.UserCreateRequest;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.dto.UserRequest;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.dto.UserResponse;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.dto.UserUpdateRequest;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.entity.User;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.service.UserService;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
   * @param request UserRequest (id)
   * @return UserResponse (id, name, email, delFlg)
   */
  @GetMapping(value = "/search")
  public UserResponse search(@ModelAttribute @Validated UserRequest request, BindingResult result)
      throws Exception {
    if (result.hasErrors()) {
      throw new InvalidParameterException(
          Converter.convertFieldErrorListToJson(result.getFieldErrors()));
    }
    User user = userService.getUser(request.getId());

    return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.isDelFlg());
  }

  /**
   * Create user.
   *
   * @param request UserCreateRequest (name, email)
   * @return UserResponse (id, name, email, delFlg)
   */
  @PostMapping(value = "/create")
  public UserResponse create(@ModelAttribute @Validated UserCreateRequest request,
      BindingResult result)
      throws Exception {
    if (result.hasErrors()) {
      throw new InvalidParameterException(
          Converter.convertFieldErrorListToJson(result.getFieldErrors()));
    }
    User user =
        new User(null, request.getName(), request.getEmail(), LocalDateTime.now(), null,
            false);
    userService.createUser(user);

    return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.isDelFlg());
  }

  /**
   * Update user.
   *
   * @param request UserUpdateRequest (id, name, email, delFlg)
   * @return UserResponse (id, name, email, delFlg)
   */
  @PostMapping(value = "/update")
  public UserResponse update(@ModelAttribute @Validated UserUpdateRequest request,
      BindingResult result)
      throws Exception {
    if (result.hasErrors()) {
      throw new InvalidParameterException(
          Converter.convertFieldErrorListToJson(result.getFieldErrors()));
    }
    User user = userService.getUser(request.getId());
    userService.updateUser(user, request);

    return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.isDelFlg());
  }

  /**
   * Delete user.
   *
   * @param request UserRequest (id)
   * @return UserResponse (id, name, email, delFlg)
   */
  @PostMapping(value = "/delete")
  public UserResponse delete(@ModelAttribute @Validated UserRequest request, BindingResult result)
      throws Exception {
    if (result.hasErrors()) {
      throw new InvalidParameterException(
          Converter.convertFieldErrorListToJson(result.getFieldErrors()));
    }
    User user = userService.getUser(request.getId());
    userService.deleteUser(user.getId());

    return new UserResponse(user.getId(), user.getName(), user.getEmail(), true);
  }
}
