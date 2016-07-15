package com.roy7wt.repository;

import com.roy7wt.model.ReaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by apple on 16/5/16.
 */
@Repository
public interface ReaderRepository extends JpaRepository<ReaderEntity, Integer>{

    @Transactional
    @Query("SELECT r " +
            "FROM ReaderEntity r " +
            "WHERE r.readerNo=:readerNoPara")
    List<ReaderEntity> findReaderByReaderNo(@Param("readerNoPara") String readerNo);

    // 验证账户和密码
    @Transactional
    @Query("SELECT COUNT(*) " +
            "FROM ReaderEntity r " +
            "WHERE r.readerNo=:readerNoPara " +
            "AND r.readerPassword=:readerPasswordPara")
    Long findReaderByReaderNoAndReaderPassword(@Param("readerNoPara") String readerNo,
                                               @Param("readerPasswordPara") String readerPassword);
    // 查找
    @Transactional
    @Query("SELECT COUNT(*) " +
            "FROM ReaderEntity r " +
            "WHERE r.readerNo=:readerNoPara")
    Long findReaderNumberByReaderNo(@Param("readerNoPara") String readerNo);


    // 恢复用户权限
    @Modifying
    @Transactional
    @Query("UPDATE ReaderEntity r " +
            "SET r.readerStatus=:status " +
            "WHERE r.readerNo=:readerNoPara")
    void setReaderStatusByReaderNo(@Param("status") String setStatus,
                                   @Param("readerNoPara") String readerNo);


    // 修改密码
    @Modifying
    @Transactional
    @Query("UPDATE ReaderEntity r " +
            "SET r.readerPassword=:readerPasswordPara " +
            "WHERE r.readerNo=:readerNoPara")
    void resetReaderPasswordByReaderNo(@Param("readerPasswordPara") String readerPassword,
                                       @Param("readerNoPara") String readerNo);

    // 借书-> 节约次数
    @Modifying
    @Transactional
    @Query("UPDATE ReaderEntity r " +
            "SET r.borrowCount=r.borrowCount+:intPara " +
            "WHERE r.readerNo=:readerNoPara")
    void setReaderBorrowCountByReaderNo(@Param("intPara") int integer,
                                        @Param("readerNoPara") String readerNo);

    // 查询剩余借阅次数
    @Transactional()
    @Query("SELECT SUM(r.borrowCount) " +
            "FROM ReaderEntity r " +
            "WHERE r.readerNo=:readerNoPara")
    Long findBorrowCountByReaderNo(@Param("readerNoPara") String readerNo);

    // 重置违规次数
    @Modifying
    @Transactional
    @Query("UPDATE ReaderEntity r " +
            "SET r.breakRules=0 " +
            "WHERE r.readerNo=:readerNoPara")
    void resetReaderTimesByReaderNo(@Param("readerNoPara") String readerNo);

    // 重置用户信息
    @Modifying
    @Transactional
    @Query("UPDATE ReaderEntity r " +
            "SET r.readerName=:readerNamePara, " +
            "r.readerSex=:readerSexPara, " +
            "r.readerPhoneNumber=:readerPhoneNumberPara," +
            "r.institude=:institudePara " +
            "WHERE r.readerNo=:readerNoPara")
    void resetReaderInfoByReaderNo(@Param("readerNamePara") String readerName,
                                   @Param("readerSexPara") String readerSex,
                                   @Param("readerPhoneNumberPara") String readerPhoneNumber,
                                   @Param("institudePara") String institude,
                                   @Param("readerNoPara") String readerNo);



    @Transactional
    @Query("SELECT COUNT(*) " +
            "FROM ReaderEntity r " +
            "WHERE r.breakRules=:breakRulesPara")
    Long findRulesBreaker(@Param("breakRulesPara") Integer breakRules);

}
