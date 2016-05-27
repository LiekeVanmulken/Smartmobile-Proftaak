package com.wouterv.quantifiedstudents;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makePieChart();
    }
    public void makePieChart(){
        pieChart = (PieChart) findViewById(R.id.scoreChart);

        ArrayList<Entry> entries = new ArrayList<>();
        float percentage = 82f; //calculate this later

        entries.add(new Entry(percentage, 0));
        entries.add(new Entry(100-percentage, 1));

        PieDataSet dataset = new PieDataSet(entries,"");
        dataset.setColors(new int[]{getResources().getColor(R.color.Orange),0});


        ArrayList<String> labels = new ArrayList<String>();
        labels.add("");
        labels.add("");
        PieData data = new PieData(labels, dataset);
        pieChart.setData(data);
        pieChart.setCenterText("90%");
        pieChart.setCenterTextSize(50);
        pieChart.animateY(1000);
        pieChart.getData().setValueTextColor(Color.TRANSPARENT);
        pieChart.setDescription("");    // Hide the description
        pieChart.getLegend().setEnabled(false);   // Hide the legend
        pieChart.setClickable(false);
    }

    public void goToActivityChart(View view) {
        Intent myIntent = new Intent(this, ChartActivity.class);
        startActivity(myIntent);
    }
}
