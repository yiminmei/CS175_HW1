package com.wearable.whatsfordinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NewDishActivity extends AppCompatActivity {
    private Button pictureButton;
    private Button nextButton;
    private EditText nameText1;
    private ArrayList<String> allingredients;
    private EditText[] editTexts;
    private Spinner[] spinners;
    private String msg="";
    private ArrayList<String> namestaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dish);
        initialize();

        nameText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    String name =nameText1.getText().toString();
                    int lengthofnames= namestaken.size();
                    for(int a =0;a<lengthofnames;a++ ){
                        if(namestaken.get(a).contains(name)){
                            name+=  " 1";
                            Toast.makeText(NewDishActivity.this, "Name has been taken", Toast.LENGTH_SHORT).show();
                        }
                    }
                    nameText1.setText(name);

                }



            }
        });


    }
    public void initialize(){
        pictureButton=(Button) findViewById(R.id.button5);
        nextButton=(Button)findViewById(R.id.button7);
        nameText1=(EditText)findViewById(R.id.editText1);
        editTexts= new EditText[10];
        spinners= new Spinner[10];

        int index1 = R.id.editText2;
        for(int a=0;a<10;a++){
            editTexts[a]=(EditText)findViewById(index1);
            index1++;
        }
        int index2= R.id.spinner2;
        for(int b=0; b<10; b++){
            spinners[b]=(Spinner)findViewById(index2);
            index2++;
        }
        Intent intent= getIntent();
        allingredients = intent.getStringArrayListExtra("Ingredients");
        namestaken=intent.getStringArrayListExtra("allnames");

        int length = allingredients.size();
        String[] ingredients = new String[length];

        for(int a=0;a<length;a++ ){
            ingredients[a] = allingredients.get(a);
        }
        int index3 = R.id.spinner2;
        for(int c = 0;c<10;c++){
            Spinner spinner = (Spinner)findViewById(index3);
            ArrayAdapter<String> adapter=
                    new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,ingredients);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            index3++;
        }

    }

    public void goNext(View view){

        double[] ingredientamount= new double[10];
        String[] ingredientsList= new String[10];
        String name = nameText1.getText().toString();
        if(name.equals(""))
            name="Nameless Item";

        for(int a=0 ;a<10;a++){
            ingredientamount[a]= Double.valueOf(editTexts[a].getText().toString() );
            ingredientsList[a]= spinners[a].getSelectedItem().toString();

        }
        Intent returnintent = new Intent(this, MenuActivity.class);

        returnintent.putExtra("name", name);
        returnintent.putExtra("ingredientslist", ingredientsList);
        returnintent.putExtra("ingredientamounts", ingredientamount);

        setResult(NewDishActivity.RESULT_OK,returnintent);
        finish();

    }
    public void getPicture(View view){
        //to be finished later

    }



}
