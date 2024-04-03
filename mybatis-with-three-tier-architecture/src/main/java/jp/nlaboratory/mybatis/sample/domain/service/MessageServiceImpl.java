package jp.nlaboratory.mybatis.sample.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

/**
 * Implementation for common message-related.
 */
@Service
public class MessageServiceImpl implements MessageService {

  private final MessageSource messageSource;

  public MessageServiceImpl(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @Override
  public String convertFieldErrorMsgListToJson(List<FieldError> errorList)
      throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> errorMap = new HashMap<>();

    for (FieldError error : errorList) {
      String field = error.getField();

      // replace {0} in ValidationMessages.properties with field name
      String message = messageSource.getMessage(error, Locale.getDefault());
      String errorMessage =
          message.substring(message.indexOf("default message") + "default message".length() + 1);

      errorMap.put(field, errorMessage);
    }

    return mapper.writeValueAsString(errorMap);
  }
}
