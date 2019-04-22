package top.hlfpersonal.vo;

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
@Table(name = "t_wait", schema = "hqf-ws", catalog = "")
public class TWait {
    private int id;
    private Timestamp createtime;
    private String info;
    private int type;
    private String uid;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "type")
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TWait tWait = (TWait) o;
        return id == tWait.id &&
                type == tWait.type &&
                Objects.equals(createtime, tWait.createtime) &&
                Objects.equals(info, tWait.info) &&
                Objects.equals(uid, tWait.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createtime, info, type, uid);
    }
}
