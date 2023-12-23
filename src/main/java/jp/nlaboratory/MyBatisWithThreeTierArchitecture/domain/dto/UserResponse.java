package jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

  /**
   * User-ID.
   */
  private Long id;
  private String name;
  private String email;
  private Boolean delFlg;
}
