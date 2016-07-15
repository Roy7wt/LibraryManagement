package com.roy7wt.controller;

import com.roy7wt.model.ReaderEntity;
import com.roy7wt.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by apple on 16/5/16.
 */
@Controller
public class RegisterController {

    @Autowired
    private LibraryService libraryService;

    public LibraryService getLibraryService() {
        return libraryService;
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "registerPage";
    }

    // 表单提交用户对象 —> 检测学号是否存在 -> 向数据库中注册用户
    @RequestMapping(value = "/register/reader", method = RequestMethod.POST)
    public String registerReader(@ModelAttribute("reader")ReaderEntity readerEntity,
                                 RedirectAttributes redirectAttributes) {
        if (getLibraryService().getReaderRepository().findReaderByReaderNo(readerEntity.getReaderNo()).size() != 0) {

            redirectAttributes.addFlashAttribute("readerNo", readerEntity.getReaderNo());
            return "redirect:/register";
        } else {

            readerEntity.setBreakRules(0);
            readerEntity.setBorrowCount(10);

            Calendar calendar = Calendar.getInstance();
            readerEntity.setEffectDate(new Date(calendar.getTimeInMillis()));
            calendar.add(Calendar.YEAR, 4);
            readerEntity.setLostEffectDate(new Date(calendar.getTimeInMillis()));

            getLibraryService().getReaderRepository().saveAndFlush(readerEntity);
            getLibraryService().setReaderEntity(readerEntity);
            getLibraryService().setTypeOnlien("reader");
            return "redirect:/";
        }
    }
}
