package com.roy7wt.repository;

import com.roy7wt.model.AdminEntity;
import com.roy7wt.model.ReaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by apple on 16/5/17.
 */

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {

    @Transactional
    @Query("SELECT a " +
            "FROM AdminEntity a " +
            "WHERE a.adminNo=:adminNoPara")
    List<AdminEntity> findAdminByAdminNo(@Param("adminNoPara") String adminNo);


    // 验证账户和密码
    @Transactional
    @Query("SELECT COUNT(*) " +
            "FROM AdminEntity a " +
            "WHERE a.adminNo=:adminNoPara " +
            "AND a.adminPassword=:adminPasswordPara")
    Long findAdminByAdminNoAndAdminPassword(@Param("adminNoPara") String adminNo,
                                            @Param("adminPasswordPara") String adminPassword);

    // 查找账号
    @Transactional
    @Query("SELECT COUNT(*) " +
            "FROM AdminEntity a " +
            "WHERE a.adminNo=:adminNoPara")
    Long findAdminNumberByAdminNo(@Param("adminNoPara") String adminNo);
}
