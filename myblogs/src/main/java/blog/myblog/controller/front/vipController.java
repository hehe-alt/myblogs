package blog.myblog.controller.front;

import blog.myblog.exception.BusinessErrorException;
import blog.myblog.exception.BusinessMsgEnum;
import blog.myblog.entity.Blog;
import blog.myblog.service.impl.BlogServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "vip视图管理")
@Controller
@RequestMapping("/vip")
public class vipController {
    @Autowired
    BlogServiceImpl blogService;

    @ApiOperation("付费文章首页")
    @GetMapping({"/toVip"})
    public String toVip(Model model){
        try{
            List<Blog> blogs =new ArrayList<>();
            blogService.queryBlogsVip().forEach(i->blogs.add(i));
            model.addAttribute("blogs",blogs);
        }catch(Exception e){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        return "front/blog/vipPage";
    }

    @ApiOperation("付费文章详情页面")
    @GetMapping("/vipPost")
    public String vipPage(Model model,int id){
        try {
            Blog blog = blogService.queryBlogById(id);
            model.addAttribute("theBlog",blog);
        }catch (Exception e){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        return "front/blog/vipPost";
    }


    @ApiOperation("付费文章查询页面")
    @GetMapping("/toSelectVip")
    public String toSelectVip(){
        return "front/blog/vipSelect";
    }
    @ApiOperation("查询付费文章")
    @GetMapping("/vipSelect")
    public String vipSelect(Model model, String title){
        try{
            List<Blog> blogs =new ArrayList<>();
            blogService.queryBlogByTitleVip(title).forEach(i->blogs.add(i));
            model.addAttribute("blogs",blogs);
        }catch (Exception e){
            throw new BusinessErrorException(BusinessMsgEnum.UNEXPECTED_EXCEPTION);
        }
        return "front/blog/vipSelect";
    }

}
