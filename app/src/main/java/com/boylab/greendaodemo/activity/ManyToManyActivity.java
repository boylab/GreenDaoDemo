package com.boylab.greendaodemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.boylab.greendaodemo.R;
import com.boylab.greendaodemo.db.helper.JoinTeachersHelp;
import com.boylab.greendaodemo.db.helper.StudentHelp;
import com.boylab.greendaodemo.db.helper.TeacherHelp;
import com.boylab.greendaodemo.db.manager.DBManager;
import com.boylab.greendaodemo.db.table.JoinTeachers;
import com.boylab.greendaodemo.db.table.Student;
import com.boylab.greendaodemo.db.table.Teacher;

import java.util.List;

public class ManyToManyActivity extends AppCompatActivity {

    private TextView text_Show;

    private StudentHelp studentHelp = DBManager.newInstance().getStudentHelp();
    private TeacherHelp teacherHelp = DBManager.newInstance().getTeacherHelp();

    private Teacher queryTeacher;
    private Student queryStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many_to_many);

        text_Show = findViewById(R.id.text_Show);
        entryData();

        findViewById(R.id.btn_Teacher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryTeacher = teacherHelp.query(1L);

                text_Show.setText("A老师 : "+queryTeacher.toString());
                text_Show.append("\n");
            }
        });

        findViewById(R.id.btn_Teacher_Students).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Student> teacherStudents = queryTeacher.getStudents();

                text_Show.append("A老师的学生有 size = "+teacherStudents.size());
                for (Student student:teacherStudents) {
                    text_Show.append("\n");
                    text_Show.append(student.toString());
                }
            }
        });


        findViewById(R.id.btn_Student).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryStudent = studentHelp.query(1L);

                text_Show.setText("学生 : "+queryStudent.toString());
                text_Show.append("\n");
            }
        });

        findViewById(R.id.btn_Student_Teachers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Teacher> studentTeachers = queryStudent.getTeachers();

                text_Show.append("学生的老师有 size = "+studentTeachers.size());
                for (Teacher teacher:studentTeachers) {
                    text_Show.append("\n");
                    text_Show.append(teacher.toString());
                }
            }
        });

    }

    public void entryData(){
        /**
         * 录入基本数据三名老师、三名学生
         */
        Teacher teacherA = new Teacher("English", 35, "boy", 170);
        Teacher teacherB = new Teacher("Chinese", 32, "girl", 185);
        Teacher teacherC = new Teacher("Math", 41, "boy", 165);
        if (teacherHelp.count() == 0){
            teacherHelp.insert(teacherA);
            teacherHelp.insert(teacherB);
            teacherHelp.insert(teacherC);
        }

        Student studentA = new Student("mike", 25, "boy", 78);
        Student studentB = new Student("lili", 22, "girl", 85);
        Student studentC = new Student("tom", 26, "boy", 65);
        if (studentHelp.count() == 0){
            long idA = studentHelp.insertOrUpdate(studentA);
            long idB = studentHelp.insertOrUpdate(studentB);
            long idC = studentHelp.insertOrUpdate(studentC);
            Log.i(">>>>>", "onCreate: idA = "+idA +"   : idB = "+idB +"   : idC = "+idC);
        }

        /**
         * 录入老师和学生的关系（多对多）
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
    }
}