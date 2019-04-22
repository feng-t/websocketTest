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
@Table(name = "t_group", schema = "hqf-ws", catalog = "")
public class TGroup {
    private String id;
    private Timestamp createtime;
    private String gname;
    private String introduce;
    private String uid;

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
    @Column(name = "gname")
    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    @Basic
    @Column(name = "introduce")
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
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
        TGroup tGroup = (TGroup) o;
        return Objects.equals(id, tGroup.id) &&
                Objects.equals(createtime, tGroup.createtime) &&
                Objects.equals(gname, tGroup.gname) &&
                Objects.equals(introduce, tGroup.introduce) &&
                Objects.equals(uid, tGroup.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createtime, gname, introduce, uid);
    }
}
