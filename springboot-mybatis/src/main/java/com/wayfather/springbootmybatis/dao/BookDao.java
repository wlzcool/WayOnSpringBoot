package com.wayfather.springbootmybatis.dao;

import com.wayfather.springbootmybatis.entity.BookDO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName BookDao
 * @Description TODO
 * @Author IBM
 * @Date 2019/9/30 10:33
 **/
@Component
public interface BookDao {
    List<BookDO> listBooks();
    BookDO getBook(Long id);
    Boolean insertBook(BookDO bookDO);
    Boolean updateBook(BookDO bookDO);
    Boolean deleteBook(Long id);
}
