package jp.nlaboratory.MyBatisWithThreeTierArchitecture.infrastructure.mapper;

import jp.nlaboratory.MyBatisWithThreeTierArchitecture.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
  User findById(Long id);
  int insert(User user);
  int update(User user);
  int delete(Long id);
}
