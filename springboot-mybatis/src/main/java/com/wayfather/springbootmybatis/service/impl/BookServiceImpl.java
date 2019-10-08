package com.wayfather.springbootmybatis.service.impl;

import com.wayfather.springbootmybatis.dao.BookDao;
import com.wayfather.springbootmybatis.entity.BookDO;
import com.wayfather.springbootmybatis.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BookServiceImpl
 * @Description TODO
 * @Author IBM
 * @Date 2019/9/30 10:26
 **/
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookDao;

    public List<BookDO> listBooks() {
        return bookDao.listBooks();
    }

    public BookDO getBook(Long id) {
        return bookDao.getBook(id);
    }

    public Boolean insertBook(BookDO bookDO) {
        return bookDao.insertBook(bookDO);
    }

    public Boolean updateBook(BookDO bookDO) {
        return bookDao.updateBook(bookDO);
    }

    public Boolean deleteBook(Long id) {
        return bookDao.deleteBook(id);
    }

}
