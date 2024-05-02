package jp.nlaboratory.mybatis.sample.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.validation.FieldError;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
})
public class MessageServiceTest {

  @Autowired
  private MessageService messageService;
  @Autowired
  MessageSource messageSource;

  @ParameterizedTest
  @ValueSource(strings = {"id", "email", "password"})
  @DisplayName("should get json validation error message.")
  public void convertFieldErrorMsgListToJson(String field) throws Exception {
    // Arrange
    MessageSourceResolvable resolvable = new DefaultMessageSourceResolvable(
        new String[] {"userUpdateRequest." + field, field}, field);
    FieldError error = new FieldError("userUpdateRequest", field, null, false, null,
        new MessageSourceResolvable[] {resolvable}, "{0} is required.");

    // Act
    String actual = messageService.convertFieldErrorMsgListToJson(List.of(error));

    // Assert
    assertEquals("{\"" + field + "\":\"[" + field + "] is required.\"}", actual);
  }
}
