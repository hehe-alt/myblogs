package blog.myblog.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import blog.myblog.shiro.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean 第三步
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("WebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        /* anno:无需认证就可以访问
           authc：必须认证才能访问
           user:必须拥有 记住我 功能才能用
           perms：拥有对某个资源的权限才能访问
           role：拥有某个角色权限才能访问*/
        Map<String,String> flterMap = new LinkedHashMap<>();
        //授权,正常情况下，没有授权会跳转到为登录页面
        flterMap.put("/toAdd","authc");
        flterMap.put("/vip/*","roles[vip]");
        bean.setFilterChainDefinitionMap(flterMap);//具有对应权限才能访问资源
        bean.setLoginUrl("/tologin");//设置登录的请求
        bean.setUnauthorizedUrl("/noauth");//设置未授权页面
        return bean;
    }

    //DefaultWebSecurityManager 第二步
    @Bean(name="WebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    //创建reaml对象,需要自定义类 第一步
    @Bean(name="userRealm")
    public UserRealm userRealm(){
        HashedCredentialsMatcher matcher =new HashedCredentialsMatcher();
        matcher.setHashIterations(2);
        matcher.setHashAlgorithmName("md5");
        return new UserRealm(matcher);
    }


    //整合ShiroDialect 用来整合shiro 和thymeleaf
    @Bean
    public ShiroDialect getshiroDialect(){
        return new ShiroDialect();
    }


}