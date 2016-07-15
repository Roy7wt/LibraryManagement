package com.roy7wt.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by apple on 16/5/17.
 */
@Entity
@Table(name = "Book", schema = "libDB", catalog = "")
public class BookEntity {
    private String bookNo;
    private String bookName;
    private String author;
    private String publishName;
    private String introduction;
    private Date publishDate;
    private Integer bookResidue;
    private String classNo;

    @Id
    @Column(name = "bookNo", nullable = false, length = 20)
    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    @Basic
    @Column(name = "bookName", nullable = false, length = 50)
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "author", nullable = false, length = 12)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "publishName", nullable = true, length = 50)
    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName;
    }

    @Basic
    @Column(name = "introduction", nullable = true, length = 200)
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "publishDate", nullable = true)
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Basic
    @Column(name = "bookResidue", nullable = true)
    public Integer getBookResidue() {
        return bookResidue;
    }

    public void setBookResidue(Integer bookResidue) {
        this.bookResidue = bookResidue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (bookNo != null ? !bookNo.equals(that.bookNo) : that.bookNo != null) return false;
        if (bookName != null ? !bookName.equals(that.bookName) : that.bookName != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (publishName != null ? !publishName.equals(that.publishName) : that.publishName != null) return false;
        if (introduction != null ? !introduction.equals(that.introduction) : that.introduction != null) return false;
        if (publishDate != null ? !publishDate.equals(that.publishDate) : that.publishDate != null) return false;
        if (bookResidue != null ? !bookResidue.equals(that.bookResidue) : that.bookResidue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookNo != null ? bookNo.hashCode() : 0;
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (publishName != null ? publishName.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (bookResidue != null ? bookResidue.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "classNo", nullable = false, length = 3)
    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }
}
