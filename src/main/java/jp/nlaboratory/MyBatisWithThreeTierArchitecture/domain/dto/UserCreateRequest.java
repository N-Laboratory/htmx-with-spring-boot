package jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.dto;

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

  @NotBlank(message = "Name is required.")
  private String name;
  
  @NotBlank(message = "Email is required.")
  private String email;
}
