package com.wouterv.quantifiedstudents.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.wouterv.quantifiedstudents.R;
import com.wouterv.quantifiedstudents.Volley.VolleyService;
import com.wouterv.quantifiedstudents.canvasmodels.Config;
import com.wouterv.quantifiedstudents.canvasmodels.Course;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvTesting = (TextView) findViewById(R.id.tvTesting);
        String testData = "";
        List<Course> courseList = Config.getInstance().getCourses();
        Config c = Config.getInstance();

        for (int i = 0; i < courseList.size(); i++) {
            testData += courseList.get(i).getName() + "\n";
//            if (courseList.get(i).getAssignments() != null) {
//                while(courseList.get(i).getAssignments()==null) {
//                    try {
//                        Thread.sleep(100);
//                        Config.getInstance().getCourses();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
            if(courseList.get(i).getAssignments()!=null) {
                for (int j = 0; j < c.getCourses().get(i).getAssignments().size(); j++) {
                    try {
                        Log.d("i:" + i + "; j:" + j, c.getCourses().get(i).getAssignments().get(j).getName());
                        testData += "\t\t" + c.getCourses().get(i).getAssignments().get(j).getName() + "\n";
                        if(c.getCourses().get(i).getAssignments().get(j).getSubmission()!=null)
                        testData += "\t\t\t\t" +c.getCourses().get(i).getAssignments().get(j).getPointsPossible()+ "/"+ c.getCourses().get(i).getAssignments().get(j).getSubmission().getGrade() +"/"+ c.getCourses().get(i).getAssignments().get(j).getSubmission().getScore() + "\n";
                    }catch (NullPointerException n){
                    }
                }
            }

//            }else Log.d(courseList.get(i).getName(),"isnull");
        }
        tvTesting.setText(testData);


        makePieChart();
//        JsonHandler j = new JsonHandler();
//        j.getJSON("https://fhict.instructure.com/api/v1/courses.json?acces_token="+ Config.canvasAPIKey,this, JsonHandler.Mode.Courses);
    }

    public void makePieChart() {
        pieChart = (PieChart) findViewById(R.id.scoreChart);

        ArrayList<Entry> entries = new ArrayList<>();
        float percentage = 82f; //calculate this later

        entries.add(new Entry(percentage, 0));
        entries.add(new Entry(100 - percentage, 1));

        PieDataSet dataset = new PieDataSet(entries, "");
        dataset.setColors(new int[]{getResources().getColor(R.color.Orange), 0});


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
