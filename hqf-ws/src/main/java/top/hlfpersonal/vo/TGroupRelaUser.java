package top.hlfpersonal.vo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: hqf-parent
 * @description:
 * @author: feng
 * @create: 2019-04-09 19:02
 **/
@Entity
@Table(name = "t_group_rela_user", schema = "hqf-ws", catalog = "")
public class TGroupRelaUser {
    private int id;
    private String gid;
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
    @Column(name = "gid")
    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
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
        TGroupRelaUser that = (TGroupRelaUser) o;
        return id == that.id &&
                type == that.type &&
                Objects.equals(gid, that.gid) &&
                Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gid, type, uid);
    }
}
