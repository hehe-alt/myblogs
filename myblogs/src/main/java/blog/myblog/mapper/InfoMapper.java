package blog.myblog.mapper;

import blog.myblog.entity.Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InfoMapper {
    @Select("select count(*) from info")
    int queryCounts();
    List<Info> queryInfos();
    @Select("select * from info limit  #{startIndex},#{pageSize}")
    List<Info> queryInfoByPage(int startIndex, int pageSize);
    @Select("select * from info where name like concat('%',#{name},'%')")
    List<Info> queryInfoByNames(String name);
    int addInfo(Info info);
    int deleteInfoById(int id);
    /*@Update("update info set name=#{name},mail=#{mail},phone=#{phone},message=#{message},createTime=#{createTime} where id = #{id}")*/
    int updateInfoById(String name, String mail, String phone, String message, String createTime, int id);
}
