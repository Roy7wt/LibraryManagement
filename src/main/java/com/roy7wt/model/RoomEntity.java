package com.roy7wt.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by apple on 16/6/18.
 */
@Entity
@Component
@Table(name = "Room", schema = "libDB", catalog = "")
public class RoomEntity {
    private String roomLocation;
    private int roomMaximum;
    private String roomStatus;
    private Timestamp roomStartDate;
    private Timestamp roomeEndDate;

    @Id
    @Column(name = "roomLocation", nullable = false, length = 12)
    public String getRoomLocation() {
        return roomLocation;
    }

    public void setRoomLocation(String roomLocation) {
        this.roomLocation = roomLocation;
    }

    @Basic
    @Column(name = "roomMaximum", nullable = false)
    public int getRoomMaximum() {
        return roomMaximum;
    }

    public void setRoomMaximum(int roomMaximum) {
        this.roomMaximum = roomMaximum;
    }

    @Basic
    @Column(name = "roomStatus", nullable = false, length = 3)
    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    @Basic
    @Column(name = "roomStartDate", nullable = true)
    public Timestamp getRoomStartDate() {
        return roomStartDate;
    }

    public void setRoomStartDate(Timestamp roomStartDate) {
        this.roomStartDate = roomStartDate;
    }

    @Basic
    @Column(name = "roomeEndDate", nullable = true)
    public Timestamp getRoomeEndDate() {
        return roomeEndDate;
    }

    public void setRoomeEndDate(Timestamp roomeEndDate) {
        this.roomeEndDate = roomeEndDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomEntity that = (RoomEntity) o;

        if (roomMaximum != that.roomMaximum) return false;
        if (roomLocation != null ? !roomLocation.equals(that.roomLocation) : that.roomLocation != null) return false;
        if (roomStatus != null ? !roomStatus.equals(that.roomStatus) : that.roomStatus != null) return false;
        if (roomStartDate != null ? !roomStartDate.equals(that.roomStartDate) : that.roomStartDate != null)
            return false;
        if (roomeEndDate != null ? !roomeEndDate.equals(that.roomeEndDate) : that.roomeEndDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomLocation != null ? roomLocation.hashCode() : 0;
        result = 31 * result + roomMaximum;
        result = 31 * result + (roomStatus != null ? roomStatus.hashCode() : 0);
        result = 31 * result + (roomStartDate != null ? roomStartDate.hashCode() : 0);
        result = 31 * result + (roomeEndDate != null ? roomeEndDate.hashCode() : 0);
        return result;
    }
}
