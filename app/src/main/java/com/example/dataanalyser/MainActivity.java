package com.example.dataanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText ephone;
    Button blogin;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ephone=(EditText)findViewById(R.id.phone);
        blogin=(Button)findViewById(R.id.login);

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,homePage.class);
                if(checkId(ephone.getText().toString())) {
                    Constants.teacher.setId(ephone.getText().toString());
                    startActivity(intent);
                }else{
                    ephone.setError("Enter Correct Id");
                }
            }
        });

    }

    private boolean checkId(String id) {
        boolean res = false;
        if(!id.isEmpty() || !id.trim().equals("") ){
            res = true;
        }
        //TODO : Logic to check if id is registered
        return res;
    }

}
