package com.boylab.greendaodemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.boylab.greendaodemo.R;
import com.boylab.greendaodemo.db.helper.FruitHelp;
import com.boylab.greendaodemo.db.manager.DBManager;
import com.boylab.greendaodemo.db.table.Fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitActivity extends AppCompatActivity {

    private TextView text_Show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        text_Show = findViewById(R.id.text_Show);

        //录入基本数据
        FruitHelp fruitHelp = DBManager.newInstance().getFruitHelp();
        if (fruitHelp.count() == 0){
            List<Fruit> fruitList = new ArrayList<Fruit>(){{
                add(new Fruit("橘子", 2.5f, 182.0f, "yellow"));
                add(new Fruit("苹果小", 10.0f, 210.0f, "red"));
                add(new Fruit("苹果大", 12.0f, 380.0f, "red"));
                add(new Fruit("香蕉小", 4.0f, 150.0f, "yellow"));
                add(new Fruit("香蕉大", 5.0f, 210.0f, "yellow"));
                add(new Fruit("西瓜", 3.5f, 5000.0f, "green"));
                add(new Fruit("梨子", 8.0f, 250.0f, "yellow"));
                add(new Fruit("李子", 6.0f, 75.0f, "purple"));
                add(new Fruit("葡萄", 9.0f, 10.0f, "red"));
                add(new Fruit("桃子", 7.0f, 150.0f, "pink"));
            }};
            fruitHelp.insert(fruitList);
        }

        //查询操作
        findViewById(R.id.btn_QueryByPage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询第一页
                int page = 0;
                List<Fruit> fruitList = fruitHelp.queryByPage(page);

                text_Show.setText("size"+fruitList.size());
                for (Fruit fruit:fruitList) {
                    text_Show.append("\n");
                    text_Show.append(fruit.toString());
                }
            }
        });

        findViewById(R.id.btn_QueryOnPrice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //查询第一页，按价格排序
                int page = 0;
                List<Fruit> fruitList = fruitHelp.queryOnPrice(page);

                text_Show.setText("size"+fruitList.size());
                for (Fruit fruit:fruitList) {
                    text_Show.append("\n");
                    text_Show.append(fruit.toString());
                }
            }
        });

        findViewById(R.id.btn_QueryAbovePrice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float price = 5;
                List<Fruit> fruitList = fruitHelp.queryAbovePrice(price);

                text_Show.setText("size"+fruitList.size());
                for (Fruit fruit:fruitList) {
                    text_Show.append("\n");
                    text_Show.append(fruit.toString());
                }
            }
        });




    }
}