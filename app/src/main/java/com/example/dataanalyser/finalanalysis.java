package com.example.dataanalyser;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class finalanalysis extends AppCompatActivity {
    int classes[] = {15, 13, 17, 20, 18, 16};
    String subjectnames[] = {"os", "pap", "st", "cns", "fs", "dmdw"};
    BarChart barChart, barChart1,barChart2,barChart3;
    BarData barData,barData1,barData2,barData3;
    BarDataSet barDataSet,barDataSet1,barDataSet2,barDataSet3;
    ArrayList barEntries,barEntries1,barEntries2,barEntries3;
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

        barDataSet = new BarDataSet(barEntries, "");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.animateY(2000);
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(18f);


        barDataSet1 = new BarDataSet(barEntries1, "");
        barData1 = new BarData(barDataSet1);
        barChart1.setData(barData1);
        barChart2.animateY(2000);
        barDataSet1.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet1.setValueTextColor(Color.BLACK);
        barDataSet1.setValueTextSize(18f);



        barDataSet2 = new BarDataSet(barEntries2, "");
        barData2 = new BarData(barDataSet2);
        barChart2.setData(barData2);
        barChart2.animateY(2000);
        barDataSet2.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet2.setValueTextColor(Color.BLACK);
        barDataSet2.setValueTextSize(18f);


        barDataSet3 = new BarDataSet(barEntries3, "");
        barData3 = new BarData(barDataSet3);
        barChart3.setData(barData3);
        barChart3.animateY(2000);
        barDataSet3.setColors(ColorTemplate.JOYFUL_COLORS);
        barDataSet3.setValueTextColor(Color.BLACK);
        barDataSet3.setValueTextSize(18f);

    }

    private void getEntries() {
        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(2f, 0));
        barEntries.add(new BarEntry(4f, 1));
        barEntries.add(new BarEntry(6f, 1));
        barEntries.add(new BarEntry(8f, 3));
        barEntries.add(new BarEntry(7f, 4));
        barEntries.add(new BarEntry(3f, 3));
    }



    private void getEntries1() {
        barEntries1 = new ArrayList<>();
        barEntries1.add(new BarEntry(2f, 0));
        barEntries1.add(new BarEntry(4f, 1));
        barEntries1.add(new BarEntry(6f, 1));
        barEntries1.add(new BarEntry(8f, 3));
        barEntries1.add(new BarEntry(7f, 4));
        barEntries1.add(new BarEntry(3f, 3));
    }



    private void getEntries2() {
        barEntries2 = new ArrayList<>();
        barEntries2.add(new BarEntry(2f, 0));
        barEntries2.add(new BarEntry(4f, 1));
        barEntries2.add(new BarEntry(6f, 1));
        barEntries2.add(new BarEntry(8f, 3));
        barEntries2.add(new BarEntry(7f, 4));
        barEntries2.add(new BarEntry(3f, 3));
    }




    private void getEntries3() {
        barEntries3 = new ArrayList<>();
        barEntries3.add(new BarEntry(2f, 0));
        barEntries3.add(new BarEntry(4f, 1));
        barEntries3.add(new BarEntry(6f, 1));
        barEntries3.add(new BarEntry(8f, 3));
        barEntries3.add(new BarEntry(7f, 4));
        barEntries3.add(new BarEntry(3f, 3));
    }






    private void setupPieChart(){
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

