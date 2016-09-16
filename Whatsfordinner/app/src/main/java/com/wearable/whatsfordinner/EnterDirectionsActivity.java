package com.wearable.whatsfordinner;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class EnterDirectionsActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_directions);
        Intent intent= getIntent();
        textView = (TextView)findViewById(R.id.editText41);
        String directions= intent.getStringExtra("directions");
        if(!directions.equals("Directions to be entered"))
            textView.setText(directions);


    }
    public void goBackto(View view){
        Intent returnintent = new Intent(EnterDirectionsActivity.this, EditActivity.class);
        returnintent.putExtra("directions", textView.getText().toString());
        setResult(Activity.RESULT_OK, returnintent);
        finish();

    }

}
