package com.wayfather.springbootmybatis.service;

import com.wayfather.springbootmybatis.entity.BookDO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BookService
 * @Description TODO
 * @Author IBM
 * @Date 2019/9/30 10:26
 **/

public interface BookService {
    List<BookDO> listBooks();
    BookDO getBook(Long id);
    Boolean insertBook(BookDO bookDO);
    Boolean updateBook(BookDO bookDO);
    Boolean deleteBook(Long id);
}
