package jp.nlaboratory.mybatis.sample.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Data request object for user update.
 */
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserUpdateRequest {

  @Schema(description = "User id", example = "1")
  @NotNull
  @Positive
  private Long id;

  @Schema(description = "User email", example = "john_smith@test.com")
  @NotBlank
  private String email;

  @Schema(description = "User password", example = "password")
  @NotBlank
  private String password;
}
