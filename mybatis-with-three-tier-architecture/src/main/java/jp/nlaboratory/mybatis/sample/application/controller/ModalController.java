package jp.nlaboratory.mybatis.sample.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jp.nlaboratory.mybatis.sample.domain.service.UserService;
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
@Tag(name = "UserController", description = "Show create/edit/delete modal.")
public class ModalController {

  private final UserService userService;

  public ModalController(UserService userService) {
    this.userService = userService;
  }

  /**
   * show user create modal.
   *
   * @return user create modal html
   */
  @GetMapping(value = "/create")
  public String showCreateModal() {
    return "modal/create";
  }

  /**
   * show user edit modal.
   *
   * @param model model
   * @return user edit modal html
   * @throws Exception exception
   */
  @GetMapping(value = "/edit")
  public String showEditModal(@RequestParam(name = "id") Long id, Model model) throws Exception {
    model.addAttribute("user", userService.getUser(id));
    return "modal/edit";
  }

  /**
   * show user delete modal.
   *
   * @param model model
   * @return user delete modal html
   * @throws Exception exception
   */
  @GetMapping(value = "/delete")
  public String showDeleteModal(@RequestParam(name = "id") Long id, Model model) throws Exception {
    model.addAttribute("user", userService.getUser(id));
    return "modal/delete";
  }
}
