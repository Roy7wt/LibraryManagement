package com.roy7wt.repository;

import com.roy7wt.model.BookEntity;
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
public interface BookRepository extends JpaRepository<BookEntity, Integer>{


    @Transactional
    @Query("SELECT b " +
            "FROM BookEntity b " +
            "WHERE b.bookNo=:bookNoPara")
    List<BookEntity> findByBookNo(@Param("bookNoPara") String bookNo);

    // 通过名字广泛查找
    @Transactional
    @Query(value ="SELECT b " +
            "FROM BookEntity b " +
            "WHERE b.bookName " +
            "LIKE :bookNamePara " +
            "ORDER BY b.publishName asc , b.publishDate desc")
    List<BookEntity> findBookByBookNameLikeSQL(@Param("bookNamePara") String bookName);


    // 详细查询 三个组成

    // 编号
    @Transactional
    @Query("SELECT b " +
            "FROM BookEntity b " +
            "WHERE b.bookNo=:bookNoPara")
    List<BookEntity> findBookByExactSearchBookNo(@Param("bookNoPara") String bookNo);


    // 书类别
    @Transactional
    @Query("SELECT b " +
            "FROM BookEntity b " +
            "WHERE b.classNo=:classNoPara " +
            "ORDER BY b.bookNo asc , b.bookName asc")
    List<BookEntity> findBookByExactSearchClassNo(@Param("classNoPara") String ClassNo);

    // 模糊查询
    @Transactional
    @Query("SELECT b " +
            "FROM BookEntity b " +
            "WHERE b.bookName like :bookNamePara or " +
            "b.author like :authorPara or " +
            "b.publishName like :publishNamePara " +
            "ORDER BY b.bookNo asc , b.bookName asc")
    List<BookEntity> findBookByExactSearchDetails(@Param("bookNamePara") String bookName,
                                                @Param("authorPara") String author,
                                                @Param("publishNamePara") String publishName);


    // 查询书籍剩余数量
    @Transactional
    @Query("SELECT SUM(b.bookResidue) " +
            "FROM BookEntity b " +
            "WHERE b.bookNo=:bookNoPara")
    Long findResidueByBookNo(@Param("bookNoPara") String bookNo);


    // 外借书籍 -> 更新书籍的"可外借"数量
    @Modifying
    @Transactional
    @Query("UPDATE BookEntity b " +
            "SET b.bookResidue=b.bookResidue-1 " +
            "WHERE b.bookNo=:bookNoPara")
    void updateAfterBorrowByBookNo(@Param("bookNoPara") String bookNoPara);

    // 归还图书 -> 更新书籍的"可外借"数量
    @Modifying
    @Transactional
    @Query("UPDATE BookEntity b " +
            "SET b.bookResidue=b.bookResidue+1 " +
            "WHERE b.bookNo=:bookNoPara")
    void updateAfterReturnByBookNo(@Param("bookNoPara") String bookNoPara);

}
