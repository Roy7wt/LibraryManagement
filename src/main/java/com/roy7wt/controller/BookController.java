package com.roy7wt.controller;

import com.roy7wt.model.*;
import com.roy7wt.service.LibraryService;
import com.roy7wt.service.StringService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by apple on 16/5/16.
 */

@Controller
public class BookController {

    @Autowired
    private LibraryService libraryService;

    public LibraryService getLibraryService() {
        return libraryService;
    }

    //
    @RequestMapping(value = "/book/info", method = RequestMethod.GET)
    public String pageAllBook(Model model){

        String type = getLibraryService().getTypeOnlien();
        if ("".equals(type)) {
            model.addAttribute("reader", null);
            model.addAttribute("admin", null);
        } else if ("reader".equals(type)) {
            model.addAttribute("reader", getLibraryService().getReaderEntity());
            model.addAttribute("admin", null);
            getLibraryService().updateBorrowedBookList();
            model.addAttribute("borrowedList", getLibraryService().getBorrowedBookList());

        } else if ("admin".equals(type)) {
            model.addAttribute("reader", null);
            model.addAttribute("admin", getLibraryService().getAdminEntity());
        }

        model.addAttribute("bookList", libraryService.getBookRepository().findAll());
        model.addAttribute("borrowRequest", libraryService.getBorrowRequest());
        return "allBooksInfo";
    }


    // 借书
    @RequestMapping(value = "/book/borrow/{bookNo}", method = RequestMethod.GET)
    public String borrowBook(@PathVariable("bookNo") String bookNo) {

        // 当前没有读者
        if (!"reader".equals(getLibraryService().getTypeOnlien())) {
            return "error";
        }

        // 获取当前用户学号
        String readerNo = libraryService.getReaderEntity().getReaderNo();

        BorrowEntity borrowEntity = new BorrowEntity();
        borrowEntity.setBookNo(bookNo);
        borrowEntity.setReaderNo(readerNo);

        // 设置借阅和归还时间
        borrowEntity.setBorrowDate(new Date(Calendar.getInstance().getTimeInMillis()));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 30);
        borrowEntity.setReturnDate(new Date(calendar.getTimeInMillis()));
        borrowEntity.setStatus("W");

        // 读者可借阅数量-1
        libraryService.getReaderRepository().setReaderBorrowCountByReaderNo(-1, readerNo);
        libraryService.getReaderRepository().flush();

        // 更新书籍可外接剩余量: 当前剩余量-1
        libraryService.getBookRepository().updateAfterBorrowByBookNo(bookNo);
        libraryService.getBookRepository().flush();


        // 保存借书记录
        libraryService.getBorrowRepository().saveAndFlush(borrowEntity);
        return "redirect:/reader/info/" + readerNo;
    }

    // 还书
    @RequestMapping(value ="/book/return/{bookNo}", method = RequestMethod.GET)
    public String returnBook(@PathVariable("bookNo") String bookNo) {
        String readerNo = getLibraryService().getReaderEntity().getReaderNo();

        // 删除借阅表里面的数据
        getLibraryService().getBorrowRepository().returnBookFromReader(readerNo, bookNo);

        // 读者可借阅数量+1
        getLibraryService().getReaderRepository().setReaderBorrowCountByReaderNo(1, readerNo);
        getLibraryService().getReaderRepository().flush();

        //  更新书籍可外接剩余量: 当前剩余量+1
        getLibraryService().getBookRepository().updateAfterReturnByBookNo(bookNo);
        getLibraryService().getBookRepository().flush();


        return "redirect:/reader/info/" + readerNo;
    }

    // 找书
    @RequestMapping(value ="/book/search",method = RequestMethod.GET)
    public String searchBookPage(Model model) {
        model.addAttribute("borrowRequest", getLibraryService().getBorrowRequest());


        return "searchBook";
    }


    // 书名作为关键词查找
    @RequestMapping(value = "/book/search", method = RequestMethod.POST)
    public String searchBook(@RequestParam("bookName") String bookName,
                             RedirectAttributes redirectAttributes) {

        List<BookEntity> bookList = getLibraryService()
                .getBookRepository()
                .findBookByBookNameLikeSQL(StringService.Concat(bookName, "%"));

        System.out.println(bookList.size() + "test ");
        redirectAttributes.addFlashAttribute("bookList", bookList);

        if ("".equals(getLibraryService().getTypeOnlien())) { //当前没有读者或者管理员
            redirectAttributes.addFlashAttribute("reader", null);
            redirectAttributes.addFlashAttribute("admin", null);

        } else if ("reader".equals(getLibraryService().getTypeOnlien())) {
            ReaderEntity readerEntity = getLibraryService().getReaderEntity();
            redirectAttributes.addFlashAttribute("reader", readerEntity);
            redirectAttributes.addFlashAttribute("admin", null);

            getLibraryService().updateBorrowedBookList();
            redirectAttributes.addFlashAttribute("borrowedList", getLibraryService().getBorrowedBookList());

        } else if ("admin".equals(getLibraryService().getTypeOnlien())) {
            AdminEntity adminEntity = getLibraryService().getAdminEntity();
            redirectAttributes.addFlashAttribute("reader", null);
            redirectAttributes.addFlashAttribute("admin", adminEntity);
        }
        return "redirect:/book/search";
    }

    // 书籍详细查询页面
    @RequestMapping(value = "/book/search/detail", method = RequestMethod.GET)
    public String searchBookDetailPage(Model model) {

        if ("".equals(getLibraryService().getTypeOnlien())) { //当前没有读者或者管理员
            model.addAttribute("reader", null);
            model.addAttribute("admin", null);

        } else if ("reader".equals(getLibraryService().getTypeOnlien())) {
            ReaderEntity readerEntity = getLibraryService().getReaderEntity();
            model.addAttribute("reader", readerEntity);
            model.addAttribute("admin", null);

            getLibraryService().updateBorrowedBookList();
            model.addAttribute("borrowedList", getLibraryService().getBorrowedBookList());

        } else if ("admin".equals(getLibraryService().getTypeOnlien())) {
            AdminEntity adminEntity = getLibraryService().getAdminEntity();
            model.addAttribute("reader", null);
            model.addAttribute("admin", adminEntity);
        }
        return "searchBookDetail";
    }


    // 详细查询实现 TODO
    @RequestMapping(value = "/book/search/detail", method = RequestMethod.POST)
    public String searchBookDetail(@RequestParam("bookNo") String bookNo,
                                   @RequestParam("classNo") String classNo,
                                   @RequestParam("bookName") String bookName,
                                   @RequestParam("author") String author,
                                   @RequestParam("publishName") String publishName,
                                   RedirectAttributes redirectAttributes) {


        List<BookEntity> bookEntityList = new ArrayList<BookEntity>();

        if (!"".equals(bookNo)) { //可以精确查询的话
            List<BookEntity> tempList = getLibraryService().
                    getBookRepository().
                    findBookByExactSearchBookNo(bookNo);

            if (tempList.size() == 1) { //精确查询有结果
                redirectAttributes.addFlashAttribute("get", "true");
                bookEntityList.add(tempList.get(0));
            } else {
                redirectAttributes.addFlashAttribute("get", "false");
            }
        }

        List<BookEntity> temp2List = getLibraryService().
                getBookRepository().
                findBookByExactSearchClassNo(classNo);
        for (BookEntity bookEntity : temp2List) {
            if (bookEntityList.contains(bookEntity)) continue;
            else bookEntityList.add(bookEntity);
        }


        List<BookEntity> temp3List = getLibraryService().
                getBookRepository().
                findBookByExactSearchDetails(
                        StringService.Concat(bookName, "%"),
                        StringService.Concat(author, "%"),
                        StringService.Concat(publishName, "%"));
        System.out.println(bookName + author + publishName + temp3List.size() + "ROY7WT!!!!!!!!!!!");
        for (BookEntity bookEntity : temp3List) {
            if (bookEntityList.contains(bookEntity)) continue;
            else bookEntityList.add(bookEntity);
        }


        System.out.println(bookEntityList.size() + "roy7wt");
        redirectAttributes.addFlashAttribute("bookList", bookEntityList);

        if ("".equals(getLibraryService().getTypeOnlien())) { //当前没有读者或者管理员
            redirectAttributes.addFlashAttribute("reader", null);
            redirectAttributes.addFlashAttribute("admin", null);

        } else if ("reader".equals(getLibraryService().getTypeOnlien())) {
            ReaderEntity readerEntity = getLibraryService().getReaderEntity();
            redirectAttributes.addFlashAttribute("reader", readerEntity);
            redirectAttributes.addFlashAttribute("admin", null);

            getLibraryService().updateBorrowedBookList();
            redirectAttributes.addFlashAttribute("borrowedList", getLibraryService().getBorrowedBookList());

        } else if ("admin".equals(getLibraryService().getTypeOnlien())) {
            AdminEntity adminEntity = getLibraryService().getAdminEntity();
            redirectAttributes.addFlashAttribute("reader", null);
            redirectAttributes.addFlashAttribute("admin", adminEntity);
        }
        return "redirect:/book/search";
    }

    // 添加新的图书 & 修改图书类别
    @RequestMapping(value = "/book/insert", method = RequestMethod.GET)
    public String insertBookPage(Model model) {

        // 预处理项目个数
        model.addAttribute("borrowRequest", getLibraryService().getBorrowRequest());

        if ("admin".equals(getLibraryService().getTypeOnlien())) {
            model.addAttribute("admin", getLibraryService().getAdminEntity());
            model.addAttribute("classList", getLibraryService().getBookClassRepository().findAll());

            return "insertBook";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/book/insert", method = RequestMethod.POST)
    public String insertBook(@ModelAttribute("book") BookEntity bookEntity) {

        getLibraryService().getBookRepository().saveAndFlush(bookEntity);
        return "redirect:/";
    }

    // 预约图书取消
    @RequestMapping(value = "/book/borrow-cancle", method = RequestMethod.POST)
    public String borrowCancle(@RequestParam("bookNo") String bookNo,
                               @RequestParam("readerNo") String readerNo){

        getLibraryService().getBorrowRepository().returnBookFromReader(readerNo, bookNo);

        // 可预约数量+1
        getLibraryService().getReaderRepository().setReaderBorrowCountByReaderNo(1, readerNo);

        return "redirect:/reader/info";
    }


    @RequestMapping(value = "/book/classChange", method = RequestMethod.POST)
    public String bookClassChangePage(@RequestParam("classNo") String classNo,
                                  Model model){

        List<BookClassEntity> bookClassEntities = getLibraryService().getBookClassRepository().findBookClassByClassNo(classNo);
        if (bookClassEntities.size() >= 1) {
            model.addAttribute("bookClass", bookClassEntities.get(0));
        }

        return "bookClassChange";
    }

    @RequestMapping(value = "/book/classChangeSubmit", method = RequestMethod.POST)
    public String bookClassChange(@RequestParam("classNo") String classNo,
                                  @RequestParam("classNoFirst") String classNoFirst,
                                  @RequestParam("className") String className) {

        if (!classNoFirst.equals(classNo)) { // classNo 修改过了
            getLibraryService().getBookClassRepository().resetClassByClassNo(classNo, className, classNoFirst);

        } else { // className 修改了
            // 更新一下名字即可
            getLibraryService().getBookClassRepository().resetClassNameByClassNo(className, classNo);
        }

        return "redirect:/book/insert";
    }

}
