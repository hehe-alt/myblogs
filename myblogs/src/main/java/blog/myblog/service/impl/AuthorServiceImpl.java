package blog.myblog.service.impl;

import blog.myblog.mapper.AuthorMapper;
import blog.myblog.entity.Author;
import blog.myblog.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorMapper authorMapper;

    @Override
    public int queryCounts() {
        return authorMapper.queryCounts();
    }

    @Override
    public List<Author> queryAuthors() {
        return authorMapper.queryAuthors();
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addAuthor(Author author) {
        return authorMapper.addAuthor(author);
    }
    @Override
    public Author queryAuthorByAccount(String account){
        return authorMapper.queryAuthorByAccount(account);
    }
    @Override
    public Author queryAuthorByName(String name){
        return authorMapper.queryAuthorByName(name);
    }

    @Override
    public List<Author> queryAuthorByNames(String name) {
        return authorMapper.queryAuthorByNames(name);
    }

    @Override
    public List<Author> queryAuthorByPage(int page, int size) {
        if(page<1) return null;
        int startIndex=(page-1)*size,pageSize=size;
        return authorMapper.queryAuthorByPage(startIndex, pageSize);
    }

    @Override
    public int updateAuthorById(String name, String sex, String num, String mail, int roleid, int id) {
        return authorMapper.updateAuthorById(name,sex,num, mail, roleid, id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAuthorById(int id){return  authorMapper.deleteAuthorById(id);}
}
