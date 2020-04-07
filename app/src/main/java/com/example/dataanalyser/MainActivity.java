package com.example.dataanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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
                checkId(ephone.getText().toString(),intent);
            }
        });

<<<<<<< HEAD

        bregister.setOnClickListener(new View.OnClickListener() {
=======
    }

    private void checkId(final String id, final Intent intent) {
        if(id.isEmpty() || id.trim().equals("") ){
            Toast.makeText(this, "Enter Some Value", Toast.LENGTH_SHORT).show();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
>>>>>>> 204ae1c981758cf2580be6a7c1eae8ea625f3e21
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    Log.e("Child",""+ childDataSnapshot.getKey()); //displays the key for the node
                    //Log.e("Child1",""+ childDataSnapshot.child("login").getValue());   //gives the value for given keyname
                    if(id.equals(childDataSnapshot.getKey())){
                        Log.e("child n","Changed");
                        Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                        Constants.teacher.setId(id);
                        startActivity(intent);
                        return;
                    }
                }
                Toast.makeText(MainActivity.this, "Id not registered", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }
        });

    }

}
