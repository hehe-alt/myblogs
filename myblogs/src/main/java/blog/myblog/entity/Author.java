package blog.myblog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ApiModel("用户实体类")
public class Author {
    private int id;
    @ApiModelProperty("用户名")
    @Size(max = 20,message="用户名最多20个字符")
    private String name;
    @NotBlank(message = "不能为空")
    private String sex;
    @NotBlank(message = "不能为空")
    private String num;
    @NotBlank(message = "不能为空")
    private String mail;
    @Size(min=6,max=10,message = "账号不能少于6位数,不多于10位数")
    private String account;
    @Size(min=6,max=10,message = "密码不能少于6位数,不多于10位数")
    private String pwd;
    private int roleid;

    public Author(){}

    public Author(int id,String name, String sex, String num,String mail,String account, String pwd,int roleid) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.num = num;
        this.mail = mail;
        this.account = account;
        this.pwd = pwd;
        this.roleid = roleid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", num='" + num + '\'' +
                ", mail='" + mail + '\'' +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", roldeid='" + roleid + '\'' +
                '}';
    }
}
