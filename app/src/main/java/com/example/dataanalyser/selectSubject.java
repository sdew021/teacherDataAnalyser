package com.example.dataanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class selectSubject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);
        final Button[] subButton = new Button[Constants.teacher.getnOS()];
        final LinearLayout myLinearLayout = findViewById(R.id.subjectButton);
        Button activity = new Button(this);



        int i = 0;
        Log.d("ssub","size " + Constants.teacher.getSubjects().size()  );
        for(String sub : Constants.teacher.getSubjects()){
            if(i == Constants.teacher.getnOS() ){
                i=0;
            }
            final Button b = new Button(this);
            b.setText(sub);
            myLinearLayout.addView(b);
            subButton[i] = b;
            subButton[i].setId(i+1);

            i++;
        }

        activity.setText("Add Activity");
        myLinearLayout.addView(activity);

        for(int j =0;j<Constants.teacher.getnOS();j++ ){
            final Button b = myLinearLayout.findViewById(j+1);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(selectSubject.this,dataEntry.class);
                    intent.putExtra("name",b.getText().toString());
                    startActivity(intent);
                }
            });
        }

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(selectSubject.this,addActivity.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onBackPressed() {
        if(true){

        }else {
            super.onBackPressed();
        }

    }
}
