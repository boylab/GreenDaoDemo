package com.boylab.greendaodemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.boylab.greendaodemo.R;
import com.boylab.greendaodemo.db.manager.DBManager;
import com.boylab.greendaodemo.db.helper.JoinTeachersHelp;
import com.boylab.greendaodemo.db.helper.StudentHelp;
import com.boylab.greendaodemo.db.helper.TeacherHelp;
import com.boylab.greendaodemo.db.table.Fruit;
import com.boylab.greendaodemo.db.table.JoinTeachers;
import com.boylab.greendaodemo.db.table.Student;
import com.boylab.greendaodemo.db.table.Teacher;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //常规使用
        findViewById(R.id.btn_Basic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FruitActivity.class));
            }
        });

        //一对多
        findViewById(R.id.btn_One_ToMany).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ToManyActivity.class));
            }
        });

        //多对多
        findViewById(R.id.btn_Many_ToMany).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ToManyActivity.class));
            }
        });

    }
}