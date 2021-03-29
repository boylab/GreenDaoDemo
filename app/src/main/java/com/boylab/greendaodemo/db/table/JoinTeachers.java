package com.boylab.greendaodemo.db.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class JoinTeachers {
    @Id(autoincrement = true)
    private Long id;
    private Long tid;
    private Long sid;
    public JoinTeachers(Long tid, Long sid) {
        this.tid = tid;
        this.sid = sid;
    }
    @Generated(hash = 978213455)
    public JoinTeachers(Long id, Long tid, Long sid) {
        this.id = id;
        this.tid = tid;
        this.sid = sid;
    }
    @Generated(hash = 973764542)
    public JoinTeachers() {
    }
    
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getTid() {
        return this.tid;
    }
    public void setTid(Long tid) {
        this.tid = tid;
    }
    public Long getSid() {
        return this.sid;
    }
    public void setSid(Long sid) {
        this.sid = sid;
    }

}
