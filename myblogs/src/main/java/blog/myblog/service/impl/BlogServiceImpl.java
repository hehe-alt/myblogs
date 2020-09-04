package blog.myblog.service.impl;

import blog.myblog.mapper.BlogMapper;
import blog.myblog.entity.Blog;
import blog.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogMapper blogMapper;

    @Override
    public int queryCounts() {
        return blogMapper.queryCounts();
    }

    @Override
    public List<Blog> queryBlogByPage(int page,int size) {
        if(page<1) return null;
        int startIndex=(page-1)*size,pageSize=size;
        return blogMapper.queryBlogByPage(startIndex,pageSize);
    }

    @Override
    public List<Blog> queryBlogByTitle(String title) {
        return blogMapper.queryBlogByTitle(title);
    }
    @Override
    public List<Blog> queryBlogByTitleVip(String title){return  blogMapper.queryBlogByTitleVip(title);}
    @Override
    public List<Blog> queryBlogs(int n) {
        return blogMapper.queryBlogs(n);
    }
    @Override
    public List<Blog> queryBlogsVip(){return blogMapper.queryBlogsVip();}

    @Override
    public List<String> queryTags() {
        return blogMapper.queryTags();
    }

    @Override
    public List<Blog> queryBlogByTag(String tag){return  blogMapper.queryBlogByTag(tag);}

    @Override
    public List<String> querySorts() {
        return blogMapper.querySorts();
    }

    @Override
    public List<Blog> queryBlogBySort(String sort) {
        return  blogMapper.queryBlogBySort(sort);
    }

    @Override
    public List<Blog> queryBlogByLast(int n){
        DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH小时mm分钟ss秒");
        List<Date> dateList = new ArrayList<>();
        List<Blog> blogs = blogMapper.queryBlogByLast(n);//得到所有非付费文章
        blogs.forEach(e->{
            try {
                dateList.add(dateFormat.parse(e.getCreateTime()));//取出日期数据（字符串类型），转换为Date类型
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });
        ListDateSort(dateList);//对日期对象进行排序
        System.out.println("排序完成");
        List<String> stringList =new ArrayList<>();
        for(int i=0;i<n;i++) {//再把排序后的日期转为字符串类型
            stringList.add(dateFormat.format(dateList.get(i)));
        }
        List<Blog> rblog = new ArrayList<>();
        for(int i=0;i<n;i++) {
            rblog.add(blogMapper.queryBlogByCreateTime(stringList.get(i)));//最后再次更加字符串查找文章，得到最近的n篇文章
        }
        return rblog;
    }

    @Override
    public int addBlog(Blog blog) {
        return blogMapper.addBlog(blog);
    }

    @Override
    public int updateBlogById(String title,String author,int  authorid,String tag, String sort,String free,int id) {
        return blogMapper.updateBlogById(title, author, authorid, tag, sort, free, id);
    }

    @Override
    public int deleteBlogById(int id) {
        return blogMapper.deleteBlogById(id);
    }

    @Override
    public Blog queryBlogById(int id){
        return  blogMapper.queryBlogById(id);
    }

    public static void ListDateSort(List<Date> list) {//对日期进行排序，由现在到以前
        Collections.sort(list, new Comparator<Date>() {
                @Override
                public int compare(Date dt1, Date dt2) {
                    try {
                        // 这是由大向小排序   如果要由小向大转换比较符号就可以
                        if (dt1.getTime() < dt2.getTime()) {
                            return 1;
                        } else if (dt1.getTime() > dt2.getTime()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }

            });
    }
}