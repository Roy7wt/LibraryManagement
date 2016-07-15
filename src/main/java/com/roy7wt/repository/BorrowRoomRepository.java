package com.roy7wt.repository;

import com.roy7wt.model.BorrowRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by apple on 16/6/19.
 */
@Repository
public interface BorrowRoomRepository extends JpaRepository<BorrowRoomEntity, Integer> {

    @Transactional
    @Query("SELECT bre.borrowTimePeriod " +
            "FROM BorrowRoomEntity bre " +
            "WHERE bre.borrowRoomLocation=:borrowRoomLocationPara")
    List<Integer> findBorrowRoomEntityByLocation(@Param("borrowRoomLocationPara") String borrowRoomLocation);

    @Transactional
    @Query("SELECT bre " +
            "FROM BorrowRoomEntity bre " +
            "WHERE bre.borrowRoomLocation=:borrowRoomLocationPara")
    List<BorrowRoomEntity> findBorrowRoomEntityByLocation2(@Param("borrowRoomLocationPara") String borrowRoomLocation);

    @Transactional
    @Query("SELECT COUNT(*) " +
            "FROM BorrowRoomEntity bre " +
            "WHERE bre.borrowRoomLocation=:borrowRoomLocationPara " +
            "and bre.borrowTimePeriod=:borrowTimePeriodPara")
    Long findBorrowEntityItem(@Param("borrowRoomLocationPara") String borrowRoomLocation,
                              @Param("borrowTimePeriodPara") Integer borrowTimePeriod);

    @Transactional
    @Query("SELECT COUNT(*) " +
            "FROM BorrowRoomEntity bre " +
            "WHERE bre.borrowStatus=:borrowStatusPara")
    Long findTotalBorrowRoomToBeDoneByStatus(@Param("borrowStatusPara") String borrowStatus);

    @Transactional
    @Query("SELECT bre " +
            "FROM BorrowRoomEntity bre " +
            "WHERE bre.borrowStatus=:borrowStatusPara")
    List<BorrowRoomEntity> findBorrowRoomToBeDoneByStatus(@Param("borrowStatusPara") String borrowStatus);

    // 批准预约图书请求
    @Modifying
    @Transactional
    @Query("UPDATE BorrowRoomEntity bre " +
            "SET bre.borrowStatus=:borrowStatusPara, " +
            "bre.borrowRoomAdminNo=:borrowRoomAdminNoPara " +
            "WHERE bre.borrowRoomLocation=:borrowRoomLocationPara " +
            "and bre.borrowRoomReaderNo=:borrowRoomReaderNoPara " +
            "and bre.borrowTimePeriod=:borrowTimePeriodPara")
    void agreeBorrowRoomeRequest(@Param("borrowStatusPara") String borrowStatus,
                                 @Param("borrowRoomAdminNoPara") String borrowRoomAdminNo,
                                 @Param("borrowRoomLocationPara") String borrowRoomLocation,
                                 @Param("borrowRoomReaderNoPara") String borrowRoomReader,
                                 @Param("borrowTimePeriodPara") Integer borrowTimePeriod);
}
