package com.example.dataanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        Button update,done;
        TextView title;
        final EditText classTaken,classTotal,marks1,marks2,marks3;
        update = findViewById(R.id.Update);
        done = findViewById(R.id.Done);

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
                subject.setSubName(bundle.getString("name"));
                if(!classTaken.getText().toString().trim().isEmpty()){
                    subject.setClassTaken(Integer.parseInt(classTaken.getText().toString()));
                }else{
                    classTaken.setError("Enter Some Value");
                }

                if(!classTotal.getText().toString().trim().isEmpty()){
                    subject.setClassTotal(Integer.parseInt(classTotal.getText().toString()));
                }else{
                    classTotal.setError("Enter Some Value");
                }

                if(!marks1.getText().toString().trim().isEmpty()){
                    subject.setMarks1(Integer.parseInt(marks1.getText().toString()));
                }else{
                    marks1.setError("Enter Some Value");
                }
                if(!marks2.getText().toString().trim().isEmpty()){
                    subject.setMarks2(Integer.parseInt(marks2.getText().toString()));
                }else{
                    marks2.setError("Enter Some Value");
                }
                if(!marks3.getText().toString().trim().isEmpty()){
                    subject.setMarks3(Integer.parseInt(marks3.getText().toString()));
                }else{
                    marks3.setError("Enter Some Value");
                }


                if(!classTaken.getText().toString().trim().isEmpty() && !classTotal.getText().toString().trim().isEmpty() && !marks1.getText().toString().trim().isEmpty()
                                                                    && !marks2.getText().toString().trim().isEmpty() && !marks3.getText().toString().trim().isEmpty()){
                    myRef.child(Constants.teacher.getId()).child("Subjects").child(bundle.getString("name")).setValue(subject);
                }



            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSub();
            }

            private void updateSub() {

                subject.setSubName(bundle.getString("name"));
                if(!classTaken.getText().toString().trim().isEmpty()){
                    subject.setClassTaken(Integer.parseInt(classTaken.getText().toString()));
                }else{
                    classTaken.setError("Enter Some Value");
                }

                if(!classTotal.getText().toString().trim().isEmpty()){
                    subject.setClassTotal(Integer.parseInt(classTotal.getText().toString()));
                }else{
                    classTotal.setError("Enter Some Value");
                }

                if(!marks1.getText().toString().trim().isEmpty()){
                    subject.setMarks1(Integer.parseInt(marks1.getText().toString()));
                }else{
                    marks1.setError("Enter Some Value");
                }
                if(!marks2.getText().toString().trim().isEmpty()){
                    subject.setMarks2(Integer.parseInt(marks2.getText().toString()));
                }else{
                    marks2.setError("Enter Some Value");
                }
                if(!marks3.getText().toString().trim().isEmpty()){
                    subject.setMarks3(Integer.parseInt(marks3.getText().toString()));
                }else{
                    marks3.setError("Enter Some Value");
                }


                if(!classTaken.getText().toString().trim().isEmpty() && !classTotal.getText().toString().trim().isEmpty() && !marks1.getText().toString().trim().isEmpty()
                        && !marks2.getText().toString().trim().isEmpty() && !marks3.getText().toString().trim().isEmpty()){
                    myRef.child(Constants.teacher.getId()).child("Subjects").child(bundle.getString("name")).setValue(subject);
                    startActivity(new Intent(dataEntry.this,selectSubject.class));
                }



            }
        });



    }
}
