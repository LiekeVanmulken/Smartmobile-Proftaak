package com.wouterv.quantifiedstudents;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {
    LineChart lineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        makelineChart();
    }
    public void makelineChart(){
        lineChart= (LineChart) findViewById(R.id.lineChart);

        ArrayList<Entry> entries = new ArrayList<>();
        float percentage = 90f; //calculate this later

        entries.add(new Entry(percentage, 0));
        entries.add(new Entry(100 - percentage, 1));

        LineDataSet dataset = new LineDataSet(entries,"");
        dataset.setColors(new int[]{getResources().getColor(R.color.Orange), 0});


        ArrayList<String> labels = new ArrayList<String>();
        labels.add("");
        labels.add("");
        LineData data = new LineData(labels, dataset);
        lineChart.setData(data);
        lineChart.animateY(1000);
    }

    public void goToActivityChart(View view) {
        Intent myIntent = new Intent(this, ChartActivity.class);
        startActivity(myIntent);
    }
}
