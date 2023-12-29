package jp.nlaboratory.mybatis.sample.domain.dto;

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

  @NotNull(message = "Id is required.")
  @Positive(message = "Id must be positive number.")
  private Long id;

  @NotBlank(message = "Name is required.")
  private String name;

  @NotBlank(message = "Email is required.")
  private String email;

  @NotNull(message = "Delete flag is required.")
  private boolean delFlg;
}
