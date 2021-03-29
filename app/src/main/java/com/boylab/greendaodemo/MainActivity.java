package com.boylab.greendaodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.boylab.greendaodemo.db.helper.DBManager;
import com.boylab.greendaodemo.db.helper.JoinTeachersHelp;
import com.boylab.greendaodemo.db.helper.StudentHelp;
import com.boylab.greendaodemo.db.helper.TeacherHelp;
import com.boylab.greendaodemo.db.table.JoinTeachers;
import com.boylab.greendaodemo.db.table.Student;
import com.boylab.greendaodemo.db.table.Teacher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 录入基本数据
         */
        Student studentA = new Student("mick", 25, "boy", 78);
        Student studentB = new Student("lili", 22, "girl", 85);
        Student studentC = new Student("tom", 26, "boy", 65);

        StudentHelp studentHelp = DBManager.newInstance().getStudentHelp();
        if (studentHelp.count() == 0){
            long idA = studentHelp.insertOrUpdate(studentA);
            long idB = studentHelp.insertOrUpdate(studentB);
            long idC = studentHelp.insertOrUpdate(studentC);
            Log.i(">>>>>", "onCreate: idA = "+idA +"   : idB = "+idB +"   : idC = "+idC);
        }

        Teacher teacherA = new Teacher("English", 35, "boy", 170);
        Teacher teacherB = new Teacher("Chinese", 32, "girl", 185);
        Teacher teacherC = new Teacher("Math", 41, "boy", 165);

        TeacherHelp teacherHelp = DBManager.newInstance().getTeacherHelp();
        if (teacherHelp.count() == 0){
            teacherHelp.insert(teacherA);
            teacherHelp.insert(teacherB);
            teacherHelp.insert(teacherC);
        }

        /**
         * 
         */
        JoinTeachersHelp joinTeachersHelp = DBManager.newInstance().getJoinTeachersHelp();
        if (joinTeachersHelp.count() == 0){
            JoinTeachers joinTeachers11 = new JoinTeachers(1L, 1L);
            JoinTeachers joinTeachers12 = new JoinTeachers(1L, 2L);
            joinTeachersHelp.insertOrUpdate(joinTeachers11, joinTeachers12);

            JoinTeachers joinTeachers22 = new JoinTeachers(2L, 2L);
            JoinTeachers joinTeachers23 = new JoinTeachers(2L, 3L);
            joinTeachersHelp.insertOrUpdate(joinTeachers22, joinTeachers23);

            JoinTeachers joinTeachers31 = new JoinTeachers(3L, 1L);
            JoinTeachers joinTeachers32 = new JoinTeachers(3L, 2L);
            JoinTeachers joinTeachers33 = new JoinTeachers(3L, 3L);
            joinTeachersHelp.insertOrUpdate(joinTeachers31, joinTeachers32, joinTeachers33);
        }

        Student queryStudent = studentHelp.query(1L);
        Log.i(">>>>>", "学生A: "+queryStudent.toString());

        List<Teacher> studentTeachers = queryStudent.getTeachers();
        for (int i = 0; i < studentTeachers.size(); i++) {
            Log.i(">>>>>", "学生A的老师: "+studentTeachers.get(i).toString());
        }

        Teacher teacher = studentTeachers.get(1);
        List<Student> teacherStudents = teacher.getStudents();
        for (int i = 0; i < teacherStudents.size(); i++) {
            Log.i(">>>>>", "老师C的学生: "+teacherStudents.get(i).toString());
        }


    }
}