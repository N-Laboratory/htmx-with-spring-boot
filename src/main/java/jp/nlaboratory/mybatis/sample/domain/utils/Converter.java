package jp.nlaboratory.mybatis.sample.domain.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.FieldError;

/**
 * Utils class for data converter.
 */
public class Converter {

  /**
   * Convert FieldErrorList to JSON string.
   *
   * @param errorList Error list of request parameter.
   * @return JSON string { "field", "message" }
   * @throws JsonProcessingException If conversion fails, throw this error.
   */
  public static String convertFieldErrorListToJson(List<FieldError> errorList)
      throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> errorMap = new HashMap<>();

    for (FieldError error : errorList) {
      String field = error.getField();
      String message = error.getDefaultMessage();
      errorMap.put(field, message);
    }

    return mapper.writeValueAsString(errorMap);
  }
}
