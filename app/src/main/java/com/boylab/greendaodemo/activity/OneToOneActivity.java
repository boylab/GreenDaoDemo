package com.boylab.greendaodemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.boylab.greendaodemo.R;
import com.boylab.greendaodemo.db.helper.HusbandHelp;
import com.boylab.greendaodemo.db.helper.WifeHelp;
import com.boylab.greendaodemo.db.manager.DBManager;
import com.boylab.greendaodemo.db.table.Husband;
import com.boylab.greendaodemo.db.table.Wife;

public class OneToOneActivity extends AppCompatActivity {

    private TextView text_Show;

    private HusbandHelp husbandHelp = DBManager.newInstance().getHusbandHelp();
    private WifeHelp wifeHelp = DBManager.newInstance().getWifeHelp();

    private Husband queryHusbandA, queryHusbandB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_to_one);

        text_Show = findViewById(R.id.text_Show);
        entryData();

        findViewById(R.id.btn_HusbandA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryHusbandA = husbandHelp.query(1L);

                text_Show.setText("A丈夫 : "+ queryHusbandA.toString());
                text_Show.append("\n");
            }
        });

        findViewById(R.id.btn_HusbandA_Wifes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wife wife = queryHusbandA.getWife();

                text_Show.append("A丈夫的妻子：");
                text_Show.append("\n");
                text_Show.append(wife.toString());
            }
        });

        findViewById(R.id.btn_HusbandB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryHusbandB = husbandHelp.query(2L);

                text_Show.setText("A丈夫 : "+ queryHusbandB.toString());
                text_Show.append("\n");
            }
        });

        findViewById(R.id.btn_HusbandB_Wifes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wife wife = queryHusbandB.getWife();

                text_Show.append("B丈夫的妻子：");
                text_Show.append("\n");
                text_Show.append(wife.toString());
            }
        });
    }

    public void entryData() {
        /**
         * 录入两名妻子
         */
        Wife wifeA = new Wife("李芳", 23, "girl");
        Wife wifeB = new Wife("许依婷", 22, "girl");
        long wifeA_ID = 0, wifeB_ID = 0;

        if (wifeHelp.count() == 0){
            wifeA_ID = wifeHelp.insert(wifeA);
            wifeB_ID = wifeHelp.insert(wifeB);
        }

        /**
         * 录入两名丈夫
         */
        if (husbandHelp.count() == 0){
            Husband husband01 = new Husband("张三", 24, "boy", wifeA_ID);

            Husband husband02 = new Husband("李四", 24, "boy", wifeB_ID);
            //Husband husband02 = new Husband("李四", 24, "boy", -1L);

            husbandHelp.insert(husband01);
            husbandHelp.insert(husband02);
        }
    }
}