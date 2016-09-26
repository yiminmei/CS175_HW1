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

import org.w3c.dom.Text;

public class WeekSummaryActivity extends AppCompatActivity {
    private String[] names;
    private double[] calories;
    private double[] carbohydrates;
    private double[] sugar;
    private double[] sodium;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_summary);
        Intent intent = getIntent();
        double[] weeklygoals= intent.getDoubleArrayExtra("goals");
        String everything = intent.getStringExtra("everything");
        names = everything.split(",");
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

        TextView textview = (TextView)findViewById(R.id.textview66);
        textview.setText("Week:\n");
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
        boolean meet1=(totalcalorie>weeklygoals[0]);
        boolean meet2=(totalcarbo>weeklygoals[1]);
        boolean meet3=(totalsugar>weeklygoals[2]);
        boolean meet4=(totalsodium>weeklygoals[3]);
        String text= "\nMy Goals: \n";
        text+="Calories: \n             " + totalcalorie + " >= " + weeklygoals[0] + " ? " + meet1+"\n";
        text+="CarboHydrates:\n             " + totalcarbo + ">=" +weeklygoals[1] + " ? "+ meet2 + "\n";
        text+="Sugar: \n             " + totalsugar + " >= "+ weeklygoals[2]+ "? " +meet3 + "\n";
        text+="Sodium: \n             "+ totalsodium + ">=" + weeklygoals[3]+ "? " + meet4+ "\n";
        textview.append(text);

    }
    public void goBack2(View view){
        finish();


    }

}
