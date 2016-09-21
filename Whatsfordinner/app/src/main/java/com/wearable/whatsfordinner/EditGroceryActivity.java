package com.wearable.whatsfordinner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditGroceryActivity extends AppCompatActivity {
    private String ingredient;
    private TextView textView1;
    private int amount;
    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grocery);
        Intent intent = getIntent();
        ingredient= intent.getStringExtra("info");
        textView1 = (TextView) findViewById(R.id.textView7);
        amount=Integer.parseInt(  ingredient.substring(0, ingredient.indexOf(" "))  );
        type=ingredient.substring(ingredient.indexOf(" "), ingredient.length());
        textView1.setText(""+amount+  " " + type);


    }
    public void add(View view){
        amount++;
        textView1.setText(""+amount + " "+ type  );

    }
    public void minus(View view){
        amount--;
        if(amount<0) amount=0;
        textView1.setText(""+amount + " "+ type );

    }
    public void done(View view){
        Intent intent = new Intent(EditGroceryActivity.this, GroceryActivity.class);
        intent.putExtra("info",""+amount+" "+type);
        setResult(EditGroceryActivity.RESULT_OK, intent);
        finish();

    }

}
