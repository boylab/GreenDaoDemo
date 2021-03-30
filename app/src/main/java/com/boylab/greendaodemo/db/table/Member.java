package com.boylab.greendaodemo.db.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Member {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private Long leaderId;

    @Unique
    private String name;
    private int age;
    private String sex;

    public Member(String name, int age, String sex, Long leaderId) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.leaderId = leaderId;
    }

    @Generated(hash = 1572158817)
    public Member(Long id, @NotNull Long leaderId, String name, int age,
                  String sex) {
        this.id = id;
        this.leaderId = leaderId;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Generated(hash = 1678089316)
    public Member() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLeaderId() {
        return this.leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Menber{" +
                "id=" + id +
                ", leaderId=" + leaderId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
