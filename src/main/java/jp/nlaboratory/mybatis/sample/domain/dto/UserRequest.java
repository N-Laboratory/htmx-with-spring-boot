package jp.nlaboratory.mybatis.sample.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data request object. for user search/delete.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

  @Schema(description = "User id", example = "1")
  @NotNull(message = "Id is required.")
  @Positive(message = "Id must be positive number.")
  private Long id;
}
