package com.roy7wt.controller;

import com.roy7wt.model.BorrowEntity;
import com.roy7wt.model.BorrowRoomEntity;
import com.roy7wt.model.ReaderEntity;
import com.roy7wt.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 * Created by apple on 16/5/17.
 */
@Controller
public class AdminController {

    @Autowired
    private LibraryService libraryService;

    public LibraryService getLibraryService() {
        return libraryService;
    }

    @RequestMapping(value = "/admin/info", method = RequestMethod.GET)
    public String adminInfoCheck(Model model) {

        if ("admin".equals(getLibraryService().getTypeOnlien())){ //确保是管理员登陆
            model.addAttribute("admin", getLibraryService().getAdminEntity());
            model.addAttribute("breakerNumber", getLibraryService().getRuleBreaker());
            model.addAttribute("borrowRequest", getLibraryService().getBorrowRequest());
            model.addAttribute("borrowRoomRequest", getLibraryService().getBorrowRoomRequest());
            return "adminInfo";
        } else {
            return "error";
        }
    }

    /**
     * 管理员查看用户部分
     */
    // 管理员admin -> 查看用户信息
    @RequestMapping(value = "/readers/info", method = RequestMethod.GET)
    public String readersInfo(Model model) {

        model.addAttribute("breakerNumber", getLibraryService().getRuleBreaker());
        model.addAttribute("borrowRequest", getLibraryService().getBorrowRequest());
        model.addAttribute("borrowRoomRequest", getLibraryService().getBorrowRoomRequest());

        if ("admin".equals(getLibraryService().getTypeOnlien())) {
            List<ReaderEntity> readerList = getLibraryService().getReaderRepository().findAll();
            model.addAttribute("readerList", readerList);
            model.addAttribute("admin", getLibraryService().getAdminEntity());
            return "allReadersInfo";
        } else {
            return "error";
        }
    }

    @RequestMapping(value = "/admin/status", method = RequestMethod.POST)
    public String readerRecover(@RequestParam("readerNo") String readerNo,
                                @RequestParam("setStatus") String setStatus) {

        getLibraryService().getReaderRepository().setReaderStatusByReaderNo(setStatus, readerNo);

        // 重置违规次数
        if ("y".equals(setStatus)) {
            getLibraryService().getReaderRepository().resetReaderTimesByReaderNo(readerNo);
        }
        return "redirect:/readers/info";
    }

    @RequestMapping(value = "/admin/borrow-transaction", method = RequestMethod.GET)
    public String borrowTransactionPage(Model model) {

        model.addAttribute("admin", getLibraryService().getAdminEntity());
        model.addAttribute("breakerNumber", getLibraryService().getRuleBreaker());
        model.addAttribute("borrowRequest", getLibraryService().getBorrowRequest());
        model.addAttribute("borrowRoomRequest", getLibraryService().getBorrowRoomRequest());

        List<BorrowEntity> borrowEntitiesToBeDone = getLibraryService()
                .getBorrowRepository()
                .findBorrowToBeDoneByStatus("W");
        model.addAttribute("borrowEntitiesToBeDone", borrowEntitiesToBeDone);

        return "adminBorrowTransaction";
    }

    @RequestMapping(value = "/admin/borrow-agree", method = RequestMethod.POST)
    public String borrowTranscation(@RequestParam("bookNo") String bookNo,
                                    @RequestParam("readerNo") String readerNo) {

        //
        getLibraryService().getBorrowRepository().agreeBorrowRequestByBookNoAndReaderNo(
                new Date(Calendar.getInstance().getTimeInMillis()),
                "A", // Approve
                getLibraryService().getAdminEntity().getAdminNo(),
                bookNo,
                readerNo
        );
        return "redirect:/admin/borrow-transaction";
    }


    @RequestMapping(value = "/admin/borrow-room-transaction", method = RequestMethod.GET)
    public String borrowRoomTransactionPage(Model model) {

        model.addAttribute("admin", getLibraryService().getAdminEntity());
        model.addAttribute("breakerNumber", getLibraryService().getRuleBreaker());
        model.addAttribute("borrowRequest", getLibraryService().getBorrowRequest());
        model.addAttribute("borrowRoomRequest", getLibraryService().getBorrowRoomRequest());

        List<BorrowRoomEntity> borrowRoomEntityList = getLibraryService()
                .getBorrowRoomRepository()
                .findBorrowRoomToBeDoneByStatus("w");

        model.addAttribute("borrowRoomEntityList", borrowRoomEntityList);

        return "adminBorrowRoomTransaction";
    }

    @RequestMapping(value = "/admin/borrow-room-agree", method = RequestMethod.POST)
    public String borrowRoomTranscation(@RequestParam("roomLocation") String roomLocation,
                                        @RequestParam("readerNo") String readerNo,
                                        @RequestParam("timePeriod") Integer timePeriod) {

        getLibraryService().getBorrowRoomRepository().agreeBorrowRoomeRequest(
                "a",//approve
                getLibraryService().getAdminEntity().getAdminNo(),
                roomLocation,
                readerNo,
                timePeriod
        );

        return "redirect:/admin/borrow-room-transaction";
    }
}
