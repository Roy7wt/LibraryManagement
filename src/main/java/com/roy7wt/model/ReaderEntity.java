package com.roy7wt.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by apple on 16/5/16.
 */
@Entity
@Table(name = "Reader", schema = "libDB", catalog = "")
@Component

public class ReaderEntity {
    private String readerNo;
    private String readerName;
    private String readerPassword;
    private String readerSex;
    private String readerPhoneNumber;
    private String institude;
    private Date effectDate;
    private Integer breakRules;
    private Date lostEffectDate;
    private Integer borrowCount;
    private String readerStatus;

    @Id
    @Column(name = "readerNo", nullable = false, length = 15)
    public String getReaderNo() {
        return readerNo;
    }

    public void setReaderNo(String readerNo) {
        this.readerNo = readerNo;
    }

    @Basic
    @Column(name = "readerName", nullable = false, length = 15)
    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    @Basic
    @Column(name = "readerPassword", nullable = false, length = 20)
    public String getReaderPassword() {
        return readerPassword;
    }

    public void setReaderPassword(String readerPassword) {
        this.readerPassword = readerPassword;
    }

    @Basic
    @Column(name = "readerSex", nullable = false, length = 5)
    public String getReaderSex() {
        return readerSex;
    }

    public void setReaderSex(String readerSex) {
        this.readerSex = readerSex;
    }

    @Basic
    @Column(name = "readerPhoneNumber", nullable = true, length = 13)
    public String getReaderPhoneNumber() {
        return readerPhoneNumber;
    }

    public void setReaderPhoneNumber(String readerPhoneNumber) {
        this.readerPhoneNumber = readerPhoneNumber;
    }

    @Basic
    @Column(name = "institude", nullable = false, length = 20)
    public String getInstitude() {
        return institude;
    }

    public void setInstitude(String institude) {
        this.institude = institude;
    }

    @Basic
    @Column(name = "effectDate", nullable = true)
    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    @Basic
    @Column(name = "breakRules", nullable = true)
    public Integer getBreakRules() {
        return breakRules;
    }

    public void setBreakRules(Integer breakRules) {
        this.breakRules = breakRules;
    }

    @Basic
    @Column(name = "lostEffectDate", nullable = true)
    public Date getLostEffectDate() {
        return lostEffectDate;
    }

    public void setLostEffectDate(Date lostEffectDate) {
        this.lostEffectDate = lostEffectDate;
    }

    @Basic
    @Column(name = "borrowCount", nullable = true)
    public Integer getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Integer borrowCount) {
        this.borrowCount = borrowCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReaderEntity that = (ReaderEntity) o;

        if (readerNo != null ? !readerNo.equals(that.readerNo) : that.readerNo != null) return false;
        if (readerName != null ? !readerName.equals(that.readerName) : that.readerName != null) return false;
        if (readerPassword != null ? !readerPassword.equals(that.readerPassword) : that.readerPassword != null)
            return false;
        if (readerSex != null ? !readerSex.equals(that.readerSex) : that.readerSex != null) return false;
        if (readerPhoneNumber != null ? !readerPhoneNumber.equals(that.readerPhoneNumber) : that.readerPhoneNumber != null)
            return false;
        if (institude != null ? !institude.equals(that.institude) : that.institude != null) return false;
        if (effectDate != null ? !effectDate.equals(that.effectDate) : that.effectDate != null) return false;
        if (breakRules != null ? !breakRules.equals(that.breakRules) : that.breakRules != null) return false;
        if (lostEffectDate != null ? !lostEffectDate.equals(that.lostEffectDate) : that.lostEffectDate != null)
            return false;
        if (borrowCount != null ? !borrowCount.equals(that.borrowCount) : that.borrowCount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = readerNo != null ? readerNo.hashCode() : 0;
        result = 31 * result + (readerName != null ? readerName.hashCode() : 0);
        result = 31 * result + (readerPassword != null ? readerPassword.hashCode() : 0);
        result = 31 * result + (readerSex != null ? readerSex.hashCode() : 0);
        result = 31 * result + (readerPhoneNumber != null ? readerPhoneNumber.hashCode() : 0);
        result = 31 * result + (institude != null ? institude.hashCode() : 0);
        result = 31 * result + (effectDate != null ? effectDate.hashCode() : 0);
        result = 31 * result + (breakRules != null ? breakRules.hashCode() : 0);
        result = 31 * result + (lostEffectDate != null ? lostEffectDate.hashCode() : 0);
        result = 31 * result + (borrowCount != null ? borrowCount.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "readerStatus", nullable = true, length = 255)
    public String getReaderStatus() {
        return readerStatus;
    }

    public void setReaderStatus(String readerStatus) {
        this.readerStatus = readerStatus;
    }
}
