package jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data request object.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

  private Long id;
  private String name;
  private String email;
  private boolean delFlg;
}
