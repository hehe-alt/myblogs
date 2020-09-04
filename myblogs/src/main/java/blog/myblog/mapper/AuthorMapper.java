package blog.myblog.mapper;

import blog.myblog.entity.Author;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AuthorMapper {
    @Select("select count(*) from author")
    int queryCounts();
    List<Author> queryAuthors();
    Author queryAuthorByAccount(String account);
    @Select("select * from author where name like #{name}")
    Author queryAuthorByName(String name);
    @Select("select * from author where name like concat('%',#{name},'%')")
    List<Author> queryAuthorByNames(String name);
    @Select("select * from author limit  #{startIndex},#{pageSize}")
    List<Author> queryAuthorByPage(int startIndex, int pageSize);
    int addAuthor(Author author);
    /*@Update("update author set name=#{name},sex=#{sex},num=#{num},mail=#{mail},roleid=#{roleid} where id = #{id}")*/
    int updateAuthorById(@Param("name") String name,@Param("sex") String sex,@Param("num") String num,@Param("mail") String mail,@Param("roleid") int roleid, @Param("id") int id);
    @Delete("delete from author where id = #{id}")
    int deleteAuthorById(int id);




}
