package com.example.dataanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
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
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class finalanalysis extends AppCompatActivity {
    int classes[] = {15, 13, 17, 20, 18, 16};
    String subjectnames[] = {"os", "pap", "st", "cns", "fs", "dmdw"};



    private FloatingActionButton fab_main, fab1_about, fab2_download;
    private Animation fab_open, fab_close, fab_clock, fab_anticlock;
    TextView aboutUs, download;

    Button next;

    Boolean isOpen = false;
    BarChart barChart, barChart1,barChart2,barChart3;
    BarData barData,barData1,barData2,barData3;
    BarDataSet barDataSet,barDataSet1,barDataSet2,barDataSet3;
    ArrayList barEntries,barEntries1,barEntries2,barEntries3;
    ArrayList barLabels,barLabels1,barLabels2,barLabels3;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalanalysis);
        setupPieChart();

        barChart = findViewById(R.id.BarChart);
        barChart1 = findViewById(R.id.Markschart1);
        barChart2 = findViewById(R.id.Markschart2);
        barChart3 = findViewById(R.id.Markschart3);
        getEntries();

        getEntries1();
        getEntries2();
        getEntries3();



        barDataSet = new BarDataSet(barEntries, "Classes Taken");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.animateY(1000);
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);
        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barLabels));
        barChart.getXAxis().setTextSize(18f);
        barChart.getXAxis().setGranularity(1f);
        barChart.getAxisLeft().setGranularity(1f);
        barChart.getAxisRight().setGranularity(1f);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.invalidate();



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



        barDataSet1 = new BarDataSet(barEntries1, "Marks Range 0-10");
        barData1 = new BarData(barDataSet1);
        barChart1.setData(barData1);
        barChart1.animateY(1000);
        barDataSet1.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet1.setValueTextColor(Color.BLACK);
        barDataSet1.setValueTextSize(18f);
        barChart1.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barLabels1));
        barChart1.getXAxis().setTextSize(18f);
        barChart1.getDescription().setEnabled(false);
        barChart1.getLegend().setEnabled(false);
        barChart1.getDescription().setEnabled(false);
        barChart1.getLegend().setEnabled(false);
        barChart1.invalidate();



        barDataSet2 = new BarDataSet(barEntries2, "Marks Range 10-20");
        barData2 = new BarData(barDataSet2);
        barChart2.setData(barData2);
        barChart2.animateY(1000);
        barDataSet2.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet2.setValueTextColor(Color.BLACK);
        barDataSet2.setValueTextSize(18f);
        barChart2.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barLabels2));
        barChart2.getXAxis().setTextSize(18f);
        barChart2.getDescription().setEnabled(false);
        barChart2.getLegend().setEnabled(false);
        barChart2.getDescription().setEnabled(false);
        barChart2.getLegend().setEnabled(false);
        barChart2.invalidate();



        barDataSet3 = new BarDataSet(barEntries3, "Marks Range 20-30");
        barData3 = new BarData(barDataSet3);
        barChart3.setData(barData3);
        barChart3.animateY(1000);
        barDataSet3.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet3.setValueTextColor(Color.BLACK);
        barDataSet3.setValueTextSize(18f);
        barChart3.getXAxis().setValueFormatter(new IndexAxisValueFormatter(barLabels3));
        barChart3.getXAxis().setTextSize(18f);
        barChart3.getDescription().setEnabled(false);
        barChart3.getLegend().setEnabled(false);
        barChart3.getDescription().setEnabled(false);
        barChart3.getLegend().setEnabled(false);
        barChart3.invalidate();

        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(finalanalysis.this,efficiencyActivity.class));
            }
        });
    }

    private void downloadPdf() {

    }

    private void getEntries() {
        barEntries = new ArrayList<>();
        barLabels = new ArrayList<>();
        for (int i = 0; i < Constants.teacher.getnOS(); i++) {
            barEntries.add(new BarEntry(i, SubjectsUtil.subjectAnalysis.get(i).getClassTaken()));
            barLabels.add(SubjectsUtil.subjectAnalysis.get(i).getSubName());
        }
    }



    private void getEntries1() {

        barEntries1 = new ArrayList<>();
        barLabels1 = new ArrayList<>();
        for (int i = 0; i < Constants.teacher.getnOS(); i++) {
            barEntries1.add(new BarEntry(i, SubjectsUtil.subjectAnalysis.get(i).getMarks1()));
            barLabels1.add(SubjectsUtil.subjectAnalysis.get(i).getSubName());
        }
    }



    private void getEntries2() {
        barEntries2 = new ArrayList<>();
        barLabels2 = new ArrayList<>();
        for (int i = 0; i < Constants.teacher.getnOS(); i++) {
            barEntries2.add(new BarEntry(i, SubjectsUtil.subjectAnalysis.get(i).getMarks2()));
            barLabels2.add(SubjectsUtil.subjectAnalysis.get(i).getSubName());
        }
    }




    private void getEntries3() {
        barEntries3 = new ArrayList<>();
        barLabels3 = new ArrayList<>();
        for (int i = 0; i < Constants.teacher.getnOS(); i++) {
            barEntries3.add(new BarEntry(i, SubjectsUtil.subjectAnalysis.get(i).getMarks3()));
            barLabels3.add(SubjectsUtil.subjectAnalysis.get(i).getSubName());
        }
    }






    private void setupPieChart(){
        //pie chart entries
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < Constants.teacher.getnOS(); i++) {
            pieEntries.add(new PieEntry(SubjectsUtil.subjectAnalysis.get(i).getClassTotal(), SubjectsUtil.subjectAnalysis.get(i).getSubName()));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "analysed data");
        PieData data = new PieData(dataSet);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextSize(30);
        //get the chart
        PieChart chart = (PieChart) findViewById(R.id.pie);
        chart.setData(data);
        chart.getDescription().setEnabled(false);
        chart.invalidate();
        chart.animateY(5000);
    }
}