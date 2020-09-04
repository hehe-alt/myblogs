package blog.myblog.service.impl;

import blog.myblog.mapper.RoleMapper;
import blog.myblog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public String queryRole(int id) {
        return roleMapper.queryRole(id);
    }
}
