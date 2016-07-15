package com.roy7wt.controller;

import com.roy7wt.model.AdminEntity;
import com.roy7wt.model.BorrowRoomEntity;
import com.roy7wt.model.ReaderEntity;
import com.roy7wt.model.RoomEntity;
import com.roy7wt.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by apple on 16/6/18.
 */

@Controller
public class RoomController {

    @Autowired
    private LibraryService libraryService;

    public LibraryService getLibraryService() {
        return libraryService;
    }

    @RequestMapping(value = "/room/info", method = RequestMethod.GET)
    public String pageAllRoom(Model model) {

        // 首先设置当前在线管理员或读者
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

        // 查询小组自习室的使用情况
        List<RoomEntity> roomEntityList = getLibraryService().getRoomRepository().findAllByLocationAsc();
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        HashMap<String, List<BorrowRoomEntity>> map2 = new HashMap<String, List<BorrowRoomEntity>>();
        for (RoomEntity roomEntity : roomEntityList) {
//            List<Integer> borrowRoomEntityList = getLibraryService()
//                    .getBorrowRoomRepository()
//                    .findBorrowRoomEntityByLocation(roomEntity.getRoomLocation());
            List<BorrowRoomEntity> borrowRoomEntityList = getLibraryService()
                    .getBorrowRoomRepository()
                    .findBorrowRoomEntityByLocation2(roomEntity.getRoomLocation());

            if (borrowRoomEntityList.size() != 0) {

                List<String> periodAndStatus = new ArrayList<String>();
                for (int i = 0; i < borrowRoomEntityList.size(); i++) {
                    BorrowRoomEntity borrowRoomEntity = borrowRoomEntityList.get(i);
                    String temp = borrowRoomEntity.getBorrowStatus() + borrowRoomEntity.getBorrowTimePeriod();
                    periodAndStatus.add(i, temp);
                }

                for (String str : periodAndStatus) {
                    System.out.println(str);
                }

                map.put(roomEntity.getRoomLocation(), periodAndStatus);
            } else map.put(roomEntity.getRoomLocation(), null);
        }

        model.addAttribute("roomEntityList", roomEntityList);
        model.addAttribute("map", map);

        return "allRoomsInfo";
    }

    @RequestMapping(value = "/room/borrow", method = RequestMethod.POST)
    public String borrowRoom(@RequestParam("roomLocation") String roomLocation,
                             @RequestParam("timePeriod") String timePeriod,
                             RedirectAttributes redirectAttributes) {

        Long res = getLibraryService()
                .getBorrowRoomRepository()
                .findBorrowEntityItem(roomLocation, Integer.valueOf(timePeriod));
        if (res != 0) {

            //已经被借阅了
            redirectAttributes.addFlashAttribute("result", "occupied");
            return "redirect:/room/info";

        } else {

            //可以借阅
            BorrowRoomEntity borrowRoomEntity = new BorrowRoomEntity();
            borrowRoomEntity.setBorrowRoomLocation(roomLocation);
            borrowRoomEntity.setBorrowRoomReaderNo(getLibraryService().getReaderEntity().getReaderNo());
            borrowRoomEntity.setBorrowTimePeriod(Integer.valueOf(timePeriod));
            borrowRoomEntity.setBorrowRoomAdminNo(null);
            borrowRoomEntity.setBorrowStatus("w");
            getLibraryService().getBorrowRoomRepository().saveAndFlush(borrowRoomEntity);

            redirectAttributes.addFlashAttribute("result", "booked");
            return "redirect:/room/info";

        }
    }
}
