package com.wearable.whatsfordinner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Math;

public class SummaryActivity extends AppCompatActivity {
    private String[] names;
    private double[] calories;
    private double[] carbohydrates;
    private double[] sugar;
    private double[] sodium;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Intent intent= getIntent();
        String day = intent.getStringExtra("day");
        String a = intent.getStringExtra("items");
        names = a.split(",");
        calories= new double[names.length];
        carbohydrates= new double[names.length];
        sugar= new double[names.length];
        sodium= new double[names.length];

        for(int c=0;c<names.length ;c++ ){
            calories[c] = Math.round(Math.random()*400)+500;
            carbohydrates[c]=Math.round(Math.random()*50) +10;
            sugar[c]= Math.round(Math.random()*30)  +20 ;
            sodium[c]= Math.round(Math.random()*30) +20;
        }
        TextView textview = (TextView)findViewById(R.id.textView78);
        textview.setText(day+"\n\n");
        for(int d= 0 ;d<names.length ;d++){
            textview.append("      "+names[d]+":\n");
            textview.append("           "+calories[d]+" calories\n           " + carbohydrates[d]+ " g carbohydrates \n" );
            textview.append("           "+sugar[d]+ " g sugar \n           "+ sodium[d]+ " g sodium \n" );

        }
        int totalcalorie=0;
        int totalcarbo=0;
        int totalsugar=0;
        int totalsodium=0;
        for(int e= 0 ; e<names.length;e++){
            totalcalorie+=calories[e];
            totalcarbo+= carbohydrates[e];
            totalsugar+=sugar[e];
            totalsodium+=sodium[e];

        }

        textview.append("\nNutrition for your day:\n");
        textview.append("    "+ totalcalorie+" calories\n    " + totalcarbo + "g carbohydrates \n    "+
                    totalsugar+ "g sugar \n    " +totalsodium+ "g sodium");





    }
    public void gotoMeals(View view){
        finish();

    }


}
