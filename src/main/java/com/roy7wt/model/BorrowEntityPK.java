package com.roy7wt.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by apple on 16/5/16.
 */
public class BorrowEntityPK implements Serializable {
    private String bookNo;
    private String readerNo;

    @Column(name = "bookNo", nullable = false, length = 20)
    @Id
    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    @Column(name = "readerNo", nullable = false, length = 15)
    @Id
    public String getReaderNo() {
        return readerNo;
    }

    public void setReaderNo(String readerNo) {
        this.readerNo = readerNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BorrowEntityPK that = (BorrowEntityPK) o;

        if (bookNo != null ? !bookNo.equals(that.bookNo) : that.bookNo != null) return false;
        if (readerNo != null ? !readerNo.equals(that.readerNo) : that.readerNo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookNo != null ? bookNo.hashCode() : 0;
        result = 31 * result + (readerNo != null ? readerNo.hashCode() : 0);
        return result;
    }
}
