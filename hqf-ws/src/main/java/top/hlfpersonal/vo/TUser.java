package top.hlfpersonal.vo;

import com.google.gson.Gson;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @program: hqf-parent
 * @description:
 * @author: feng
 * @create: 2019-04-09 19:02
 **/
@Entity
@Table(name = "t_user", schema = "hqf-ws", catalog = "")
public class TUser {
    private String id;
    private Timestamp createtime;
    private String email;
    private String nickname;
    private String password;
    private int sex;
    private String username;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "createtime")
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "sex")
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TUser tUser = (TUser) o;
        return sex == tUser.sex &&
                Objects.equals(id, tUser.id) &&
                Objects.equals(createtime, tUser.createtime) &&
                Objects.equals(email, tUser.email) &&
                Objects.equals(nickname, tUser.nickname) &&
                Objects.equals(password, tUser.password) &&
                Objects.equals(username, tUser.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createtime, email, nickname, password, sex, username);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
