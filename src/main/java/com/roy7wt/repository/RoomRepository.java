package com.roy7wt.repository;

import com.roy7wt.model.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by apple on 16/6/18.
 */
@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {

    @Transactional
    @Query("SELECT r " +
            "FROM RoomEntity r " +
            "ORDER BY r.roomLocation asc ")
    List<RoomEntity> findAllByLocationAsc();


}
