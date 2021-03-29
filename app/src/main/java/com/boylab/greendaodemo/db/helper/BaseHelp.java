package com.boylab.greendaodemo.db.helper;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class BaseHelp<T> {

    private AbstractDao<T, Long> mDao;

    public BaseHelp(AbstractDao dao) {
        mDao = dao;
    }

    public long insert(T item) {
        return mDao.insert(item);
    }

    public void insert(T... items) {
        mDao.insertInTx(items);
    }

    public void insert(List<T> items) {
        mDao.insertInTx(items);
    }

    public long insertOrUpdate(T item) {
        return mDao.insertOrReplace(item);
    }

    public void insertOrUpdate(T... items) {
        mDao.insertOrReplaceInTx(items);
    }

    public void insertOrUpdate(List<T> items) {
        mDao.insertOrReplaceInTx(items);
    }

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

    public void update(T item) {
        mDao.update(item);
    }

    public void update(T... items) {
        mDao.updateInTx(items);
    }

    public void update(List<T> items) {
        mDao.updateInTx(items);
    }

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