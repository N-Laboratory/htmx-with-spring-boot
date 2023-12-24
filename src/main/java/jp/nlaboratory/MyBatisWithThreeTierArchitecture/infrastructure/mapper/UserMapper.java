package jp.nlaboratory.MyBatisWithThreeTierArchitecture.infrastructure.mapper;

import java.util.Optional;
import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper for user data CRUD.
 */
@Mapper
public interface UserMapper {

  Optional<User> findById(Long id);

  boolean insert(User user);

  boolean update(User user);

  boolean delete(Long id);
}
