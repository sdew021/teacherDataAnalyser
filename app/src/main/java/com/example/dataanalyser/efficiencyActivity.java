package com.example.dataanalyser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class efficiencyActivity extends AppCompatActivity {

    private FloatingActionButton fab_main, fab1_about, fab2_download;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;
    TextView aboutUs, download;
    Boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_efficiency);

        //TODO :: START
        fab_main = findViewById(R.id.fab);
        fab1_about = findViewById(R.id.fab1);
        fab2_download = findViewById(R.id.fab2);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);

        aboutUs = (TextView) findViewById(R.id.aboutUs);
        download = (TextView) findViewById(R.id.download);

        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOpen) {

                    aboutUs.setVisibility(View.INVISIBLE);
                    download.setVisibility(View.INVISIBLE);
                    fab2_download.startAnimation(fab_close);
                    fab1_about.startAnimation(fab_close);
                    fab_main.startAnimation(fab_anticlock);
                    fab2_download.setClickable(false);
                    fab1_about.setClickable(false);
                    isOpen = false;
                } else {
                    aboutUs.setVisibility(View.VISIBLE);
                    download.setVisibility(View.VISIBLE);
                    fab2_download.startAnimation(fab_open);
                    fab1_about.startAnimation(fab_open);
                    fab_main.startAnimation(fab_clock);
                    fab2_download.setClickable(true);
                    fab1_about.setClickable(true);
                    isOpen = true;
                }

            }
        });


        fab2_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Downloading...", Toast.LENGTH_SHORT).show();
                downloadPdf();
            }
        });

        fab1_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "About Us", Toast.LENGTH_SHORT).show();

            }
        });
        //TODO :: END


        //TODO :: Efficiency Start
        final TextView[] subj = new TextView[Constants.teacher.getnOS()];
        final TextView[] cta = new TextView[Constants.teacher.getnOS()];
        final TextView[] cto = new TextView[Constants.teacher.getnOS()];
        final TextView[] eff = new TextView[Constants.teacher.getnOS()];
        final LinearLayout myLinearLayout = findViewById(R.id.efficiencyLayout);

        int i = 0;

        for (String sub : Constants.teacher.getSubjects()) {
            if (i == Constants.teacher.getnOS()) {
                i = 0;
            }
            float e, n1, n2;
            final TextView su = new TextView(this);
            final TextView ta = new TextView(this);
            final TextView to = new TextView(this);
            final TextView ef = new TextView(this);

            View v = new View(this);
            v.setLayoutParams(new LinearLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    5
            ));
            v.setBackgroundColor(Color.parseColor("#B3B3B3"));


            su.setText(SubjectsUtil.subjectAnalysis.get(i).getSubName());
            su.setTextColor(Color.BLACK);
            su.setTextSize(25f);
            su.setPaintFlags(su.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
            su.setAllCaps(true);
            ta.setText("Classes Taken : " + SubjectsUtil.subjectAnalysis.get(i).getClassTaken());
            ta.setTextColor(Color.BLACK);
            ta.setTextSize(18f);
            n1 = (SubjectsUtil.subjectAnalysis.get(i).getClassTaken());
            to.setText("Classes Total : " + SubjectsUtil.subjectAnalysis.get(i).getClassTotal());
            to.setTextColor(Color.BLACK);
            to.setTextSize(18f);
            n2 = (SubjectsUtil.subjectAnalysis.get(i).getClassTotal());
            ef.setTextColor(Color.BLACK);
            e = (n1 / n2) * 100.0f;
            ef.setText("Efficiency : " + e);
            ef.setTextSize(18f);
            myLinearLayout.addView(su);
            myLinearLayout.addView(ta);
            myLinearLayout.addView(to);
            myLinearLayout.addView(ef);
            myLinearLayout.addView(v);
            subj[i] = su;
            cta[i] = ta;
            cto[i] = to;
            eff[i] = ef;
            //subButton[i].setId(i + 1);

            i++;
        }

        //TODO :: Efficiency End

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMarginStart(150);


        final TextView ACT = new TextView(this);
        ACT.setText("ACTIVITIES");
        //ACT.setLayoutParams(params);
        ACT.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
        ACT.setGravity(View.TEXT_ALIGNMENT_CENTER);
        ACT.setAllCaps(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ACT.setTextAppearance(R.style.TextAppearance_AppCompat_Large);
        }
        ACT.setTextColor(Color.parseColor("#0E375F"));
        ACT.setHighlightColor(Color.parseColor("#E7E4E4"));
        ACT.setTextSize(35);
        ACT.setTypeface(null, Typeface.BOLD);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child(Constants.teacher.getId());

        myRef.child("actCnt").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Constants.actCount = dataSnapshot.getValue(Integer.class);
                if (Constants.actCount != 0) {

                    myLinearLayout.addView(ACT);
                    updateActivities();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void updateActivities() {
        final TextView[] titl = new TextView[Constants.actCount];
        final TextView[] dat = new TextView[Constants.actCount];
        final TextView[] subj = new TextView[Constants.actCount];
        final TextView[] desc = new TextView[Constants.actCount];
        final LinearLayout myLinearLayout = findViewById(R.id.efficiencyLayout);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child(Constants.teacher.getId());

        for (int i = 0; i < Constants.actCount; i++) {
            final int finalI = i;
            myRef.child("Activity").child("a" + i).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Activity a = dataSnapshot.getValue(Activity.class);

                    View v = new View(getApplicationContext());
                    v.setLayoutParams(new LinearLayout.LayoutParams(
                            RelativeLayout.LayoutParams.MATCH_PARENT,
                            5
                    ));
                    v.setBackgroundColor(Color.parseColor("#B3B3B3"));

                    final TextView ti = new TextView(getApplicationContext());
                    final TextView da = new TextView(getApplicationContext());
                    final TextView su = new TextView(getApplicationContext());
                    final TextView de = new TextView(getApplicationContext());

                    ti.setText(a.getTitle());
                    ti.setTextColor(Color.BLACK);
                    ti.setTextSize(25f);
                    ti.setPaintFlags(su.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    ti.setAllCaps(true);


                    da.setText(a.getDate());
                    da.setTextColor(Color.BLACK);
                    da.setTextSize(18f);

                    su.setTextColor(Color.BLACK);
                    su.setTextSize(18f);
                    su.setText(a.getSubject());

                    de.setText(a.getDesc());
                    de.setTextColor(Color.BLACK);
                    de.setTextSize(18f);


                    myLinearLayout.addView(ti);
                    myLinearLayout.addView(da);
                    myLinearLayout.addView(su);
                    myLinearLayout.addView(de);
                    myLinearLayout.addView(v);
                    subj[finalI] = su;
                    desc[finalI] = de;
                    dat[finalI] = da;
                    desc[finalI] = de;

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }

    private void downloadPdf() {
    }
}

