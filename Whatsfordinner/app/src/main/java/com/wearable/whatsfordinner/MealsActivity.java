package com.wearable.whatsfordinner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.Toast;

public class MealsActivity extends AppCompatActivity {
    private ArrayList<String> selecteddishes;
    private ArrayList<Dish> dishes;
    private TextView[] textViews;
    private Button[] buttons;
    private int location= -1;
    private double[] weeklygoals;
    private boolean weeklygoalsset=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
        Intent intent= getIntent();
        selecteddishes= new ArrayList<String>();
        textViews= new TextView[14];
        buttons= new Button[14];
        weeklygoals=new double[4];
        dishes = new ArrayList<>();

        int length = intent.getIntExtra("selectedlength", 0);
        for (int a = 0; a < length; a++) {
            String name = intent.getStringExtra("name" + a);
            String[] ingredientslist = intent.getStringArrayExtra("ingredientslist" + a);
            double[] amountofingredients = intent.getDoubleArrayExtra("amountofingredients" + a);
            dishes.add( new Dish(name,ingredientslist, amountofingredients)  );
            selecteddishes.add(name);
        }
        int y= R.id.textview31;

        for(int a= 0 ; a<14;a++){
            textViews[a]= (TextView) findViewById(y);
            y++;
        }
        int x = R.id.button51;

        for(int a=0 ;a<14 ;a++ ){
            buttons[a]=(Button)findViewById(x);
            x++;

            if(a%2==0) {
                buttons[a].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MealsActivity.this, AddActivity.class);
                        location = Integer.parseInt(v.getContentDescription().toString());
                        intent.putExtra("selecteddishes", selecteddishes);
                        startActivityForResult(intent, 1234);

                    }

                });
            }else{
                buttons[a].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            int a = Integer.parseInt(v.getContentDescription().toString());

                            if(!textViews[a-1].getText().toString().equals("Eating Out")) {
                                Intent intent = new Intent(MealsActivity.this, SummaryActivity.class);
                                intent.putExtra("items", textViews[a - 1].getText().toString());
                                intent.putExtra("day", textViews[a-2].getText().toString());
                                startActivity(intent);
                            }

                    }
                });

            }

        }

        String[] messages= intent.getStringArrayExtra("SAVE");
        boolean clickedbefore = intent.getBooleanExtra("clickedbefore",false);
        if(clickedbefore==true) {
            for (int a = 0; a < 14; a++) {
                textViews[a].setText(messages[a]);
            }

        }


    }
    public void backtoMenu(View view){
        Intent intent= new Intent(MealsActivity.this, MenuActivity.class);
        String[] saves= new String[14];
        for(int a=0 ; a<14;a++){
            saves[a]= textViews[a].getText().toString();
        }
        int length= dishes.size();
        for(int a =0 ;a<length ;a++ ){
            String name= "name"+a;
            String amount= "amount"+a;
            String ingredients= "ingredients"+a;
            intent.putExtra(name, dishes.get(a).getName());
            intent.putExtra(ingredients, dishes.get(a).getIngredients());
            intent.putExtra(amount, dishes.get(a).getIngredientsAmount());
        }
        intent.putExtra("length",length);
        intent.putExtra("saves",saves);

        setResult(RESULT_OK,intent);
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1234){
            if(resultCode==RESULT_OK) {
                Intent intent = getIntent();
                int clickedlocation = data.getIntExtra("clickedlocation", 0);
                if (textViews[location].getText().toString().equals("Eating Out")) {
                    textViews[location].setText(""+ selecteddishes.get(clickedlocation - 1) + ",");


                } else{
                    textViews[location].append("" + selecteddishes.get(clickedlocation - 1) + ",");
                }
                selecteddishes.remove(clickedlocation-1);
                dishes.remove(clickedlocation-1);
            }

        }
        if(requestCode==5432){
            if(resultCode==RESULT_OK){
                weeklygoals= data.getDoubleArrayExtra("results");

            }


        }



    }
    public void goGoals(View v){
        Intent intent = new Intent( MealsActivity.this, GoalsActivity.class);
        weeklygoalsset=true;
        startActivityForResult(intent, 5432);


    }
    public void goSummary(View view){
        if(weeklygoalsset) {
            Intent intent = new Intent(MealsActivity.this, WeekSummaryActivity.class);
            String everything = "";
            for (int a = 1; a < 14; a += 2) {
                if (!textViews[a].getText().toString().equals("Eating Out")) {
                    everything += textViews[a].getText().toString();
                }
            }
            intent.putExtra("everything", everything);
            intent.putExtra("goals", weeklygoals);
            startActivity(intent);


        }else{
            Toast.makeText(MealsActivity.this, "Set your goals first!", Toast.LENGTH_SHORT).show();
        }


    }

}
