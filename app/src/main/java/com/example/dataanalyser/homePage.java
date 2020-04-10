package com.example.dataanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class homePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Button next = findViewById(R.id.next);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        final EditText sub = findViewById(R.id.sub);
        final EditText branch = findViewById(R.id.branch);
        final EditText sem = findViewById(R.id.sem);

        checkData(myRef, sub, branch, sem, next);


    }

    private void checkData(final DatabaseReference myRef, final EditText sub, final EditText branch, final EditText sem, final Button next) {
        myRef.child(Constants.teacher.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("error", "" + dataSnapshot.getChildrenCount());
                if (dataSnapshot.getChildrenCount() == 0) {
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final Intent intent = new Intent(homePage.this, subjectList.class);

                            if (!(sub.getText().toString()).isEmpty() && !(branch.getText().toString()).isEmpty() && !(sem.getText().toString()).isEmpty()) {
                                Constants.teacher.setnOS(Integer.parseInt((sub.getText().toString())));
                                Constants.teacher.setSem((sem.getText().toString()));
                                Constants.teacher.setBranch((branch.getText().toString()));
                                myRef.child(Constants.teacher.getId()).setValue(Constants.teacher).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        startActivity(intent);
                                    }
                                });

                            } else {
                                if ((sub.getText().toString()).isEmpty()) {
                                    sub.setError("Enter some value");
                                }
                                if ((branch.getText().toString()).isEmpty()) {
                                    branch.setError("Enter some value");
                                }
                                if ((sem.getText().toString()).isEmpty()) {
                                    sem.setError("Enter some value");
                                }
                            }
                        }
                    });
                } else {
                    for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                        Log.e("logging", "" + childDataSnapshot.getKey()); //displays the key for the node
                        Log.e("logging", "" + childDataSnapshot.getValue());   //gives the value for given keyname
                        switch (childDataSnapshot.getKey()) {
                            case "branch":
                                branch.setText("Branch\t: " + childDataSnapshot.getValue().toString());
                                branch.setEnabled(false);
                                Constants.teacher.setBranch((childDataSnapshot.getValue().toString()));
                                break;
                            case "sem":
                                sem.setText("Semester\t: " + childDataSnapshot.getValue().toString());
                                sem.setEnabled(false);
                                Constants.teacher.setSem(childDataSnapshot.getValue().toString());

                                break;
                            case "nOS":
                                sub.setText("No. of subjects\t: " + childDataSnapshot.getValue().toString());
                                sub.setEnabled(false);
                                Constants.teacher.setnOS(Integer.parseInt((childDataSnapshot.getValue().toString())));
                                break;
                            case "Subjects":
                                List<String> l = new ArrayList<>();
                                for (DataSnapshot snap : childDataSnapshot.getChildren()) {
                                    l.add(snap.getKey().toString());
                                }
                                Log.e("logging", "" + l.size()); //displays the key for the node
                                Constants.teacher.setSubjects(l);
                                break;
                            default:
                                Log.e("logging", "" + childDataSnapshot.getKey()); //displays the key for the node
                                Log.e("logging", "" + childDataSnapshot.getValue());   //gives the value for given keyname
                        }
                        if (Constants.teacher.getSubjects() == null) {
                            next.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(homePage.this, subjectList.class));
                                }
                            });
                        } else {
                            next.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(new Intent(homePage.this, selectSubject.class));
                                }
                            });
                        }


                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", "" + databaseError.getMessage()); //displays the key for the node
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