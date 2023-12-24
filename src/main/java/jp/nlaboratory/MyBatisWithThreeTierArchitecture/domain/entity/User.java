package jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for user data.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private Long id;
  private String name;
  private String email;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private boolean delFlg;
}
