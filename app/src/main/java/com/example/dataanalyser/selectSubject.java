package com.example.dataanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class selectSubject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);
        final Button[] subButton = new Button[Constants.teacher.getnOS()];
        final LinearLayout myLinearLayout = findViewById(R.id.subjectButton);
        Button activity = new Button(this);
        final Button analyse = new Button(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child(Constants.teacher.getId());


        int i = 0;
        Log.d("ssub", "size " + Constants.teacher.getSubjects().size());
        for (String sub : Constants.teacher.getSubjects()) {
            if (i == Constants.teacher.getnOS()) {
                i = 0;
            }
            final Button b = new Button(this);
            b.setText(sub);
            myLinearLayout.addView(b);
            subButton[i] = b;
            subButton[i].setId(i + 1);

            i++;
        }

        activity.setText("Add Activity");
        myLinearLayout.addView(activity);

        analyse.setText("Analyse");
        //analyse.setVisibility(View.INVISIBLE);
        myLinearLayout.addView(analyse);


        for (int j = 0; j < Constants.teacher.getnOS(); j++) {
            final Button b = myLinearLayout.findViewById(j + 1);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(selectSubject.this, dataEntry.class);
                    intent.putExtra("name", b.getText().toString());
                    startActivity(intent);
                }
            });
        }

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(selectSubject.this, addActivity.class);
                startActivity(intent);
            }
        });

        for (String sub : Constants.teacher.getSubjects()) {
            myRef.child("Subjects").child(sub).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.e("abcde", dataSnapshot.getKey());
                    Log.e("abcde", dataSnapshot.getChildrenCount() + "");
                    if (dataSnapshot.getChildrenCount() == 0) {
                        analyse.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        analyse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SubjectsUtil.subjectAnalysis.size() == Constants.teacher.getnOS()) {
                    startActivity(new Intent(selectSubject.this, finalanalysis.class));
                }else{
                    updateAnalysis();
                }
            }
        });

    }


    private void updateAnalysis() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child(Constants.teacher.getId()).child("Subjects");

        for (String sub : Constants.teacher.getSubjects()) {
            myRef.child(sub).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.e("UpdateAnalysis", dataSnapshot.getKey() + " " + dataSnapshot.getValue());
                    Subject s = dataSnapshot.getValue(Subject.class);
                    SubjectsUtil.subjectAnalysis.add(s);
                    // Log.e("UpdateAnalysis", " " + SubjectsUtil.subjectAnalysis.get(1).getClassTaken());
                    Log.e("UpdateAnalysis", " " + SubjectsUtil.subjectAnalysis.size());
                    if (SubjectsUtil.subjectAnalysis.size() == Constants.teacher.getnOS()) {
                        startActivity(new Intent(selectSubject.this, finalanalysis.class));
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }

    @Override
    public void onBackPressed() {
        if (true) {

        } else {
            super.onBackPressed();
        }

    }
}
