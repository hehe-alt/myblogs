package blog.myblog.controller.admin;

import blog.myblog.entity.Blog;
import blog.myblog.service.impl.BlogServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api(tags = "后台首页相关控制器")
@Controller
@RequestMapping("admin")
public class blogContrtller {
    @Autowired
    BlogServiceImpl blogService;

    static int pageSize = 8;

    @GetMapping("/blogNextPage")
    public String blogNextPage(Model model,int page){
        System.out.println("当前页数:" +page);
        List<Blog> blogs = blogService.queryBlogByPage(page+1,pageSize);
        model.addAttribute("blogs",blogs);
        model.addAttribute("page",page+1);

        int count = blogService.queryCounts();
        model.addAttribute("count",count);
        return "admin/blog/blog";
    }
    @GetMapping("/blogPrePage")
    public String blogPrePage(Model model,int page){
        System.out.println("当前页数:" +page);
        if(page<1) return "admin/blog/blog";
        List<Blog> blogs = blogService.queryBlogByPage(page-1,pageSize);
        model.addAttribute("blogs",blogs);
        model.addAttribute("page",page-1);

        int count = blogService.queryCounts();
        model.addAttribute("count",count);
        return "admin/blog/blog";
    }


    @GetMapping("/blogToAdd")
    public String blogToAdd(Model model){
        Blog blog = new Blog();
        model.addAttribute("nblog",blog);
        return "admin/blog/add";
    }

    @ResponseBody
    @PostMapping("/blogAdd")
    public String blogAdd(Blog blog){
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH小时mm分钟ss秒");
        String time = df.format(date);//把当前时间转换为字符串
        blog.setCreateTime(time);blog.setUpdateTime(time);
        System.out.println(blog.toString());
        int r = blogService.addBlog(blog);
        if(r!=1) return "新增博客失败";
        return "新增博客成功";
    }

    @GetMapping("/blogSearch")
    public String blogSearch(String title,Model model){
        List<Blog> blogs = blogService.queryBlogByTitleVip(title);
        model.addAttribute("blogs",blogs);
        model.addAttribute("page",1);
        model.addAttribute("count",blogs.size());
        return "admin/blog/blog";
    }

    @PostMapping("/blogOption")
    public String blogOption(int id, String opt, RedirectAttributes redirectAttributes){
        if(opt.equals("删除")) {//使用RedirectAttributes传递重定向过程中的参数
            redirectAttributes.addAttribute("id",id);
            return "redirect:/admin/blogDelete";
        }
        else{
            redirectAttributes.addAttribute("id",id);
            return "redirect:/admin/blogToEdit";
        }
    }

    @ResponseBody
    @GetMapping("/blogDelete")
    public String blogDelete(@RequestParam(value = "id")int id){
        int r = blogService.deleteBlogById(id);
        if(r==0) return "删除失败";
        System.out.println("删除成功" + id);
        return "删除成功";
    }
    @GetMapping("/blogToEdit")
    public String blogToEdit(@RequestParam(value = "id")int id, Model model){
        model.addAttribute("id",id);
        return "/admin/blog/edit";
    }
    @ResponseBody
    @PostMapping("/blogEdit")
    public String blogEdit(String title,String author,int authorid,String tag,String sort,String free,int id){
        /*Blog blog = blogService.queryBlogById(id);
        if(title!=null) blog.setTitle(title);
        if(author!=null) blog.setAuthor(author);
        if(authorid!=0) blog.setAuthorid(authorid);
        if(tag!=null) blog.setTag(tag);
        if(sort!=null) blog.setSort(sort);
        System.out.println("编辑博客成功"+id);*/
        int r = blogService.updateBlogById(title, author, authorid, tag, sort, free, id);
        if(r==0) return "编辑失败";
        return "编辑成功";
    }

}
