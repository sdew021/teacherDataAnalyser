package com.example.dataanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class subjectList extends AppCompatActivity {

    public static int N;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        TextView branch, sem;


        branch = findViewById(R.id.branch);
        sem = findViewById(R.id.sem);
        N = Constants.teacher.getnOS();

        final EditText[] myTextViews = new EditText[N]; // create an empty array;
        final LinearLayout myLinearLayout = findViewById(R.id.subjectListLayout);
        branch.setText("  Branch : " + Constants.teacher.getBranch());
        sem.setText("  Sem : " + Constants.teacher.getSem());


        for (int i = 0; i < N; i++) {

            final EditText subjectListText = new EditText(this);

            subjectListText.setHint("Enter Subject #" + (i + 1));

            myLinearLayout.addView(subjectListText);

            myTextViews[i] = subjectListText;
            myTextViews[i].setId(i+1);
        }
        myRef.child(Constants.teacher.getId()).setValue(Constants.teacher);
        final Button next = new Button(this);
        next.setText("NEXT");
        next.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        next.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        myLinearLayout.addView(next);
        final List<String> subList = new ArrayList<>();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateSubjects();
                Log.d("Checkaba", "Added and complete");
                startActivity(new Intent(subjectList.this,selectSubject.class));
            }

            private void updateSubjects() {
                for (int i = 0; i < N; i++) {
                    TextView e = myLinearLayout.findViewById(i + 1);
                    subList.add(e.getText().toString().trim());
                }
                Constants.teacher.setSubjects(subList);
                for (String subj : Constants.teacher.getSubjects()) {
                    myRef.child(Constants.teacher.getId()).child("Subjects").child(subj).setValue(true);
                    Log.d("Checkaba",subj);
                }
                //subList.clear();
            }
        });


    }
}
