package com.boylab.greendaodemo.db.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Unique;

import com.boylab.greendaodemo.db.tableDao.DaoSession;
import com.boylab.greendaodemo.db.tableDao.TeacherDao;
import com.boylab.greendaodemo.db.tableDao.StudentDao;

@Entity
public class Student {

    @Id(autoincrement = true)
    private Long id;

    @Unique
    private String name;
    private int age;
    private String sex;
    private float scores;

    // 对多，@JoinEntity注解：entity 中间表；sourceProperty 实体属性；targetProperty 外链实体属性
    @ToMany
    @JoinEntity(
            entity = JoinTeachers.class,
            sourceProperty = "sid",
            targetProperty = "tid"
    )
    private List<Teacher> teachers;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1943931642)
    private transient StudentDao myDao;

    public Student(String name, int age, String sex, float scores) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.scores = scores;
    }

    @Generated(hash = 994060533)
    public Student(Long id, String name, int age, String sex, float scores) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.scores = scores;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public float getScores() {
        return this.scores;
    }

    public void setScores(float scores) {
        this.scores = scores;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1400050783)
    public List<Teacher> getTeachers() {
        if (teachers == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeacherDao targetDao = daoSession.getTeacherDao();
            List<Teacher> teachersNew = targetDao._queryStudent_Teachers(id);
            synchronized (this) {
                if (teachers == null) {
                    teachers = teachersNew;
                }
            }
        }
        return teachers;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 456331696)
    public synchronized void resetTeachers() {
        teachers = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1701634981)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStudentDao() : null;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", scores=" + scores +
                '}';
    }
}
