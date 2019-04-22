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
@Table(name = "t_msg", schema = "hqf-ws", catalog = "")
public class TMsg {
    private int id;
    private String content;
    private Timestamp createtime;
    private String form;
    private int mtype;
    private String to;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    @Column(name = "form")
    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Basic
    @Column(name = "mtype")
    public int getMtype() {
        return mtype;
    }

    public void setMtype(int mtype) {
        this.mtype = mtype;
    }

    @Basic
    @Column(name = "to")
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TMsg tMsg = (TMsg) o;
        return id == tMsg.id &&
                mtype == tMsg.mtype &&
                Objects.equals(content, tMsg.content) &&
                Objects.equals(createtime, tMsg.createtime) &&
                Objects.equals(form, tMsg.form) &&
                Objects.equals(to, tMsg.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, createtime, form, mtype, to);
    }

    @Override
    public String toString() {
        return "TMsg{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                ", form='" + form + '\'' +
                ", mtype=" + mtype +
                ", to='" + to + '\'' +
                '}';
    }
}
