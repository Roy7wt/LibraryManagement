package com.roy7wt.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by apple on 16/5/16.
 */
@Entity
@Table(name = "Borrow", schema = "libDB", catalog = "")
@IdClass(BorrowEntityPK.class)
public class BorrowEntity {
    private String bookNo;
    private String readerNo;
    private Date borrowDate;
    private Date returnDate;
    private String status;
    private String adminNo;
    private Date approvalTime;

    @Id
    @Column(name = "bookNo", nullable = false, length = 20)
    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    @Id
    @Column(name = "readerNo", nullable = false, length = 15)
    public String getReaderNo() {
        return readerNo;
    }

    public void setReaderNo(String readerNo) {
        this.readerNo = readerNo;
    }

    @Basic
    @Column(name = "borrowDate", nullable = true)
    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Basic
    @Column(name = "returnDate", nullable = true)
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BorrowEntity that = (BorrowEntity) o;

        if (bookNo != null ? !bookNo.equals(that.bookNo) : that.bookNo != null) return false;
        if (readerNo != null ? !readerNo.equals(that.readerNo) : that.readerNo != null) return false;
        if (borrowDate != null ? !borrowDate.equals(that.borrowDate) : that.borrowDate != null) return false;
        if (returnDate != null ? !returnDate.equals(that.returnDate) : that.returnDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookNo != null ? bookNo.hashCode() : 0;
        result = 31 * result + (readerNo != null ? readerNo.hashCode() : 0);
        result = 31 * result + (borrowDate != null ? borrowDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 3)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "adminNo", nullable = true, length = 12)
    public String getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(String adminNo) {
        this.adminNo = adminNo;
    }

    @Basic
    @Column(name = "approvalTime", nullable = true)
    public Date getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }
}
