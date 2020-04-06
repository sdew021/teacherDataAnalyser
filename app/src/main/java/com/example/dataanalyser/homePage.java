package com.example.dataanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class homePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Button next = findViewById(R.id.next);
        final EditText sub = findViewById(R.id.sub);
        final EditText branch = findViewById(R.id.branch);
        final EditText sem = findViewById(R.id.sem);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homePage.this,subjectList.class);

                if(!(sub.getText().toString()).isEmpty() && !(branch.getText().toString()).isEmpty() && !(sem.getText().toString()).isEmpty()) {
                    Constants.teacher.setnOS(Integer.parseInt((sub.getText().toString())));
                    Constants.teacher.setSem((sem.getText().toString()));
                    Constants.teacher.setBranch((branch.getText().toString()));
                    startActivity(intent);
                }else{
                    if((sub.getText().toString()).isEmpty()){
                        sub.setError("Enter some value");
                    }
                    if((branch.getText().toString()).isEmpty()){
                        branch.setError("Enter some value");
                    }
                    if((sem.getText().toString()).isEmpty()){
                        sem.setError("Enter some value");
                    }
                }
            }
        });
    }
}
