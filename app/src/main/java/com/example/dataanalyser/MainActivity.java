package com.example.dataanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText ephone,epass;
    Button blogin,bregister;
    TextView forgot;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ephone=(EditText)findViewById(R.id.phone);
        epass=(EditText)findViewById(R.id.pass);
        blogin=(Button)findViewById(R.id.login);
        bregister=(Button)findViewById(R.id.register);
        forgot=(TextView) findViewById(R.id.forgot);
    }
}
