package com.boylab.greendaodemo.db.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.boylab.greendaodemo.db.helper.FruitHelp;
import com.boylab.greendaodemo.db.helper.JoinTeachersHelp;
import com.boylab.greendaodemo.db.helper.StudentHelp;
import com.boylab.greendaodemo.db.helper.TeacherHelp;
import com.boylab.greendaodemo.db.tableDao.DaoMaster;
import com.boylab.greendaodemo.db.tableDao.DaoSession;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 *  DBManager作为数据库唯一入口
 * 每一个Util都管理着数据库中的一个表，由DBManager进行统一管理
 */
public class DBManager {

    private static DBManager instance;
    private DBOpenHelper devOpenHelper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;

    private DBManager(){

    }
    public static DBManager newInstance(){
        if (instance==null){
            synchronized (DBManager.class){
                if (instance==null){
                    instance = new DBManager();
                }
            }
        }
        return instance;
    }

    public void setDataBase(Context context){
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        devOpenHelper = new DBOpenHelper(context, "boylab-db", null);
        db = devOpenHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private SQLiteDatabase database() {
        return db;
    }

    private DaoSession daoSession() {
        return daoSession;
    }

    /**
     * 设置debug模式开启或关闭，默认关闭
     */
    public void setDebug(boolean flag) {
        QueryBuilder.LOG_SQL = flag;
        QueryBuilder.LOG_VALUES = flag;
    }

    /**
     *  DBManager作为数据库唯一入口
     * 每一个Util都管理着数据库中的一个表，由DBManager进行统一管理
     */
    private FruitHelp fruitHelp;
    public synchronized FruitHelp getFruitHelp() {
        if (fruitHelp == null){
            fruitHelp = new FruitHelp(daoSession().getFruitDao());
        }
        return fruitHelp;
    }

    /**
     * 多对多表管理类
     */
    private TeacherHelp teacherHelp;
    private StudentHelp studentHelp;
    private JoinTeachersHelp joinTeachersHelp;

    public synchronized TeacherHelp getTeacherHelp() {
        if (teacherHelp == null){
            teacherHelp = new TeacherHelp(daoSession().getTeacherDao());
        }
        return teacherHelp;
    }

    public synchronized StudentHelp getStudentHelp() {
        if (studentHelp == null){
            studentHelp = new StudentHelp(daoSession().getStudentDao());
        }
        return studentHelp;
    }

    public synchronized JoinTeachersHelp getJoinTeachersHelp() {
        if (joinTeachersHelp == null){
            joinTeachersHelp = new JoinTeachersHelp(daoSession().getJoinTeachersDao());
        }
        return joinTeachersHelp;
    }


}
