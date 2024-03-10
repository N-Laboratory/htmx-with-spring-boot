package jp.nlaboratory.mybatis.sample.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data response object.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

  @Schema(description = "User id", example = "1")
  private Long id;

  @Schema(description = "User email", example = "john_smith@test.com")
  private String email;

  @Schema(description = "User password", example = "password")
  private String password;

  @Schema(description = "User delete flag", example = "false")
  private Boolean delFlg;
}
