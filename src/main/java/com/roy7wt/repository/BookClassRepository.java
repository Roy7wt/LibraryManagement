package com.roy7wt.repository;

import com.roy7wt.model.BookClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by apple on 16/5/28.
 */
@Repository
public interface BookClassRepository extends JpaRepository<BookClassEntity, Integer>{

    @Transactional
    @Query("SELECT b " +
            "FROM BookClassEntity b " +
            "WHERE b.classNo=:classNoPara")
    List<BookClassEntity> findBookClassByClassNo(@Param("classNoPara") String classNo);

    // 删除某个entity
    @Modifying
    @Transactional
    @Query("DELETE " +
            "FROM BookClassEntity b " +
            "WHERE b.classNo=:classNoPara")
    void deleteClassByClassNo(@Param("classNoPara") String classNo);

    // 更新
    @Modifying
    @Transactional
    @Query("UPDATE BookClassEntity b " +
            "SET b.classNo=:classNoPara, " +
            "b.className=:classNamePara " +
            "WHERE b.classNo=:classNoFirstPara")
    void resetClassByClassNo(@Param("classNoPara") String classNo,
                             @Param("classNamePara") String className,
                             @Param("classNoFirstPara") String classNoFirst);

    /*
    test
     */
//    @Modifying
//    @Transactional
//    @Query("UPDATE BookClassEntity b " +
//            "SET b.classNo=:classNoPara " +
//            "WHERE b.classNo=:classNoFirstPara")
//    void resetClassByClassNoTest(@Param("classNoPara") String classNo,
//                             @Param("classNoFirstPara") String classNoFirst);

    // 更新名字
    @Modifying
    @Transactional
    @Query("UPDATE BookClassEntity b " +
            "SET b.className=:classNamePara " +
            "WHERE b.classNo=:classNoPara")
    void resetClassNameByClassNo(@Param("classNamePara") String className,
                                 @Param("classNoPara") String classNo);

}
