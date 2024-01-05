package jp.nlaboratory.mybatis.sample.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data request object for user create.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

  @Schema(description = "User name", example = "John Smith")
  @NotBlank
  private String name;

  @Schema(description = "User email", example = "john_smith@test.com")
  @NotBlank
  private String email;
}
