package blog.myblog.controller.front;


import blog.myblog.exception.BusinessErrorException;
import blog.myblog.exception.BusinessMsgEnum;
import blog.myblog.entity.Blog;
import blog.myblog.entity.Info;
import blog.myblog.service.impl.BlogServiceImpl;
import blog.myblog.service.impl.InfoServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "首页相关控制器")
@Controller
public class indexController {

    @Autowired
    BlogServiceImpl blogService;
    @Autowired
    InfoServiceImpl infoService;

    @GetMapping("/succ")
    public String succ(){
        return "succ";
    }

    @ApiOperation("首页")
    @GetMapping({"/","/index"})
    public String toIndex(HttpServletRequest request,Model model){
        try{
            ServletContext application = request.getServletContext();
            List<Blog> blogs = (List<Blog>) application.getAttribute("blogList");
            model.addAttribute("blogs",blogs);
        }catch(Exception e){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        return "front/index";
    }


    @ApiOperation("文章详情页面")
    @GetMapping("/page")
    public String page(Model model,int id){
        try {
            Blog blog = blogService.queryBlogById(id);
            model.addAttribute("theBlog",blog);
        }catch (Exception e){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        return "front/post";
    }

    @ApiOperation("文章查询页面")
    @GetMapping("/toSelect")
    public String toSelect(){
        return "front/blog/select";
    }


    @ApiOperation("查询文章")
    @GetMapping("/select")
    public String select(Model model,String title){
        try{
            List<Blog> blogs =new ArrayList<>();
            blogService.queryBlogByTitle(title).forEach(i->blogs.add(i));
            model.addAttribute("blogs",blogs);
        }catch (Exception e){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        return "front/blog/select";
    }

    @ApiOperation("关于我页面")
    @GetMapping("/toAbout")
    public String toAbout(){
        return "front/about";
    }
    @ApiOperation("联系我页面")
    @GetMapping("/toContact")
    public String toContact(){
        return "front/contact";
    }
    @ApiOperation("留言功能")
    @PostMapping("/contact")
    public String Contact(String name,String Email,String phone,String message){
        try{
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH小时mm分钟ss秒");
            String time = df.format(date);//把当前时间转换为字符串
            Info info = new Info();
            info.setName(name);info.setMail(Email);info.setPhone(phone);info.setMessage(message);info.setCreateTime(time);
            infoService.addInfo(info);
        }catch (Exception e){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        return "redirect:/index";
    }

    @GetMapping("/toAdd")
    public String toAdd(){return "front/add";}
    @ApiOperation("新增文章功能")
    @PostMapping("/add")
    public String add(String title, String txt, String sort,String tag){
        try{
            Date date = new Date();
            DateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH小时mm分钟ss秒");
            String time = df.format(date);//把当前时间转换为字符串
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();//根据session得到当前用户的用户名和id
            Blog blog = new Blog();
            blog.setTitle(title);blog.setTxt(txt);blog.setAuthor((String)session.getAttribute("username"));blog.setAuthorid((int)session.getAttribute("id"));blog.setCreateTime(time);blog.setUpdateTime(time);blog.setTag(tag);blog.setFree("yes");blog.setSort(sort);
            System.out.println(blog.toString());
            //blogService.addBlog(blog);
        }catch(Exception e){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }

        return "redirect:/index";
    }

    @ApiOperation("权限不足提示")
    @GetMapping("/noauth")
    @ResponseBody
    public String unauthorized() {
        return "未经授权无法访问此页面";
    }


    @ApiOperation("分类页面")
    @GetMapping("/sort")
    public String sort(){

        return "front/blog/sort";
    }

    @ApiOperation("标签页面")
    @GetMapping("/tag")
    public String tag(){

        return "";
    }

    @ApiOperation("根据类别查询")
    @GetMapping("/queryBySort")
    public String readNote(Model model,String sort){
        List<Blog> blogs = blogService.queryBlogBySort(sort);
        model.addAttribute("blogs",blogs);
        return "front/index";
    }

    @ApiOperation("根据标签查询")
    @GetMapping("/queryByTag")
    public String tag(Model model,String tag){
        System.out.println("当前标签是："+tag);
        List<Blog> blogs = blogService.queryBlogByTag(tag);
        model.addAttribute("blogs",blogs);
        return "front/index";
    }

}
