package com.roy7wt.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by apple on 16/5/17.
 */
@Entity
@Table(name = "Admin", schema = "libDB", catalog = "")
@Component
public class AdminEntity {
    private String adminNo;
    private String adminName;
    private String adminSex;
    private String adminPhoneNumber;
    private String adminAddress;
    private String adminPassword;

    @Id
    @Column(name = "adminNo", nullable = false, length = 12)
    public String getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(String adminNo) {
        this.adminNo = adminNo;
    }

    @Basic
    @Column(name = "adminName", nullable = false, length = 12)
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Basic
    @Column(name = "adminSex", nullable = false, length = 4)
    public String getAdminSex() {
        return adminSex;
    }

    public void setAdminSex(String adminSex) {
        this.adminSex = adminSex;
    }

    @Basic
    @Column(name = "adminPhoneNumber", nullable = true, length = 13)
    public String getAdminPhoneNumber() {
        return adminPhoneNumber;
    }

    public void setAdminPhoneNumber(String adminPhoneNumber) {
        this.adminPhoneNumber = adminPhoneNumber;
    }

    @Basic
    @Column(name = "adminAddress", nullable = true, length = 40)
    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    @Basic
    @Column(name = "adminPassword", nullable = true, length = 15)
    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminEntity that = (AdminEntity) o;

        if (adminNo != null ? !adminNo.equals(that.adminNo) : that.adminNo != null) return false;
        if (adminName != null ? !adminName.equals(that.adminName) : that.adminName != null) return false;
        if (adminSex != null ? !adminSex.equals(that.adminSex) : that.adminSex != null) return false;
        if (adminPhoneNumber != null ? !adminPhoneNumber.equals(that.adminPhoneNumber) : that.adminPhoneNumber != null)
            return false;
        if (adminAddress != null ? !adminAddress.equals(that.adminAddress) : that.adminAddress != null) return false;
        if (adminPassword != null ? !adminPassword.equals(that.adminPassword) : that.adminPassword != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adminNo != null ? adminNo.hashCode() : 0;
        result = 31 * result + (adminName != null ? adminName.hashCode() : 0);
        result = 31 * result + (adminSex != null ? adminSex.hashCode() : 0);
        result = 31 * result + (adminPhoneNumber != null ? adminPhoneNumber.hashCode() : 0);
        result = 31 * result + (adminAddress != null ? adminAddress.hashCode() : 0);
        result = 31 * result + (adminPassword != null ? adminPassword.hashCode() : 0);
        return result;
    }
}
