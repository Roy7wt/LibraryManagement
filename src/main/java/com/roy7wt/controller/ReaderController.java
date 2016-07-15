package com.roy7wt.controller;

import com.roy7wt.model.BorrowEntity;
import com.roy7wt.model.ReaderEntity;
import com.roy7wt.service.LibraryService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by apple on 16/5/16.
 */
@Controller
public class ReaderController {

    @Autowired
    private LibraryService libraryService;

    public LibraryService getLibraryService() {
        return libraryService;
    }

    //
    @RequestMapping(value = "/reader/info/{readerNo}", method = RequestMethod.GET)
    public String readerInfoCheck(@PathVariable("readerNo") String readerNo){

        if (readerNo.equals(getLibraryService().getReaderEntity().getReaderNo())) {
            return "redirect:/reader/info";
        } else {
            return "error";
        }
    }

    // 显示读者信息和借阅信息
    @RequestMapping(value = "/reader/info", method = RequestMethod.GET)
    public String readerInfo(Model model) {

        // Question : 如何做到同步
        // 目前是很基本的通过查询数据库来找到borrowCount的值

        getLibraryService().getReaderEntity().setBorrowCount(
                getLibraryService().getReaderRepository().findBorrowCountByReaderNo(
                        getLibraryService().getReaderEntity().getReaderNo()).intValue());

        model.addAttribute("reader", getLibraryService().getReaderEntity());
//        System.out.println("Roy7wt" + getLibraryService().getReaderEntity().getBorrowCount());

        // 更新借阅数理
        getLibraryService().updateBorrowedBookMap();
        model.addAttribute("bookMap", getLibraryService().getBorrowedBookMap());
        model.addAttribute("overdueBookList", getLibraryService().getOverdueBookList());

        HashMap<String, String> borrowEntityStatusMap = new HashMap<String, String>();
        for(BorrowEntity borrowEntity : getLibraryService().getBorrowEntityList()) {
            borrowEntityStatusMap.put(borrowEntity.getBookNo(), borrowEntity.getStatus());
            System.out.println("r:" + borrowEntity.getBookNo() + " " + borrowEntity.getStatus());
        }
        model.addAttribute("borrowedBookStatusMap", borrowEntityStatusMap);
        return "readerInfo";
    }

    // 读者自行修改读者信息
    @RequestMapping(value = "/reader/infoChange", method = RequestMethod.GET)
    public String readerInfoChangePage(Model model) {
        model.addAttribute("reader", getLibraryService().getReaderEntity());
        return "readerInfoChange";
    }

    @RequestMapping(value = "/reader/infoChange", method = RequestMethod.POST)
    public String readerInfoChange(@RequestParam("readerNo") String readerNo,
                                   @RequestParam("readerName") String readerName,
                                   @RequestParam("readerSex") String readerSex,
                                   @RequestParam("readerPhoneNumber") String readerPhoneNumber,
                                   @RequestParam("institude") String institude) {

        getLibraryService().getReaderRepository().resetReaderInfoByReaderNo(
                readerName,
                readerSex,
                readerPhoneNumber,
                institude,
                readerNo
        );

        getLibraryService().getReaderEntity().setReaderName(readerName);
        getLibraryService().getReaderEntity().setReaderSex(readerSex);
        getLibraryService().getReaderEntity().setReaderPhoneNumber(readerPhoneNumber);
        getLibraryService().getReaderEntity().setInstitude(institude);

        return "redirect:/reader/info/" + readerNo;
    }

    @RequestMapping(value = "/reader/changePassword", method = RequestMethod.POST)
    public String readerChangePassword(@RequestParam("readerNo") String readerNo,
                                       @RequestParam("newpwd") String newpwd,
                                       @RequestParam("confirmpwd") String confirmpwd,
                                       RedirectAttributes redirectAttributes) {

        if (!readerNo.equals(getLibraryService().getReaderEntity().getReaderNo())) { //不是同一个人
            return "error";
        }

        if (!confirmpwd.equals(newpwd)) { // 两次输入的密码不一样
            redirectAttributes.addFlashAttribute("check_type", "error");
            return "redirect:/reader/infoChange";
        } else {
            redirectAttributes.addFlashAttribute("check_type", "right");
            getLibraryService().getReaderRepository().resetReaderPasswordByReaderNo(newpwd, readerNo);
            return "redirect:/reader/infoChange";
        }
    }


}
