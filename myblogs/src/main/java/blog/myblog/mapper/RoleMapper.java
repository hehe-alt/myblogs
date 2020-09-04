package blog.myblog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleMapper {
    @Select("select rolename from role where id =#{id}")
    String queryRole(int id);
}
