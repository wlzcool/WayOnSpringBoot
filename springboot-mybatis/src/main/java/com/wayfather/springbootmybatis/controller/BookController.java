package com.wayfather.springbootmybatis.controller;

import com.wayfather.springbootmybatis.entity.BookDO;
import com.wayfather.springbootmybatis.service.BookService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @ClassName BookController
 * @Description TODO
 * @Author IBM
 * @Date 2019/9/30 10:12
 **/
@Slf4j
@RequestMapping("/book")
@RestController
public class BookController {
    @Value("${web.upload-file-path}")
    private String filePathUrl;
    @Autowired
    BookService bookService;

    @GetMapping("/listShow")
    public ModelAndView listBooksPage() {
        ModelAndView mv = new ModelAndView("/book/listBook");
        return mv;
    }

    @RequestMapping("/list")
    public List<BookDO> listBooks() {
        return bookService.listBooks();
    }

    @GetMapping("/insertShow")
    public ModelAndView insertBookPage() {
        ModelAndView mv = new ModelAndView("/book/insertBook");
        return mv;
    }

    @PostMapping("/insert")
    public Boolean insertBook(@RequestBody BookDO bookDO) {
        return bookService.insertBook(bookDO);
    }

    @RequestMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        log.error("/upload/" + file.getOriginalFilename());
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePathUrl + file.getOriginalFilename());

            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getOriginalFilename();
    }
}
