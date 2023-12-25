package jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.dto;

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

  @NotNull(message = "Id is required.")
  @Positive(message = "Id must be positive number.")
  private Long id;
}
