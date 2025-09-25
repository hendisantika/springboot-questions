package com.hendisantika.springbootquestions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-questions
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/11/20
 * Time: 08.24
 */
@Entity
@Table(name = "user_info")
@Data
@NoArgsConstructor
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "pwd", nullable = false)
    private String pwd;

    @Column(name = "roles")
    private String roles;

    public UserInfo(String username, String pwd, String roles) {
        this.username = username;
        this.pwd = pwd;
        this.roles = roles;
    }

    public String getRoles() {
        return roles;
    }

    public String getPwd() {
        return pwd;
    }

    public String getUsername() {
        return username;
    }
}
