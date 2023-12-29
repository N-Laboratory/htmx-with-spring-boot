package jp.nlaboratory.mybatis.sample.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data request object for user update.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {

  @Schema(description = "User id", example = "1")
  @NotNull(message = "Id is required.")
  @Positive(message = "Id must be positive number.")
  private Long id;

  @Schema(description = "User name", example = "John Smith")
  @NotBlank(message = "Name is required.")
  private String name;

  @Schema(description = "User email", example = "john_smith@test.com")
  @NotBlank(message = "Email is required.")
  private String email;

  @Schema(description = "User delete flag", example = "false")
  @NotNull(message = "Delete flag is required.")
  private boolean delFlg;
}
