package jp.nlaboratory.mybatis.sample.domain.dto;

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

  private Long id;

  private String name;

  private String email;

  private Boolean delFlg;
}
