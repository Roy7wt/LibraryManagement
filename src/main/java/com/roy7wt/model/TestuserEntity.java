package com.roy7wt.model;

import javax.persistence.*;

/**
 * Created by apple on 16/5/15.
 */
@Entity
@Table(name = "testuser", schema = "libDB", catalog = "")

public class TestuserEntity {
    private int userId;
    private String email;
    private String password;

    @Id
    @Column(name = "userID", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestuserEntity entity = (TestuserEntity) o;

        if (userId != entity.userId) return false;
        if (email != null ? !email.equals(entity.email) : entity.email != null) return false;
        if (password != null ? !password.equals(entity.password) : entity.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
