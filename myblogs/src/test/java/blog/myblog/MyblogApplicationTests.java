package blog.myblog;

import blog.myblog.mapper.BlogMapper;
import blog.myblog.mapper.InfoMapper;
import blog.myblog.mapper.RoleMapper;
import blog.myblog.entity.Blog;

import blog.myblog.service.AuthorService;
import blog.myblog.service.BlogService;
import blog.myblog.service.InfoService;
import blog.myblog.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class MyblogApplicationTests {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    BlogService blogService;
    @Autowired
    AuthorService authorMapper;
    @Autowired
    AuthorService authorService;
    @Autowired
    InfoMapper infoMapper;
    @Autowired
    InfoService infoService;
    @Autowired
    RedisService redisService;
    @Autowired
    RoleMapper roleMapper;

    /**
     * 测试博客增删改查
     */
    @Test
    void testBlog() {
       /* List<Blog> blogs = blogMapper.queryBlogByTitle("0");
        blogs.forEach(e-> System.out.println(e.toString()));*/
        //List<Blog> blogs = blogMapper.queryBlogs(5);
        //List<Blog> blogs = blogMapper.queryBlogsVip();
        //List<Blog> blogs = blogMapper.queryBlogByTitleVip("2");
        //List<Blog> blogs = blogMapper.queryBlogByTag("读书笔记");
        //blogs.forEach(e-> System.out.println(e.toString()));

        /*Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH小时mm分钟ss秒");
        String time = df.format(date);
        Blog blog = new Blog(4, "fdssdfsdffdsfd","试题3", time, time, "张三",1 ,"算法题目","yes");
        int r = blogMapper.addBlog(blog);
        System.out.println(r+" "+blog.getId());*/

        //List<String> tags = blogMapper.queryTags();
        /*List<String> tags = blogMapper.querySorts();
        tags.forEach(e-> System.out.println(e));*/

       /* int r = blogMapper.updateBlogById("7895", 2);
        System.out.println(r);*/

        /*int r = blogMapper.deleteBlogByTitle("7895");
        System.out.println(r);*/

        //List<Blog> blogs = blogService.queryBlogByLast(4);
        /*List<Blog> blogs = blogService.queryBlogByPage(2, 3);
        blogs.forEach(e-> System.out.println(e.toString()));*/

        /*int r = blogMapper.updateBlogById("心得感悟01", "李四", 2, "经验", "心得感悟", "yes", 15);
        System.out.println(r);*/

        //System.out.println(blogMapper.queryBlogByCreateTime("2020年010月03日21小时04分钟54秒").toString());
        blogService.updateBlogById("十九", null, 3, null, null, "no", 19);
    }
    /**
     * 测试作者增删改查
     */
    @Test
    void testAuthor(){
        /*List<Author> authors = authorMapper.queryAuthors();
        authors.forEach(e-> System.out.println(e.toString()));*/

        /*Author author = authorService.queryAuthorByAccount("123456");
        System.out.println(author.toString());*/

       /* Author author = new Author(0,"李四" ,"男","123456","123456@qq.com","123456","123456","");
        int r = authorMapper.addAuthor(author);
        System.out.println(r+" "+author.getId());*/
       /*int r = authorMapper.deleteAuthorById(2);
        System.out.println(r);*/

        //System.out.println(roleMapper.queryRole(1));
        authorService.updateAuthorById(null ,null, null, "1634656@163.com", 2, 10);
    }
    /**
     * 测试留言信息增删改查
     */
    @Test
    void testInfo(){
        /*Info info = new Info(0,"张三","456@qq.com","8888","留言信息测试",new Date());
        int r = infoService.addInfo(info);
        System.out.println(r+" ");*/

        /*int r = infoService.deleteInfoById(1);
        System.out.println(r);*/

       /* List<Info> infos = infoService.queryInfos();
        infos.forEach(e-> System.out.println(e.toString()));*/

        //System.out.println(infoMapper.queryCounts());

        int r = infoMapper.updateInfoById(null, null, null, null, "2021-8-9", 14);
        System.out.println(r);

    }

    @Test
    void testRedis(){
        redisService.setString("weichat","test000test");
        System.out.println(redisService.getString("weichat"));


    }

}
