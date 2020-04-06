package com.example.dataanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class dataEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);

        final Subject subject = new Subject();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        Button update,analyse;
        TextView title;
        final EditText classTaken,classTotal,marks1,marks2,marks3;
        update = findViewById(R.id.Update);
        analyse = findViewById(R.id.Analyse);

        classTaken = findViewById(R.id.classTaken);
        classTotal = findViewById(R.id.classTotal);

        marks1 = findViewById(R.id.marks1);
        marks2 = findViewById(R.id.marks2);
        marks3 = findViewById(R.id.marks3);

        title = findViewById(R.id.name);
        final Bundle bundle = getIntent().getExtras();
        title.setText("Data Entry : "+bundle.getString("name"));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSub();
            }

            private void updateSub() {
                subject.setClassTaken(Integer.parseInt(classTaken.getText().toString()));
                subject.setClassTotal(Integer.parseInt(classTotal.getText().toString()));

                subject.setMarks1(Integer.parseInt(marks1.getText().toString()));
                subject.setMarks2(Integer.parseInt(marks2.getText().toString()));
                subject.setMarks3(Integer.parseInt(marks3.getText().toString()));

                myRef.child(Constants.teacher.getId()).child("Subjects").child(bundle.getString("name")).setValue(subject);


            }
        });



    }
}
