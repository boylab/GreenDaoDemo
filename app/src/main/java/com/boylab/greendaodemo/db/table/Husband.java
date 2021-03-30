package com.boylab.greendaodemo.db.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.boylab.greendaodemo.db.tableDao.DaoSession;
import com.boylab.greendaodemo.db.tableDao.WifeDao;
import com.boylab.greendaodemo.db.tableDao.HusbandDao;

@Entity
public class Husband {

    @Id(autoincrement = true)
    private Long id;

    @Unique
    private String name;
    private int age;
    private String sex;

    private Long wifiID;

    @ToOne(joinProperty = "wifiID")
    Wife wife;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1693210069)
    private transient HusbandDao myDao;

    public Husband(String name, int age, String sex, Long wifiID) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.wifiID = wifiID;
    }

    @Generated(hash = 1860609950)
    public Husband(Long id, String name, int age, String sex, Long wifiID) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.wifiID = wifiID;
    }

    @Generated(hash = 890504812)
    public Husband() {
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

    public Long getWifiID() {
        return this.wifiID;
    }

    public void setWifiID(Long wifiID) {
        this.wifiID = wifiID;
    }

    @Generated(hash = 963033483)
    private transient Long wife__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1891565345)
    public Wife getWife() {
        Long __key = this.wifiID;
        if (wife__resolvedKey == null || !wife__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            WifeDao targetDao = daoSession.getWifeDao();
            Wife wifeNew = targetDao.load(__key);
            synchronized (this) {
                wife = wifeNew;
                wife__resolvedKey = __key;
            }
        }
        return wife;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 180912047)
    public void setWife(Wife wife) {
        synchronized (this) {
            this.wife = wife;
            wifiID = wife == null ? null : wife.getId();
            wife__resolvedKey = wifiID;
        }
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
    @Generated(hash = 1639199962)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getHusbandDao() : null;
    }

}
