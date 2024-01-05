package jp.nlaboratory.mybatis.sample.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

/**
 * Implementation for common message-related.
 */
@Service
public class MessageServiceImpl implements MessageService {

  @Autowired
  MessageSource messageSource;

  @Override
  public String convertFieldErrorMsgListToJson(List<FieldError> errorList)
      throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> errorMap = new HashMap<>();

    for (FieldError error : errorList) {
      String field = error.getField();
      // replace {0} in ValidationMessages with field name
      String message = messageSource.getMessage(error, Locale.getDefault());
      errorMap.put(field, message);
    }

    return mapper.writeValueAsString(errorMap);
  }
}
