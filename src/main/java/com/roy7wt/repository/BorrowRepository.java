package com.roy7wt.repository;

import com.roy7wt.model.BorrowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * Created by apple on 16/5/16.
 */

@Repository
public interface BorrowRepository extends JpaRepository<BorrowEntity, Integer> {


    // 通过读者号找书号
    @Transactional
    @Query("SELECT br.bookNo " +
            "FROM BorrowEntity br " +
            "WHERE br.readerNo =:readerNoPara")
    List<String> findBookNoListByReaderNo(@Param("readerNoPara") String readerNo);

    // 通过读者号找到借阅关系
    @Transactional
    @Query("SELECT br " +
            "FROM BorrowEntity br " +
            "WHERE br.readerNo =:readerNoPara")
    List<BorrowEntity> findBorrowListByReaderNo(@Param("readerNoPara") String readerNo);

    // 还书操作
    @Modifying
    @Transactional
    @Query("DELETE " +
            "FROM BorrowEntity br " +
            "WHERE br.readerNo=:readerNoPara AND" +
            "      br.bookNo=:bookNoPara")
    void returnBookFromReader(@Param("readerNoPara") String readerNo,
                              @Param("bookNoPara") String bookNo);


    // 待批准的预约图书请求
    @Transactional
    @Query("SELECT br " +
            "FROM BorrowEntity br " +
            "WHERE br.status=:statusPara")
    List<BorrowEntity> findBorrowToBeDoneByStatus(@Param("statusPara") String status);


    @Transactional
    @Query("SELECT COUNT(*) " +
            "FROM BorrowEntity br " +
            "WHERE br.status=:statusPara")
    Long findTotalBorrowToBeDoneByStatus(@Param("statusPara") String status);

    // 批准预约图书请求
    @Modifying
    @Transactional
    @Query("UPDATE BorrowEntity br " +
            "SET br.approvalTime=:apT," +
            "br.status=:s," +
            "br.adminNo=:an " +
            "WHERE br.bookNo=:bn and " +
            "br.readerNo=:rn")
    void agreeBorrowRequestByBookNoAndReaderNo(@Param("apT") Date approvalTime,
                                               @Param("s") String status,
                                               @Param("an") String adminNo,
                                               @Param("bn") String bookNo,
                                               @Param("rn") String readerNo);


}
