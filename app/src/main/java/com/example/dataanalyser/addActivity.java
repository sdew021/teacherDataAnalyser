package com.example.dataanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText title,desc,date,subject;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        desc = findViewById(R.id.desc);
        title = findViewById(R.id.title);
        date = findViewById(R.id.date);
        subject = findViewById(R.id.subject);

        final Activity activity = new Activity();

        Button add;
        add =findViewById(R.id.addAct);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.setDate(date.getText().toString());
                activity.setDesc(desc.getText().toString());
                activity.setTitle(title.getText().toString());
                activity.setSubject(subject.getText().toString());

                myRef.child(Constants.teacher.getId()).child("actCnt").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Constants.actCount = dataSnapshot.getValue(Integer.class) + 1;
                        myRef.child(Constants.teacher.getId()).child("Activity").child("a"+(Constants.actCount-1)).setValue(activity);
                        //Constants.actCount++;
                        myRef.child(Constants.teacher.getId()).child("actCnt").setValue(Constants.actCount);

                        Toast.makeText(addActivity.this, "Activity Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(addActivity.this,selectSubject.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });






            }
        });

    }
}
