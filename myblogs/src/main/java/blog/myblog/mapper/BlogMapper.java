package blog.myblog.mapper;

import blog.myblog.entity.Blog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogMapper {
    @Select("select count(*) from blog")
    int queryCounts();
    @Select("select * from blog limit  #{startIndex},#{pageSize}")
    List<Blog> queryBlogByPage(int startIndex,int pageSize);
    List<Blog> queryBlogByTitle(String title);
    @Select("select * from blog where title like concat('%',#{title},'%')")
    List<Blog> queryBlogByTitleVip(String title);
    Blog queryBlogById(int id);
    List<Blog> queryBlogs(int n);
    List<Blog> queryBlogsVip();
    @Select("select distinct tag from blog")
    List<String> queryTags();
    @Select("select * from blog where tag = #{tag} and free = 'yes' ")
    List<Blog> queryBlogByTag(String tag);
    @Select("select distinct sort from blog")
    List<String> querySorts();
    @Select("select * from blog where sort = #{sort} and free = 'yes' ")
    List<Blog> queryBlogBySort(String sort);
    @Select("select * from blog where free = 'yes' ")
    List<Blog> queryBlogByLast(int n);
    @Select("select distinct * from blog where createTime = #{createTime} ")
    Blog queryBlogByCreateTime(String createTime);
    int addBlog(Blog blog);
    /*@Update("update blog set title=#{title},author=#{author},authorid=#{authorid},tag=#{tag},sort=#{sort},free=#{free} where id =#{id}")*/
    int updateBlogById(@Param("title")String title,@Param("author")String author,@Param("authorid")int  authorid,@Param("tag")String tag,@Param("sort")String sort,@Param("free")String free,@Param("id") int id);
    @Delete("delete from blog where id = #{id}")
    int deleteBlogById(int id);
}
