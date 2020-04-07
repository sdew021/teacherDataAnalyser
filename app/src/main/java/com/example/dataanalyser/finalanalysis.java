package com.example.dataanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class finalanalysis extends AppCompatActivity {
    int classes[] = {15, 13, 17, 20, 18, 16};
    String subjectnames[] = {"os", "pap", "st", "cns", "fs", "dmdw"};
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;
    ArrayList barLabels;


    private FloatingActionButton fab_main, fab1_mail, fab2_share;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;
    TextView aboutUs, download;

    Boolean isOpen = false;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalanalysis);
        setupPieChart();
        barChart = findViewById(R.id.BarChart);
        getEntries();
        barDataSet = new BarDataSet(barEntries, "Bar Data");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barLabels));



        //TODO :: START
        fab_main = findViewById(R.id.fab);
        fab1_mail = findViewById(R.id.fab1);
        fab2_share = findViewById(R.id.fab2);
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
                    fab2_share.startAnimation(fab_close);
                    fab1_mail.startAnimation(fab_close);
                    fab_main.startAnimation(fab_anticlock);
                    fab2_share.setClickable(false);
                    fab1_mail.setClickable(false);
                    isOpen = false;
                } else {
                    aboutUs.setVisibility(View.VISIBLE);
                    download.setVisibility(View.VISIBLE);
                    fab2_share.startAnimation(fab_open);
                    fab1_mail.startAnimation(fab_open);
                    fab_main.startAnimation(fab_clock);
                    fab2_share.setClickable(true);
                    fab1_mail.setClickable(true);
                    isOpen = true;
                }

            }
        });


        fab2_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Downloading...", Toast.LENGTH_SHORT).show();

            }
        });

        fab1_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "About Us", Toast.LENGTH_SHORT).show();

            }
        });
        //TODO :: END

    }

    private void getEntries() {
        barEntries = new ArrayList<>();
        barLabels = new ArrayList<>();
        barEntries.add(new BarEntry(0f, 0));
        barLabels.add("A");
        barEntries.add(new BarEntry(1f, 1));
        barLabels.add("B");
        barEntries.add(new BarEntry(2f, 1));
        barLabels.add("C");
        barEntries.add(new BarEntry(3f, 3));
        barLabels.add("D");
        barEntries.add(new BarEntry(4f, 4));
        barLabels.add("E");
        barEntries.add(new BarEntry(5f, 3));
        barLabels.add("F");
    }


    private void setupPieChart() {
        //pie chart entries
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < classes.length; i++) {
            pieEntries.add(new PieEntry(classes[i], subjectnames[i]));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "analysed data");
        PieData data = new PieData(dataSet);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextSize(30);
        //get the chart
        PieChart chart = (PieChart) findViewById(R.id.pie);
        chart.setData(data);
        chart.invalidate();
        chart.animateY(1000);
    }
}
