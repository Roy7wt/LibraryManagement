package com.roy7wt.service;

import com.roy7wt.model.*;
import com.roy7wt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by apple on 16/5/16.
 */

@Component()
@Scope(
        value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS
)

public class LibraryService implements MyService{

    private List<BookEntity> borrowedBookList;
    private HashMap<BookEntity, String> borrowedBookMap;
    private List<BorrowEntity> borrowEntityList;
    private List<String> overdueBookList;

    /**
     * Repositories
     */
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private BookClassRepository bookClassRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BorrowRoomRepository borrowRoomRepository;


    public ReaderRepository getReaderRepository() {
        return readerRepository;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public BorrowRepository getBorrowRepository() {
        return borrowRepository;
    }

    public AdminRepository getAdminRepository() {
        return adminRepository;
    }

    public BookClassRepository getBookClassRepository() {
        return bookClassRepository;
    }

    public RoomRepository getRoomRepository() {
        return roomRepository;
    }

    public BorrowRoomRepository getBorrowRoomRepository() {
        return borrowRoomRepository;
    }

    /**
     * Entities
     */

    // Flag来判断
    private String typeOnlien = "";

    public String getTypeOnlien() {
        return typeOnlien;
    }

    public void setTypeOnlien(String typeOnlien) {
        this.typeOnlien = typeOnlien;
    }

    // 当前在图书馆系统的读者
    @Autowired
    private ReaderEntity readerEntity = null;

    public ReaderEntity getReaderEntity() {
        return readerEntity;
    }

    public void setReaderEntity(ReaderEntity readerEntity) {
        this.readerEntity = readerEntity;
    }

    // 当前在图书馆系统的管理员
    @Autowired
    private AdminEntity adminEntity = null;

    public AdminEntity getAdminEntity() {
        return adminEntity;
    }

    public void setAdminEntity(AdminEntity adminEntity) {
        this.adminEntity = adminEntity;
    }

    /**
     * Method For Use
     */

    public List<BookEntity> getBorrowedBookList() {
        return borrowedBookList;
    }

    public void updateBorrowedBookList() {
        borrowedBookList = null;
        borrowedBookList = new ArrayList<BookEntity>();
        List<String> borrowedBookNoList = getBorrowRepository().findBookNoListByReaderNo(getReaderEntity().getReaderNo());

        for (String bookNo : borrowedBookNoList) {
            for (BookEntity bookEntity : getBookRepository().findByBookNo(bookNo)) {
                getBorrowedBookList().add(bookEntity);
            }
        }
    }

    public HashMap<BookEntity, String> getBorrowedBookMap() {
        return borrowedBookMap;
    }

    public List<BorrowEntity> getBorrowEntityList() {
        return borrowEntityList;
    }

    public List<String> getOverdueBookList() {
        return overdueBookList;
    }

    // 获取借阅图书entity以及对应的借阅时间
    public void updateBorrowedBookMap() {
        borrowedBookMap = null;
        borrowedBookMap = new HashMap<BookEntity, String>();

        overdueBookList = null;
        overdueBookList = new ArrayList<String>();

        borrowedBookList = null;
        borrowEntityList = getBorrowRepository().findBorrowListByReaderNo(getReaderEntity().getReaderNo());

        for (BorrowEntity borrowEntity : borrowEntityList) {
            for (BookEntity bookEntity: getBookRepository().findByBookNo(borrowEntity.getBookNo())) {
                getBorrowedBookMap().put(bookEntity, StringService.dateToString(borrowEntity.getReturnDate()));

                // 如果过期了 就加入这个列表
                if(StringService.isOverdue(borrowEntity.getReturnDate())) {
                    overdueBookList.add(borrowEntity.getBookNo());
                }
            }
        }
    }


    /**
     * 管理待办事情数字
     */

    public Long getRuleBreaker() {
        return getReaderRepository().findRulesBreaker(3);
    }

    public Long getBorrowRequest() {
        return getBorrowRepository().findTotalBorrowToBeDoneByStatus("W");
    }

    public Long getBorrowRoomRequest() {
        return getBorrowRoomRepository().findTotalBorrowRoomToBeDoneByStatus("w");
    }





}
