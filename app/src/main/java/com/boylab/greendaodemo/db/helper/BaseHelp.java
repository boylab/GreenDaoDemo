package com.boylab.greendaodemo.db.helper;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class BaseHelp<T> {

    private AbstractDao<T, Long> mDao;

    public BaseHelp(AbstractDao dao) {
        mDao = dao;
    }

    /**
     * 新增
     */
    public long insert(T item) {
        return mDao.insert(item);
    }

    public void insert(T... items) {
        mDao.insertInTx(items);
    }

    public void insert(List<T> items) {
        mDao.insertInTx(items);
    }

    /**
     * 新增或修改
     */
    public long insertOrUpdate(T item) {
        return mDao.insertOrReplace(item);
    }

    public void insertOrUpdate(T... items) {
        mDao.insertOrReplaceInTx(items);
    }

    public void insertOrUpdate(List<T> items) {
        mDao.insertOrReplaceInTx(items);
    }

    /**
     * 删除
     */
    public void deleteByKey(Long key) {
        mDao.deleteByKey(key);
    }

    public void delete(T item) {
        mDao.delete(item);
    }

    public void delete(T... items) {
        mDao.deleteInTx(items);
    }

    public void delete(List<T> items) {
        mDao.deleteInTx(items);
    }

    public void deleteAll() {
        mDao.deleteAll();
    }

    /**
     * 修改
     */
    public void update(T item) {
        mDao.update(item);
    }

    public void update(T... items) {
        mDao.updateInTx(items);
    }

    public void update(List<T> items) {
        mDao.updateInTx(items);
    }

    /**
     * 查询
     */
    public T query(Long key) {
        return mDao.load(key);
    }

    public List<T> queryAll() {
        return mDao.loadAll();
    }

    public List<T> query(String where, String... params) {
        return mDao.queryRaw(where, params);
    }

    public QueryBuilder<T> queryBuilder() {
        return mDao.queryBuilder();
    }

    public long count() {
        return mDao.count();
    }

    public void refresh(T item) {
        mDao.refresh(item);
    }

    public boolean detach(T item) {
        return mDao.detach(item);
    }

}