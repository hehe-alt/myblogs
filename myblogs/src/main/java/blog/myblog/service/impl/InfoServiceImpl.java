package blog.myblog.service.impl;

import blog.myblog.mapper.InfoMapper;
import blog.myblog.entity.Info;
import blog.myblog.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    InfoMapper infoMapper;

    @Override
    public int queryCounts() {
        return infoMapper.queryCounts();
    }

    @Override
    public List<Info> queryInfos() {
        return infoMapper.queryInfos();
    }

    @Override
    public List<Info> queryInfoByPage(int page, int size) {
        if(page<1) return null;
        int startIndex=(page-1)*size,pageSize=size;
        return infoMapper.queryInfoByPage(startIndex,pageSize);
    }

    @Override
    public List<Info> queryInfoByNames(String name) {
        return infoMapper.queryInfoByNames(name);
    }

    @Override
    public int addInfo(Info info) {
        return infoMapper.addInfo(info);
    }

    @Override
    public int deleteInfoById(int id) {
        return infoMapper.deleteInfoById(id);
    }

    @Override
    public int updateInfoById(String name, String mail, String phone, String message, String createTime, int id) {
        return infoMapper.updateInfoById(name,mail,phone,message,createTime,id);
    }
}
