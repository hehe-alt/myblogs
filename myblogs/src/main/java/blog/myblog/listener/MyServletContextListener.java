package blog.myblog.listener;


import blog.myblog.entity.Blog;
import blog.myblog.service.AuthorService;
import blog.myblog.service.BlogService;
import blog.myblog.service.InfoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * 使用ApplicationListener将一些常用数据（比如首页的数据）保存到application
 * 不过 application 这种是缓存在内存中，对内存会有消耗，
 */
@Component
public class MyServletContextListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 先获取到application上下文
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        // 获取对应的service
        BlogService blogService = applicationContext.getBean(BlogService.class);
        List<Blog> blogList = blogService.queryBlogs(6);
        List<String> tags  = blogService.queryTags();
        List<String> sorts  = blogService.querySorts();
        List<Blog> lasts  = blogService.queryBlogByLast(4);
        // 获取application域对象，将查到的信息放到application域中
        ServletContext application = applicationContext.getBean(ServletContext.class);
        application.setAttribute("blogList", blogList);
        application.setAttribute("tags", tags);
        application.setAttribute("sorts", sorts);
        application.setAttribute("lasts", lasts);


    }
}
