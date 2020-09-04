package blog.myblog.service;

import blog.myblog.entity.Info;

import java.util.List;

public interface InfoService {
    int queryCounts();
    List<Info> queryInfos();
    List<Info> queryInfoByPage(int page, int size);
    List<Info> queryInfoByNames(String name);
    int addInfo(Info info);
    int deleteInfoById(int id);
    int updateInfoById(String name,String mail,String phone,String message,String createTime,int id);
}
