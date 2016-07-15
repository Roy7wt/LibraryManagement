package com.roy7wt.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by apple on 16/5/28.
 */
@Entity
@Component
@Table(name = "BookClass", schema = "libDB", catalog = "")
public class BookClassEntity {
    private String classNo;
    private String className;

    @Id
    @Column(name = "classNo", nullable = false, length = 3)
    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    @Basic
    @Column(name = "className", nullable = true, length = 20)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookClassEntity that = (BookClassEntity) o;

        if (classNo != null ? !classNo.equals(that.classNo) : that.classNo != null) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classNo != null ? classNo.hashCode() : 0;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        return result;
    }
}
