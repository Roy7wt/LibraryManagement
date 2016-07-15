package com.roy7wt.controller;

import com.roy7wt.model.AdminEntity;
import com.roy7wt.model.ReaderEntity;
import com.roy7wt.model.RoomEntity;
import com.roy7wt.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by apple on 16/5/15.
 */
@Controller
public class HomeController {

    @Autowired
    private LibraryService libraryService;

    public LibraryService getLibraryService() {
        return libraryService;
    }


    // 首页显示 -> 通过LibraryService来获取当前图书馆管理系统的在线者 -> 一台电脑只能有一个读者或管理员
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage(Model model) throws ParseException {

//        //TODO
//        /**test**/
//        List<RoomEntity> list = getLibraryService().getRoomRepository().findAll();
//        for (RoomEntity roomEntity : list) {
////            Timestamp timestamp = roomEntity.getRoomStartDate();
////            timestamp.
//
//
//            System.out.println("Time:" + roomEntity.getRoomStartDate());
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = simpleDateFormat.parse("2016-06-16 09:00:00");
//            roomEntity.setRoomeEndDate(new Timestamp(date.getTime()));
//            getLibraryService().getRoomRepository().saveAndFlush(roomEntity);
//        }
//
//        /**test**/

        if ("".equals(getLibraryService().getTypeOnlien())) { //当前没有读者或者管理员
            model.addAttribute("reader", null);
            model.addAttribute("admin", null);
        } else if ("reader".equals(getLibraryService().getTypeOnlien())) {
            ReaderEntity readerEntity = getLibraryService().getReaderEntity();
            model.addAttribute("reader", readerEntity);
            model.addAttribute("admin", null);
        } else if ("admin".equals(getLibraryService().getTypeOnlien())) {
            AdminEntity adminEntity = getLibraryService().getAdminEntity();
            model.addAttribute("reader", null);
            model.addAttribute("admin", adminEntity);
        }

//        Calendar calendar = Calendar.getInstance();
//        Date dateSmall = new Date(calendar.getTimeInMillis());
//
//        calendar.add(Calendar.DAY_OF_YEAR, 30);
//        Date dateLarge = new Date(calendar.getTimeInMillis());
//
//        if (dateSmall.before(dateLarge)) {
//            System.out.println("small");
//        } else {
//            System.out.println("later");
//        }

//        ReaderEntity readerEntity = getLibraryService().getReaderEntity();
//
//        // 用户已注销
//        if (readerEntity == null) {
//            System.out.println("已注销");
//            model.addAttribute("reader", null);
//        } else {
//
//            if (readerEntity.getReaderNo() == null) {
//                model.addAttribute("reader" ,null);
//            } else {
//                model.addAttribute("reader", readerEntity);
//            }
//        }

//        // 还没有用户
//        if (readerEntity.getReaderNo() == null) {
//            model.addAttribute("reader" ,null);
//        } else {
//            model.addAttribute("reader", readerEntity);
//        }
        model.addAttribute("breakerNumber", getLibraryService().getRuleBreaker());
        model.addAttribute("borrowRequest", getLibraryService().getBorrowRequest());
        model.addAttribute("borrowRoomRequest", getLibraryService().getBorrowRoomRequest());
        return "libraryHomePage";
    }

}
