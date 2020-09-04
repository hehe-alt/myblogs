package blog.myblog.controller.front;

import blog.myblog.exception.BusinessErrorException;
import blog.myblog.exception.BusinessMsgEnum;
import blog.myblog.entity.Author;
import blog.myblog.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Api(tags = "登录注册控制器")
@Controller
@RequestMapping
public class LoginRegisterController {
    @Autowired
    AuthorService authorService;

    @ApiOperation("登录页面")
    @GetMapping("/toLogin")
    public String toLogin(){
        return "front/login";
    }
    @ApiOperation("登录功能")
    @PostMapping("/login")
    public String login(String account, String pwd, Model model){
        Subject subject = SecurityUtils.getSubject();//获取当前用户
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(account,pwd);
        try {
            subject.login(token);//执行登录方法，没有异常就说明ok了
            Session session = subject.getSession();//从当前对象获取session
            session.setAttribute("loginuser",account);//把登录成功的用户放入session中
            session.setAttribute("username",subject.getPrincipal());
            session.setAttribute("id",authorService.queryAuthorByName((String)subject.getPrincipal()).getId());
            return "redirect:/index";
        } catch (UnknownAccountException e) {//用户名不存在
            model.addAttribute("msg","用户名或密码错误");
            return "front/login";
        } catch (IncorrectCredentialsException e){//密码错误
            model.addAttribute("msg","用户名或密码错误");
            return "front/login";
        }catch (Exception e){
            e.printStackTrace();
            return "front/login";
        }
    }
    @ApiOperation("注册页面")
    @GetMapping("/toRegister")
    public String toRegister(Model model){
        model.addAttribute("author",new Author());
        return "front/register";
    }
    @ApiOperation("注册功能")
    @PostMapping("/register")
    public String Register(@Valid Author author,Errors errors){
        if (errors.hasErrors()) {
            System.out.println(errors.toString());
            return "front/register";
        }
        author.setRoleid(3);//普通用户角色统一默认为3
        try {
            Integer hashIterations = 2;//散列次数
            //第一个参数是算法名称，这里指定md5，第二个是要加密的密码，第三个参数是加盐，第四个是散列次数
            SimpleHash hash = new SimpleHash("md5", author.getPwd(), author.getName(),hashIterations);
            author.setPwd(hash.toString());
            authorService.addAuthor(author);
        }catch (Exception e){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        return "redirect:/index";
    }
    @ApiOperation("退出功能")
    @GetMapping("/logout")
    public String logout(){
        //登出清除缓存
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        System.out.println("退出成功！");
        return "redirect:/index";
    }


}
