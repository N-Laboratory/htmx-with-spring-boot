package jp.nlaboratory.mybatis.sample.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jp.nlaboratory.mybatis.sample.application.exception.InvalidParameterException;
import jp.nlaboratory.mybatis.sample.domain.dto.UserCreateRequest;
import jp.nlaboratory.mybatis.sample.domain.dto.UserResponse;
import jp.nlaboratory.mybatis.sample.domain.dto.UserUpdateRequest;
import jp.nlaboratory.mybatis.sample.domain.entity.User;
import jp.nlaboratory.mybatis.sample.domain.service.MessageService;
import jp.nlaboratory.mybatis.sample.domain.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Api for user data CRUD.
 */
@Controller
@RequestMapping("api/v1")
@Tag(name = "UserController", description = "User CRUD API with MyBatis.")
public class UserController {

  private final UserService userService;
  private final MessageService messageService;

  public UserController(UserService userService, MessageService messageService) {
    this.userService = userService;
    this.messageService = messageService;
  }

  /**
   * Search all user.
   *
   * @return user table
   */
  @Operation(
      summary = "Get all user data.",
      description = "Get all user data from MySQL DB using MyBatis."
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Success.",
              content = {
                  @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = UserResponse.class)
                  )
              }
          ),
          @ApiResponse(responseCode = "404", description = "User not found.",
              content = {
                  @Content(mediaType = "text/plan",
                      schema = @Schema(example = "User not found. User id = 1.")
                  )
              }
          ),
          @ApiResponse(responseCode = "500", description = "Internal Server Error.",
              content = {
                  @Content(mediaType = "text/plan",
                      schema = @Schema(example = "An unexpected error has occurred. "
                          + "Some error message..."))
              }
          )
      }
  )
  @GetMapping(value = "/users")
  public String searchAll(Model model) throws Exception {
    model.addAttribute("users", userService.getAllUser());

    Thread.sleep(1000);
    return "userList";
  }

  /**
   * Create user.
   *
   * @param request UserCreateRequest (email, password)
   * @return UserResponse (id, email, password, delFlg)
   */
  @Operation(
      summary = "Create user data.",
      description = "Insert user data into MySQL DB using MyBatis."
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Success.",
              content = {
                  @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = UserResponse.class)
                  )
              }
          ),
          @ApiResponse(responseCode = "400", description = "Invalid request parameter.",
              content = {
                  @Content(mediaType = "text/plan",
                      schema = @Schema(example = "Request parameter is invalid.\n"
                          + "{\"password\":\"password is required.\"}"))
              }
          ),
          @ApiResponse(responseCode = "500", description = "Internal server error.",
              content = {
                  @Content(mediaType = "text/plan",
                      schema = @Schema(example = "An unexpected error has occurred. "
                          + "Some error message..."))
              }
          )
      }
  )
  @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Data request object for user creation.",
      required = true,
      content = {
          @Content(schema = @Schema(implementation = UserCreateRequest.class))
      }
  )
  @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String create(@RequestBody @Validated UserCreateRequest request,
      BindingResult result, Model model)
      throws Exception {
    if (result.hasErrors()) {
      throw new InvalidParameterException(
          messageService.convertFieldErrorMsgListToJson(result.getFieldErrors()));
    }

    User user =
        new User(null, request.getEmail(), request.getPassword());
    userService.createUser(user);

      model.addAttribute("user", user);
      model.addAttribute("message", "The following user have been created:");

    return "modal/result";
  }

  /**
   * Update user.
   *
   * @param request UserUpdateRequest (id, email, password, delFlg)
   * @return UserResponse (id, email, password, delFlg)
   */
  @Operation(
      summary = "Update user data.",
      description = "Update user data in MySQL DB using MyBatis."
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Success.",
              content = {
                  @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = UserResponse.class)
                  )
              }
          ),
          @ApiResponse(responseCode = "400", description = "Invalid request parameter.",
              content = {
                  @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                      schema = @Schema(example = "Request parameter is invalid.\n"
                          + "{\"password\":\"password is required.\"}"))
              }
          ),
          @ApiResponse(responseCode = "404", description = "User not found.",
              content = {
                  @Content(mediaType = "text/plan",
                      schema = @Schema(example = "User not found. User id = 1")
                  )
              }
          ),
          @ApiResponse(responseCode = "500", description = "Internal server error.",
              content = {
                  @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                      schema = @Schema(example = "An unexpected error has occurred. "
                          + "Some error message..."))
              }
          )
      }
  )
  @io.swagger.v3.oas.annotations.parameters.RequestBody(
      description = "Data request object for user update.",
      required = true,
      content = {
          @Content(schema = @Schema(implementation = UserUpdateRequest.class))
      }
  )
  @PutMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String update(@RequestBody @Validated UserUpdateRequest request,
      BindingResult result, Model model)
      throws Exception {
    if (result.hasErrors()) {
      throw new InvalidParameterException(
          messageService.convertFieldErrorMsgListToJson(result.getFieldErrors()));
    }

    User user = userService.getUser(request.getId());
    userService.updateUser(user, request);
    model.addAttribute("user", user);
    model.addAttribute("message", "The following user edits have been completed:");

    return "modal/result";
  }

  /**
   * Delete user.
   *
   * @param id User id
   * @return UserResponse (id, email, password, delFlg)
   */
  @Operation(
      summary = "Delete user data.",
      description = "Delete user data in MySQL DB using MyBatis."
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Success.",
              content = {
                  @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                      schema = @Schema(implementation = UserResponse.class)
                  )
              }
          ),
          @ApiResponse(responseCode = "404", description = "User not found.",
              content = {
                  @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                      schema = @Schema(example = "User not found. User id = 1")
                  )
              }
          ),
          @ApiResponse(responseCode = "500", description = "Internal server error.",
              content = {
                  @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                      schema = @Schema(example = "An unexpected error has occurred. "
                          + "Some error message..."))
              }
          )
      }
  )
  @DeleteMapping(value = "/user")
  public String delete(@Parameter(
      name = "id",
      description = "User id for user deletion.",
      schema = @Schema(example = "1"),
      in = ParameterIn.QUERY,
      required = true
  ) @RequestParam(name = "id") Long id, Model model) throws Exception {
    User user = userService.getUser(id);
    userService.deleteUser(user.getId());
    model.addAttribute("user", user);
    model.addAttribute("message", "The following user have been deleted:");

    return "modal/result";
  }
}
