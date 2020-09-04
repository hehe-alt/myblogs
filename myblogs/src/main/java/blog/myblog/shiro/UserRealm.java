package blog.myblog.shiro;

import blog.myblog.entity.Author;
import blog.myblog.service.AuthorService;
import blog.myblog.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


//自定义的UserRealm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    AuthorService authorService;
    @Autowired
    RoleService roleService;
    public UserRealm(CredentialsMatcher matcher) {
        super(matcher);
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权"+new Date().toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();//拿到当前登录的对象
        String name = (String) subject.getPrincipal();//拿到用户名
        //根据用户名找到用户
        Author currentUser = authorService.queryAuthorByName(name);
        //得到角色
        String rolename = roleService.queryRole(currentUser.getRoleid());
        System.out.println("当前用户的角色是："+rolename);
        //info.addStringPermission(rolename);
        //设置当前用户的角色
        info.addRole(rolename);
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("开始认证 "+new Date().toString());
        SimpleAuthenticationInfo info = null;
        String account = (String) token.getPrincipal();//得到账号
        Author user = authorService.queryAuthorByAccount(account);//根据账号查找对应用户
        if(user==null) throw new UnknownAccountException();
        info = new SimpleAuthenticationInfo(user.getName(), user.getPwd(), ByteSource.Util.bytes(user.getName()), getName());
        return info;
    }
    /*//用户名，密码  数据库中取
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        User user = userRepository.findUserByName(userToken.getUsername());
        if(user==null){
            return null;//抛出异常 UnknownAccountException
        }
        Subject currentSbject = SecurityUtils.getSubject();
        Session session = currentSbject.getSession();//从当前对象获取session
        session.setAttribute("loginuser",user);//把登录成功的用户放入session中
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");//传入user对象
*/
}
