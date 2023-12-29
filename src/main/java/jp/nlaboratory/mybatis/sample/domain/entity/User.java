package jp.nlaboratory.mybatis.sample.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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

  @Schema(description = "User id", example = "1")
  private Long id;

  @Schema(description = "User name", example = "John Smith")
  private String name;

  @Schema(description = "User email", example = "john_smith@test.com")
  private String email;

  @Schema(description = "Time the user was created", example = "2023/10/10 10:10:10")
  private LocalDateTime createdAt;

  @Schema(description = "Time the user was updated", example = "2023/12/31 23:59:59")
  private LocalDateTime updatedAt;

  @Schema(description = "User delete flag", example = "false")
  private boolean delFlg;
}
