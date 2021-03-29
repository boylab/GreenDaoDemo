package com.boylab.greendaodemo.db.helper;

import com.boylab.greendaodemo.db.table.Fruit;
import com.boylab.greendaodemo.db.tableDao.FruitDao;

import org.greenrobot.greendao.AbstractDao;

import java.util.List;

public class FruitHelp extends BaseHelp<Fruit>{

    public FruitHelp(AbstractDao dao) {
        super(dao);
    }

    // 分页加载
    public List<Fruit> queryByPage(int page){
        return queryBuilder()
                .orderAsc(FruitDao.Properties.Id)
                .offset(page * 10)
                .limit(10)
                .list();
    }

    // 按价格分页排序
    public List<Fruit> queryOnPrice(int page){
        return queryBuilder()
                .orderAsc(FruitDao.Properties.Price)
                .offset(page * 10)
                .limit(10)
                .list();
    }

    public List<Fruit> queryAbovePrice(float price){
        return queryBuilder()
                .whereOr(FruitDao.Properties.Price.gt(price), FruitDao.Properties.Price.eq(price))
                .orderAsc(FruitDao.Properties.Price)
                .list();
    }


    /**
     * 增加功能、添加方法
     */

}
