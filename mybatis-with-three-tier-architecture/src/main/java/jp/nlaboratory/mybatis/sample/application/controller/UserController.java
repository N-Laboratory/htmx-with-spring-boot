package jp.nlaboratory.mybatis.sample.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jp.nlaboratory.mybatis.sample.application.exception.InvalidParameterException;
import jp.nlaboratory.mybatis.sample.domain.dto.UserCreateRequest;
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
  @SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "checkstyle:MemberName"})
  private final String USER_FOUND = """
      <table>
        <thead>
          <tr>
            <th>Email</th>
            <th>Password</th>
            <th>Edit</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>test@test.com</td>
            <td>test</td>
            <td><button>Edit</button></td>
            <td><button>Delete</button></td>
          </tr>
        </tbody>
      </table>""";
  @SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "checkstyle:MemberName"})
  private final String USER_NOT_FOUND = """
      <div>
        <span class="sr-only">Info</span>
        <div>
          <span class="font-medium">User not found!</span> Please create new user.
        </div>
      </div>""";

  @SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "checkstyle:MemberName"})
  private final String ERROR = """
      <div>
        <div>
          <div>
            <h3>An unexpected error occurred.</h3>
          </div>
        </div>
        <button>OK</button>
      </div>""";

  @SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "checkstyle:MemberName"})
  private final String USER_NOT_FOUND_ERROR = """
      <div>
        <div>
          <div>
            <h3>User not found.</h3>
          </div>
        </div>
        <button>OK</button>
      </div>""";

  @SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "checkstyle:MemberName"})
  private final String GET_RESULT = """
      <div>
        <div>
          <div>
            <h3>The following user was found:</h3>
            <div>
              <p>Email: test@test.com</p>
              <p>Password: test</p>
            </div>
          </div>
        </div>
        <button>OK</button>
      </div>""";

  @SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "checkstyle:MemberName"})
  private final String CREATE_RESULT = """
      <div>
        <div>
          <div>
            <h3>The following user have been created:</h3>
            <div>
              <p>Email: test@test.com</p>
              <p>Password: test</p>
            </div>
          </div>
        </div>
        <button>OK</button>
      </div>""";

  @SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "checkstyle:MemberName"})
  private final String UPDATE_RESULT = """
      <div>
        <div>
          <div>
            <h3>The following user edits have been completed:</h3>
            <div>
              <p>Email: test@test.com</p>
              <p>Password: test</p>
            </div>
          </div>
        </div>
        <button>OK</button>
      </div>""";

  @SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "checkstyle:MemberName"})
  private final String DELETE_RESULT = """
      <div>
        <div>
          <div>
            <h3>The following user have been deleted:</h3>
            <div>
              <p>Email: test@test.com</p>
              <p>Password: test</p>
            </div>
          </div>
        </div>
        <button>OK</button>
      </div>""";

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
      description = "Get all user data to display user table."
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Success.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {@ExampleObject(name = "user found", value = USER_FOUND),
                          @ExampleObject(name = "user not found", value = USER_NOT_FOUND)}
                  )
              }
          ),
          @ApiResponse(responseCode = "500", description = "Internal Server Error.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {@ExampleObject(name = "error", value = ERROR)}
                  )
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
   * Search user.
   *
   * @param id User id
   * @return result modal
   */
  @Operation(
      summary = "Get user data.",
      description = "Display user info in result modal."
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "User found.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {@ExampleObject(name = "user found", value = GET_RESULT)}
                  )
              }
          ),
          @ApiResponse(responseCode = "404", description = "User not found.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {
                          @ExampleObject(name = "user not found", value = USER_NOT_FOUND_ERROR)}
                  )
              }
          ),
          @ApiResponse(responseCode = "500", description = "Internal Server Error.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {@ExampleObject(name = "error", value = ERROR)}
                  )
              }
          )
      }
  )
  @GetMapping(value = "/user")
  public String search(@Parameter(
      name = "id",
      description = "User id for user search.",
      schema = @Schema(example = "1"),
      in = ParameterIn.QUERY,
      required = true
  ) @RequestParam(name = "id") Long id, Model model)
      throws Exception {
    User user = userService.getUser(id);

    model.addAttribute("user", user);
    model.addAttribute("message", "The following user was found:");

    return "modal/result";
  }

  /**
   * Create user.
   *
   * @param request UserCreateRequest (email, password)
   * @return result modal
   */
  @Operation(
      summary = "Create user data.",
      description = "Display created user info in result modal."
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Success.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {
                          @ExampleObject(name = "user creation succeeded", value = CREATE_RESULT)}
                  )
              }
          ),
          @ApiResponse(responseCode = "400", description = "Invalid request parameter.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {@ExampleObject(name = "invalid parameter", value = ERROR)}
                  )
              }
          ),
          @ApiResponse(responseCode = "500", description = "Internal server error.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {@ExampleObject(name = "error", value = ERROR)}
                  )
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
   * @return result modal
   */
  @Operation(
      summary = "Update user data.",
      description = "Display updated user info in result modal."
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Success.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {
                          @ExampleObject(name = "user update succeeded", value = UPDATE_RESULT)}
                  )
              }
          ),
          @ApiResponse(responseCode = "400", description = "Invalid request parameter.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {@ExampleObject(name = "invalid parameter", value = ERROR)}
                  )
              }
          ),
          @ApiResponse(responseCode = "404", description = "User not found.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {
                          @ExampleObject(name = "user not found", value = USER_NOT_FOUND_ERROR)}
                  )
              }
          ),
          @ApiResponse(responseCode = "500", description = "Internal server error.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {@ExampleObject(name = "error", value = ERROR)}
                  )
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
   * @return result modal
   */
  @Operation(
      summary = "Delete user data.",
      description = "Display deleted user info in result modal."
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Success.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {
                          @ExampleObject(name = "user deletion succeeded", value = DELETE_RESULT)}
                  )
              }
          ),
          @ApiResponse(responseCode = "404", description = "User not found.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {
                          @ExampleObject(name = "user not found", value = USER_NOT_FOUND_ERROR)}
                  )
              }
          ),
          @ApiResponse(responseCode = "500", description = "Internal server error.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {@ExampleObject(name = "error", value = ERROR)}
                  )
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
