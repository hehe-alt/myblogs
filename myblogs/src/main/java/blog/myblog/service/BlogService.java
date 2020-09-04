package blog.myblog.service;

import blog.myblog.entity.Blog;

import java.util.List;


public interface BlogService {
    int queryCounts();
    List<Blog> queryBlogByPage(int page,int size);
    List<Blog> queryBlogByTitle(String title);
    List<Blog> queryBlogByTitleVip(String title);
    Blog queryBlogById(int id);
    List<Blog> queryBlogs(int n);
    List<Blog> queryBlogsVip();
    List<String> queryTags();
    List<Blog> queryBlogByTag(String tag);
    List<String> querySorts();
    List<Blog> queryBlogBySort(String sort);
    List<Blog> queryBlogByLast(int n);
    int addBlog(Blog blog);
    int updateBlogById(String title,String author,int  authorid,String tag, String sort,String free,int id);
    int deleteBlogById(int id);
}
