package com.boylab.greendaodemo.db.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.boylab.greendaodemo.db.tableDao.DaoMaster;

/**
 * todo 待解决
 */
public class DBOpenHelper extends DaoMaster.OpenHelper{

    public DBOpenHelper(Context context, String name) {
        super(context, name);
    }

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }


    /**
     * 需要在实体类加一个字段 或者 改变字段属性等 就需要版本更新来保存以前的数据了
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);

        //这里添加要增加的字段
        //MigrationHelper.migrate(db, CarNumberDao.class);

    }

}
