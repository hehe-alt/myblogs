package blog.myblog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
@ApiModel("留言信息类")
public class Info {
    private int id;
    @ApiModelProperty("留言者姓名")
    private String name;
    private String mail;
    private String phone;
    private String message;
    private String createTime;

    public Info(){}
    public Info(int id, String name, String mail, String phone, String message, String createTime) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.message = message;
        this.createTime = createTime;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", message='" + message + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
