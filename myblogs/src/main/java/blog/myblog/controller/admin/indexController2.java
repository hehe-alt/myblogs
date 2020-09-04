package blog.myblog.controller.admin;

import blog.myblog.entity.Author;
import blog.myblog.entity.Blog;
import blog.myblog.entity.Info;
import blog.myblog.service.AuthorService;
import blog.myblog.service.impl.BlogServiceImpl;
import blog.myblog.service.impl.InfoServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Api(tags = "后台首页相关控制器")
@Controller
@RequestMapping("admin")
public class indexController2 {
    @Autowired
    BlogServiceImpl blogService;
    @Autowired
    InfoServiceImpl infoService;
    @Autowired
    AuthorService authorService;

    @GetMapping("/index")
    public String index(){
        return "admin/index";
    }
    @GetMapping("/blog")
    public String blog(Model model){
        List<Blog> blogs = blogService.queryBlogByPage(1,8 );
        model.addAttribute("blogs",blogs);
        model.addAttribute("page",1);//当前页数
        int count = blogService.queryCounts();
        model.addAttribute("count",count);//数据的总条数
        return "admin/blog/blog";
    }
    @GetMapping("/author")
    public String author(Model model){
        List<Author> authors = authorService.queryAuthorByPage(1, 8);
        model.addAttribute("authors",authors);
        model.addAttribute("page",1);
        int count = authorService.queryCounts();
        model.addAttribute("count",count);
        return "admin/author/author";
    }
    @GetMapping("/info")
    public String info(Model model){
        int count = infoService.queryCounts();
        List<Info> infos = infoService.queryInfoByPage(1, 8);
        model.addAttribute("infos",infos);
        model.addAttribute("page",1);
        model.addAttribute("count",count);
        return "admin/info/info";
    }



}
