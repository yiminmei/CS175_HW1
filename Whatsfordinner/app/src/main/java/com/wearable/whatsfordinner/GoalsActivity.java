package com.wearable.whatsfordinner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

public class GoalsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
    }
    public void goMeal(View view){
        double[] results= new double[4];
        String a = ((EditText)findViewById(R.id.editText)).getText().toString();
        results[0]= Double.parseDouble(a);

        String b = ((EditText)findViewById(R.id.editText12)).getText().toString();
        results[1]= Double.parseDouble(b);

        String c = ((EditText)findViewById(R.id.editText13)).getText().toString();
        results[2]= Double.parseDouble(c);
        String d = ((EditText)findViewById(R.id.editText14)).getText().toString();
        results[3]= Double.parseDouble(d);

        Intent intent= new Intent(GoalsActivity.this, MealsActivity.class);
        intent.putExtra("results",results);
        setResult(RESULT_OK,intent);


        finish();
    }

}
