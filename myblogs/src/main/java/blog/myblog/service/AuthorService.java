package blog.myblog.service;

import blog.myblog.entity.Author;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface AuthorService {
    int queryCounts();
    List<Author> queryAuthors();
    int addAuthor(Author author);
    Author queryAuthorByAccount(String account);
    Author queryAuthorByName(String name);
    List<Author> queryAuthorByNames(String name);
    List<Author> queryAuthorByPage(int page, int size);
    int updateAuthorById(String name,String sex,String num,String mail,int roleid,int id);
    int deleteAuthorById(int id);
}
