package com.wearable.whatsfordinner;

import android.app.Activity;
import android.app.ListActivity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.content.res.Resources;
import android.content.Intent;
import android.widget.ListView;
public class RecipesActivity extends ListActivity{
    private ArrayList<Dish> alldishes;
    private ArrayList<Dish> selecteddishes;
    private String[] names;
    private int longclickedlocation=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_recipes);
        alldishes= new ArrayList<>();
        selecteddishes= new ArrayList<>();
        Intent intent= getIntent();
        final int length =intent.getIntExtra("length",0);
        for(int a=0; a<length;a++){
            String name= intent.getStringExtra("name"+a);
            String[] ingredientslist= intent.getStringArrayExtra("ingredientslist"+a);
            double[] amountofingredients= intent.getDoubleArrayExtra("amountofingredients"+a);
            String directions= intent.getStringExtra("directions"+a);
            alldishes.add( new Dish(name,ingredientslist,amountofingredients));
            alldishes.get(a).setDirections(directions);
        }
        names= new String[length+1];
        for(int a =0; a<length; a++){
            names[a]= alldishes.get(a).getName();
        }
        names[length]= "<------------Done------------->";


        ListView listview = getListView();
        TextView title = new TextView(this);
        title.setTextSize(40);
        title.setGravity(0x11 );
        title.setText("Recipes List");
        title.setTextColor(Color.BLACK);
        title.setBackgroundColor(Color.WHITE);
        listview.setTextFilterEnabled(true);
        listview.setCacheColorHint(Color.BLUE);
        listview.addHeaderView(title);
        setListAdapter(new ArrayAdapter<String>(this,R.layout.content_recipes,names));
        final Intent returnintent = new Intent(this, MenuActivity.class);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){

                }
                else if(position==length+1){
                    int selectedlength= selecteddishes.size();
                    for(int a =0 ;a<selectedlength ;a++){
                        String samplename= "name"+a;
                        String sampleingredientsList= "ingredientslist" +a;
                        String sampleingredientamount= "amountofingredients"+a;
                        returnintent.putExtra(samplename, selecteddishes.get(a).getName());
                        returnintent.putExtra(sampleingredientsList,selecteddishes.get(a).getIngredients());
                        returnintent.putExtra(sampleingredientamount, selecteddishes.get(a).getIngredientsAmount());
                        returnintent.putExtra("selectedlength", selectedlength);
                        setResult(NewDishActivity.RESULT_OK,returnintent);
                    }
                    returnintent.putExtra("selectedlength",selectedlength);

                    int length= alldishes.size();
                    for(int b=10; b<length+10;b++ ){
                        String samplename1 = "name"+b;
                        String sampleingredientslist1= "ingredientslist"+b;
                        String sampleamountofingredients1="amountofingredients"+b;
                        String directions="directions"+b;;
                        returnintent.putExtra(directions,alldishes.get(b-10).getDirections());
                        returnintent.putExtra(samplename1,alldishes.get(b-10).getName() );
                        returnintent.putExtra(sampleingredientslist1, alldishes.get(b-10).getIngredients() );
                        returnintent.putExtra(sampleamountofingredients1, alldishes.get(b-10).getIngredientsAmount());

                    }
                    returnintent.putExtra("alldishlength",length);
                    finish();
                }else  {

                    selecteddishes.add(alldishes.get(position-1));
                    Toast.makeText(RecipesActivity.this,
                            alldishes.get(position-1).getName() + " has been added", Toast.LENGTH_SHORT).show();

                }

            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(position!=length+1 ) {
                    Intent intent = new Intent(RecipesActivity.this, DisplayActivity.class);
                    intent.putExtra("name", alldishes.get(position - 1).getName());
                    intent.putExtra("ingredientslist", alldishes.get(position - 1).getIngredients());
                    intent.putExtra("amountingredientslist", alldishes.get(position - 1).getIngredientsAmount());
                    intent.putExtra("directions", alldishes.get(position - 1).getDirections());
                    longclickedlocation = position - 1;
                    startActivityForResult(intent, 6666);

                }
                return false;

            }
        });




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==6666){
            if(resultCode== Activity.RESULT_OK){
                String name= data.getStringExtra("name");
                String[] ingredients= data.getStringArrayExtra("ingredientslist");
                double[] amountingredients=data.getDoubleArrayExtra("ingredientamounts");
                String directions= data.getStringExtra("directions");
                alldishes.remove(longclickedlocation);
                alldishes.add(new Dish(name,ingredients,amountingredients  )  );
                alldishes.get(alldishes.size()-1).setDirections(directions);
                ListView view =getListView();

                int length =alldishes.size();
                names= new String[length+1];
                for(int a =0; a<length; a++){
                    names[a]= alldishes.get(a).getName();
                }
                names[length]= "<------------Done------------->";
                setListAdapter(new ArrayAdapter<String>(this,R.layout.content_recipes,names));


            }

        }




    }



}
