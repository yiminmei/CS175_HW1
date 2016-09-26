package com.wearable.whatsfordinner;

import android.app.SharedElementCallback;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.content.Intent;
import java.util.ArrayList;
import android.app.Activity;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    private ImageView banner;
    private ArrayList<String> allIngredients= new ArrayList<>();
    private ArrayList<Dish> alldishes = new ArrayList<>();
    private ArrayList<Dish> selecteddishes= new ArrayList<>();
    private ArrayList<String> ingredientstobuy;
    private String[] messages= new String[14];
    private boolean clickedbefore= false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        banner=(ImageView)findViewById(R.id.imageView1);
        initiate();

        banner.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(MenuActivity.this, InformationActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }
    public void initiate(){

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
        String[] bb= {allIngredients.get(14),allIngredients.get(13),allIngredients.get(4),
                      allIngredients.get(11),"Choose","Choose","Choose","Choose","Choose",
                "Choose"};
        double[] bb1= {1.5,3.0,4,2,0,0,0,0,0,0};
        Dish sampledish1 = new Dish("Brocolli Beef",bb,bb1);
        String[] cc = {allIngredients.get(2),allIngredients.get(4),allIngredients.get(5),
                allIngredients.get(12),"Choose","Choose","Choose","Choose","Choose",
                "Choose"};
        double[] cc1= {2,3,3,3,0,0,0,0,0,0};
        Dish sampledish2 = new Dish("Grilled Meat",cc,cc1);
        double[]dd1={ 2,2,6,3,4,0,0,0,0,0};
        String[] uafteach={allIngredients.get(3),allIngredients.get(2),allIngredients.get(7),
                allIngredients.get(12),allIngredients.get(10),"Choose","Choose","Choose","Choose",
                "Choose"};

        double[]ee1={ 2,2,6,3,4,0,0,0,0,0};
        String[] uafteach1={allIngredients.get(6),allIngredients.get(9),allIngredients.get(15),
                allIngredients.get(4),allIngredients.get(6),"Choose","Choose","Choose","Choose",
                "Choose"};
        Dish sampledish4= new Dish("Thai duck",uafteach1 ,ee1);

        double[]ee11={ 2,2,6,3,4,0,0,0,0,0};
        String[] uafteach11={allIngredients.get(6),allIngredients.get(9),allIngredients.get(15),
                allIngredients.get(1),allIngredients.get(6),"Choose","Choose","Choose","Choose",
                "Choose"};
        Dish sampledish41= new Dish("Chuyun fish",uafteach11 ,ee11);
        alldishes.add(sampledish1);
        alldishes.add(sampledish2);
        alldishes.add(sampledish4);
        alldishes.add(sampledish41);

    }

    public void goTo(View view){
        int location = view.getId();

        if(location==R.id.button1){
            Intent intent = new Intent(MenuActivity.this, MealsActivity.class);

            int selectedlength= selecteddishes.size();
            for(int a=0; a<selectedlength;a++ ){
                String samplename = "name"+a;
                String sampleingredientslist= "ingredientslist"+a;
                String sampleamountofingredients="amountofingredients"+a;
                intent.putExtra(samplename,selecteddishes.get(a).getName() );
                intent.putExtra(sampleingredientslist, selecteddishes.get(a).getIngredients() );
                intent.putExtra(sampleamountofingredients, selecteddishes.get(a).getIngredientsAmount());

            }
            intent.putExtra("SAVE",messages);
            intent.putExtra("clickedbefore",clickedbefore);
            clickedbefore=true;
            intent.putExtra("selectedlength",selectedlength);
            selecteddishes.removeAll(selecteddishes);
            startActivityForResult(intent,4321);

        }else if (location==R.id.button3){
            Intent intent = new Intent(MenuActivity.this, GroceryActivity.class);
            int selectedlength= selecteddishes.size();
            for(int a=0; a<selectedlength;a++ ){
                String samplename = "name"+a;
                String sampleingredientslist= "ingredientslist"+a;
                String sampleamountofingredients="amountofingredients"+a;
                intent.putExtra(samplename,selecteddishes.get(a).getName() );
                intent.putExtra(sampleingredientslist, selecteddishes.get(a).getIngredients() );
                intent.putExtra(sampleamountofingredients, selecteddishes.get(a).getIngredientsAmount());

            }
            intent.putExtra("selectedlength",selectedlength);
            startActivityForResult(intent,3333);

        }else if( location== R.id.button2){
            Intent intent = new Intent(MenuActivity.this, RecipesActivity.class);
            int length= alldishes.size();
            for(int a=0; a<length;a++ ){
                String samplename = "name"+a;
                String sampleingredientslist= "ingredientslist"+a;
                String sampleamountofingredients="amountofingredients"+a;
                String directions="directions"+a;;
                intent.putExtra(directions,alldishes.get(a).getDirections());

                intent.putExtra(samplename,alldishes.get(a).getName() );
                intent.putExtra(sampleingredientslist, alldishes.get(a).getIngredients() );
                intent.putExtra(sampleamountofingredients, alldishes.get(a).getIngredientsAmount());


            }
            intent.putExtra("length",length);
            startActivityForResult(intent,2222);
        }else{
            Intent report= new Intent(this, NewDishActivity.class);
            report.putStringArrayListExtra("Ingredients",allIngredients);
            ArrayList<String> namestaken= new ArrayList<>();
            for(int a=0 ;a<alldishes.size() ;a++){
                namestaken.add(alldishes.get(a).getName());

            }
            report.putStringArrayListExtra("allnames",namestaken);


            startActivityForResult(report,1111);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==4321){
            if(resultCode==RESULT_OK){
                messages= data.getStringArrayExtra("saves");
                int length = data.getIntExtra("length",0);

                for(int a =0 ;a<length ;a++ ){
                    String name= data.getStringExtra("name"+a);
                    double[] amount=data.getDoubleArrayExtra( "amount"+a);
                    String[] ingredients= data.getStringArrayExtra("ingredients"+a);
                    selecteddishes.add(new Dish(name,ingredients,amount));
                }

            }
        }


        if(requestCode==3333){
            if(resultCode== Activity.RESULT_OK){
                int length = data.getIntExtra("length",0);
                ingredientstobuy= new ArrayList<>();
                String[] ingredients= data.getStringArrayExtra("ingredientstobuy");


            }
        }
        if (requestCode == 1111) {
            if(resultCode == Activity.RESULT_OK) {
                Dish newdish= new Dish();
                newdish.setName(data.getStringExtra("name"));
                newdish.setIngredients(data.getStringArrayExtra("ingredientslist"));
                newdish.setIngredientsAmount(data.getDoubleArrayExtra("ingredientamounts"));
                alldishes.add(newdish);
            }
        }
        if(requestCode==2222){
            if(resultCode==Activity.RESULT_OK){
                int length= data.getIntExtra("selectedlength",0);
                for(int a=0; a<length;a++){
                    String name= data.getStringExtra("name"+a);
                    String[] ingredientslist= data.getStringArrayExtra("ingredientslist"+a);
                    double[] amountofingredients= data.getDoubleArrayExtra("amountofingredients"+a);
                    String directions = data.getStringExtra("directions");
                    selecteddishes.add( new Dish(name,ingredientslist,amountofingredients ));
                    selecteddishes.get(a).setDirections(directions);


                }
                alldishes.clear();
                int length1= data.getIntExtra("alldishlength",0);
                for(int b=10; b<length1+10;b++ ){
                    String name= data.getStringExtra("name"+b);
                    String[] ingredientslist= data.getStringArrayExtra("ingredientslist"+b);
                    double[] amountofingredients= data.getDoubleArrayExtra("amountofingredients"+b);
                    String directions= data.getStringExtra("directions"+b);

                    alldishes.add( new Dish(name,ingredientslist,amountofingredients));
                    alldishes.get(b-10).setDirections(directions);
                }
            }
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
