package com.tutorial.api.mvc.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // Tên bảng có thể tuỳ chỉnh
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String fullName;
    private String password;
    private Integer role = 1; // Đặt mặc định role là 1
    private boolean status = true; // Mặc định là true

    // Constructors
    public User() {
        // Constructor mặc định
    }

    public User(String username, String fullName, String password) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // Sử dụng annotation @PrePersist để đảm bảo giá trị mặc định khi lưu vào DB
    @PrePersist
    protected void onCreate() {
        if (this.role == null) {
            this.role = 1; // Đặt role mặc định là 1 nếu không có giá trị
        }
        if (!this.status) {
            this.status = true; // Đặt status mặc định là true nếu không có giá trị
        }
    }
}
