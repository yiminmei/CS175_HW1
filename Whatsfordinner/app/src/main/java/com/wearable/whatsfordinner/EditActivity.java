package com.wearable.whatsfordinner;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
    private Spinner[] spinners;
    private EditText[] editTexts;
    private EditText titleText;
    private ArrayList<String> allIngredients;
    private Dish item;
    private TextView directionstext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initialize();
        editTexts= new EditText[10];
        spinners= new Spinner[10];
        titleText=(EditText) findViewById(R.id.editText31);

        int index1 = R.id.editText21;
        for(int a=0;a<10;a++){
            editTexts[a]=(EditText)findViewById(index1);
            index1++;
        }
        int index2= R.id.spinner21;
        for(int b=0; b<10; b++){
            spinners[b]=(Spinner)findViewById(index2);
            index2++;
        }
        Intent intent= getIntent();
        String name = intent.getStringExtra("name");
        String[] ingredients = intent.getStringArrayExtra("ingredients");
        double[] amountingredients= intent.getDoubleArrayExtra("amountingredients");
        String directions= intent.getStringExtra("directions");
        item= new Dish( name,ingredients,amountingredients);
        item.setDirections(directions);
        titleText.setText(name);
        for(int a=0 ;a<10 ;a++){
            editTexts[a].setText(""+amountingredients[a]);
        }

        String[] allIngredientsarray= new String[16];
        for(int a= 0 ; a<16 ; a++){
            allIngredientsarray[a]=allIngredients.get(a);
        }

        int index3 = R.id.spinner21;
        for(int c = 0;c<10;c++){
            Spinner spinner = (Spinner)findViewById(index3);
            ArrayAdapter<String> adapter=
                    new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,allIngredientsarray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            index3++;
        }

        for(int a=0; a<10; a++){
            int position=-1;
            String item = ingredients[a];
            for( int b =0; b<16  ;b++){

                if(item.contains(allIngredientsarray[b])){
                    position=b;
                }
            }
            spinners[a].setSelection(position);

        }
    }
    public void initialize(){
        allIngredients=new ArrayList<>();
        allIngredients.add("Choose");   //0
        allIngredients.add("Fish (lbs)");  //1
        allIngredients.add("chicken (lbs)");  //2
        allIngredients.add("pork (lbs)");  //3
        allIngredients.add("beef (lbs)");  //4
        allIngredients.add("shrimp (lbs)");  //5
        allIngredients.add("duck (lbs)");   //6
        allIngredients.add("garlic (cloves)"); //7
        allIngredients.add("onions (cups) ");  //8
        allIngredients.add("rice (cup)");  //9
        allIngredients.add("green pepper(cups)"); //10
        allIngredients.add("wasabi (teaspoons)"); //11
        allIngredients.add("ginger (cups)");  //12
        allIngredients.add("lettuce (lb)"); //13
        allIngredients.add("brocolli (lb)"); //14
        allIngredients.add("salt (teaspoon)"); //15

    }
    public void goNext(View view){
        Intent intent = new Intent(EditActivity.this, EnterDirectionsActivity.class);
        intent.putExtra("directions",item.getDirections());
        startActivityForResult(intent,5555 );

    }
    public void goDone(View view){
        double[] ingredientamount= new double[10];
        String[] ingredientsList= new String[10];
        String name = titleText.getText().toString();

        for(int a=0 ;a<10;a++){
            ingredientamount[a]= Double.valueOf(editTexts[a].getText().toString() );
            ingredientsList[a]= spinners[a].getSelectedItem().toString();

        }
        Intent returnintent = new Intent(EditActivity.this, DisplayActivity.class);
        returnintent.putExtra("name", name);
        returnintent.putExtra("ingredientslist", ingredientsList);
        returnintent.putExtra("amountingredients", ingredientamount);
        returnintent.putExtra("directions",item.getDirections());
        setResult(Activity.RESULT_OK,returnintent);
        finish();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==5555){
            if(resultCode== Activity.RESULT_OK){
                String a = data.getStringExtra("directions");
                item.setDirections(a);


            }
        }


    }
}
