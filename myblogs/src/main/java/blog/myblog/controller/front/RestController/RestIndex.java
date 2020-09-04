package blog.myblog.controller.front.RestController;

import blog.myblog.entity.Blog;
import blog.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestIndex {
    @Autowired
    BlogService blogService;

    @GetMapping("/index/{size}/{page}")
    public List<Blog> index(@PathVariable("size") int size,@PathVariable("page") int page){
        List<Blog> blogs = blogService.queryBlogByPage(size,page);
        return blogs;
    }


    @GetMapping("/aa")
    public String aa(){
        return "sdfdfsdfdf";
    }

}
