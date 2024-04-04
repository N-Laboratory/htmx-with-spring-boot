package jp.nlaboratory.mybatis.sample.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jp.nlaboratory.mybatis.sample.domain.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * show create/edit/delete modal.
 */
@Controller
@RequestMapping("api/v1/modal")
@Tag(name = "ModalController", description = "Show create/edit/delete modal.")
public class ModalController {

  private final UserService userService;

  @SuppressWarnings({"checkstyle:MemberName", "checkstyle:AbbreviationAsWordInName"})
  private final String CREATE_MODAL = """
      <div>
        <div>
          <h3>Create New User</h3>
          <button>
            <span>Close modal</span>
          </button>
        </div>
        <form>
          <div>
            <div>
              <label>Email</label>
              <input type="text" name="email"/>
            </div>
            <div>
              <label>Password</label>
              <input type="password" name="password"/>
            </div>
          </div>
          <button>Add new user</button>
        </form>
      </div>""";

  @SuppressWarnings({"checkstyle:MemberName", "checkstyle:AbbreviationAsWordInName"})
  private final String EDIT_MODAL = """
      <div>
        <div>
          <h3>Edit User</h3>
          <button>
            <span>Close modal</span>
          </button>
        </div>
        <form>
          <input type="hidden" name="id" value="1" />
          <div>
            <div>
              <label>Email</label>
              <input type="text" name="email" />
            </div>
            <div>
              <label>Password</label>
              <input type="password" name="password" />
            </div>
          </div>
          <button>Submit</button>
        </form>
      </div>""";

  @SuppressWarnings({"checkstyle:MemberName", "checkstyle:AbbreviationAsWordInName"})
  private final String DELETE_MODAL = """
        <div>
          <div>
            <h3>Are you sure you want to delete following user?</h3>
            <div>
              <p>Email: test@test.com</p>
              <p>Password: test</p>
            </div>
            <form>
              <input type="hidden" name="id" value="1" />
              <button>OK</button>
            </form>
            <button>Cancel</button>
          </div>
        </div>
      """;

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

  @SuppressWarnings({"checkstyle:MemberName", "checkstyle:AbbreviationAsWordInName"})
  private final String USER_NOT_FOUND_ERROR = """
      <div>
        <div>
          <div>
            <h3>User not found.</h3>
          </div>
        </div>
        <button>OK</button>
      </div>""";

  public ModalController(UserService userService) {
    this.userService = userService;
  }

  /**
   * show user creation modal.
   *
   * @return user creation modal
   */
  @Operation(
      summary = "show user creation modal."
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "show user creation modal.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {
                          @ExampleObject(name = "show modal",
                              description = "show user creation modal", value = CREATE_MODAL)}
                  )
              }
          ),
      }
  )
  @GetMapping(value = "/create")
  public String showCreateModal() {
    return "modal/create";
  }

  /**
   * show user edit modal.
   *
   * @param model model
   * @return user edit modal
   * @throws Exception exception
   */
  @Operation(
      summary = "show user edit modal."
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "show user edit modal.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {
                          @ExampleObject(name = "show modal",
                              description = "show user edit modal", value = EDIT_MODAL)}
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
  @GetMapping(value = "/edit")
  public String showEditModal(@RequestParam(name = "id") Long id, Model model) throws Exception {
    model.addAttribute("user", userService.getUser(id));
    return "modal/edit";
  }

  /**
   * show user deletion modal.
   *
   * @param model model
   * @return user deletion modal
   * @throws Exception exception
   */
  @Operation(
      summary = "show user deletion modal."
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Show user deletion modal.",
              content = {
                  @Content(mediaType = MediaType.TEXT_HTML_VALUE,
                      examples = {
                          @ExampleObject(name = "show modal",
                              description = "show user deletion modal", value = DELETE_MODAL)}
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
  @GetMapping(value = "/delete")
  public String showDeleteModal(@RequestParam(name = "id") Long id, Model model) throws Exception {
    model.addAttribute("user", userService.getUser(id));
    return "modal/delete";
  }
}
