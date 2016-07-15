package com.roy7wt.model;

import javax.persistence.*;

/**
 * Created by apple on 16/6/19.
 */
@Entity
@Table(name = "BorrowRoom", schema = "libDB", catalog = "")
public class BorrowRoomEntity {
    private int item;
    private Integer borrowTimePeriod;
    private String borrowStatus;
    private String borrowRoomLocation;
    private String borrowRoomReaderNo;
    private String borrowRoomAdminNo;

    @Id
    @Column(name = "item", nullable = false)
    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    @Basic
    @Column(name = "borrowTimePeriod", nullable = true)
    public Integer getBorrowTimePeriod() {
        return borrowTimePeriod;
    }

    public void setBorrowTimePeriod(Integer borrowTimePeriod) {
        this.borrowTimePeriod = borrowTimePeriod;
    }

    @Basic
    @Column(name = "borrowStatus", nullable = true, length = 3)
    public String getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(String borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BorrowRoomEntity that = (BorrowRoomEntity) o;

        if (item != that.item) return false;
        if (borrowTimePeriod != null ? !borrowTimePeriod.equals(that.borrowTimePeriod) : that.borrowTimePeriod != null)
            return false;
        if (borrowStatus != null ? !borrowStatus.equals(that.borrowStatus) : that.borrowStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = item;
        result = 31 * result + (borrowTimePeriod != null ? borrowTimePeriod.hashCode() : 0);
        result = 31 * result + (borrowStatus != null ? borrowStatus.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "borrowRoomLocation", nullable = false, length = 12)
    public String getBorrowRoomLocation() {
        return borrowRoomLocation;
    }

    public void setBorrowRoomLocation(String borrowRoomLocation) {
        this.borrowRoomLocation = borrowRoomLocation;
    }

    @Basic
    @Column(name = "borrowRoomReaderNo", nullable = false, length = 15)
    public String getBorrowRoomReaderNo() {
        return borrowRoomReaderNo;
    }

    public void setBorrowRoomReaderNo(String borrowRoomReaderNo) {
        this.borrowRoomReaderNo = borrowRoomReaderNo;
    }

    @Basic
    @Column(name = "borrowRoomAdminNo", nullable = true, length = 12)
    public String getBorrowRoomAdminNo() {
        return borrowRoomAdminNo;
    }

    public void setBorrowRoomAdminNo(String borrowRoomAdminNo) {
        this.borrowRoomAdminNo = borrowRoomAdminNo;
    }
}
