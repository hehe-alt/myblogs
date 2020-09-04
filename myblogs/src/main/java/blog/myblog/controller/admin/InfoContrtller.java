package blog.myblog.controller.admin;

import blog.myblog.entity.Author;
import blog.myblog.entity.Info;
import blog.myblog.service.AuthorService;
import blog.myblog.service.InfoService;
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
public class InfoContrtller {
    @Autowired
    InfoService infoService;

    static int pageSize = 8;

    @GetMapping("/infoNextPage")
    public String authorNextPage(Model model,int page){
        System.out.println("当前页数:" +page);
        List<Info> infos = infoService.queryInfoByPage(page+1,pageSize);
        model.addAttribute("infos",infos);
        model.addAttribute("page",page+1);

        int count = infoService.queryCounts();
        model.addAttribute("count",count);
        return "admin/info/info";
    }
    @GetMapping("/infoPrePage")
    public String authorPrePage(Model model,int page){
        System.out.println("当前页数:" +page);
        List<Info> infos = infoService.queryInfoByPage(page-1,pageSize);
        model.addAttribute("infos",infos);
        model.addAttribute("page",page-1);

        int count = infoService.queryCounts();
        model.addAttribute("count",count);
        return "admin/info/info";
    }


    @GetMapping("/infoToAdd")
    public String infoToAdd(Model model){
        Info info = new Info();
        model.addAttribute("ninfo",info);
        return "admin/info/add";
    }

    @ResponseBody
    @PostMapping("/infoAdd")
    public String infoAdd(Info info){
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH小时mm分钟ss秒");
        String time = df.format(date);//把当前时间转换为字符串
        info.setCreateTime(time);
        System.out.println(info.toString());
        int r = infoService.addInfo(info);
        if(r!=1) return "新增留言失败";
        return "新增留言成功";
    }

    @GetMapping("/infoSearch")
    public String infoSearch(String name,Model model){
        System.out.println(name);
        List<Info> infos = infoService.queryInfoByNames(name);
        model.addAttribute("infos",infos);
        model.addAttribute("page",1);
        model.addAttribute("count",infos.size());
        return "admin/info/info";
    }
    @PostMapping("/infoOption")
    public String infoOption(int id, String opt, RedirectAttributes redirectAttributes){
        if(opt.equals("删除")) {//使用RedirectAttributes传递重定向过程中的参数
            redirectAttributes.addAttribute("id",id);
            return "redirect:/admin/infoDelete";
        }
        else{
            redirectAttributes.addAttribute("id",id);
            return "redirect:/admin/infoToEdit";
        }
    }

    @ResponseBody
    @GetMapping("/infoDelete")
    public String infoDelete(@RequestParam(value = "id")int id){
        int r = infoService.deleteInfoById(id);
        if(r!=1) return "删除失败";
        System.out.println("删除成功" + id);
        return "删除成功";
    }
    @GetMapping("/infoToEdit")
    public String infoToEdit(@RequestParam(value = "id")int id, Model model){
        model.addAttribute("id",id);
        return "/admin/info/edit";
    }
    @ResponseBody
    @PostMapping("/infoEdit")
    public String infoEdit(String name, String mail, String phone, String message, String createTime, int id){
        int r = infoService.updateInfoById(name, mail, phone, message,createTime, id);
        if(r!=1) return "编辑失败";
        System.out.println("编辑成功");
        return "编辑成功";
    }
}
