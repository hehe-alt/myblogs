package blog.myblog.controller.admin;

import blog.myblog.entity.Author;
import blog.myblog.entity.Blog;
import blog.myblog.service.AuthorService;
import blog.myblog.service.impl.BlogServiceImpl;
import io.swagger.annotations.Api;
import org.apache.shiro.crypto.hash.SimpleHash;
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
public class AuthorContrtller {
    @Autowired
    AuthorService authorService;

    static int pageSize = 8;

    @GetMapping("/authorNextPage")
    public String authorNextPage(Model model,int page){
        System.out.println("当前页数:" +page);
        List<Author> authors = authorService.queryAuthorByPage(page+1,pageSize);
        model.addAttribute("authors",authors);
        model.addAttribute("page",page+1);

        int count = authorService.queryCounts();
        model.addAttribute("count",count);
        return "admin/author/author";
    }
    @GetMapping("/authorPrePage")
    public String authorPrePage(Model model,int page){
        System.out.println("当前页数:" +page);
        List<Author> authors = authorService.queryAuthorByPage(page-1,pageSize);
        model.addAttribute("authors",authors);
        model.addAttribute("page",page-1);

        int count = authorService.queryCounts();
        model.addAttribute("count",count);
        return "admin/author/author";
    }


   @GetMapping("/authorToAdd")
    public String blogToAdd(Model model){
        Author author = new Author();
        model.addAttribute("nauthor",author);
        return "admin/author/add";
    }

    @ResponseBody
    @PostMapping("/authorAdd")
    public String authorAdd(Author author){
        System.out.println(author.toString());
        Integer hashIterations = 2;//散列次数
        //第一个参数是算法名称，这里指定md5，第二个是要加密的密码，第三个参数是加盐，第四个是散列次数
        SimpleHash hash = new SimpleHash("md5", author.getPwd(), author.getName(),hashIterations);
        author.setPwd(hash.toString());
        System.out.println(author.toString());
        int r = authorService.addAuthor(author);
        if(r!=1) return "新增用户失败";
        return "新增用户成功";
    }

    @GetMapping("/authorSearch")
    public String blogSearch(String name,Model model){
        System.out.println(name);
        List<Author> authors = authorService.queryAuthorByNames(name);
        model.addAttribute("authors",authors);
        model.addAttribute("page",1);
        model.addAttribute("count",authors.size());
        return "admin/author/author";
    }

    @PostMapping("/authorOption")
    public String authorOption(int id, String opt, RedirectAttributes redirectAttributes){
        if(opt.equals("删除")) {//使用RedirectAttributes传递重定向过程中的参数
            redirectAttributes.addAttribute("id",id);
            return "redirect:/admin/authorDelete";
        }
        else{
            redirectAttributes.addAttribute("id",id);
            return "redirect:/admin/authorToEdit";
        }
    }

    @ResponseBody
    @GetMapping("/authorDelete")
    public String authorDelete(@RequestParam(value = "id")int id){
        int r = authorService.deleteAuthorById(id);
        if(r==0) return "删除失败";
        System.out.println("删除成功" + id);
        return "删除成功";
    }
    @GetMapping("/authorToEdit")
    public String authorToEdit(@RequestParam(value = "id")int id, Model model){
        model.addAttribute("id",id);
        return "/admin/author/edit";
    }
    @ResponseBody
    @PostMapping("/authorEdit")
    public String blogEdit(String name,String sex,String num,String mail,int roleid,int id){
        int r = authorService.updateAuthorById(name, sex, num, mail, roleid, id);
        if(r==0) return "编辑失败";
        System.out.println("编辑成功");
        return "编辑成功";
    }
}
