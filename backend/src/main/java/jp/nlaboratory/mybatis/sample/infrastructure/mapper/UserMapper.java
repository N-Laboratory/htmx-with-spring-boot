package jp.nlaboratory.mybatis.sample.infrastructure.mapper;

import java.util.List;
import java.util.Optional;
import jp.nlaboratory.mybatis.sample.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper for user data CRUD.
 */
@Mapper
public interface UserMapper {

  List<User> findAll();

  Optional<User> findById(Long id);

  boolean insert(User user);

  boolean update(User user);

  boolean delete(Long id);
}
