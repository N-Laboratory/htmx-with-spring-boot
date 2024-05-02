package jp.nlaboratory.mybatis.sample.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.springframework.validation.FieldError;

/**
 * Interface for common message-related.
 */
public interface MessageService {

  String convertFieldErrorMsgListToJson(List<FieldError> errorList) throws JsonProcessingException;
}
