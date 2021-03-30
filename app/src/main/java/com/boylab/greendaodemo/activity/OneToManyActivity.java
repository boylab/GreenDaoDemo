package com.boylab.greendaodemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.boylab.greendaodemo.R;
import com.boylab.greendaodemo.db.helper.LeaderHelp;
import com.boylab.greendaodemo.db.helper.MenberHelp;
import com.boylab.greendaodemo.db.manager.DBManager;
import com.boylab.greendaodemo.db.table.Leader;
import com.boylab.greendaodemo.db.table.Member;

import java.util.List;

public class OneToManyActivity extends AppCompatActivity {

    private TextView text_Show;

    private LeaderHelp leaderHelp = DBManager.newInstance().getLeaderHelp();
    private MenberHelp menberHelp = DBManager.newInstance().getMenberHelp();

    private Leader queryLeaderA, queryLeaderB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_to_many);

        text_Show = findViewById(R.id.text_Show);
        entryData();

        findViewById(R.id.btn_LeaderA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryLeaderA = leaderHelp.query(1L);

                text_Show.setText("A领导 : "+ queryLeaderA.toString());
                text_Show.append("\n");
            }
        });

        findViewById(R.id.btn_LeaderA_Menbers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Member> leaderMembers = queryLeaderA.getMemberList();

                text_Show.append("A领导的员工有 size = "+ leaderMembers.size());
                for (Member member : leaderMembers) {
                    text_Show.append("\n");
                    text_Show.append(member.toString());
                }
            }
        });

        findViewById(R.id.btn_LeaderB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryLeaderB = leaderHelp.query(2L);

                text_Show.setText("A领导 : "+ queryLeaderB.toString());
                text_Show.append("\n");
            }
        });

        findViewById(R.id.btn_LeaderB_Menbers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Member> leaderMembers = queryLeaderB.getMemberList();

                text_Show.append("A领导的员工有 size = "+ leaderMembers.size());
                for (Member member : leaderMembers) {
                    text_Show.append("\n");
                    text_Show.append(member.toString());
                }
            }
        });

    }

    public void entryData(){
        /**
         * 录入两名领导
         */
        Leader leaderA = new Leader("刘经理", 35, "man");
        Leader leaderB = new Leader("张经理", 28, "woman");
        long leaderA_ID = 0, leaderB_ID = 0;

        if (leaderHelp.count() == 0){
            leaderA_ID = leaderHelp.insert(leaderA);
            leaderB_ID = leaderHelp.insert(leaderB);
        }

        /**
         * 录入两名领导下的员工
         */
        if (menberHelp.count() == 0){
            Member member01 = new Member("小刘", 24, "boy", leaderA_ID);
            Member member02 = new Member("小丽", 22, "girl", leaderA_ID);
            Member member03 = new Member("小吴", 27, "boy", leaderA_ID);
            menberHelp.insert(member01);
            menberHelp.insert(member02);
            menberHelp.insert(member03);

            Member member04 = new Member("小韩", 29, "boy", leaderB_ID);
            Member member05 = new Member("小颖", 26, "girl", leaderB_ID);
            menberHelp.insert(member04);
            menberHelp.insert(member05);
        }

    }
}