package com.wearable.whatsfordinner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {
    private Dish item;
    private TextView textView3;
    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent intent = getIntent();
        String name= intent.getStringExtra("name");
        String[] ingredientlist = intent.getStringArrayExtra("ingredientslist");
        double[] amountingredientlist=intent.getDoubleArrayExtra("amountingredientslist");
        String directions= intent.getStringExtra("directions");
        item= new Dish(name,ingredientlist,amountingredientlist);
        item.setDirections(directions);
        textView1 = (TextView)findViewById(R.id.textView21);
        textView2 = (TextView)findViewById(R.id.textView22);
        textView3= (TextView)findViewById(R.id.textView3);
        textView1.setText(name);
        textView2.setText("Ingredients List: \n");
        for(int a=0;a<10;a++){
            double amount = amountingredientlist[a];
            String ingredient= ingredientlist[a];
            if(amount>0 && !ingredient.contains("Choose")){
                textView2.append(""+amount+" "+ ingredient+"\n" );
            }


        }
        textView3.setText("Directions: \n"+directions);


    }

    public void goBack(View view){

        Intent returnintent = new Intent(DisplayActivity.this, RecipesActivity.class);
        returnintent.putExtra("name",item.getName());
        returnintent.putExtra("ingredientslist", item.getIngredients());
        returnintent.putExtra("ingredientamounts",item.getIngredientsAmount());
        returnintent.putExtra("directions", item.getDirections());
        setResult(NewDishActivity.RESULT_OK,returnintent);
        finish();

    }
    public void goEdit(View view){
        Intent intent = new Intent(DisplayActivity.this, EditActivity.class);
        intent.putExtra("name",item.getName());
        intent.putExtra("ingredients",item.getIngredients());
        intent.putExtra("amountingredients",item.getIngredientsAmount());
        intent.putExtra("directions",item.getDirections());
        startActivityForResult(intent,4444);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==4444){
            if(resultCode== Activity.RESULT_OK){
                String name= data.getStringExtra("name");
                String[] ingredients= data.getStringArrayExtra("ingredientslist");
                double[] amountingredients=data.getDoubleArrayExtra("amountingredients");
                String directions= data.getStringExtra("directions");
                item= new Dish(name, ingredients,amountingredients);
                item.setDirections(directions);
                textView3.setText(directions);
                textView1.setText(name);
                textView2.setText("Ingredients List: \n");
                for(int a=0;a<10;a++){
                    double amount = amountingredients[a];
                    String ingredient= ingredients[a];
                    if(amount>0 && !ingredient.contains("Choose")){
                        textView2.append(""+amount+" "+ ingredient+"\n" );
                    }


                }



            }


        }



    }
}
