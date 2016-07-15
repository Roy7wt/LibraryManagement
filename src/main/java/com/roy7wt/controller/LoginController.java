package com.roy7wt.controller;

import com.roy7wt.model.AdminEntity;
import com.roy7wt.model.ReaderEntity;
import com.roy7wt.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by apple on 16/5/16.
 */

@Controller
public class LoginController {

    @Autowired
    private LibraryService libraryService;

    public LibraryService getLibraryService() {
        return libraryService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "loginPage";
    }


    @RequestMapping(value = "/login/{loginType}", method = RequestMethod.GET)
    public String loginPage2(@PathVariable("loginType") String loginType,
                             Model model) {
        model.addAttribute("loginType", loginType);
        return "loginPage";
    }

    @RequestMapping(value = "/login/post", method = RequestMethod.POST)
    public String loginRedirect(@RequestParam("readerNo") String readerNo,
                                @RequestParam("readerPassword") String readerPassword,
                                @RequestParam("adminNo") String adminNo,
                                @RequestParam("adminPassword") String adminPassword,
                                @RequestParam("type") String type,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if ("reader".equals(type)) { // 读者类型登陆
            if (getLibraryService().getReaderRepository().
                    findReaderByReaderNoAndReaderPassword(readerNo, readerPassword) == 0) {
                if (getLibraryService().getReaderRepository().
                        findReaderNumberByReaderNo(readerNo) == 0) {

                    // 无读者学号
                    redirectAttributes.addFlashAttribute("login_error", readerNo);
                } else {

                    // 读者密码错误
                    redirectAttributes.addFlashAttribute("password_error", readerNo);
                }
                return "redirect:/login/reader";
            } else {

                ReaderEntity readerEntity = getLibraryService().getReaderRepository().findReaderByReaderNo(readerNo).get(0);

                if ("y".equals(readerEntity.getReaderStatus())) { // 用户状态可用
                    getLibraryService().setReaderEntity(readerEntity);
                    getLibraryService().setTypeOnlien("reader");
                    return  "redirect:/";
                } else if ("n".equals(readerEntity.getReaderStatus())) { // 用户状态不可以用

                    redirectAttributes.addFlashAttribute("status_error", readerNo);
                    return "redirect:/login/reader";
                }
            }
        } else if ("admin".equals(type)) { // 管理员类型登陆
            if (getLibraryService().getAdminRepository().
                    findAdminByAdminNoAndAdminPassword(adminNo, adminPassword) == 0) {
                if (getLibraryService().getAdminRepository().
                        findAdminNumberByAdminNo(adminNo) == 0) {

                    // 无管理员账号
                    redirectAttributes.addFlashAttribute("login_error", adminNo);
                } else {

                    // 管理员密码错误
                    redirectAttributes.addFlashAttribute("password_error", adminPassword);
                }
                return "redirect:/login/admin";
            } else {
                AdminEntity adminEntity = getLibraryService().getAdminRepository().findAdminByAdminNo(adminNo).get(0);
                getLibraryService().setAdminEntity(adminEntity);
                getLibraryService().setTypeOnlien("admin");
                return  "redirect:/";
            }
        }
        return "error";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(){
        if ("reader".equals(getLibraryService().getTypeOnlien())) {
            getLibraryService().setReaderEntity(null);
            getLibraryService().setTypeOnlien("");
        } else if ("admin".equals(getLibraryService().getTypeOnlien())){
            getLibraryService().setAdminEntity(null);
            getLibraryService().setTypeOnlien("");
        }
        return "redirect:/";
    }
}
