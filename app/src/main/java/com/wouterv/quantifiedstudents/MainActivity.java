package com.wouterv.quantifiedstudents;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ImageView currentyNyxEmoji;
    private TextView currentStatusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        this.currentyNyxEmoji = (ImageView) this.findViewById(R.id.currentNyxEmoji);
        this.currentStatusText = (TextView) this.findViewById(R.id.currentStatusText);

        this.currentyNyxEmoji.setImageResource(R.drawable.happy_512px);
        this.currentStatusText.setText("Your Nyx is very happy! Keep it up!");
    }

    public void navigate(View view) {
        this.startActivity(new Intent(this, OverviewActivity.class));
    }
}
